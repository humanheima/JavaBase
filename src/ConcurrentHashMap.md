```java
/**
 * sizeCtl是一个多线程共享变量，用来控制表的初始化和大小调整。值为负数的时候，说明表正在初始化，或者调整大小。
 * 如果值是-1表示在初始化，值不是-1，比如说是-4，代表的意思是 -(1+正在调整表大小的线程数)。
 * 另外当表是null的时候，sizeCtl的值表示在创建表的时候表的大小是多少，sizeCtl默认是0。
 * 在表初始化以后，sizeCtl表示当元素的数量到达sizeCtl则调整表的大小。
  *
 */
private transient volatile int sizeCtl;
```

```java
 static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        //是线程可见性的 
        volatile V val;
        //Node的next是线程可见性的
        volatile Node<K,V> next;
        
        //...
}
```
```java
//table数组是被volatile关键字修饰的，这就代表我们不需要担心table数组的线程可见性问题
//当一个共享变量被volatile修饰时，它会保证修改的值会立即被更新到主存，当有其他线程需要读取时，它会去内存中读取新值。
transient volatile Node<K,V>[] table;
```
```java
/**
 * Initializes table, using the size recorded in sizeCtl.
 */
private final Node<K,V>[] initTable() {
       Node<K,V>[] tab; int sc;
       while ((tab = table) == null || tab.length == 0) {
           if ((sc = sizeCtl) < 0)
               Thread.yield(); // 让出CPU时间
           else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
               try {
                   f ((tab = table) == null || tab.length == 0) {
                       //决定表的初始化大小
                       int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
                       //创建长度为n的数组
                       Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
                       table = tab = nt;
                       //sc为表长度的0.75,设置扩容的阈值
                       sc = n - (n >>> 2);
                   }
               } finally {
                   //将sc赋值给sizeCtl
                   sizeCtl = sc;
               }
               break;
           }
       }
       return tab;
}
```
```
final V putVal(K key, V value, boolean onlyIfAbsent) {
   if (key == null || value == null) throw new NullPointerException();
      int hash = spread(key.hashCode());
      int binCount = 0;
      //注意这里的for循环
      for (Node<K,V>[] tab = table;;) {
          Node<K,V> f; int n, i, fh;
          if (tab == null || (n = tab.length) == 0)
              //初始化表
              tab = initTable();
          //指定位置上没有元素
          else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
              //创建一个元素加入并返回
              if (casTabAt(tab, i, null,new Node<K,V>(hash, key, value, null)))
                    break;                   
            }
            else if ((fh = f.hash) == MOVED)
                tab = helpTransfer(tab, f);
            else {
                V oldVal = null;
                synchronized (f) {//在index=f的位置上加锁，其他位置上没有锁住，所以此时其他线程可以安全的获得其他的table位置来进行操作
                    if (tabAt(tab, i) == f) {
                        if (fh >= 0) {//在链表中
                            binCount = 1;
                            for (Node<K,V> e = f;; ++binCount) {
                                K ek;
                                if (e.hash == hash &&
                                    ((ek = e.key) == key ||
                                     (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    //替换oldVal
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                Node<K,V> pred = e;
                                //加入到链表末尾
                                if ((e = e.next) == null) {
                                    pred.next = new Node<K,V>(hash, key,
                                                              value, null);
                                    break;
                                }
                            }
                        }
                        else if (f instanceof TreeBin) {//在树中
                            Node<K,V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                           value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        treeifyBin(tab, i);
                    if (oldVal != null)//更新操作返回老的值，
                        return oldVal;
                    break;
                }
            }
        }
        //这个方法应该是增加表中的item的数量
        addCount(1L, binCount);
        return null;
    }

```

```java
public V get(Object key) {
        Node<K,V>[] tab; Node<K,V> e, p; int n, eh; K ek;
        int h = spread(key.hashCode());
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (e = tabAt(tab, (n - 1) & h)) != null) {//如果链表的头，或者红黑树的根结点就是我们要找的元素
            if ((eh = e.hash) == h) {
                if ((ek = e.key) == key || (ek != null && key.equals(ek)))
                    return e.val;
            }
            else if (eh < 0)//在红黑树中，使用find方法来查找
                return (p = e.find(h, key)) != null ? p.val : null;
            while ((e = e.next) != null) {//在链表中
                if (e.hash == h &&
                    ((ek = e.key) == key || (ek != null && key.equals(ek))))
                    return e.val;
            }
        }
        return null;
    }
```

```java
static final int TREEBIN   = -2; // 红黑树根结点的hash值
```
###ArrayDeque

看一下ArrayDeque中几个成员变量
```
//储存元素，长度总是2的次幂，数组不允许饱和，在使用addX方法添加元素以后，如果数组饱和了，那么就会立即扩容到原来长度的两倍
transient Object[] elements;
//双端队列的头部元素的下标
transient int head;
//标志：如果一个新元素要被添加到双端队列尾部(通过 addLast(E), add(E), or push(E))，那么这个新元素在双端队列的下标就是tail
transient int tail;
//elements最小初始容量，如果构造函数指定初始容量下限小于8，那么就选择8作为初始容量
private static final int MIN_INITIAL_CAPACITY = 8;
```

构造函数
```java
public ArrayDeque() {
    elements = new Object[16];
}

//指定初始容量下限，最终初始容量是大于等于numElements并且是2的次幂的一个数，比如指定numElements=9，那么初始容量最终会是16
public ArrayDeque(int numElements) {
    allocateElements(numElements);
}

//使用一个集合初始化队列
public ArrayDeque(Collection<? extends E> c) {
    allocateElements(c.size());
    addAll(c);
}
```

我们指定队列的初始容量为8，然后来看看常用的方法

* addFirst(E e) 把元素插入到队列的前面。后插入的元素在前面，使用 pollFirst() 方法会先返回后插入的数据。

```java

public void addFirst(E e) {
    if (e == null)
        throw new NullPointerException();
    elements[head = (head - 1) & (elements.length - 1)] = e;
    if (head == tail)
        doubleCapacity();
}
```
可以看到元素是不允许为null的，初始的时候`head`和`tail`都是0，elements.length=8， 我们试着添加两个元素

```java
ArrayDeque<Integer> deque = new ArrayDeque<>(8);
for (int i = 0; i < 8; i++) {
    deque.addFirst(i);
}
```

添加第一个元素，表达式`head = (head - 1) & (elements.length - 1)`就等价于`(-1&7)=7`,所以`elements[7]=0`。

中间插一句,[原码, 反码, 补码 详解](http://www.cnblogs.com/zhangziqiu/archive/2011/03/30/ComputerCode.html)

插入以后 head=7，不等于 tail (tail = 0)，不需要扩容。


添加第二个元素 ，表达式`head = (head - 1) & (elements.length - 1)`就等价于`(6&7)=6`,所以`elements[6]=1`。当我们添加完第8个元素时，
`elements=[7, 6, 5, 4, 3, 2, 1, 0]`，这时，head=0,head=tail,这时候需要扩容。

`doubleCapacity()`，这个方法的作用就是把`elements`长度增加两倍，然后使用`System.arraycopy()`方法重新放置元素，我们在后面会进行详细分析。

* addLast(E e) 把元素插入到队列的尾部

```java
public void addLast(E e) {
    if (e == null)
        throw new NullPointerException();
    elements[tail] = e;
    if ( (tail = (tail + 1) & (elements.length - 1)) == head)
        doubleCapacity();
}
```

```java
ArrayDeque<Integer> deque = new ArrayDeque<>(8);
for (int i =1; i < 8; i++) {
    deque.addLast(i);
}
```

这个方法和`addFirst(E e)`正好是相反的，添加完8个元素以后，`elements=[0, 1, 2, 3, 4, 5, 6, 7]`

* offerFirst(E e) 内部调用addFirst(e)方法实现，不再赘述

```java
public boolean offerFirst(E e) {
    addFirst(e);
    return true;
}
```

* offerLast(E e) 内部调用addLast(e)方法实现，不再赘述

```java
public boolean offerLast(E e) {
    addLast(e);
    return true;
}
```

* pollFirst() 查询第一个元素

```java
public E pollFirst() {
    int h = head;
    @SuppressWarnings("unchecked")
    E result = (E) elements[h];
    // Element is null if deque empty
   if (result == null)
     return null;
   elements[h] = null;     // Must null out slot
   head = (h + 1) & (elements.length - 1);
   return result;
}
```
当队列为空的时候，返回null。不为空时，删除并返回head下标上的元素，然后把head向后移动一个位置。

* pollLast() 查询最后一个元素

```java
public E pollLast() {
    //tail是代表下一个要插入的位置，所以要-1
    int t = (tail - 1) & (elements.length - 1);
    @SuppressWarnings("unchecked")
    E result = (E) elements[t];
    if (result == null)
        return null;
    elements[t] = null;
    tail = t;
    return result;
}
```

当队列为空的时候，返回null。不为空时，删除并返回最后一个元素，然后把tail向前移动一个位置。

* removeFirst() 内部调用pollFirst(),如果元素为null，则抛出异常。

```java
public E removeFirst() {
    E x = pollFirst();
    if (x == null)
        throw new NoSuchElementException();
    return x;
}
```

* removeLast() 内部调用pollLast(),如果元素为null，则抛出异常

```java
public E removeLast() {
    E x = pollLast();
    if (x == null)
        throw new NoSuchElementException();
    return x;
}
```

下面几个方法比较简单一起说了
```java
//返回队列第一个元素，但是不删除，如果为null，怎抛出异常
public E getFirst() {
    @SuppressWarnings("unchecked")
    E result = (E) elements[head];
    if (result == null)
        throw new NoSuchElementException();
    return result;
}

//返回队列最后一个元素，但是不删除，如果为null，怎抛出异常
public E getLast() {
    @SuppressWarnings("unchecked")
    E result = (E) elements[(tail - 1) & (elements.length - 1)];
    if (result == null)
        throw new NoSuchElementException();
    return result;
}
    
//返回队列第一个元素，但是不删除，返回值可以为null
@SuppressWarnings("unchecked")
public E peekFirst() {
    // elements[head] is null if deque empty
    return (E) elements[head];
}

//返回队列最后一个元素，但是不删除，返回值可以为null
@SuppressWarnings("unchecked")
public E peekLast() {
    return (E) elements[(tail - 1) & (elements.length - 1)];
}
```

* removeFirstOccurrence(Object o) 删除队列中第一个和指定元素相等的元素，从head到tail遍历。如果指定元素为null，或者删除失败返回false，删除成功返回true。

```java
public boolean removeFirstOccurrence(Object o) {
    if (o == null)
        return false;
    int mask = elements.length - 1;
    int i = head;
    Object x;
    while ( (x = elements[i]) != null) {
        if (o.equals(x)) {
            //调用delete删除,delete方法通过移动 elements 中的元素，来覆盖要删除位置上的元素，并对移动元素做了相关优化，并相应的改变head和tail
            delete(i);
            return true;
        }
        i = (i + 1) & mask;
    }
    return false;
}
```

* removeLastOccurrence(Object o) 删除队列中最后一个和指定元素相等的元素

```java
public boolean removeLastOccurrence(Object o) {
    if (o == null)
        return false;
    int mask = elements.length - 1;
    int i = (tail - 1) & mask;
    Object x;
    while ( (x = elements[i]) != null) {
        if (o.equals(x)) {
            delete(i);
            return true;
        }
        i = (i - 1) & mask;
    }
    return false;
}
```

*分析一下 doubleCapacity() 增加elements容量为原来的两倍，重新放置元素

```java
 private void doubleCapacity() {
    assert head == tail;
    int p = head;
    int n = elements.length;
    int r = n - p; // number of elements to the right of p
    int newCapacity = n << 1;
    if (newCapacity < 0)
        throw new IllegalStateException("Sorry, deque too big");
    Object[] a = new Object[newCapacity];
    System.arraycopy(elements, p, a, 0, r);
    System.arraycopy(elements, 0, a, r, p);
    elements = a;
    head = 0;
    tail = n;
}
```

这个方法中为什么`System.arraycopy()`方法为什么要调用两次呢，作用是为了保证拷贝后的顺序和插入是一致的。保证我们取数据的时候是符合先进先出的规则的。

下面来分析一下：


首先这个方法调用的地方有两个，addFirst() 和 addLast()

第一种情况：只使用addFirst()方法添加元素，当我们添加完了第8个元素，此时`elements=[7,6,5,4,3,2,1,0]`,需要进行扩容了

此时

`head == tail=0;` 
`p=0;`
`n=8;`
`r=8;`

首先把数组容量增加两倍，然后

`System.arraycopy(elements, p, a, 0, r);`会把elements中的元素从下标`p=0`拷贝到a中，一共拷贝`r=8`个元素，a从下标为0开始放置元素

//第二次拷贝因为`p=0`，拷贝0个元素，所以并不会改变a。

`System.arraycopy(elements, 0, a, r, p);`

最后让elements指向a。拷贝完成后 `elements=[7,6,5,4,3,2,1,0,null,null,null,null,null,null,null,null]`,`head=0,tail=8`;

再用addFirst()方法添加个元素8，结果`elements=[7,6,5,4,3,2,1,0,null,null,null,null,null,null,null,8]`

再用addFirst()方法添加个元素9，结果`elements=[7,6,5,4,3,2,1,0,null,null,null,null,null,null,9,8]`

当我们继续添加元素`elements=[7,6,5,4,3,2,1,0,15,14,13,12,11,10,9,8]`，此时数组满了又需要扩容了
此时
`head == tail=8;` 
`p=8;`
`n=16;`
`r=8;`
首先把数组容量增加两倍，然后
`System.arraycopy(elements, p, a, 0, r);`会把elements中的元素从下标`p=8`拷贝到a中，一共拷贝`r=8`个元素，a从下标为0开始放置元素
拷贝完成后`a=[15,14,13,12,11,10,9,8,...]`
//第二次拷贝`p=8`，会把elements中的元素从下标`0`拷贝到a中，一共拷贝`p=8`个元素，a从下标`r=8`开始放置元素
`System.arraycopy(elements, 0, a, r, p);`
最后让elements指向a。拷贝完成后 `a=[15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,...]`,`head=0,tail=16`;

**插一句,到这里隐隐感觉到写这个代码的人真是厉害，佩服！**

第二种情况：只使用addLast()方法添加元素，当我们添加完了第8个元素，此时`elements=[0,1,2,3,4,5,6,7]`,需要进行扩容了
此时
`head == tail=0;` 
`p=0;`
`n=8;`
`r=8;`
首先把数组容量增加两倍，然后
`System.arraycopy(elements, p, a, 0, r);`会把elements中的元素从下标`p=0`拷贝到a中，一共拷贝`r=8`个元素，a从下标为0开始放置元素
//第二次拷贝因为`p=0`，所以并不会改变a。
`System.arraycopy(elements, 0, a, r, p);`
最后让elements指向a。拷贝完成后 `elements=[0,1,2,3,4,5,6,7,null,null,null,null,null,null,null,null]`,`head=0,tail=8`;
再用addLast()方法添加个元素8，结果`elements=[0,1,2,3,4,5,6,7,8,null,null,null,null,null,null,null]`
再用addLast()方法添加个元素9，结果`elements=[0,1,2,3,4,5,6,7,8,9,null,null,null,null,null,null]`


当我们继续添加元素`elements=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]`，此时数组满了又需要扩容了
此时
`head == tail=0;` 
`p=0;`
`n=16;`
`r=16;`
首先把数组容量增加两倍，然后
`System.arraycopy(elements, p, a, 0, r);`会把elements中的元素从下标`p=0`拷贝到a中，一共拷贝`r=16`个元素，a从下标为0开始放置元素
//第二次拷贝因为`p=0`，所以并不会改变a。
`System.arraycopy(elements, 0, a, r, p);`
最后让elements指向a。拷贝完成后 `elements=[0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,...]`,`head=0,tail=16`;

**插一句,到这里深深感觉到写这个代码的人真是厉害，佩服！**

再分析一种情况
使用addFirst()方法添加元素，当我们添加完了第7个元素，此时`elements=[null,6,5,4,3,2,1,0]`,然后使用addLast()方法添加一个元素7，
此时`elements=[7,6,5,4,3,2,1,0]`然后数组满了需要扩容
此时
`head == tail=1;` 
`p=1;`
`n=8;`
`r=7;`
数组容量增加两倍
`System.arraycopy(elements, p, a, 0, r);`会把elements中的元素从下标`p=1`拷贝到a中，一共拷贝`r=7`个元素，a从下标为0开始放置元素，拷贝
完成后`a=[6,5,4,3,2,1,0,...]`

第二次拷贝
 `System.arraycopy(elements, 0, a, r, p);`会把elements中的元素从下标0拷贝到a中，一共拷贝`p=1`个元素，a从下标为`r=7`开始放置元素，拷贝完成后
 `a=[6,5,4,3,2,1,0,7，...]`此时`elements=[6,5,4,3,2,1,0,7，...]`,`head = 0``tail=8`
如果此时继续用然后使用addLast()添加8个元素`element=[6,5,4,3,2,1,0,7,8,9,10,11,12,13,14,15]`，此时数组饱和，扩容

 `head == tail=0;` 
 `p=0;`
 `n=16;`
 `r=16;`

 数组容量增加两倍
 `System.arraycopy(elements, p, a, 0, r);`会把elements中的元素从下标`p=0`拷贝到a中，一共拷贝`r=16`个元素，a从下标为0开始放置元素，拷贝
 完成后`a=[6,5,4,3,2,1,0,7,8,9,10,11,12,13,14,15,...]`
 第二次拷贝p=0,a不会改变。
 最后`elements=[6,5,4,3,2,1,0,7,8,9,10,11,12,13,14,15,...]`,`head = 0``tail=16`


没细看，在扩容之后，保证我们取数据的时候是符合先进先出的规则的。











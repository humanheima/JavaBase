LinkedHashMap 使用双向链表保存数据。该双向链表定义了LinkedHashMap的迭代顺序，通常就是key插入到map中
的顺序（insertion-order），一个key重新插入到map中不会影响插入顺序（insertion-order）。

LinkedHashMap可以用来拷贝一个map对象，并保持map对象中数据的原始顺序，然后在晚些时候返回。

LinkedHashMap有一个特殊的构造函数`LinkedHashMap(int,float,boolean)`来创建一个LinkedHashMap。
这个LinkedHashMap对象的迭代顺序由最后访问LinkedHashMap中的item来决定。顺序是从最近最少访问到最近最多访问。这种类型的map
适合用来构建LRU缓存。

可以覆盖 {@link #removeEldestEntry(Map.Entry)}方法来实现当新的键值对加入到map中的时候自动移除老的键值对。

LinkedHashMap支持所有的Map操作，元素可以为null。

LinkedHashMap不是线程安全的。

添加或删除一个多个元素或者其他导致结构性变化的修改，对于`access-ordered`的LinkedHashMap来说，影响迭代顺序。
对于insertion-ordered的LinkedHashMap来说，只改变已存在于map中的元素不是结构性修改。对于`access-ordered`的LinkedHashMap来说
只有get方法是结构性修改。

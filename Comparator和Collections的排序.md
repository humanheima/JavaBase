Comparator 接口

```java


public interface Comparator<T> {
    
}
```
接口定义：

一个比较函数，对一些对象集合施加了一个完全排序。Comparators 可以传递给一个排序方法，(例如 `Collections.sort` 或者 `Arrays.sort`)来精确的控制排序顺序。

Comparators 也可以被用来控制置特定数据结构(例如 SortedSet 或者 SortedMap )的顺序，或者为没有｛@link Comparable自然排序｝的对象集合提供排序。

>Comparator 接口定义了一种比较函数，可以对一些对象集合实现完整的排序。可以将比较器传递给排序方法（例如 Collections.sort 或 Arrays.sort），从而精确控制排序顺序。
比较器也可以用于控制某些数据结构的顺序（例如 SortedSet 或 SortedMap），或为没有自然排序的对象集合提供排序。 在元素集合 S 上，比较器 c 对元素实施的排序被称为“与等于关系一致”，当且仅当对于 S 中的每个 e1 和 e2，c.compare(e1, e2)==0 与 e1.equals(e2) 具有相同的布尔值。
在使用能够对非相等元素实施排序的比较器对一个已排序集合（或已排序映射）排序时，要小心。 如果 c 对 S 实施的排序与等于关系不一致，则已排序集合（或已排序映射）将表现出异常行为，特别是将违反 Set（或 Map）的一般合同，该协定基于 equals 的定义。
在具有手动检索集合数据的情况下，如果在使用一个已声明比较器 c 的已排序集合（或已排序映射）中添加两个元素 a 和 b，使得（a.equals(b) && c.compare(a, b) != 0），则第二个添加操作将返回 true（并且集合的大小将增加）；这是因为从树集的角度来看，a 和 b 是不同的，即使这与 Set.add 方法的规范相反。请注意，通常为比较器实现 java.io.Serializable 接口是一种好习惯，因为它们可能被用作序列化数据结构（如 TreeSet、TreeMap）中的排序方法。为使数据结构成功序列化，比较器（如果有提供）必须实现 Serializable。对于理解数学的人来说，由给定比较器 c 所实施的顺序定义的关系是： {(x, y) such that c.compare(x, y) <= 0}；对于此总序关系的商是： {(x, y) such that c.compare(x, y) == 0}。从 compare 的合同立即可得商是 S 上的等价关系，实施的排序是 S 上的总序。当我们说 c 对 S 实施的排序是“与等于关系一致”的时候，我们指排序的

上面这段话看不懂的话，可以记住：使用 `Comparator` 要注意，如果元素重写了 equals 方法。那么得保证集合中的每个 e1 和 e2，c.compare(e1, e2)==0 与 e1.equals(e2) 具有相同的布尔值。


```java
/**
 * 根据指定比较器产生的排序方式对指定列表进行排序。列表中的所有元素必须是可以使用指定的比较器进行相互比较的（这意味着对于列表中的任何元素 e1 和 e2 ，c.compare（e1，e2）不得抛出 ClassCastException ）。
 * 此排序操作保证是稳定的：相等的元素不会由于排序而被重新排序。指定的列表必须是可修改的，但不需要调整大小。 
 * 此实现使用指定列表和比较器的{@link List＃sort（Comparator）}方法。
 * @param <T>列表中对象的类 
 * @param列表要排序的列表。
 * @param c比较器确定列表的顺序。值为 null 表示应使用元素的自然排序。 
 * @throws ClassCastException 如果列表包含不能使用指定比较器进行相互比较的元素。
 * @throws UnsupportedOperationException如果指定的列表的列表迭代器不支持 set 操作。 
 * @throws IllegalArgumentException（可选）如果发现比较器违反{@link Comparator} 约定。 @see List＃sort（比较器）}
 */
/**
     * Sorts the specified list according to the order induced by the
     * specified comparator.  All elements in the list must be <i>mutually
     * comparable</i> using the specified comparator (that is,
     * {@code c.compare(e1, e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the list).
     *
     * <p>This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.
     *
     * <p>The specified list must be modifiable, but need not be resizable.
     *
     * @implNote
     * This implementation defers to the {@link List#sort(Comparator)}
     * method using the specified list and comparator.
     *
     * @param  <T> the class of the objects in the list
     * @param  list the list to be sorted.
     * @param  c the comparator to determine the order of the list.  A
     *        {@code null} value indicates that the elements' <i>natural
     *        ordering</i> should be used.
     * @throws ClassCastException if the list contains elements that are not
     *         <i>mutually comparable</i> using the specified comparator.
     * @throws UnsupportedOperationException if the specified list's
     *         list-iterator does not support the {@code set} operation.
     * @throws IllegalArgumentException (optional) if the comparator is
     *         found to violate the {@link Comparator} contract
     * @see List#sort(Comparator)
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
    }
```





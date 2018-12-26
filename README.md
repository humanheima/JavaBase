## Java 基础知识

Object类的方法
![Object类的方法](ObjectMethod.png)

枚举两个枚举成员应该使用 ==

* 使用集合转数组的方法，必须使用集合的 `<T> T[] toArray(T[] a)` ，传入的是类型完全一样的数组，大小就是 list.size();
直接使用 `Object[] toArray()`无参方法存在问题，此方法返回值只能是 Object[] 类，若强转其它类型数组将出现 ClassCastException 错误。

* 避免出现重复的代码 （Don ’ t Repeat Yourself） ，即 DRY 原则。
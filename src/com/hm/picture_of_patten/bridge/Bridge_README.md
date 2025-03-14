
桥接模式（Bridge Pattern）是面向对象设计模式中的一种结构型模式。它旨在将一个类的抽象部分与它的实现部分分离，使它们都可以独立变化。
桥接模式的主要目的是解耦接口和实现，使得两者可以独立地发展和演化。


Bridge 模式的作用是在 "类的功能层次结构" 和 "类的实现层次结构" 之间搭建桥梁。

类的功能层次结构：父类具有基本功能，在子类中添加新的方法来增加新的功能。

类的实现层次结构: 父类通过声明抽象方法来定义接口。子类通过实现具体方法来实现接口。


桥接模式通常用于以下场景：

* 当你不希望在抽象和它的实现之间有一个固定的绑定关系时。
* 当你想要避免永久绑定带来的负面影响，例如在运行时刻选择或切换实现的时候。
* 当你有一个类层次结构，而这个层次结构中的某些变化需要在多个子类中进行重复编码时。
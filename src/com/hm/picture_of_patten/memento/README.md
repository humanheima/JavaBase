### Memento 模式

Memento 模式：保存对象的状态。

Originator：生成者，生成状态的对象。在该例中，由Gamer类扮演此角色。

Memento：纪念品，会将 Originator 的内部信息整合在一起。在Memento校色中虽然保存了 Originator 的信息，但是不会向外部公开这些信息。
Memento 角色有以下两种接口
* wide interface  宽接口：指用于获取恢复对象状态信息的方法的集合。由于宽接口会暴露所有 Memento 的内部信息，因此能够使用宽接口的只有Originator。
* narrow interface 窄接口：Memento为外部的 Caretaker 角色提供了窄接口。可以通过窄接口获取的 Memento 的内部信息非常有限，因此可以有效地防止信息泄露。

通过以上两种接口，可以有效地防止对象的封装性被破坏。

Caretaker：负责人，在此例中Main类扮演此角色。

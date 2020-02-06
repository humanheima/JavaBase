距离上次写东西，已经是快两个月了，时光匆匆如流水啊。感觉做Android开发要学的东西真的是太多了，首先Java得继续深入，Android也得继续深入，各种新框架都得学习，感觉时间真是紧张，而且自己还想玩玩游戏，锻炼锻炼身体。2018还有四个多月，继续努力。

代理模式定义：为其他对象提供一种代理以控制对这个对象的访问。

主要解决：在直接访问对象时带来的问题，比如说：要访问的对象在远程的机器上。在面向对象系统中，有些对象由于某些原因（比如对象创建开销很大，或者某些操作需要安全控制，或者需要进程外的访问），直接访问会给使用者或者系统结构带来很多麻烦，我们可以在访问此对象时加上一个对此对象的访问层。

使用场景：按职责来划分，通常有以下使用场景： 
1、远程代理。 
2、虚拟代理。 
3、Copy-on-Write 代理。 
4、保护（Protect or Access）代理。 
5、Cache代理。 
6、防火墙（Firewall）代理。 
7、同步化（Synchronization）代理。 
8、智能引用（Smart Reference）代理。

注意事项： 
1、和适配器模式的区别：适配器模式主要改变所考虑对象的接口，而代理模式不能改变所代理类的接口。 
2、和装饰器模式的区别：装饰器模式为了增强功能，而代理模式是为了加以控制。


**举一个保护（Protect or Access）代理的例子**

##静态代理
举个例子，买火车票
过年回家买火车票，在12306上买不到票，怎么办。用携程啊，携程在手，说走就走，搞个30块钱的加速包，（这30块钱是不是12306和携程一人15啊）估计就十拿九稳了。你自己买不到票，就让携程帮你买，那么携程就是你的代理了。下面用代码说明
1.首先定义一个买票的接口
```
public interface BuyTicket {

    //买票的方法
    void buy();

}
```
真正想买票的人，就是被代理者
```
public class MyselfBuyTicket implements BuyTicket {

    @Override
    public void buy() {
        providerInfo();
        System.out.println("老板，我想买票");
    }
    
    private void providerInfo() {
        System.out.println("提供所需购票信息");
    }
}

```
代理者，持有被代理者的引用
```
/**
 * 代理类：代理类同样也要实现BuyTicket接口，并且要持有被代理者
 */
public class CtripBuyTicket implements BuyTicket {

    /**
     * 被代理者，小赤佬
     */
    private BuyTicket xiaochilao;

    public CtripBuyTicket(BuyTicket buyTicket) {
        this.xiaochilao = buyTicket;
    }

    @Override
    public void buy() {
        System.out.println("30块钱到手，哈哈");
        xiaochilao.buy();
    }
}
```
搞一个测试类测试一下
```
/**
 * 测试静态代理
 */
public class Client {

    public static void main(String[] args) {
        BuyTicket xiaochilao = new MyselfBuyTicket();
        BuyTicket purchasing = new CtripBuyTicket(xiaochilao);
        purchasing.buy();
    }
}
```
输出
```
30块钱到手，哈哈
提供所需购票信息
老板，我想买票
```
妥妥的，买到票了，哈哈。

静态代理：上述方式称为静态代理模式，为什么称为静态代理？因为代理对象需要与目标对象实现一样的接口。代理类如果太多会出现什么样的问题？（比如，我还可以用飞猪等等其他代购软件）一旦接口增加方法，目标对象与代理对象都要维护，这样的话代码改起来就相当烦琐和冗余。为了解决这个问题，Java给我们提供了一种解决方式，这种解决方式大家一般称为动态代理。

###动态代理

动态代理则是在代码运行时通过反射来动态的生成代理类的对象，并确定到底来代理谁。也就是我们在编码阶段不需要知道代理谁，代理谁我们将会在代码运行时决定。Java提供了Proxy这个类和InvocationHandler这个接口来实现动态代理。

每个代理实例都有一个关联的调用处理者，当代理实例调用一个方法的时候，这个方法调用会被转发到调用处理者的`invoke `方法中
```
public class CtripInvocationHandler implements InvocationHandler {

    /**
     * 在动态代理类中我们声明一个Object的引用，该引用指向被代理类
     */
    private Object obj;

    public CtripInvocationHandler(Object obj) {
        this.obj = obj;
    }

    /**
     * @param proxy  代理对象
     * @param method 代理对象调用的方法
     * @param args   方法调用参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("30块钱到手，哈哈");
        //obj是被代理对象，通过反射调用被代理对象对应的方法
        return method.invoke(obj, args);
    }
}
```
测试动态代理
```
public class DynamicClient {

    public static void main(String[] args) {

        //被代理对象
        BuyTicket xiaochilao = new MyselfBuyTicket();

        //创建调用处理者
        CtripInvocationHandler invocationHandler = new CtripInvocationHandler(xiaochilao);

        //创建动态代理对象
        BuyTicket purchasing = (BuyTicket) Proxy.newProxyInstance(
                xiaochilao.getClass().getClassLoader(),
                xiaochilao.getClass().getInterfaces(),
                invocationHandler);

        /**
         * 注意：这里调用的是代理对象的方法，该方法会转发到CtripInvocationHandler的invoke方法中，
         * 最终会调用被代理对象对应的方法。
         */
        purchasing.buy();

        //注释1处，用来生成动态代理对象的class文件，后面会讲，ProxyUtils在最后给出
        //ProxyUtils.generateClassFile(xiaochilao.getClass(), "XiaochilaoProxy");
    }
}

```
输出结果
```
30块钱到手，哈哈
提供所需购票信息
老板，我想买票
```
在上面这个例子中，动态体现在哪里呢？体现在，我们现在并不需要一个`CtripBuyTicket`这样一个类了。现在我们使用`Proxy.newProxyInstance()`方法在运行时，动态创建一个`MyselfBuyTicket`的代理者。

在这个例子中，动态代理的调用过程如下图所示
![动态代理对象调用流程.jpg](https://upload-images.jianshu.io/upload_images/3611193-a9282492cc67451d.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 动态代理对象的创建过程，还有方法调用如何转发的？

我们先看一下Proxy类的newProxyInstance方法简化版
```java
/**
 * 返回指定interfaces的代理对象，该代理对象将方法调用转发到指定的invocation handler中
 * 
 *
 * @param   loader 类加载器，用来定义代理类
 * @param   interfaces 代理类要实现的接口
 *          
 * @param   h InvocationHandler 对象，代理对象会将方法调用转发到InvocationHandler对象的invoke方法中
 * @return  返回一个由class loader定义的代理类的对象，该对象实现了指定的interfaces。
 *           生成的代理类对象持有我们传入的invocation handler对象。
 * 
 */
public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
    throws IllegalArgumentException {

    final Class<?>[] intfs = interfaces.clone();
        
    //...

    /*
     * Look up or generate the designated proxy class.
     */
    Class<?> cl = getProxyClass0(loader, intfs);

    /*
     * Invoke its constructor with the designated invocation handler.
     */
    try {
        //...

        final Constructor<?> cons = cl.getConstructor(constructorParams);
        final InvocationHandler ih = h;
        //...
        //返回生成的代理类对象
        return cons.newInstance(new Object[]{h});
    } 
    //...
}

```

Proxy类的newProxyInstance方法就是根据传入的ClassLoader、interfaces和InvocationHandler生成代理对象并返回。

```java
//用来生成动态代理对象的Class文件，名称叫做XiaochilaoProxy.class
ProxyUtils.generateClassFile(xiaochilao.getClass(), "XiaochilaoProxy");
```
我们将在main方法的注释1处的代码打开，会生成动态代理对象的class文件，生成的文件所在的路径和DynamicClient类生成的class文件所在的路径一致，生成的文件名是`XiaochilaoProxy.class`如下所示

![生成的class文件路径.jpg](https://upload-images.jianshu.io/upload_images/3611193-a43c5782b6123787.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


XiaochilaoProxy.class

```
``java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.hm.pattern.proxy.BuyTicket;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class XiaochilaoProxy extends Proxy implements BuyTicket {
    
    private static Method m3;
    
    static {
            try {
                //BuyTicket接口中定义的buy方法
                m3 = Class.forName("com.hm.pattern.proxy.BuyTicket").getMethod("buy");
            } catch (NoSuchMethodException var2) {
                throw new NoSuchMethodError(var2.getMessage());
            } catch (ClassNotFoundException var3) {
                throw new NoClassDefFoundError(var3.getMessage());
            }
        }

    
    /**
     * 
     * @param var1 传入的invocation handler对象
     */
    public XiaochilaoProxy(InvocationHandler var1) throws  {
        super(var1);
    }

    public final void buy() throws  {
        try {
            //注释1处，调用传入的invocation handler的invoke方法
            super.h.invoke(this, m3, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }
    
    //...
 
}

```

注释1处，中当我们调用代理对象的buy方法的时候，会调用传入的InvocationHandler对象的invoke方法，然后我们在InvocationHandler对象的invoke方法中再调用被代理对象的buy方法。

到这里，动态代理对象的创建过程，还有方法调用如何转发的就明白了。

ProxyUtils类
```
public class ProxyUtils {
    /**
     * 将根据类信息动态生成的二进制字节码保存到硬盘中，默认的是clazz目录下
     * params: clazz 需要生成动态代理类的类
     * proxyName: 为动态生成的代理类的名称
     */
    public static void generateClassFile(Class clazz, String proxyName) {
        // 根据类信息和提供的代理类名称，生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);
        FileOutputStream out = null;
        try {
            //保留到硬盘中
            out = new FileOutputStream(paths + proxyName + ".class");
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

```

参考链接:
* [设计模式之死磕代理模式（原创）](https://www.jianshu.com/p/e9aa4d5952fe)
* [设计模式（六）代理模式](https://blog.csdn.net/itachi85/article/details/50912632)
* [Java动态代理](https://juejin.im/post/5ad3e6b36fb9a028ba1fee6a)

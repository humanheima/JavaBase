package com.hm.pattern.facade.hometheater;

/**
 * Created by dumingwei on 2023/12/18
 */

// 客户端代码
public class Client {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.operation();
    }
}

// 子系统类
class SubSystemA {
    void operationA() {
        // ...
        System.out.println("SubSystemA operationA");
    }
}

class SubSystemB {
    void operationB() {
        // ...
        System.out.println("SubSystemB operationB");
    }
}

// 外观类
class Facade {

    private SubSystemA subSystemA;
    private SubSystemB subSystemB;

    public Facade() {
        subSystemA = new SubSystemA();
        subSystemB = new SubSystemB();
    }

    void operation() {
        subSystemA.operationA();
        subSystemB.operationB();
    }
}



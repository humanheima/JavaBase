package com.hm.clone;

/**
 * Created by dumingwei on 2017/6/6.
 *
 * 浅拷贝(Object类中的clone()方法)是指在拷贝对象时，对于基本数据类型的变量会重新复制一份，
 * 而对于引用类型的变量只是对引用进行拷贝。
 *
 * 深拷贝(或叫深克隆) 则是对对象及该对象关联的对象内容，都会进行一份拷贝。
 */
public class Student implements Cloneable {

    private int number;
    public Address add;
    private String name;

    public static void main(String[] args) {
        Address address = new Address();
        address.setAddress("上海");
        Student stu1 = new Student();
        stu1.setAdd(address);
        stu1.setNumber(12345);
        stu1.setName("杜明伟");
        Student stu2 = (Student) stu1.clone();
        System.out.println("学生1：" + stu1.getNumber() + ",地址：" + stu1.getAdd().getAddress()+",name"+stu1.getName());
        System.out.println("学生2：" + stu2.getNumber() + ",地址：" + stu2.getAdd().getAddress()+",name"+stu2.getName());
        stu2.setNumber(54321);
        address.setAddress("北京");
        stu1.setName("别了");
        System.out.println("学生1：" + stu1.getNumber() + ",地址：" + stu1.getAdd().getAddress()+",name"+stu1.getName());
        System.out.println("学生2：" + stu2.getNumber() + ",地址：" + stu2.getAdd().getAddress()+",name"+stu2.getName());
    }

    @Override
    public Object clone() {
        Student stu = null;
        try {
            stu = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        stu.add = (Address) add.clone();
        return stu;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Address getAdd() {
        return add;
    }

    public void setAdd(Address add) {
        this.add = add;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

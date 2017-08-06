package com.hm.base;

/**
 * Created by dumingwei on 2017/7/10.
 * Java复制对象
 * 浅复制：只是复制了对象的引用。
 * 深复制：开辟一块新的空间，将值复制后再将引用返回给新的对象
 * http://blog.csdn.net/tounaobun/article/details/8491392
 */
public class CopyTest {

    public static void main(String[] args) {

        Address address = new Address();
        address.setAdd("杭州市");
        Student student = new Student();
        student.setNumber(12345);
        student.setAddress(address);
        Student stu2 = (Student) student.clone();
        System.out.println("学生1：" + student.getNumber() + ",地址：" + student.getAddress().getAdd());
        System.out.println("学生2：" + stu2.getNumber() + ",地址：" + stu2.getAddress().getAdd());
        address.setAdd("西湖区");
        stu2.setNumber(54321);
        System.out.println("学生1：" + student.getNumber() + ",地址：" + student.getAddress().getAdd());
        System.out.println("学生2：" + stu2.getNumber() + ",地址：" + stu2.getAddress().getAdd());
    }

}

class Student implements Cloneable {

    private int number;
    private Address address;

    @Override
    public Object clone() {
        Student stu = null;
        try {
            stu = (Student) super.clone();
            stu.address = (Address) address.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}

class Address implements Cloneable {

    private String add;

    @Override
    public Object clone() {
        Address add = null;
        try {
            add = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return add;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }
}


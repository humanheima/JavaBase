package com.hm.clone;

/**
 * Created by dumingwei on 2020/4/15.
 * <p>
 * Desc:测试在浅拷贝clone的时候，克隆后的对象修改String类型的属性，不会影响原对象，因为String是不可变对象。
 * String 内部就是一个 final char[] value; 用final修饰的，所以String是不可变对象。
 */
public class CloneTestString {

    public static void main(String[] args) {

        User user = new User();
        user.setUsername("张三");


        Dog dog = new Dog();
        dog.setDogName("小狗1");
        user.setDog(dog);

        user.setAge(18);

        User user2 = new User();
        user2.setUsername(user.getUsername());
        user2.setDog(user.getDog());

        user2.setAge(user.getAge());


        System.out.println("user username : " + user.getUsername() + ", age : " + user.getAge() + ", dog " + user.getDog() + ", dogName : " + user.getDog().getDogName());

        System.out.println("user2 username : " + user2.getUsername() + ", age : " + user2.getAge() + ", dog " + user2.getDog() + ", dogName : " + user2.getDog().getDogName());

        user2.setAge(20);
        user2.setUsername("李四");
        user2.getDog().setDogName("小狗2");

        System.out.println("user username : " + user.getUsername() + ", age : " + user.getAge() + ", dog " + user.getDog() + ", dogName : " + user.getDog().getDogName());
        System.out.println("user2 username : " + user2.getUsername() + ", age : " + user2.getAge() + ", dog " + user2.getDog() + ", dogName : " + user2.getDog().getDogName());


    }

}
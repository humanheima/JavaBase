package com.hm.clone;

/**
 * Created by dumingwei on 2020/4/15.
 * <p>
 * Desc:
 */
public class CloneTest {

    public static void main(String[] args) {

        User user = new User();
        user.setUsername("张三");

        Dog dog = new Dog();
        dog.setDogName("小狗1");

        user.setDog(dog);

        User user2 = ObjCloner.cloneObj(user);

        System.out.println("user username : " + user.getUsername());
        System.out.println("user dogname : " + user.getDog().getDogName());
        System.out.println("user2 username : " + user2.getUsername());
        System.out.println("user2 dogname : " + user2.getDog().getDogName());

        System.out.println(" -------------------------------------");

        user2.setUsername("李四");
        user2.getDog().setDogName("小狗2");

        System.out.println("user username : " + user.getUsername());
        System.out.println("user dogname : " + user.getDog().getDogName());
        System.out.println("user2 username : " + user2.getUsername());
        System.out.println("user2 dogname : " + user2.getDog().getDogName());
    }

}
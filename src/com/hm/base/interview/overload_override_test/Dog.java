package com.hm.base.interview.overload_override_test;

public class Dog {

    public Dog() {
    }

    void bark() {
        System.out.println("no barking!");
    }

    String bark(String text) {
        return "string bark" + text;
    }

    void bark(String text, int volume) {
        System.out.println("bark text=" + text + " volume=" + volume);
    }

    void bark(int volume, String text) {
        System.out.println("bark volume=" + volume + " text=" + text);
    }


}

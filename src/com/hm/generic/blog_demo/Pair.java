package com.hm.generic.blog_demo;

class Pair<T> {

    private T value;  

    public T getValue() {  
        return value;  
    }  

    public void setValue(T value) {  
        this.value = value;  
    }


    public void test(){
        System.out.println("test");
    }
}
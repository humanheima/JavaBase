package com.hm.effective_java.chapter_two;

/**
 * Created by dumingwei on 2018/3/18 0018.
 * 使用枚举类型实现单例模式
 */
public enum Elvis {

    INSTANCE;

    public void test() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return super.toString() + "@" + Integer.toHexString(hashCode());
    }
}

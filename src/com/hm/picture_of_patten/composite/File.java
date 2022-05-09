package com.hm.picture_of_patten.composite;

/**
 * Created by dumingwei on 2022/5/7
 * <p>
 * Desc:
 */
public class File extends Entry {

    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
        //等价于
        //System.out.println(prefix + "/" + this.toString());
    }

}

package com.hm.picture_of_patten.composite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by dumingwei on 2022/5/7
 * <p>
 * Desc:
 */
public class Directory extends Entry {

    private String name;                    // 文件夹的名字
    private ArrayList<Entry> directory = new ArrayList<>();      // 文件夹中目录条目的集合

    public Directory(String name) {         // 构造函数
        this.name = name;
    }

    public String getName() {               // 获取名字
        return name;
    }

    public int getSize() {                  // 获取大小
        int size = 0;
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            size += entry.getSize();
        }
        return size;
    }

    public Entry add(Entry entry) {         // 增加目录条目
        directory.add(entry);
        return this;
    }

    protected void printList(String prefix) {       // 显示目录条目一览
        System.out.println(prefix + "/" + this);
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            entry.printList(prefix + "/" + name);
        }
    }
}

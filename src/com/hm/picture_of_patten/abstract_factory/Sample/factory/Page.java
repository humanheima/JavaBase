package com.hm.picture_of_patten.abstract_factory.Sample.factory;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by dumingwei on 2022/5/3.
 * <p>
 * Desc: 这里扮演的角色是：抽象的产品
 */
public abstract class Page {

    protected String title;
    protected String author;

    //产品有多个零件，用集合来保存
    protected ArrayList<Item> content = new ArrayList<>();

    public Page(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void add(Item item) {
        content.add(item);
    }

    public void output() {
        try {
            String filename = "/Users/xmly/IdeaProjects/JavaBase/src/com/hm/picture_of_patten/abstract_factory/" + title + ".html";
            Writer writer = new FileWriter(filename);
            writer.write(this.makeHTML());
            writer.close();
            System.out.println(filename + " 编写完成。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract String makeHTML();
}

package com.hm.picture_of_patten.abstract_factory.Sample.listfactory;

import com.hm.picture_of_patten.abstract_factory.Sample.factory.Factory;
import com.hm.picture_of_patten.abstract_factory.Sample.factory.Link;
import com.hm.picture_of_patten.abstract_factory.Sample.factory.Page;
import com.hm.picture_of_patten.abstract_factory.Sample.factory.Tray;

public class ListFactory extends Factory {

    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }

    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}

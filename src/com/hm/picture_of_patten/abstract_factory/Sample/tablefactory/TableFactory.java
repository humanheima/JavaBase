package com.hm.picture_of_patten.abstract_factory.Sample.tablefactory;

import com.hm.picture_of_patten.abstract_factory.Sample.factory.*;

public class TableFactory extends Factory {

    public Link createLink(String caption, String url) {
        return new TableLink(caption, url);
    }

    public Tray createTray(String caption) {
        return new TableTray(caption);
    }

    public Page createPage(String title, String author) {
        return new TablePage(title, author);
    }
}

package com.hm.picture_of_patten.abstract_factory.Sample.tablefactory;

import com.hm.picture_of_patten.abstract_factory.Sample.factory.Link;

public class TableLink extends Link {

    public TableLink(String caption, String url) {
        super(caption, url);
    }

    public String makeHTML() {
        return "<td><a href=\"" + url + "\">" + caption + "</a></td>\n";
    }
}

package com.hm.picture_of_patten.abstract_factory.Sample.listfactory;

import com.hm.picture_of_patten.abstract_factory.Sample.factory.Link;

public class ListLink extends Link {

    public ListLink(String caption, String url) {
        super(caption, url);
    }

    public String makeHTML() {
        return "  <li><a href=\"" + url + "\">" + caption + "</a></li>\n";
    }
}

package com.hm.picture_of_patten.abstract_factory.Sample.listfactory;

import com.hm.picture_of_patten.abstract_factory.Sample.factory.Item;
import com.hm.picture_of_patten.abstract_factory.Sample.factory.Tray;

import java.util.Iterator;

public class ListTray extends Tray {

    public ListTray(String caption) {
        super(caption);
    }

    public String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<li>\n");
        buffer.append(caption + "\n");
        buffer.append("<ul>\n");
        Iterator it = tray.iterator();
        while (it.hasNext()) {
            Item item = (Item) it.next();
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("</li>\n");
        return buffer.toString();
    }
}

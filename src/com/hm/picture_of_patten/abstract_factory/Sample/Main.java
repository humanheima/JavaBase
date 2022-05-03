package com.hm.picture_of_patten.abstract_factory.Sample;

import com.hm.picture_of_patten.abstract_factory.Sample.factory.Factory;
import com.hm.picture_of_patten.abstract_factory.Sample.factory.Link;
import com.hm.picture_of_patten.abstract_factory.Sample.factory.Page;
import com.hm.picture_of_patten.abstract_factory.Sample.factory.Tray;

/**
 * Created by dumingwei on 2022/5/3.
 * <p>
 * Desc:Main类使用抽象工厂生产零件，并将零件组装成产品。Main类没有使用任何具体零件、产品和工厂。
 */
public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main class.name.of.ConcreteFactory");
            System.out.println("Example 1: java Main listfactory.ListFactory");
            System.out.println("Example 2: java Main tablefactory.TableFactory");
            System.exit(0);
            //src/com/hm/picture_of_patten/abstract_factory/Sample/listfactory/ListFactory.java
            //com/hm/picture_of_patten/abstract_factory/Sample/listfactory/ListFactory.java
            //src/com/hm/picture_of_patten/abstract_factory/Sample/listfactory/ListFactory.java
        }
        Factory factory = Factory.getFactory(args[0]);

        Link people = factory.createLink("人民日报", "http://www.people.com.cn/");
        Link gmw = factory.createLink("光明日报", "http://www.gmw.cn/");

        Link us_yahoo = factory.createLink("Yahoo!", "http://www.yahoo.com/");
        Link jp_yahoo = factory.createLink("Yahoo!Japan", "http://www.yahoo.co.jp/");
        Link excite = factory.createLink("Excite", "http://www.excite.com/");
        Link google = factory.createLink("Google", "http://www.google.com/");

        Tray traynews = factory.createTray("日报");
        traynews.add(people);
        traynews.add(gmw);

        Tray trayyahoo = factory.createTray("Yahoo!");
        trayyahoo.add(us_yahoo);
        trayyahoo.add(jp_yahoo);

        Tray traysearch = factory.createTray("检索引擎");
        traysearch.add(trayyahoo);
        traysearch.add(excite);
        traysearch.add(google);

        Page page = factory.createPage("LinkPage", "杨文轩");
        page.add(traynews);
        page.add(traysearch);
        page.output();
    }
}

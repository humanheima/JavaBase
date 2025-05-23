package com.hm.picture_of_patten.facade.pagemaker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by dumingwei on 2022/5/8
 * <p>
 * Desc:扮演Facade角色并提供高层接口的类
 */

public class PageMaker {

    private PageMaker() {   // 防止外部new出PageMaker的实例，所以声明为private方法
    }

    public static void makeWelcomePage(String mailaddr, String filename) {
        try {
            Properties mailprop = Database.getProperties("/Users/dumingwei/idea_project/JavaBase/src/com/hm/picture_of_patten/facade/maildata");
            String username = mailprop.getProperty(mailaddr);
            HtmlWriter writer = new HtmlWriter(new FileWriter("/Users/dumingwei/idea_project/JavaBase/src/com/hm/picture_of_patten/facade/" + filename));
            writer.title("Welcome to " + username + "'s page!");
            writer.paragraph("欢迎来到" + username + "的主页。");
            writer.paragraph("等着你的邮件哦！");
            writer.mailto(mailaddr, username);
            writer.close();
            System.out.println(filename + " is created for " + mailaddr + " (" + username + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

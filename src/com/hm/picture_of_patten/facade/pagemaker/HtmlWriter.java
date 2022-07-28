package com.hm.picture_of_patten.facade.pagemaker;

import java.io.Writer;
import java.io.IOException;

/**
 * Created by dumingwei on 2022/5/8
 * <p>
 * Desc:用于编写HTML文件的类
 */
public class HtmlWriter {
    private Writer writer;

    public HtmlWriter(Writer writer) {  // 构造函数
        this.writer = writer;
    }

    public void title(String title) throws IOException {    // 输出标题
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<title>" + title + "</title>");
        writer.write("</head>");
        writer.write("<body>\n");
        writer.write("<h1>" + title + "</h1>\n");
    }

    public void paragraph(String msg) throws IOException {  // 输出段落
        writer.write("<p>" + msg + "</p>\n");
    }

    public void link(String href, String caption) throws IOException {  // 输出超链接
        paragraph("<a href=\"" + href + "\">" + caption + "</a>");
    }

    public void mailto(String mailaddr, String username) throws IOException {   //  输出邮件地址
        link("mailto:" + mailaddr, username);
    }

    public void close() throws IOException {    // 结束输出HTML
        writer.write("</body>");
        writer.write("</html>\n");
        writer.close();
    }
}

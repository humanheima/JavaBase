package com.hm.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.Date;
import java.util.List;

/**
 * Created by dumingwei on 2017/6/4.
 */
public class AttributeViewTest {

    public static final String path = "D:\\test\\AttributeViewTest.java";

    public static void main(String[] args) {
        Path path = Paths.get(AttributeViewTest.path);
        BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        try {
            BasicFileAttributes attributes = basicView.readAttributes();
            System.out.println("创建时间:" + new Date(attributes.creationTime().toMillis()));
            System.out.println("最后访问时间:" + new Date(attributes.lastAccessTime().toMillis()));
            System.out.println("最后修改时间:" + new Date(attributes.lastModifiedTime().toMillis()));
            System.out.println("文件大小:" + attributes.size());
            FileOwnerAttributeView ownerView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
            System.out.println(ownerView.getOwner());
            UserPrincipal user = FileSystems.getDefault().getUserPrincipalLookupService()
                    .lookupPrincipalByName("guest");
            ownerView.setOwner(user);
            UserDefinedFileAttributeView userView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
            List<String> attrNames = userView.list();
            for (String attrName : attrNames) {
                ByteBuffer buf = ByteBuffer.allocate(userView.size(attrName));
                userView.read(attrName, buf);
                buf.flip();
                String value = Charset.defaultCharset().decode(buf).toString();
                System.out.println(attrName + "----->" + value);
            }
            //添加一个自定义属性
            userView.write("发行者", Charset.defaultCharset().encode("java"));
            DosFileAttributeView dosView = Files.getFileAttributeView(path, DosFileAttributeView.class);
            //dosView.setReadOnly(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

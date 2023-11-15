package com.hm.base;

/**
 * Created by dumingwei on 2017/7/11.
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        String MD5 = "56:63:47:84:96:0D:7C:5F:7F:92:F5:14:26:57:39:68";
        MD5 = MD5.replace(":", "");
        MD5 = MD5.toLowerCase();
        System.out.println("MD5=" + MD5);

        String SHA1 = "AC:5E:D9:C2:98:D1:25:1B:A1:E1:BE:D8:4E:88:03:43:9E:3F:16:7D";

        SHA1 = SHA1.replace(":", "");
        SHA1 = SHA1.toLowerCase();
        System.out.println("SHA1=" + SHA1);

        String SHA256 = "6B:07:01:EB:92:02:EC:14:2C:2F:B8:81:37:90:87:60:30:56:28:B3:DC:59:5A:92:01:2D:36:49:7F:03:50:F3";

        SHA256 = SHA256.replace(":", "");
        SHA256 = SHA256.toLowerCase();
        System.out.println("SHA256=" + SHA256);
    }

    public Test() {
        System.out.println("构造函数");
        System.out.println(a);
    }

    private int a = 1;

    static {
        System.out.println("静态代码初始块");
    }

    {
        System.out.println("代码初始块");
        a = 2;
    }
}
package com.hm.base;

import java.io.*;
import java.net.URLEncoder;

/**
 * Created by dumingwei on 2017/10/16.
 */
public class TestString {

    public static void main(String[] args) {
        //copy();
        testByteOffset();
    }

    private static void copy() {
        BufferedReader bfr = null;
        BufferedWriter bfw = null;
        // build_full_log.txt
        //build_full_log.txt
        // build_full_log.txt
        ///Users/xmly/IdeaProjects/JavaBase/build_full_log.txt

        try {
            bfr = new BufferedReader(new FileReader("/Users/xmly/IdeaProjects/JavaBase/build_full_log.txt"));
            bfw = new BufferedWriter(new FileWriter("/Users/xmly/IdeaProjects/JavaBase/most_consume_time_task.txt"));
            String line;
            while ((line = bfr.readLine()) != null) {
                if (line.contains("spend")) {
                    bfw.write(line);
                    bfw.newLine();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bfw != null) {
                try {
                    bfw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bfr != null) {
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static void testByteOffset() {
        String s = "xcv.0 古道西风瘦马夕阳西下";
        String substring = s.substring(0, 0);
        System.out.println("haha" + substring + "哈哈");
        System.out.println(s.length());
        String childStr = s.substring(6);
        System.out.println(childStr);
        System.out.println(childStr.length());

        byte[] bytes = s.getBytes();

        byte[] bytesChild = childStr.getBytes();

        System.out.println(bytes.length);
        System.out.println(bytesChild.length);

        int sentenceFirstCharIndex = s.indexOf(childStr);
        System.out.println(sentenceFirstCharIndex);

        String frontString = s.substring(0, sentenceFirstCharIndex);

        System.out.println(frontString);
        byte[] frontBytes = frontString.getBytes();

        System.out.println(frontBytes.length);

        System.out.println(new String(frontBytes));

    }

}



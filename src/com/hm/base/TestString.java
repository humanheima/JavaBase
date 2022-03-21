package com.hm.base;

import java.io.*;

/**
 * Created by dumingwei on 2017/10/16.
 */
public class TestString {

    public static void main(String[] args) {
        copy();
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

}



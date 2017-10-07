package com.hm.io;

import java.io.*;

/**
 * Created by dumingwei on 2017/10/4.
 */
public class FileDescriptorTest {

    private static final String fileName = "file.txt";
    private static final String outText = "Hi FileDescriptor";


    public static void main(String[] args) {
        testRead();
        //testWrite();
        //testStandFD();
    }

    private static void testStandFD() {
        PrintStream out = new PrintStream(new FileOutputStream(FileDescriptor.out));
        out.println(outText);
        out.close();
    }

    private static void testRead() {
        try {
            FileInputStream in1 = new FileInputStream(fileName);
            FileDescriptor fdin = in1.getFD();
            FileInputStream in2 = new FileInputStream(fdin);
            System.out.println("in1.read():"+(char)in1.read());
            System.out.println("in2.read():"+(char)in2.read());
            System.out.printf("fdin(%s) is %s\n", fdin, fdin.valid());
            in1.close();
            in2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testWrite() {
        try {
            FileOutputStream out1 = new FileOutputStream(fileName);
            FileDescriptor fdout = out1.getFD();
            FileOutputStream out2 = new FileOutputStream(fdout);
            out1.write('A');
            out2.write('A');
            System.out.printf("fdout(%s) is %s\n", fdout, fdout.valid());
            out1.close();
            out2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

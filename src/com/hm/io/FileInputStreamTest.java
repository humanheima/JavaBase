package com.hm.io;

import java.io.*;
import java.util.Scanner;

/**
 * Created by dumingwei on 2017/5/31.
 */
public class FileInputStreamTest {

    public static void main(String[] args) {
        //test();
        //testRead();
        //testPrintStream();
        //testStringReader();
        //testKeyIn();
        //testPushBack();
        //testRedirect();
        //testRandomAccess();
        testSerializable();
    }

    private static void testSerializable() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\testSerializable.txt"));
            Person person = new Person("孙悟空", 500);
            Teacher t1 = new Teacher("唐僧", person);
            Teacher t2 = new Teacher("菩提祖师", person);
            oos.writeObject(t1);
            oos.writeObject(t2);
            oos.writeObject(person);
            oos.writeObject(t2);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\testSerializable.txt"));
            Teacher teacher1 = (Teacher) ois.readObject();
            Teacher teacher2 = (Teacher) ois.readObject();
            Person p = (Person) ois.readObject();
            Teacher teacher3 = (Teacher) ois.readObject();
            System.out.println("teacher1的student的引用和p是否相同:" + (teacher1.getStudent() == p));
            System.out.println("teacher2的student的引用和p是否相同:" + (teacher2.getStudent() == p));
            System.out.println("teacher2和teacher3是同一个对象:" + (teacher2 == teacher3));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testRandomAccess() {
        try {
            RandomAccessFile raf = new RandomAccessFile("D:\\io_fw.java", "rw");
            System.out.println(raf.getFilePointer());
            raf.seek(raf.length());
            raf.write("hello world\r\n".getBytes());
            raf.seek(0);
            int len;
            byte[] buf = new byte[1024];
            while ((len = raf.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void testRedirect() {
        try {
            FileInputStream fis = new FileInputStream("D:\\io_fw.java");
            System.setIn(fis);
            Scanner scanner = new Scanner(fis);
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                System.out.println("键盘输入的内容:" + scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("D:\\FileInputStreamTest.java");
            fos = new FileOutputStream("D:\\io.java");
            byte[] buf = new byte[1024];
            int hasRead;
            while ((hasRead = fis.read(buf)) != -1) {
                fos.write(buf, 0, hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testRead() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader("D:\\FileInputStreamTest.java");
            fw = new FileWriter("D:\\io_fw.java");
            char[] buf = new char[32];
            int hasRead;
            while ((hasRead = fr.read(buf)) != -1) {
                fw.write(buf, 0, hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testPrintStream() {
        FileOutputStream fo = null;
        PrintStream pt = null;
        try {
            fo = new FileOutputStream("D:\\iooo.java");
            pt = new PrintStream(fo);
            pt.print("使用PrintStream");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pt != null) {
                pt.close();
            }
        }
    }

    private static void testStringReader() {
        java.lang.String src = "我是一个健康的人";
        char[] buf = new char[32];
        int len;
        StringReader reader = new StringReader(src);
        StringWriter writer = new StringWriter();
        try {
            while ((len = reader.read(buf)) != -1) {
                System.out.print(new java.lang.String(buf, 0, len));
            }
            writer.write("美丽的姑娘");
            System.out.println();
            System.out.println(writer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testKeyIn() {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                if (line.equals("exit"))
                    System.exit(1);
                System.out.println("输入内容为:" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testPushBack() {
        try {
            PushbackReader pr = new PushbackReader(new FileReader("D:\\io_fw.java"), 64);
            char[] buf = new char[32];
            String lastContent = "";
            int len;
            while ((len = pr.read(buf)) != -1) {
                String content = new String(buf, 0, len);
                int targetIndex = (lastContent + content).indexOf("main");
                if (targetIndex > 0) {
                    pr.unread((lastContent + content).toCharArray());
                    if (targetIndex > 32) {
                        buf = new char[targetIndex];
                    }
                    pr.read(buf, 0, targetIndex);
                    System.out.println(new String(buf, 0, len));
                    System.exit(0);
                } else {
                    System.out.print(lastContent);
                    lastContent = content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

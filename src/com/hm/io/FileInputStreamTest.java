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
        testKeyIn();
        //testPushBack();
        //testRedirect();
        //testRandomAccess();
        //testSerializable();
        //copy();
        //test();
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

    /**
     * 拷贝文件
     */
    private static void test() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream("D:\\image.png"));
            bos = new BufferedOutputStream(new FileOutputStream("D:\\imageCopy.png"));
            int hasRead;
            while ((hasRead = bis.read()) != -1) {
                bos.write(hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //构建一个BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                if ("over".equals(line)) {
                    break;
                }
                bw.write(line);
                bw.newLine();//换行
                bw.flush();
            }
            br.close();
            bw.close();
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

    private static void copy() {
        BufferedReader bfr = null;
        BufferedWriter bfw = null;
        try {
            bfr = new BufferedReader(new FileReader("D:/FileInputStreamTest.java"));
            bfw = new BufferedWriter(new FileWriter("D:/FileInputStreamTestCopy.java"));
            String line;
            while ((line = bfr.readLine()) != null) {
                bfw.write(line);
                bfw.newLine();
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

    private static void closeInputStream(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeOutputStream(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

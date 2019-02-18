package com.hm.classloader;

import java.io.*;

/**
 * Created by dumingwei on 2019/2/13
 * <p>
 * Desc:
 */
public class DiskClassLoader extends ClassLoader {

    private String path;

    public DiskClassLoader(String path) {
        this.path = path;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException("not found" + name);
        } else {
            clazz = defineClass(name, classData, 0, classData.length);
        }
        return clazz;
    }

    private byte[] loadClassData(String name) {
        String fileName = getFileName(name);
        File file = new File(path, fileName);
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String getFileName(String name) {
        int index = name.lastIndexOf(".");
        if (index == -1) {
            return name + ".class";
        }
        return name.substring(index + 1) + ".class";
    }
}

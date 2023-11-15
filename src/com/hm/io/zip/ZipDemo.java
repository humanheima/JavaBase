package com.hm.io.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

//zip操作
public class ZipDemo {
    //目录名称
    private static String driName =null;
    //压缩后是否包含最外层的目录 
    private static Boolean delOutside = false;

    public static String getDriName() {
        return driName;
    }

    public static void setDriName(String driName) {
        ZipDemo.driName = driName;
    }

    public static Boolean getDelOutside() {
        return delOutside;
    }

    public static void setDelOutside(Boolean delOutside) {
        ZipDemo.delOutside = delOutside;
    }

    /**
     * 压缩
     * @param outFile 文件输出的地址
     * @param targetFile 需要压缩的目录
     * @param isDelOutside 压缩后是否包含最外层的目录 true 包含 false 不包含
     */
    private static void compression(String outFile, File targetFile,Boolean isDelOutside) {
        
        System.out.println("压缩进行中");
        long start = System.currentTimeMillis();
        delOutside = isDelOutside;
        BufferedOutputStream bos = null;
        try {
            if (!targetFile.exists()) {
                System.out.println("文件地址不存在");
                return;
            }
            ZipOutputStream zOut = new ZipOutputStream(new FileOutputStream(outFile));
            bos = new BufferedOutputStream(zOut);
            driName = targetFile.getName();
            zip(zOut, targetFile, driName, bos);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        long end = System.currentTimeMillis();

        System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        System.out.println("压缩完成");
    }

    private static void zip(ZipOutputStream zOut, File targetFile, String name, BufferedOutputStream bos) throws IOException {
        if (targetFile.isDirectory()) {
            File[] files = targetFile.listFiles();
            //判断是否为空目录
            if (files.length == 0) {
                zOut.putNextEntry(new ZipEntry(name + "/"));
            } else {
                //目录递归调用
                for (File file : files) {
                    zip(zOut, file, name + "/" + file.getName(), bos);
                }
            }
        } else {
            if(!delOutside){
                name = name.replaceFirst(driName+"/","");
            }
            zOut.putNextEntry(new ZipEntry(name));
            InputStream in = new FileInputStream(targetFile);
            BufferedInputStream bis = new BufferedInputStream(in);
            //将源文件写入到zip文件中
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            bis.close();
        }
    }

    //解压
    private static void decompression(String targetFileName, String parent) {

        try {
            File targetFile = new File(targetFileName);
            if(!targetFile.exists()){
                System.out.println("文件地址不存在");
            }
            ZipInputStream zis = new ZipInputStream(new FileInputStream(targetFile));
            ZipEntry entry = null;
            File file = null;
            while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
                file = new File(parent, entry.getName());
                if (!file.exists()) {
                    new File(file.getParent()).mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                int len = -1;
                byte[] bytes = new byte[1024];
                while ((len = zis.read(bytes)) != -1) {
                    bos.write(bytes, 0, len);
                }
                System.out.println(file.getAbsoluteFile() + "解压完成");
                bos.close();
            }
            System.out.println("解压结束");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        //压缩调用
        compression("F:\\1.zip", new File("F:\\测试"),true);
        //解压调用
//        decompression("F:\\1.zip", "F:\\测试\\");
    }

}


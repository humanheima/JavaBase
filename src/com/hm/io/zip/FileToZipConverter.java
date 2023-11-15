package com.hm.io.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by dumingwei on 2023/09/24
 */
public class FileToZipConverter {

    public static void main(String[] args) {

        String sourceFilePath = "/Users/dumingwei/Downloads/temp_zip";
        String zipFilePath = "/Users/dumingwei/Downloads/temp_zip.zip";


        zipDirectory(sourceFilePath, zipFilePath);
    }

    /**
     * 将文件夹压缩成zip文件
     *
     * @param sourceFilePath
     * @param zipFilePath
     */
    public static void zipDirectory(String sourceFilePath, String zipFilePath) {

        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("start at " + currentTimeMillis);

        try {
            FileOutputStream fos = new FileOutputStream(zipFilePath);
            ZipOutputStream zos = new ZipOutputStream(fos);

            File sourceFolder = new File(sourceFilePath);
            addFolderToZip(sourceFolder, sourceFolder.getName(), zos);

            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end 耗时 " + (System.currentTimeMillis() - currentTimeMillis));

    }

    private static void addFolderToZip(File folder, String parentFolderName, ZipOutputStream zos) throws IOException {
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        byte[] buffer = new byte[1024];

        for (File file : files) {
            if (file.isDirectory()) {
                addFolderToZip(file, parentFolderName + "/" + file.getName(), zos);
                continue;
            }

            FileInputStream fis = new FileInputStream(file);
            System.out.println("Compressing parentFolderName =  " + parentFolderName + " file.getName() = " + file.getName());
            zos.putNextEntry(new ZipEntry(file.getName()));

            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }

            zos.closeEntry();
            fis.close();
        }
    }
}
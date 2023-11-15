package com.hm.io.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFile {
    public static void main(String[] args) {
        String zipFilePath = "/Users/dumingwei/Downloads/temp_zipfile.zip";
        String destDirPath = "/Users/dumingwei/Downloads/temp_zip_2";

        try {
            File destDir = new File(destDirPath);
            if (!destDir.exists()) {
                destDir.mkdir();
            }

            FileInputStream fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                System.out.println("Unzipping fileName = " + fileName);
                int index = fileName.lastIndexOf("/");
                fileName = fileName.substring(index + 1);
                System.out.println("Unzipping replace fileName = " + fileName);

                File newFile = new File(destDirPath + File.separator + fileName);

                // Create all non-existent directories
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);
                byte[] buffer = new byte[1024];
                int length;

                while ((length = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }

                fos.close();
                zipEntry = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
            fis.close();

            System.out.println("File successfully unzipped.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
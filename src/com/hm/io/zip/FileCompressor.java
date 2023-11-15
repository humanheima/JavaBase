package com.hm.io.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileCompressor {
    public static void main(String[] args) {
        String sourceFilePath = "/Users/dumingwei/Downloads/temp_zip";
        String zipFilePath = "/Users/dumingwei/Downloads/temp_zipfile.zip";

        try {
            File sourceFile = new File(sourceFilePath);
            FileOutputStream fos = new FileOutputStream(zipFilePath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            compressFile(sourceFile, sourceFile.getName(), zipOut);

            zipOut.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compressFile(File fileToCompress, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToCompress.isHidden()) {
            return;
        }

        if (fileToCompress.isDirectory()) {
            File[] children = fileToCompress.listFiles();
            for (File childFile : children) {
                compressFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }

        FileInputStream fis = new FileInputStream(fileToCompress);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        fis.close();
    }
}
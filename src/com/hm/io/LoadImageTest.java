package com.hm.io;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by dumingwei on 2017/10/20.
 * 测试下载图片
 */
public class LoadImageTest {

    private ThreadPoolExecutor executor;

    public static void main(String[] args) {
        LoadImageTest test = new LoadImageTest();
        test.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        for (int i = 0; i < Images.IMAGES.length; i++) {
            test.executor.submit(new DownloadThread(Images.IMAGES[i], i, test.executor));
        }
        System.out.println("已经加入了所有的任务");
        test.executor.shutdown();
        System.out.println("关闭任务");
        while (true) {
            if (test.executor.isTerminated()) {
                System.out.println("所有任务已经执行完毕");
                break;
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static class DownloadThread implements Runnable {

        private String imageUrl;
        private int position;
        private ThreadPoolExecutor executor;

        public DownloadThread(String imageUrl, int position, ThreadPoolExecutor executor) {
            this.imageUrl = imageUrl;
            this.position = position;
            this.executor = executor;
        }

        @Override
        public void run() {
            HttpURLConnection con = null;
            FileOutputStream fos;
            BufferedOutputStream bos = null;
            BufferedInputStream bis = null;
            File imageFile;
            try {
                URL url = new URL(imageUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5000);
                con.setReadTimeout(15000);
                con.setDoInput(true);
                con.setDoOutput(true);
                bis = new BufferedInputStream(con.getInputStream());
                imageFile = getImageFile();
                fos = new FileOutputStream(imageFile);
                bos = new BufferedOutputStream(fos);
                int d;
                while ((d = bis.read()) != -1) {
                    bos.write(d);
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
                    if (con != null) {
                        con.disconnect();
                    }
                    if (position == Images.IMAGES.length) {
                        if (executor != null) {
                            executor.shutdown();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static File getImageFile() throws IOException {
        File directory = new File("D:\\ImageStorePath");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = File.createTempFile("img_", ".jpg", directory);
        return file;
    }
}

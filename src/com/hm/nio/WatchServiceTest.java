package com.hm.nio;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by dumingwei on 2017/6/4.
 */
public class WatchServiceTest {

    public static void main(String[] args) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Paths.get("D:\\test")
                    .register(watchService,
                            StandardWatchEventKinds.ENTRY_CREATE,
                            StandardWatchEventKinds.ENTRY_DELETE,
                            StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    System.out.println("文件发生了:" + watchEvent.kind() + "事件");
                }
                boolean valid = key.reset();
                if (!valid)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

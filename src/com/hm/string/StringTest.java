package com.hm.string;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hm.anno.A;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dumingwei on 2017/4/13.
 */
public class StringTest {

    public static void main(String[] args) {

        /*Map<String, AlbumListenInfo> map = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyy_MM_dd");
        for (int i = 0; i < 10; i++) {
            AlbumListenInfo albumListenInfo = new AlbumListenInfo();
            String key = "albumId" + i;
            albumListenInfo.albumId = key;
            albumListenInfo.listenTime = i;
            albumListenInfo.popNumber = i;
            albumListenInfo.saveDate = format.format(System.currentTimeMillis());
            map.put(key, albumListenInfo);
        }

        Gson gson = new Gson();

        String String = gson.toJson(map);
        System.out.println(String);

        Map<String, AlbumListenInfo> newMap = gson.fromJson(String, new TypeToken<HashMap<String, AlbumListenInfo>>() {
        }.getType());

        for (Map.Entry<java.lang.String, AlbumListenInfo> stringAlbumListenInfoEntry : newMap.entrySet()) {
            System.out.println(stringAlbumListenInfoEntry.getKey());
            System.out.println(stringAlbumListenInfoEntry.getValue());
        }*/


        boolean a = false;
        boolean b = false;

        System.out.println("a ^ b = " + (a ^ b));

        long aaa = 120;
        long bbbb = 10;
        System.out.println( String.format("%1$02d:%2$02d", aaa,bbbb));


    }
}


class AlbumListenInfo {

    String albumId;
    long listenTime;
    int popNumber;
    String saveDate;

    @Override
    public String toString() {
        return "AlbumListenInfo{" +
                "albumId='" + albumId + '\'' +
                ", listenTime=" + listenTime +
                ", popNumber=" + popNumber +
                ", saveDate='" + saveDate + '\'' +
                '}';
    }
}

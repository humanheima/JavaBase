package com.hm.picture_of_patten.state;

/**
 * Created by dumingwei on 2022/5/10
 * <p>
 * Desc:
 */

public class Main {

    public static void main(String[] args) {
        SafeFrame frame = new SafeFrame("State Sample");
        while (true) {
            for (int hour = 0; hour < 24; hour++) {
                frame.setClock(hour);   // 设置时间
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}

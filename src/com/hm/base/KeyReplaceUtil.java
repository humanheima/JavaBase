package com.hm.base;

/**
 * Created by dmw on 2018/11/26.
 * Desc:
 */
public class KeyReplaceUtil {

    public static void main(String[] args) {
        System.out.println(getKeyStoreLowCase("EB:2C:42:76:05:2B:0D:C6:D1:16:24:53:3F:6A:3C:6E"));
        String s = String.valueOf(System.currentTimeMillis());
    }

    public static String getKeyStoreLowCase(String input) {

        if (input == null || input.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            if (':' == temp) {
                continue;
            }
            if (temp >= 'A' && temp <= 'Z') {
                builder.append((char) (temp + 32));
            } else {
                builder.append(temp);
            }
        }
        return builder.toString();
    }
}

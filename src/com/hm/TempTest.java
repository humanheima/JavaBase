package com.hm;

import java.util.ArrayList;
import java.util.List;

public class TempTest {


    public static void main(String[] args) {
        String[] input = {"h", "e", "l", "l", "o", " ", "w", "o", "r", "l", "d", " ", "h", "i"};
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (String s : input) {
            builder.append(s);
        }

        String result = builder.toString();
        String[] array = result.split("\\s");


        builder = new StringBuilder();

        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            builder.append(array[i]);
            if (i > 0) {
                builder.append(" ");
            }
        }
        System.out.println(builder.toString().toCharArray());

    }


}

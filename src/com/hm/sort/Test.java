package com.hm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dumingwei on 2022/4/18.
 * <p>
 * Desc:
 */
public class Test {


    public static void main(String[] args) {
        ArrayList<String> destinations = new ArrayList<>();
        destinations.add("bali");
        destinations.add("malta");
        destinations.add("palma");
        destinations.add("paris");
        System.out.println(autoCorrectSuggestions("paris", destinations));
    }

    public static List<String> autoCorrectSuggestions(String input, List<String> destinations) {
        if (input == null || destinations == null) {
            return null;
        }
        List<String> result = new ArrayList<>();
        int diffCharCount = input.length();//有多少个字符不同
        char[] inputChars = input.toCharArray();
        Arrays.sort(inputChars);
        for (int i = 0; i < destinations.size(); i++) {
            String found = destinations.get(i);
            if (found != null) {
                int length = found.length();
                if (length == input.length()) {
                    char[] foundChars = found.toCharArray();
                    Arrays.sort(foundChars);
                    int tempDiffCharCount = 0;
                    for (int j = 0; j < length; j++) {
                        if (inputChars[j] != foundChars[j]) {
                            tempDiffCharCount++;
                        }
                    }
                    if (tempDiffCharCount < diffCharCount) {
                        result.clear();
                        result.add(found);
                        diffCharCount = tempDiffCharCount;
                    } else if (tempDiffCharCount == diffCharCount) {
                        //加入
                        if (!result.contains(found)){
                            result.add(found);
                        }
                    }
                }
            }
        }
        return result;
    }
}

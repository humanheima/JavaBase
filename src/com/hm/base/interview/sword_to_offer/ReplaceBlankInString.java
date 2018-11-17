package com.hm.base.interview.sword_to_offer;

/**
 * Created by dmw on 2018/11/17.
 * Desc: 把字符串中的空格替换为 %20
 */
public class ReplaceBlankInString {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("We are happy.");
        System.out.println(replaceBlankWithApi(stringBuilder));
        System.out.println(replaceBlankUseAnotherString(stringBuilder));
        System.out.println(replaceBlankInPlace(stringBuilder));

    }

    /**
     * 使用String的replaceAll()方法
     *
     * @param stringBuilder
     * @return
     */
    public static String replaceBlankWithApi(StringBuilder stringBuilder) {
        if (stringBuilder == null) {
            return null;
        }
        return stringBuilder.toString().replaceAll(" ", "%20");


    }

    /**
     * 使用额外的一个字符串
     *
     * @param stringBuilder
     * @return
     */
    public static String replaceBlankUseAnotherString(StringBuilder stringBuilder) {
        if (stringBuilder == null) {
            return null;
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == ' ') {
                out.append("%20");
            } else {
                out.append(stringBuilder.charAt(i));
            }
        }
        return out.toString();
    }

    /**
     * 在字符串本身上进行修改
     *
     * @param stringBuilder
     * @return
     */
    public static String replaceBlankInPlace(StringBuilder stringBuilder) {
        if (stringBuilder == null) {
            return null;
        }
        int originalLength = stringBuilder.length();
        int blankNum = 0;
        for (int i = 0; i < originalLength; i++) {
            if (stringBuilder.charAt(i) == ' ') {
                blankNum++;
            }
        }
        if (blankNum == 0) {//如果没有空格直接返回
            return stringBuilder.toString();
        }
        int newLength = originalLength + 2 * blankNum;//新的字符串的长度
        int indexOriginal = originalLength - 1;
        int indexNew = newLength - 1;
        //扩展stringBuilder的长度
        stringBuilder.setLength(newLength);
        while (indexOriginal >= 0) {
            if (stringBuilder.charAt(indexOriginal) == ' ') {
                stringBuilder.setCharAt(indexNew, '0');
                indexNew--;
                stringBuilder.setCharAt(indexNew, '2');
                indexNew--;
                stringBuilder.setCharAt(indexNew, '%');
                indexNew--;
            } else {
                stringBuilder.setCharAt(indexNew, stringBuilder.charAt(indexOriginal));
                indexNew--;
            }
            indexOriginal--;
        }
        return stringBuilder.toString();

    }

}

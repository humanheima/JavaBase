package com.hm.anno;

/**
 * 字符串编码，把空格替换成 %20
 */

public class ReplaceWhiteSpace {

    public static void main(String[] args) {

//        String testOne = "Hello world!";
//        String result = getEncodeString(testOne);
//        System.out.println(result);

        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            //0,1,2,3,4
            array[i] = i;
        }
        int resultIndex = binarySearch(array, 10);
        System.out.println(resultIndex);
    }

    private static String getEncodeString(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();

        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            if (c == ' ') {
                builder.append("%20");
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * 二查找
     *
     * @param array
     * @return
     */
    private static int binarySearch(int[] array, int key) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] > key) {
                right = mid - 1;
            } else if (array[mid] < key) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


}

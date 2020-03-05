第一次提交通过的版本

暴力破解法

```java
class Solution {
    public String longestPalindrome(String s) {
         if (s.length() == 1) {
            return s;
        }
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length(); j >= i + 1; j--) {
                String tmp = s.substring(i, j);
                if (result.length() > tmp.length()) {
                    break;
                }
                if (isPalindrome(tmp) && tmp.length() > result.length()) {
                    result = tmp;
                }
            }
        }
        return result;
    }

    public static boolean isPalindrome(String numberStr) {
        int length = numberStr.length();
        if (length == 1) {
            return true;
        }
        //标记是否为回文数
        boolean result = true;
        //获取中间位置
        int middle = length / 2;

        for (int i = 0; i < middle; i++) {
            char left = numberStr.charAt(i);
            char right = numberStr.charAt(length - 1 - i);
            //如果有一个字符不想等，退出循环，返回false。
            if (left != right) {
                result = false;
                break;
            }

        }
        return result;
    }
}
```


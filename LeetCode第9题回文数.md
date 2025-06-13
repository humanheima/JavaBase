判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:

>输入: 121
输出: true

示例 2:
>输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。

示例 3:

>输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
进阶:

### 解体思路：
1. 把数字转化成字符串，从中间分开，左边从前向后遍历，右边从后向前遍历，如果每个字符都相等的话，说明是回文数。

### 算法实现

Java实现

```java
    public static boolean isPalindrome(int number) {
        //将数字转化为字符串
        String numberStr = String.valueOf(number);
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
```

Kotlin实现
```
    fun isPalindrome(number: Int): Boolean {
        val numberStr = number.toString()
        val length = numberStr.length
        if (length == 1) {
            return true
        }
        var result = true

        val middle = length / 2

        for (i in 0 until middle) {
            val left = numberStr[i]
            val right = numberStr[length - 1 - i]
            if (left != right) {
                result = false
                break
            }
        }
        return result
    }
```

### 疑问
1. 数字转化成字符串以后，长度为奇数或者偶数，我们在遍历过程中并没有区分，这样会不会有问题呢？

答案：不会的，我们来分析一下上面的算法。

```
    int middle = length / 2;
    for (int i = 0; i < middle; i++) {
        char left = numberStr.charAt(i);
        //right从后向前取
        char right = numberStr.charAt(length - 1 - i);
        if (left != right) {
            result = false;
            break;
        }
    }
```

举例来看

当长度为奇数的情况

121
>length = 3，middle = length / 2 =1
左边遍历的字符依次是：1
右边遍历的字符依次是：1
左右相等，所以121是回文数。
可以看到，当长度为奇数的情况下，下标为middle的字符并没有被遍历到，我们只需要判断下标为middle的字符左右两边的字符序列是一样的即可。

当长度为偶数的情况

1221

>length = 4
middle = length / 2 =2
左边遍历的字符依次是：12
右边遍历的字符依次是：12
左右相等，所以1221是回文数
可以看到，当长度为偶数的情况下，下标为middle的字符会被遍历到，位于右半边遍历的区域。




###你能不将整数转为字符串来解决这个问题吗？

* 可以参考[LeetCode 第 9 号问题：回文数](https://www.cxyxiaowu.com/6847.html)

参考链接：
* [9. 回文数](https://leetcode-cn.com/problems/palindrome-number)
* [LeetCode 第 9 号问题：回文数](https://www.cxyxiaowu.com/6847.html)

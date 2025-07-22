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

# 回文数，现将整数转化为字符串，算法怎么实现


我来讲解如何判断一个整数是否为回文数，通过将整数转化为字符串来实现，并提供 Java 代码。回文数是指一个整数正着读和反着读相同，例如 `121` 是回文数，`123` 不是。

---

### 问题描述
给定一个整数 `x`，判断它是否为回文数。要求将整数转化为字符串来处理。

**示例**：
- 输入：`x = 121` → 输出：`true`（字符串 `"121"` 正反读相同）
- 输入：`x = -121` → 输出：`false`（字符串 `"-121"` 正反读不同）
- 输入：`x = 10` → 输出：`false`（字符串 `"10"` 正反读不同）
- 输入：`x = 0` → 输出：`true`（字符串 `"0"` 是回文）

---

### 算法讲解
通过将整数转化为字符串，我们可以利用字符串的特性来判断是否为回文。主要步骤如下：

1. **将整数转化为字符串**：
    - 使用 `String.valueOf(x)` 或 `Integer.toString(x)` 将整数 `x` 转换为字符串。
    - 例如，`x = 121` 转换为 `"121"`。

2. **检查回文**：
    - 使用双指针法：
        - 左指针 `left` 从字符串开头（索引 0）开始。
        - 右指针 `right` 从字符串末尾（索引 `length-1`）开始。
        - 比较 `left` 和 `right` 位置的字符是否相同：
            - 如果相同，`left++`，`right--`，继续比较。
            - 如果不同，直接返回 `false`。
        - 当 `left >= right` 时，说明比较完成，返回 `true`。
    - 或者，直接反转字符串，比较反转后的字符串是否与原字符串相等。

3. **处理特殊情况**：
    - 负数：负数（如 `-121`）转换为字符串后会包含负号（`"-121"`），正反读不同，因此负数直接返回 `false`。
    - 单个数字：如 `0` 或 `5`，字符串长度为 1，天然是回文。

4. **时间复杂度**：
    - 转换整数为字符串：O(log x)，因为整数的位数与 log(x) 相关。
    - 检查回文（双指针）：O(n)，其中 n 是字符串长度（即 log x）。
    - 总时间复杂度：O(log x)。
5. **空间复杂度**：
    - 存储字符串：O(log x)。
    - 如果使用双指针法，仅需常数额外空间（不计字符串本身）。

---

### 图示讲解
以 `x = 121` 为例：
```
1. 转换为字符串：s = "121"
2. 双指针检查：
   - left = 0, right = 2: s[0] = '1', s[2] = '1', 相同
   - left = 1, right = 1: s[1] = '2', s[1] = '2', 相同
   - left >= right，结束，返回 true
```

以 `x = -121` 为例：
```
1. 检查负数：x < 0，直接返回 false
```

以 `x = 123` 为例：
```
1. 转换为字符串：s = "123"
2. 双指针检查：
   - left = 0, right = 2: s[0] = '1', s[2] = '3', 不同
   - 立即返回 false
```

---

### Java 实现
以下是使用字符串和双指针法的 Java 代码：

```x-java-source
public class Solution {
    public boolean isPalindrome(int x) {
        // 负数不是回文数
        if (x < 0) {
            return false;
        }
        
        // 将整数转换为字符串
        String s = String.valueOf(x);
        
        // 双指针检查回文
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}
```

---

### 代码解析
1. **负数检查**：
    - 如果 `x < 0`，直接返回 `false`，因为负数字符串包含负号，无法是回文。
2. **转换为字符串**：
    - 使用 `String.valueOf(x)` 将整数转为字符串。
3. **双指针检查**：
    - `left` 从字符串开头，`right` 从字符串末尾。
    - 比较 `s.charAt(left)` 和 `s.charAt(right)`，不同则返回 `false`。
    - 循环直到 `left >= right`，返回 `true`。

---

### 替代方法：反转字符串
另一种方法是将字符串反转后与原字符串比较：

```x-java-source
public class Solution {
    public boolean isPalindrome(int x) {
        // 负数不是回文数
        if (x < 0) {
            return false;
        }
        
        // 将整数转换为字符串
        String s = String.valueOf(x);
        
        // 反转字符串并比较
        String reversed = new StringBuilder(s).reverse().toString();
        return s.equals(reversed);
    }
}
```

**说明**：
- 使用 `StringBuilder` 的 `reverse()` 方法反转字符串。
- 比较原字符串和反转后的字符串是否相等。
- 空间复杂度略高（需要存储反转字符串），但代码更简洁。

---

### 测试用例
```java
public static void main(String[] args) {
    Solution rotatedArraySearch = new Solution();
    int[] tests = {121, -121, 10, 0, 12321};
    for (int test : tests) {
        System.out.println("输入: " + test + " -> 输出: " + rotatedArraySearch.isPalindrome(test));
    }
}
```

**输出**：
```
输入: 121 -> 输出: true
输入: -121 -> 输出: false
输入: 10 -> 输出: false
输入: 0 -> 输出: true
输入: 12321 -> 输出: true
```

---

### 性能对比
1. **双指针法**（第一个实现）：
    - 时间复杂度：O(log x)
    - 空间复杂度：O(log x)（字符串本身）
    - 优点：只需要遍历字符串一次，空间效率较高。
2. **反转字符串法**（第二个实现）：
    - 时间复杂度：O(log x)
    - 空间复杂度：O(log x)（字符串 + 反转字符串）
    - 优点：代码简洁，易于理解。

---

### 注意事项
- **负数**：负数一定不是回文数，直接返回 `false`。
- **单字符**：单个数字（如 `0`、`5`）是回文数。
- **大整数**：字符串方法适用于任意大小的整数，不会因数值溢出而受限。

如果需要非字符串解法（例如通过数学运算反转整数）或有其他问题，请告诉我！








###你能不将整数转为字符串来解决这个问题吗？

* 可以参考[LeetCode 第 9 号问题：回文数](https://www.cxyxiaowu.com/6847.html)

参考链接：
* [9. 回文数](https://leetcode-cn.com/problems/palindrome-number)
* [LeetCode 第 9 号问题：回文数](https://www.cxyxiaowu.com/6847.html)

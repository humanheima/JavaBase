我来为你详细解析 LeetCode 3 号问题“最长无重复字符子串”（Longest Substring Without Repeating Characters），并提供 Java 实现。

### 问题描述
给定一个字符串 `s`，请你找出其中不含有重复字符的最长子串的长度。

**示例：**
- 输入: "abcabcbb"
- 输出: 3
- 解释: 因为无重复字符的最长子串是 "abc"，其长度为 3。

- 输入: "bbbbb"
- 输出: 1
- 解释: 因为无重复字符的最长子串是 "b"，其长度为 1。

- 输入: "pwwkew"
- 输出: 3
- 解释: 因为无重复字符的最长子串是 "wke"，其长度为 3。

---

### 算法解析
这个问题可以用 **滑动窗口**（Sliding Window）来解决。滑动窗口是一种非常高效的解决子串问题的技巧，核心思想是维护一个窗口，使得窗口内的字符始终满足题目要求（这里是无重复字符），通过调整窗口的左右边界来找到最优解。

#### 思路
1. **定义窗口**：用两个指针 `left` 和 `right` 表示窗口的左右边界，初始时都指向字符串开头。
2. **记录字符位置**：用一个哈希表（HashMap）或数组记录窗口内每个字符的最新出现位置。
3. **扩展窗口**：移动 `right` 指针，依次将字符加入窗口。
4. **遇到重复字符**：
    - 如果当前字符在窗口内已经出现过，更新 `left` 指针，使其跳到重复字符的上次出现位置的下一个位置。这句话是重点！！！
    - 更新哈希表中该字符的新位置。
5. **更新最大长度**：每次移动指针后，计算当前窗口长度（`right - left + 1`），更新全局最大值。
6. **重复直到结束**：当 `right` 到达字符串末尾时，结束循环。

#### 时间复杂度
- 时间复杂度：O(n)，其中 n 是字符串长度。每个字符最多被访问两次（`left` 和 `right` 各一次）。
- 空间复杂度：O(min(m, n))，m 是字符集大小（例如 ASCII 码为 128 或 256），n 是字符串长度，取决于哈希表存储的字符数量。

---

### Java 实现
以下是使用滑动窗口和 HashMap 的 Java 代码：

```java
import java.util.HashMap;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // 哈希表记录字符及其最新出现位置
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;  // 最长无重复子串长度
        int left = 0;       // 窗口左边界
        
        // 遍历字符串，right 是窗口右边界
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // 如果当前字符已在窗口内出现，更新左边界
            if (map.containsKey(currentChar)) {
                // 左边界跳到重复字符的上次出现位置的下一个位置
                left = Math.max(left, map.get(currentChar) + 1);
            }
            
            // 更新当前字符的位置
            map.put(currentChar, right);
            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }

    // 测试代码
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        
        System.out.println(solution.lengthOfLongestSubstring(s1)); // 输出: 3
        System.out.println(solution.lengthOfLongestSubstring(s2)); // 输出: 1
        System.out.println(solution.lengthOfLongestSubstring(s3)); // 输出: 3
    }
}
```

---

### 代码逐步解析
1. **初始化**：
    - 如果输入字符串为空或 null，返回 0。
    - 创建 `HashMap` 存储字符和其最新位置。
    - `maxLength` 记录最大长度，`left` 初始化为 0。

2. **遍历字符串**：
    - `right` 作为窗口右边界，从 0 开始递增。
    - 获取当前字符 `currentChar`。

3. **处理重复字符**：
    - 如果 `currentChar` 已存在于 `map` 中，说明窗口内有重复。
    - 更新 `left` 为 `map.get(currentChar) + 1`，但要用 `Math.max`，防止 `left` 回退（例如 "abba" 的情况）。

4. **更新状态**：
    - 将当前字符及其位置存入 `map`。
    - 计算当前窗口长度 `right - left + 1`，与 `maxLength` 比较并更新。

5. **返回结果**：
    - 遍历结束后，`maxLength` 即为答案。

---

### 示例运行
以 "abcabcbb" 为例：
- `right = 0, left = 0`：窗口 "a"，map = {a:0}，maxLength = 1
- `right = 1, left = 0`：窗口 "ab"，map = {a:0, b:1}，maxLength = 2
- `right = 2, left = 0`：窗口 "abc"，map = {a:0, b:1, c:2}，maxLength = 3
- `right = 3, left = 1`：遇到重复 'a'，窗口 "bca"，map = {a:3, b:1, c:2}，maxLength = 3
- `right = 4, left = 2`：遇到重复 'b'，窗口 "cab"，map = {a:3, b:4, c:2}，maxLength = 3
- 继续遍历，最终返回 3。

---

希望这个解析和实现对你有帮助！如果有任何疑问，欢迎继续提问。
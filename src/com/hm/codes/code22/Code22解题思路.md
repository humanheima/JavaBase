# 括号生成

数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

这是一个经典的括号生成问题，力扣22号问题“括号生成”（Generate Parentheses）。给定一个数字 \( n \)，表示括号的对数，要求生成所有可能的有效括号组合。以下是详细的算法解析和Java实现。

---

### 问题解析
- **输入**：一个正整数 \( n \)，表示括号的对数。
- **输出**：所有可能的有效括号组合（字符串列表），顺序任意。
- **有效括号组合**：
    - 每个组合包含 \( n \) 个左括号 `(` 和 \( n \) 个右括号 `)`。
    - 括号必须配对，且任意前缀的左括号数量不少于右括号数量。
- **示例**：
    - 输入：\( n = 3 \)
    - 输出：`["((()))","(()())","(())()","()(())","()()()"]`
    - 输入：\( n = 1 \)
    - 输出：`["()"]`

---

### 算法解析
这个问题可以通过**回溯法**（Backtracking）高效解决，因为需要生成所有可能的组合，并确保括号有效。以下是详细分析：

#### 1. 主要思路：回溯法
- **核心思想**：
    - 使用回溯法递归生成所有可能的括号组合。
    - 在每一层递归中，选择添加左括号 `(` 或右括号 `)`，并确保结果有效。
- **有效性约束**：
    - 左括号数量不能超过 \( n \)。
    - 右括号数量不能超过当前已使用的左括号数量（确保括号匹配）。
- **递归过程**：
    - 维护当前括号字符串和剩余的左括号、右括号数量。
    - 当字符串长度达到 \( 2n \)（即用完所有括号），加入结果。
- **优化**：
    - 使用 `StringBuilder` 动态构建字符串，避免字符串拼接的性能开销。
    - 提前终止无效分支（例如右括号多于左括号）。

#### 2. 算法步骤
1. **初始化**：
    - 创建结果列表 `result` 存储所有有效组合。
    - 使用 `StringBuilder` 构建当前括号字符串。
    - 初始化左括号和右括号的剩余数量（均为 \( n \)）。
2. **回溯函数**：
    - 参数：当前字符串、剩余左括号数、剩余右括号数、结果列表。
    - 终止条件：当字符串长度为 \( 2n \)，加入结果。
    - 递归选择：
        - 如果剩余左括号大于0，添加 `(`，递归调用。
        - 如果剩余右括号大于剩余左括号（确保有效），添加 `)`，递归调用。
    - 回溯：移除当前添加的括号，恢复状态。
3. **边界处理**：
    - 如果 \( n = 0 \)，返回空列表。

#### 3. 复杂度分析
- **时间复杂度**：\( O(\frac{4^n}{\sqrt{n}}) \)
    - 生成的组合数是卡塔兰数 \( C_n = \frac{1}{n+1} \binom{2n}{n} \)，近似为 \( \frac{4^n}{\sqrt{n}} \)。
    - 每个组合长度为 \( 2n \)，构建字符串的时间为 \( O(n) \)。
    - 总体时间复杂度为 \( O(\frac{4^n}{\sqrt{n}}) \)。
- **空间复杂度**：\( O(n) \)
    - 递归栈深度为 \( O(n) \)。
    - 每个组合占用 \( O(n) \) 空间，`StringBuilder` 占用 \( O(n) \)。
    - 不计输出空间，空间复杂度为 \( O(n) \).

---

### Java实现
以下是基于回溯法的Java代码实现，包含测试用的 `main` 函数。

```java
import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        // 测试用例
        int[] testCases = {3, 1, 0};
        
        Solution solution = new Solution();
        
        for (int n : testCases) {
            System.out.println("Test Case: n = " + n);
            List<String> result = solution.generateParenthesis(n);
            System.out.println("Result: " + result);
            System.out.println();
        }
    }

    static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            backtrack(new StringBuilder(), n, n, result);
            return result;
        }
        
        private void backtrack(StringBuilder current, int left, int right, List<String> result) {
            // 终止条件：字符串长度达到 2n
            if (left == 0 && right == 0) {
                result.add(current.toString());
                return;
            }
            
            // 添加左括号
            if (left > 0) {
                current.append('(');
                backtrack(current, left - 1, right, result);
                current.deleteCharAt(current.length() - 1); // 回溯
            }
            
            // 添加右括号（需确保右括号不多于左括号）
            if (right > left) {
                current.append(')');
                backtrack(current, left, right - 1, result);
                current.deleteCharAt(current.length() - 1); // 回溯
            }
        }
    }
}
```

---

### 代码解析
1. **Solution 类**：
    - `generateParenthesis`：主函数，初始化结果列表和 `StringBuilder`，调用回溯函数。
    - `backtrack`：回溯函数，参数包括：
        - `current`：当前构建的括号字符串（`StringBuilder`）。
        - `left`：剩余左括号数量。
        - `right`：剩余右括号数量。
        - `result`：存储所有有效组合。
    - 终止条件：当 `left == 0` 且 `right == 0`，字符串长度为 \( 2n \)，加入结果。
    - 递归选择：
        - 如果 `left > 0`，添加左括号，递归调用。
        - 如果 `right > left`，添加右括号，递归调用（确保有效性）。
    - 回溯：使用 `current.deleteCharAt` 移除最后添加的字符。
2. **main 函数**：
    - 测试用例：
        - \( n = 3 \)：常规情况，生成5个有效组合。
        - \( n = 1 \)：简单情况，生成 `["()"]`。
        - \( n = 0 \)：边界情况，返回空列表。
    - 输出输入 \( n \) 和结果列表。

---

### 输出示例
运行上述代码，输出如下：

```
Test Case: n = 3
Result: [((())), (()()), (())(), ()(()), ()()()]

Test Case: n = 1
Result: [()]

Test Case: n = 0
Result: []
```

---

### 其他算法
虽然回溯法是最自然和高效的解法，也可以考虑以下替代方法，但通常效率较低或复杂：


#### 2. BFS/队列法
- **思路**：
    - 使用队列存储中间状态（当前字符串、左括号数、右括号数）。
    - 每次从队列中取状态，扩展为新状态（加 `(` 或 `)`），直到字符串长度为 \( 2n \)。
- **复杂度**：
    - 时间：\( O(\frac{4^n}{\sqrt{n}}) \)，空间：\( O(\frac{4^n}{\sqrt{n}}) \)。
- **缺点**：
    - 队列存储大量中间状态，空间效率低。
    - 实现复杂，逻辑不如回溯清晰。

#### 比较
| 算法       | 时间复杂度              | 空间复杂度       | 优点                          | 缺点                          |
|------------|-------------------------|------------------|------------------------------|------------------------------|
| 回溯法     | \( O(\frac{4^n}{\sqrt{n}}) \) | \( O(n) \)       | 直观，代码简洁，空间效率高    | 需理解回溯概念                |
| 动态规划   | \( O(\frac{4^n}{\sqrt{n}}) \) | \( O(\frac{4^n}{\sqrt{n}}) \) | 可扩展到复杂变种             | 空间开销大，实现复杂          |
| BFS/队列   | \( O(\frac{4^n}{\sqrt{n}}) \) | \( O(\frac{4^n}{\sqrt{n}}) \) | 非递归，适合特定场景          | 空间效率低，代码复杂          |

**推荐**：回溯法是最佳选择，代码简洁、效率高、易于理解。

---

### 优化与注意事项
1. **优化**：
    - 使用 `StringBuilder` 替代字符串拼接，降低时间开销。
    - 约束 `right > left` 确保括号有效，减少无效分支。
2. **边界处理**：
    - \( n = 0 \) 返回空列表。
    - 确保输入 \( n \geq 0 \)，否则抛出异常（本题约束 \( n \geq 0 \)）。
3. **扩展**：
    - 可修改代码支持生成带其他字符的组合（如 `{}` 或 `[]`）。
    - 可统计组合数量（卡塔兰数）而不生成具体组合。

如果需要其他语言实现、更多测试用例、或进一步优化，请告诉我！
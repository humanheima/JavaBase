# LeetCode 17. 电话号码的字母组合（Letter Combinations of a Phone Number）解题文档

## 一、题目描述

给定一个仅包含数字 `2-9` 的字符串，返回所有它能表示的字母组合。答案可以按**任意顺序**返回。

数字到字母的映射与电话按键相同（注意 `1` 不对应任何字母）：

| 数字 | 字母 | 数字 | 字母 |
|------|------|------|------|
| 2 | abc | 6 | mno |
| 3 | def | 7 | pqrs |
| 4 | ghi | 8 | tuv |
| 5 | jkl | 9 | wxyz |

**示例：**

| 输入 | 输出 | 说明 |
|------|------|------|
| `"23"` | `["ad","ae","af","bd","be","bf","cd","ce","cf"]` | 2→abc，3→def 的笛卡尔积 |
| `""` | `[]` | 空串没有任何组合 |
| `"2"` | `["a","b","c"]` | 单个数字 |

**提示：**
- `0 <= digits.length <= 4`
- `digits[i]` 是 `['2','9']` 之间的数字。

**链接：** https://leetcode.cn/problems/letter-combinations-of-a-phone-number/

---

## 二、核心观察 / 关键点

1. **本质是笛卡尔积**：每个数字贡献一组候选字母，最终结果是各组字母「每组各取一个」的所有组合。`"23"` 就是 `{a,b,c} × {d,e,f}`，共 `3×3=9` 个。
2. **用映射表把数字→字母**：`MAPPING[d]` 存数字 `d` 对应的字母串，数组下标直接对齐数字 `0-9`，下标 0、1 留空。取字母用 `MAPPING[digits.charAt(i) - '0']`。
3. **空串要特判**：`digits` 为空时应返回**空列表 `[]`**，而不是含一个空串的 `[""]`。若不特判，回溯会把空串当成一个合法组合加入。
4. **组合数量级**：每个数字最多 4 个字母，长度 `n`，组合总数最多 `4^n`。`n <= 4`，最多 `256` 个，规模很小。

---

## 三、解法

### 解法一：回溯 / DFS（推荐，最贴合问题递归本质）

**思路：**
1. 用 `index` 表示当前在处理 `digits` 的第几位，`current` 是已经拼好的前缀。
2. 当 `index == digits.length()` 时，说明每一位都选了字母，`current` 是一个完整组合，加入结果。
3. 否则取出第 `index` 位数字对应的字母串，**逐个字母**：选择（append）→ 递归处理下一位 → 撤销选择（deleteCharAt）。
4. 「撤销选择」是回溯的核心：让 `current` 恢复到进入循环前的状态，以便尝试同一位上的下一个字母。

**关键代码片段：**（与源文件 `Code17.java` 一致）

```java
private void backtrack(String digits, int index, StringBuilder current, List<String> result) {
    if (index == digits.length()) {       // 选满每一位 -> 一个完整组合
        result.add(current.toString());
        return;
    }
    String letters = MAPPING[digits.charAt(index) - '0'];
    for (char c : letters.toCharArray()) {
        current.append(c);                                // 选择
        backtrack(digits, index + 1, current, result);    // 递归下一位
        current.deleteCharAt(current.length() - 1);       // 撤销选择（回溯）
    }
}
```

- **时间复杂度：** O(4ⁿ · n)。最多 `4ⁿ` 个组合，每个组合拼成字符串需 O(n)。
- **空间复杂度：** O(n)（递归栈深度 + `StringBuilder`，不含结果集）。
- **优点：** 直观贴合「逐位选择」的递归结构，空间开销小（只维护一条路径）。
- **缺点：** 需要理解回溯的「选择 / 撤销」配对，初学者易漏掉撤销那一步。

---

### 解法二：队列迭代 / BFS（无递归，易理解）

**思路：**
1. 队列初始化为只含一个空串 `[""]`。
2. 逐个处理 `digits` 的每个数字：取出队列中**当前层**的每个组合（先记录 `size`，只处理这么多个），给它追加当前数字的每个字母，生成新组合再入队。
3. 处理完所有数字后，队列里就是全部组合。

**关键代码片段：**（与源文件 `Code17.java#letterCombinations2` 一致）

```java
LinkedList<String> queue = new LinkedList<>();
queue.offer("");
for (char digit : digits.toCharArray()) {
    String letters = MAPPING[digit - '0'];
    int size = queue.size();          // 关键：只处理上一层留下的组合
    for (int i = 0; i < size; i++) {
        String current = queue.poll();
        for (char c : letters.toCharArray()) {
            queue.offer(current + c);
        }
    }
}
```

- **时间复杂度：** O(4ⁿ · n)。
- **空间复杂度：** O(4ⁿ · n)，队列要同时存放一整层的中间组合。
- **优点：** 没有递归，逻辑像「层层扩展」，对不熟悉回溯的人更直观。
- **缺点：** 队列要存中间结果，空间占用比回溯大；`size` 必须先存快照，否则会把本层新生成的组合也卷进来。

---

## 四、解法对比表

| 解法 | 核心思路 | 时间 | 空间 | 优点 | 缺点 |
|------|----------|------|------|------|------|
| 一、回溯 DFS | 逐位选择 + 撤销 | O(4ⁿ·n) | O(n) | 贴合递归本质、省空间 | 需理解回溯配对 |
| 二、队列 BFS | 逐数字层层扩展 | O(4ⁿ·n) | O(4ⁿ·n) | 无递归、直观 | 存中间结果、`size` 易写错 |

---

## 五、易错点总结

1. **空串特判**：`digits` 为空返回 `[]`。漏掉会得到 `[""]`（回溯把空串当成完整组合），不符合预期。
2. **映射下标对齐**：`MAPPING` 的下标 0、1 要留空串占位，这样 `MAPPING[digit - '0']` 才能直接对齐数字 2-9。
3. **回溯必须撤销**：`append` 之后递归完一定要 `deleteCharAt` 还原，否则前缀会越拼越长、组合全错。
4. **队列法的 `size` 快照**：循环前先 `int size = queue.size()`，只处理当前层；若直接用 `queue.size()` 作循环条件，会把本层新入队的组合也处理，导致死循环 / 结果爆炸。
5. **顺序无所谓**：题目允许任意顺序返回，所以对照测试时应排序后再比较，而不是要求与某个固定顺序逐字相等。

---

## 六、关联代码

- 解法一（回溯）+ 解法二（队列）：`Code17.java`
- 两种解法对照测试：`LetterCombinationsTest.java`

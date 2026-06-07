# LeetCode 6. Z 字形变换

> https://leetcode.cn/problems/zigzag-conversion/

## 一、问题描述

将一个给定字符串 `s` 根据给定的行数 `numRows`，以**从上往下、从左到右**进行 Z 字形排列，
然后**逐行**读取，拼接成一个新字符串返回。

以 `"PAYPALISHIRING"`、`numRows = 3` 为例，排列如下：

```
P   A   H   N
A P L S I I G
Y   I   R
```

逐行读取得到：`"PAHNAPLSIIGYIR"`。

| 输入 | 输出 | 说明 |
| --- | --- | --- |
| `s = "PAYPALISHIRING", numRows = 3` | `"PAHNAPLSIIGYIR"` | 见上图 |
| `s = "PAYPALISHIRING", numRows = 4` | `"PINALSIGYAHRPI"` | 4 行排列 |
| `s = "A", numRows = 1` | `"A"` | 只有一行，原样返回 |

`numRows = 4` 时的排列：

```
P     I    N
A   L S  I G
Y A   H R
P     I
```

## 二、读懂 Z 字形的"走向"

所谓 Z 字形，字符的填充顺序其实是一个**来回往复**的过程：

1. 先从第 `0` 行**竖直向下**走到第 `numRows-1` 行（竖线部分）；
2. 到底后**斜向上**走回第 `0` 行（斜线部分，每步行号减 1）；
3. 回到顶部再次向下……如此循环，直到字符用完。

竖直向下时行号 `0 → 1 → 2 → ... → numRows-1`，
斜向上时行号 `numRows-1 → ... → 1 → 0`，
**只有到达第一行或最后一行时才掉头**。

> 关键洞察：我们不需要真的去构造那个二维矩阵，只要知道**每个字符落在第几行**，
> 把同一行的字符按顺序收集起来，最后按行拼接即可。

### 名字的"坑"：其实更像"倒 N 字形"

中文题名叫"Z 字形"容易让人误解，实际笔画并不像字母 **Z**：

- 字母 **Z** 的笔画是 横(`─`) → 斜(`╱`) → 横(`─`)，核心是**横向**来回；
- 本题字符的填充轨迹是 竖直向下(`│`) → 斜向右上(`╱`) → 竖直向下 → 斜向右上……
  即 `│╱│╱`，核心是**竖向**下笔加斜线往上勾。

把"竖线 + 斜线 + 竖线"连起来看是 `│╱│`：字母 **N** 的中间斜线是 `╲`（左上→右下），
而本题的斜线是 `╱`（左下→右上），恰好是 N 的镜像——所以说成 **"倒 N 字形"** 反而更贴切。

> 英文原题 **Zigzag**（锯齿形 / 来回往复）是泛指，没问题；只是中文直译成"Z 字形"
> 容易让人按字母 Z 的形状去想，实际更接近重复的"倒 N"。理解填充走向即可，不必纠结名字。

## 三、核心思想：按行模拟

用 `numRows` 个 `StringBuilder` 分别代表每一行。
维护两个变量沿着字符串遍历：

- `curRow`：当前字符应该放进哪一行；
- `goingDown`：当前的移动方向，`true` 表示向下、`false` 表示向上。

遍历每个字符时：

1. 把字符追加到 `rows[curRow]`；
2. 若 `curRow` 已经到达**第一行**（`0`）或**最后一行**（`numRows-1`），翻转方向；
3. 按方向移动行号：向下 `curRow++`，向上 `curRow--`。

遍历结束后，把所有行的 `StringBuilder` 依次拼接，就是答案。

## 四、边界处理

```java
if (numRows == 1 || s.length() <= numRows) {
    return s;
}
```

- `numRows == 1`：只有一行，永远不会拐弯，结果就是原字符串。
  若不特判，`curRow == 0 && curRow == numRows-1` 同时成立会让方向反复翻转，逻辑虽仍正确但没必要。
- `s.length() <= numRows`：字符数还填不满第一列，所有字符各占一行的第一格，
  逐行读取顺序与原串完全一致，可直接返回，省去构造开销。

## 五、执行流程示例（`"PAYPALISHIRING"`, numRows = 3）

| 字符 | 放入行 | 到达边界？ | 翻转后方向 | 下一个 curRow |
| --- | --- | --- | --- | --- |
| P | 0 | 是（第一行） | 向下 | 1 |
| A | 1 | 否 | 向下 | 2 |
| Y | 2 | 是（最后一行） | 向上 | 1 |
| P | 1 | 否 | 向上 | 0 |
| A | 0 | 是（第一行） | 向下 | 1 |
| L | 1 | 否 | 向下 | 2 |
| I | 2 | 是（最后一行） | 向上 | 1 |
| ... | ... | ... | ... | ... |

最终三行分别为：

```
行 0: P A H N
行 1: A P L S I I G
行 2: Y I R
```

拼接得 `"PAHNAPLSIIGYIR"`。

## 六、代码

```java
public String convert(String s, int numRows) {
    // 只有一行（或填不满一列）时无需变换，直接返回
    if (numRows == 1 || s.length() <= numRows) {
        return s;
    }

    // 每一行对应一个 StringBuilder
    StringBuilder[] rows = new StringBuilder[numRows];
    for (int i = 0; i < numRows; i++) {
        rows[i] = new StringBuilder();
    }

    int curRow = 0;            // 当前所在行
    boolean goingDown = false; // 移动方向，true 表示向下

    for (char c : s.toCharArray()) {
        rows[curRow].append(c);
        // 到达第一行或最后一行时改变方向
        if (curRow == 0 || curRow == numRows - 1) {
            goingDown = !goingDown;
        }
        curRow += goingDown ? 1 : -1;
    }

    // 按行拼接得到结果
    StringBuilder result = new StringBuilder();
    for (StringBuilder row : rows) {
        result.append(row);
    }
    return result.toString();
}
```

## 七、复杂度分析

- **时间复杂度** `O(n)`：每个字符只被访问一次（追加），最后拼接也是 `O(n)`，`n = s.length()`。
- **空间复杂度** `O(n)`：`numRows` 个 `StringBuilder` 总共存储了全部 `n` 个字符。

## 八、其他解法对比

| 解法 | 时间 | 空间 | 特点 |
| --- | --- | --- | --- |
| **按行模拟（本文）** | `O(n)` | `O(n)` | 用指针来回扫，思路最直观 |
| 按行访问规律（找下标公式） | `O(n)` | `O(1)`（不计输出） | 直接用周期公式算每行字符下标，省去中间 buffer，但公式较绕 |

> 规律法：一个完整周期长度 `cycleLen = 2 * numRows - 2`。
> 第一行和最后一行的字符下标按 `cycleLen` 等差；中间第 `i` 行除了 `j + i` 还多一个 `j + cycleLen - i`（斜线上的字符）。

## 九、小结

Z 字形变换的套路：

1. 看穿表象——字符填充就是**行号在 `[0, numRows-1]` 之间来回往复**；
2. 不必真的建二维矩阵，只需用 `numRows` 个缓冲区**按行收集**字符；
3. 用 `goingDown` 标记方向，**仅在触顶 / 触底时翻转**；
4. 别忘了特判 `numRows == 1`，否则方向翻转逻辑会空转。

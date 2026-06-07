# LeetCode 16. 最接近的三数之和（3Sum Closest）解题文档

## 一、题目描述

给你一个长度为 `n` 的整数数组 `nums` 和一个目标值 `target`。请你从 `nums` 中选出三个整数，使它们的**和与 `target` 最接近**。返回这三个数的和。

假定每组输入只存在恰好一个解。

**示例：**

| 输入 | 输出 | 说明 |
|------|------|------|
| `nums = [-1,2,1,-4], target = 1` | `2` | 最接近的和是 `2`（`-1 + 2 + 1`） |
| `nums = [0,0,0], target = 1` | `0` | 唯一可选的和就是 `0` |

**提示：**
- `3 <= nums.length <= 1000`
- `-1000 <= nums[i] <= 1000`
- `-10^4 <= target <= 10^4`

**链接：** https://leetcode.cn/problems/3sum-closest/

---

## 二、核心观察 / 关键点

1. **和「三数之和」是同一套骨架**：排序 + 固定一个数 + 双指针。区别在于：15 题要求和**恰好等于 0**，本题要求和**尽量接近 target**——所以不是「命中才记录」，而是**每一步都用差值更新答案**。
2. **用「绝对差」衡量接近程度**：维护 `closestSum`，只要 `|sum - target| < |closestSum - target|` 就更新。
3. **双指针的移动方向由 `sum` 与 `target` 的大小决定**：
   - `sum < target`：和偏小，`left++`（排序后右移变大）。
   - `sum > target`：和偏大，`right--`（左移变小）。
   - `sum == target`：差值为 0，已是理论最优，**直接返回**。
4. **不需要去重**：本题只返回一个整数和，重复三元组不影响答案；去重只是用于**剪枝加速**，不是正确性必需。

---

## 三、解法

### 解法一：排序 + 双指针（基础版，推荐）

**思路：**
1. 排序数组，用前三个数初始化 `closestSum`。
2. 固定第一个数 `nums[i]`，在 `[i+1, n-1]` 上用左右指针 `left`、`right` 夹逼。
3. 每次算出 `sum`，若它比当前答案更接近 `target` 就更新 `closestSum`。
4. 按 `sum` 与 `target` 的大小移动指针；命中 `target` 立即返回。

**关键代码片段：**（与源文件 `LeetCode16.java` 一致）

```java
Arrays.sort(nums);
int closestSum = nums[0] + nums[1] + nums[2];
for (int i = 0; i < nums.length - 2; i++) {
    int left = i + 1, right = nums.length - 1;
    while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
            closestSum = sum;        // 更接近就更新
        }
        if (sum == target) {
            return sum;              // 差值为 0，最优，直接返回
        } else if (sum < target) {
            left++;                  // 和偏小，左指针右移
        } else {
            right--;                 // 和偏大，右指针左移
        }
    }
}
return closestSum;
```

- **时间复杂度：** O(n²)。外层 O(n)，内层双指针 O(n)；排序 O(n log n) 被吞没。
- **空间复杂度：** O(1)（不含排序栈开销）。
- **优点：** 简洁、标准、一遍扫描即可。
- **缺点：** 没有提前剪枝，最坏情况要把所有 i 跑满。

---

### 解法二：排序 + 双指针 + 剪枝（加速版）

**思路：** 在解法一基础上，对每个固定的 `i` 先做两次边界判断，把不可能更优的整段直接跳过。

1. **下界剪枝**：`minSum = nums[i] + nums[i+1] + nums[i+2]`（i 配最小两个数）。若 `minSum > target`，则后续所有组合都 `> target` 且更远，先用 `minSum` 尝试更新答案后直接 `break`。
2. **上界剪枝**：`maxSum = nums[i] + nums[n-1] + nums[n-2]`（i 配最大两个数）。若 `maxSum < target`，则这一轮 i 的所有组合都 `< target`，先用 `maxSum` 尝试更新后 `continue`，跳过内层双指针。
3. **跳过重复固定数**：`nums[i] == nums[i-1]` 时 `continue`（纯加速，不影响答案）。

**关键代码片段：**

```java
for (int i = 0; i < n - 2; i++) {
    if (i > 0 && nums[i] == nums[i - 1]) continue;       // 跳过重复固定数

    int minSum = nums[i] + nums[i + 1] + nums[i + 2];
    if (minSum > target) {                               // 下界已超过 target
        if (Math.abs(minSum - target) < Math.abs(closestSum - target)) closestSum = minSum;
        break;
    }
    int maxSum = nums[i] + nums[n - 1] + nums[n - 2];
    if (maxSum < target) {                               // 上界仍不到 target
        if (Math.abs(maxSum - target) < Math.abs(closestSum - target)) closestSum = maxSum;
        continue;
    }
    // ……与解法一相同的双指针内层……
}
```

- **时间复杂度：** 最坏仍 O(n²)，但实际常数显著更小（很多 i 被整段跳过）。
- **空间复杂度：** O(1)。
- **优点：** 在数据偏向一侧时（如全正、全负）能大幅减少无效遍历。
- **缺点：** 代码更长，剪枝时**别忘了先用边界值更新答案**再跳过，否则可能错过最优解。

---

### 解法三：暴力三重循环（仅作对照基准）

**思路：** 三层循环枚举所有三元组，逐个用差值更新 `closestSum`。

- **时间复杂度：** O(n³)。
- **空间复杂度：** O(1)。
- **优点：** 思路最直白，适合作为正确性基准交叉验证双指针。
- **缺点：** n 接近 1000 时约 1.6×10⁸ 次，能过但偏慢，不是首选。

---

## 四、解法对比表

| 解法 | 核心思路 | 时间 | 空间 | 优点 | 缺点 |
|------|----------|------|------|------|------|
| 一、双指针基础版 | 固定一数 + 左右夹逼 + 差值更新 | O(n²) | O(1) | 简洁标准 | 无剪枝 |
| 二、双指针 + 剪枝 | 在基础版上加上/下界剪枝 | O(n²) | O(1) | 实测更快 | 代码更长，易漏更新 |
| 三、暴力枚举 | 三重循环 | O(n³) | O(1) | 直白、作基准 | 偏慢 |

---

## 五、易错点总结

1. **初始化 `closestSum` 不能用 0 或随便一个数**：必须用一个**真实存在的三元组和**（如前三个数之和），否则可能返回一个根本不存在的和。
2. **更新答案用绝对差**：`Math.abs(sum - target) < Math.abs(closestSum - target)`，别漏 `abs`，也别和 15 题一样只在 `sum == target` 时才记录。
3. **剪枝时先更新再跳过**：解法二里 `break` / `continue` 之前要先拿边界值 `minSum` / `maxSum` 尝试更新答案，否则可能把最优解剪掉。
4. **命中即返回**：`sum == target` 时差值为 0，是理论最优，直接 `return` 能省下后续遍历。
5. **下标范围**：外层到 `n - 2`，且 `i+1`、`i+2`、`n-1`、`n-2` 在 `n >= 3` 时才安全（题目保证 `n >= 3`）。
6. **去重非必需**：本题不要求返回三元组，去重只为加速，不要误以为漏了去重就会答案错误。

---

## 六、关联代码

- 解法一（双指针基础版）：`LeetCode16.java`
- 解法一 + 二 + 三 对照测试：`ThreeSumClosestTest.java`

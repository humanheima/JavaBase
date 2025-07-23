



### 问题描述
给定一个有序数组，要求最多保留每个元素重复 `K` 次（即每个元素出现次数超过 `K` 次的部分被移除），返回处理后的数组长度，并修改原数组。假设数组已按非降序排序，`K` 是一个正整数。

**示例**：
- 输入：`nums = [1,1,1,2,2,3], K = 2`
- 输出：`5`
- 修改后的数组：`[1,1,2,2,3]`
- 说明：1 最多保留 2 次，2 最多保留 2 次，3 保留 1 次，结果长度为 5。

### 算法解析
由于数组已排序，相同的元素会相邻出现。我们可以使用以下方法解决问题：
1. **双指针法**：
  - 使用一个指针 `writeIndex` 表示结果数组的写入位置。
  - 遍历数组，跟踪当前元素及其出现次数。
  - 当某个元素的出现次数小于等于 `K` 时，将其写入结果数组；超过 `K` 次的跳过。
  - 由于数组有序，可以通过比较当前元素与前一个元素来判断是否为新元素。
2. **时间复杂度**：O(n)，其中 n 是数组长度，只需遍历一次。
3. **空间复杂度**：O(1)，原地修改数组，不需要额外空间。

### 算法步骤
1. 如果数组为空或 `K <= 0`，返回 0。
2. 初始化 `writeIndex = 1`（写入位置,第一个元素index=0，总是保留），`count = 1`（当前元素出现次数）。
3. 从索引 1 开始遍历数组：
  - 如果当前元素 `nums[i]` 等于前一个元素 `nums[i-1]`，则 `count++`。
  - 如果 `count <= K`，将当前元素写入 `nums[writeIndex]`，并递增 `writeIndex`。
  - 如果当前元素与前一个不同，重置 `count = 1`，并写入当前元素。
4. 返回 `writeIndex`，即新数组的长度。

### Java 实现
```java
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums, int k) {
        // 边界检查
        if (nums == null || nums.length == 0 || k <= 0) {
            return 0;
        }
        
        // 如果 k >= 数组长度，直接返回原长度
        if (k >= nums.length) {
            return nums.length;
        }

        // 初始化写入位置和当前元素计数
        int writeIndex = 1;
        int count = 1; // 第一个元素的计数
        
        // 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                // 相同元素，计数增加
                count++;
            } else {
                // 新元素，重置计数
                count = 1;
            }
            
            // 如果当前元素出现次数不超过 k，写入数组
            if (count <= k) {
                nums[writeIndex] = nums[i];
                writeIndex++;
            }
        }
        
        return writeIndex;
    }

    // 测试代码
    public static void main(String[] args) {
        RemoveDuplicates solution = new RemoveDuplicates();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int length = solution.removeDuplicates(nums, k);
        System.out.println("新数组长度: " + length);
        System.out.print("修改后的数组: ");
        for (int i = 0; i < length; i++) {
            System.out.print(nums[i] + " ");
        }
        // 输出: 新数组长度: 5
        // 修改后的数组: 1 1 2 2 3
    }
}
```

### 代码说明
1. **边界处理**：
  - 如果数组为 `null`、空或 `k <= 0`，返回 0。
  - 如果 `k >= nums.length`，无需修改，直接返回原长度。
2. **初始化**：
  - `writeIndex = 1`：从第二个位置开始写入（第一个元素总是保留）。
  - `count = 1`：第一个元素的初始计数。
3. **遍历与计数**：
  - 比较当前元素 `nums[i]` 与前一个 `nums[i-1]`：
    - 若相等，`count++`。
    - 若不同，重置 `count = 1`。
  - 若 `count <= k`，将当前元素写入 `nums[writeIndex]`，并递增 `writeIndex`。
4. **结果**：
  - `writeIndex` 即为新数组长度。
  - 数组前 `writeIndex` 个元素为修改后的结果。

### 示例运行
- 输入：`nums = [1,1,1,2,2,3], k = 2`
- 过程：
  - `i=0`: `nums[0]=1`, `count=1`, 保留（`writeIndex=0`, 数组不变）。
  - `i=1`: `nums[1]=1`, `count=2`, 保留，`nums[1]=1`, `writeIndex=2`。
  - `i=2`: `nums[2]=1`, `count=3`, 跳过（`count > k`）。
  - `i=3`: `nums[3]=2`, `count=1`, 保留，`nums[2]=2`, `writeIndex=3`。
  - `i=4`: `nums[4]=2`, `count=2`, 保留，`nums[3]=2`, `writeIndex=4`。
  - `i=5`: `nums[5]=3`, `count=1`, 保留，`nums[4]=3`, `writeIndex=5`。
- 输出：`5`，数组变为 `[1,1,2,2,3]`。

### 复杂度分析
- **时间复杂度**：O(n)，单次遍历数组。
- **空间复杂度**：O(1)，原地修改数组，仅使用常数额外空间。

### 扩展
- 如果需要支持非有序数组，可以先排序数组（增加 O(n log n) 时间复杂度）。
- 如果 `K = 1`，问题退化为移除所有重复元素；如果 `K = 2`，等同于保留最多两个重复元素。

这个实现高效且清晰，符合题目要求，适用于有序数组的重复元素保留场景。


我来详细解析之前提供的Java实现的代码原理。这个算法的核心是使用双指针技术在原地修改数组。下面是逐步解析：

### 代码回顾
```java
public int removeDuplicates(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    if (k >= nums.length) {
        return nums.length;
    }
    
    int writePos = 1;  // 写入位置指针
    int count = 1;     // 当前元素出现次数
    
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] == nums[i-1]) {
            count++;
            if (count <= k) {
                nums[writePos] = nums[i];
                writePos++;
            }
        } else {
            count = 1;
            nums[writePos] = nums[i];
            writePos++;
        }
    }
    
    return writePos;
}
```

### 工作原理详解

#### 1. 边界条件处理
```java
if (nums == null || nums.length == 0) {
    return 0;
}
if (k >= nums.length) {
    return nums.length;
}
```
- 如果数组为空或null，直接返回0
- 如果k大于等于数组长度，说明不需要删除任何元素，返回原长度
- 这些检查确保算法在异常输入下也能正确工作

#### 2. 核心变量
- `writePos`: 写入位置指针，表示新数组的下一个写入位置
    - 初始化为1，因为第一个元素总是保留的
- `count`: 当前元素出现的次数计数器
    - 初始化为1，因为遍历从第二个元素开始时，第一个元素已出现1次

#### 3. 主循环逻辑
```java
for (int i = 1; i < nums.length; i++) {
```
- 从索引1开始遍历（i作为读取指针）
- 因为数组是升序的，重复元素必然是连续的

#### 4. 两种情况处理
##### 情况1：当前元素与前一个元素相同
```java
if (nums[i] == nums[i-1]) {
    count++;
    if (count <= k) {
        nums[writePos] = nums[i];
        writePos++;
    }
}
```
- `count++`: 相同元素计数增加
- `if (count <= k)`: 检查是否还能保留这个元素
    - 如果计数不超过k，就写入当前位置并移动writePos
    - 如果超过k，就跳过（不写入）

##### 情况2：当前元素与前一个不同
```java
else {
    count = 1;
    nums[writePos] = nums[i];
    writePos++;
}
```
- `count = 1`: 新元素第一次出现，重置计数
- 无条件写入新元素并移动writePos

#### 5. 返回值
- `writePos`就是新数组的长度
- 因为它记录了有效元素的数量

### 示例演算
输入：`nums = [1,1,1,2,2,3], k = 2`

| i | nums[i] | count | writePos | 操作 | 数组状态 |
|---|---------|-------|----------|------|----------|
| 1 | 1       | 2     | 2        | 写入 | [1,1,1,2,2,3] |
| 2 | 1       | 3     | 2        | 跳过 | [1,1,1,2,2,3] |
| 3 | 2       | 1     | 3        | 写入 | [1,1,2,2,2,3] |
| 4 | 2       | 2     | 4        | 写入 | [1,1,2,2,2,3] |
| 5 | 3       | 1     | 5        | 写入 | [1,1,2,2,3,3] |

最终结果：[1,1,2,2,3]，长度=5

### 关键特性
1. **原地操作**: 不需要额外数组，空间复杂度O(1)
2. **单次遍历**: 时间复杂度O(n)
3. **保持顺序**: 因为是升序数组，算法保留了这个性质
4. **精确控制**: 每个元素恰好保留min(k,实际出现次数)个

### 为什么有效
- 利用了数组升序的性质，重复元素必然连续
- 双指针（i和writePos）配合计数器完美解决了问题
- 当count超过k时自动跳过多余元素，保证了要求

这个实现优雅地解决了“最多保留k个重复元素”的需求，是双指针技术在数组问题中的典型应用。
### 插入元素后，调用 `siftUpComparable` 方法

### 示例
假设 `PriorityQueue` 当前的数组为 `queue = [2, 4, 3, 7, 5]`，表示以下最小堆：
```
       2
      / \
     4   3
    / \
   7   5
```
现在插入新元素 `x = 1`，初始索引 `k = 5`（插入到数组末尾，`queue = [2, 4, 3, 7, 5, 1]`）。

1. **初始状态**：
    - `k = 5`，`x = 1`，`queue = [2, 4, 3, 7, 5, 1]`。
    - 计算父节点：`parent = (5 - 1) >>> 1 = 4 >>> 1 = 2`。
    - 父节点值为 `queue[2] = 3`。

2. **第一次比较**：
    - `key.compareTo(3)`：`1 < 3`，因此需要调整。
    - 将父节点 `3` 下移到 `queue[5]`：`queue = [2, 4, 3, 7, 5, 3]`。
    - 更新 `k = 2`。

```
       2
     /   \
    4     3
   / \   / 
  7   5  3
```

3. **第二次比较**：
    - 新父节点：`parent = (2 - 1) >>> 1 = 1 >>> 1 = 0`。
    - 父节点值为 `queue[0] = 2`。
    - `key.compareTo(2)`：`1 < 2`，需要调整。
    - 将父节点 `2` 下移到 `queue[2]`：`queue = [2, 4, 2, 7, 5, 3]`。
    - 更新 `k = 0`。

```
       2
     /   \
    4     2
   / \   / 
  7   5  3
```


4. **结束循环**：
    - `k = 0`，停止循环（到达根节点）。
    - 将 `key = 1` 放入 `queue[0]`：`queue = [1, 4, 2, 7, 5, 3]`。

5. **最终堆**：

```
       1
     /   \
    4     2
   / \   / 
  7   5  3
```


- 最小堆性质保持：每个父节点（1, 4, 2）都小于或等于其子节点。

### 时间复杂度
- **时间复杂度**：O(log n)，其中 `n` 是堆中元素数量。向上调整最多遍历树的高度（`log n` 层）。
- **空间复杂度**：O(1)，仅使用常数额外空间。


### 总结
`siftUpComparable` 是优先级队列插入元素时的核心调整逻辑，通过不断比较新元素与其父节点，将新元素上移到合适位置，确保最小堆性质。它的实现高效且简洁，适合动态维护堆结构。如果需要进一步分析（如 `siftDown` 或其他方法），请告诉我！

# 删除元素后调用 `siftDownComparable` 方法
```java
private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>)x;
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size && ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
                //注释1处，如果左子节点大于右子节点，就取右子节点准备替换
                c = queue[child = right];
            if (key.compareTo((E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }
    
```
### 初始堆
- **树形结构**：
  ```
         2
        / \
       4   3
      / \
     7   5
  ```
- **数组表示**：`queue = [2, 4, 3, 7, 5]`，`size = 5`。
- **说明**：这是一个合法的最小堆，每个父节点的值小于或等于其子节点。

### 示例场景
假设我们删除根节点（`2`），用堆尾元素 `5` 替换根节点，得到 `queue = [2, 4, 3, 7]`，`size = 4`，并调用 `siftDownComparable(0, 5)` 来调整堆。我们将逐步跟踪 `siftDownComparable` 方法的执行过程，并为每次循环后提供堆的数组表示和树形图示。

### 方法代码
```java
private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>) x;
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        int child = (k << 1) + 1; // assume left child is least
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
        if (key.compareTo((E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = key;
}
```

### 逐步分析与图示

#### 初始状态
- **输入**：`k = 0`, `x = 5`, `queue = [2, 4, 3, 7]`, `size = 4`。
- **堆状态**：
    - 树形图示：
      ```
             2
            / \
           4   3
          /
         7
      ```
- **说明**：根节点 `5` 用堆尾元素替换，破坏了最小堆性质（5 > 4 和 5 > 3），需要向下调整。

#### 第一次循环

2. **堆状态**：
    - 数组：`[3, 4, 3, 7]`
    - 树形图示：
      ```
            3
           / \
          4   3
         /
        7
      ```
    - **说明**：右子节点 `3` 上移到根节点，`k` 更新为 `2`，准备检查下一层。当前堆是中间状态，`key = 5` 尚未放入。

#### 循环退出
- **检查**：`k = 2 >= half = 2`，循环条件 `k < half` 不满足，退出循环。
- **执行**：`queue[k] = key`，即 `queue[2] = 5`。
- **最终堆状态**：
    - 数组：`[3, 4, 5, 7]`
    - 树形图示：
      ```
             3
            / \
           4   5
          /
         7
      ```
    - **说明**：`key = 5` 放入索引 `2`，调整完成，堆恢复最小堆性质。

### 验证最小堆性质
- 根节点 `3` 小于子节点 `4` 和 `5`。
- 节点 `4` 小于子节点 `7`。
- 节点 `5` 无子节点。
- 满足最小堆性质：每个父节点小于或等于其子节点。

### 时间复杂度
- **时间复杂度**：O(log n)，调整最多遍历树高（`log n` 层）。这里只执行一次循环，实际复杂度为 O(1)。
- **空间复杂度**：O(1)，仅用常数额外空间。

### 注意事项
- **循环退出**：`k < half` 确保只对非叶子节点调整。`k = 2 >= half = 2` 时循环退出，因此只有一次循环。
- **越界检查**：`right < size` 防止访问无效索引，`k < half` 保证 `child < size`。
- **位运算**：`(k << 1) + 1` 和 `size >>> 1` 优化了索引计算，效率高于乘除法。
- **最小堆**：方法专为最小堆设计，若需最大堆，需修改比较逻辑（如将 `key.compareTo((E) c) <= 0` 改为 `>= 0`）。

### 总结
`siftDownComparable` 方法通过将 `x = 5` 与较小子节点比较并下移，从初始状态 `[5, 4, 3, 7]` 调整到最终状态 `[3, 4, 5, 7]`，恢复最小堆性质。过程只有一次循环，每次循环后的堆状态通过树形图示展示。如果需要其他场景分析（如不同初始堆或 `siftUpComparable`），请告诉我！


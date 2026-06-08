# LeetCode 23. 合并 K 个升序链表（Merge k Sorted Lists）解题文档

## 一、题目描述

给你一个链表数组，每个链表都已经按 **升序** 排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。

**示例：**

| 输入 | 输出 | 说明 |
|------|------|------|
| `[[1,4,5],[1,3,4],[2,6]]` | `[1,1,2,3,4,4,5,6]` | 合并为一条有序链表 |
| `[]` | `[]` | 链表数组为空 |
| `[[]]` | `[]` | 数组里只有一条空链表 |

**提示：** `k == lists.length`，`0 <= k <= 10^4`；单条链表长度 `0 ~ 500`；`-10^4 <= val <= 10^4`；所有结点总数 `≤ 10^4`。

**链接：** https://leetcode.cn/problems/merge-k-sorted-lists/

---

## 二、核心观察

1. **本题是「合并两个有序链表（LeetCode 21）」的推广**：只要有一个稳健的「合并两条」子过程，问题就归结为「如何组织这 k 条链表的合并顺序」，不同组织方式带来不同复杂度。
2. **每一步只需关心 k 个头结点中的最小值**：所有链表内部已有序，全局下一个最小结点必然是某条链表当前头结点之一 —— 这正是**最小堆**的用武之地。
3. **合并顺序决定效率**：逐一顺序合并会让前面的长结果被反复扫描（`O(Nk)`）；改成**两两分治**可把重复扫描降到 `O(N log k)`。
4. **边界要先挡住**：`lists` 为 `null`/空数组、数组中含 `null`（空链表）都要正确处理，否则会 NPE。

记 `N` 为所有结点总数，`k` 为链表条数。

---

## 三、解法

### 解法一：最小堆 / 优先队列（推荐）

**思路：**

1. 把每条非空链表的**头结点**放入小顶堆（按 `val` 排序）。
2. 循环弹出堆顶（当前全局最小结点），接到结果链表末尾。
3. 若该结点有 `next`，把 `next` 入堆，维持「堆中是各链表当前最前沿」的不变量。
4. 堆空时合并完成。堆中元素始终 `≤ k` 个。

**关键代码：**

```java
public ListNode mergeKListsByHeap(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;
    PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
    for (ListNode node : lists) {
        if (node != null) heap.offer(node);
    }
    ListNode dummy = new ListNode(0), tail = dummy;
    while (!heap.isEmpty()) {
        ListNode min = heap.poll();
        tail.next = min;
        tail = tail.next;
        if (min.next != null) heap.offer(min.next);
    }
    tail.next = null; // 断开尾部，避免悬挂指针
    return dummy.next;
}
```

- **时间复杂度：** `O(N log k)` —— 每个结点进出堆各一次，单次堆操作 `O(log k)`。
- **空间复杂度：** `O(k)` —— 堆中最多 `k` 个结点（结果链表为原地接入，不计）。
- **优点：** 实现直观、复杂度最优、对 k 很大时尤其稳。
- **缺点：** 需要额外的堆结构；比较器 `a.val - b.val` 在数值跨度大时有溢出风险（本题值域小，安全）。

---

### 解法二：分治 / 两两归并（推荐）

**思路：**

1. 把 `k` 条链表数组从中间一分为二，递归合并左半与右半。
2. 递归到只剩一条时直接返回。
3. 回溯时用「合并两条有序链表」把左右结果合并。
4. 每一层归并处理的总结点数都是 `N`，共 `log k` 层。

**关键代码：**

```java
public ListNode mergeKListsByDivide(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;
    return merge(lists, 0, lists.length - 1);
}

private ListNode merge(ListNode[] lists, int lo, int hi) {
    if (lo == hi) return lists[lo];
    int mid = lo + (hi - lo) / 2;
    ListNode left = merge(lists, lo, mid);
    ListNode right = merge(lists, mid + 1, hi);
    return mergeTwoLists(left, right);
}
```

- **时间复杂度：** `O(N log k)` —— 共 `log k` 层，每层合并代价合计 `O(N)`。
- **空间复杂度：** `O(log k)` —— 递归栈深度。
- **优点：** 复杂度与堆法相同，不需要额外数据结构，常数更小。
- **缺点：** 递归思路对新手稍绕；`mid` 用 `lo + (hi-lo)/2` 防溢出。

---

### 解法三：逐一顺序合并（基准做法）

**思路：** 维护一个累积结果 `result`，从头到尾依次与第 `i` 条链表合并。

**关键代码：**

```java
public ListNode mergeKListsBySequential(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;
    ListNode result = null;
    for (ListNode node : lists) {
        result = mergeTwoLists(result, node);
    }
    return result;
}
```

- **时间复杂度：** `O(N k)` —— 第 `i` 次合并要扫描已累积的约 `i·(N/k)` 个结点，等差求和得 `O(Nk)`。
- **空间复杂度：** `O(1)`。
- **优点：** 最直观，代码最短，复用合并两条即可。
- **缺点：** 累积结果被反复扫描，k 大时明显慢，不适合大规模。

> 三种解法共用同一个「合并两条有序链表」子过程：

```java
private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0), tail = dummy;
    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) { tail.next = l1; l1 = l1.next; }
        else                  { tail.next = l2; l2 = l2.next; }
        tail = tail.next;
    }
    tail.next = (l1 != null) ? l1 : l2;
    return dummy.next;
}
```

---

## 四、解法对比表

| 解法 | 核心思路 | 时间 | 空间 | 优点 | 缺点 |
|------|----------|------|------|------|------|
| 一、最小堆 | 堆维护 k 个头结点取最小 | O(N log k) | O(k) | 直观、复杂度最优 | 需额外堆 |
| 二、分治归并 | 两两合并，logk 轮 | O(N log k) | O(log k) | 无额外结构、常数小 | 递归稍绕 |
| 三、逐一合并 | 累积结果依次合并 | O(N k) | O(1) | 最简单 | k 大时慢 |

**推荐：** 最小堆或分治归并，二者复杂度同为 `O(N log k)`；面试中分治写法常被认为更优雅，堆法更易解释。

---

## 五、易错点总结

1. **跳过空链表**：`lists` 中可能含 `null`（空链表），入堆前要判 `null`，否则 `NPE`。`[[]]`、`[[], [1], []]` 是高频测试点。
2. **整体空输入**：`lists == null` 或 `lists.length == 0` 要直接返回 `null`。
3. **尾指针悬挂**：堆法最后一个接入的结点其 `next` 可能仍指向旧链表残留，必须 `tail.next = null` 断尾，否则可能形成多余尾巴甚至环。
4. **比较器溢出**：`(a, b) -> a.val - b.val` 在 `val` 跨越正负极值时可能溢出，稳妥写法用 `Integer.compare(a.val, b.val)`（本题值域 `±10^4` 不会溢出）。
5. **mid 计算**：分治用 `lo + (hi - lo) / 2`，避免 `(lo+hi)` 溢出。
6. **复杂度别记错**：逐一合并是 `O(Nk)` 而非 `O(N log k)`，这正是为什么要用堆/分治。

---

## 六、关联代码

- 三种解法 + 交叉验证：`MergeKListsTest.java`
- 复用的「合并两条有序链表」：参见 `code21/`（LeetCode 21）。

---

## 七、验证输出

运行 `MergeKListsTest.main` 实测（三解法交叉验证，全部 ✓）：

```
最小堆:[1, 1, 2, 3, 4, 4, 5, 6]   分治:[1, 1, 2, 3, 4, 4, 5, 6]   逐一:[1, 1, 2, 3, 4, 4, 5, 6]   期望:[1, 1, 2, 3, 4, 4, 5, 6]   ✓
最小堆:[]                         分治:[]                         逐一:[]                         期望:[]                         ✓
最小堆:[]                         分治:[]                         逐一:[]                         期望:[]                         ✓
最小堆:[1]                        分治:[1]                        逐一:[1]                        期望:[1]                        ✓
最小堆:[-3, -2, -1, 0, 1, 1, 2]   分治:[-3, -2, -1, 0, 1, 1, 2]   逐一:[-3, -2, -1, 0, 1, 1, 2]   期望:[-3, -2, -1, 0, 1, 1, 2]   ✓
最小堆:[1, 2, 3, 4, 5]            分治:[1, 2, 3, 4, 5]            逐一:[1, 2, 3, 4, 5]            期望:[1, 2, 3, 4, 5]            ✓
```

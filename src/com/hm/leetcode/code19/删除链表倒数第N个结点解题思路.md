# LeetCode 19. 删除链表的倒数第 N 个结点（Remove Nth Node From End of List）解题文档

## 一、题目描述

给你一个链表，删除链表的**倒数第 `n` 个**结点，并返回链表的头结点。

**进阶：** 你能尝试使用**一趟扫描**实现吗？

**示例：**

| 输入 | 输出 | 说明 |
|------|------|------|
| `head = [1,2,3,4,5], n = 2` | `[1,2,3,5]` | 删掉倒数第 2 个（值 4） |
| `head = [1], n = 1` | `[]` | 删掉唯一结点，链表变空 |
| `head = [1,2], n = 1` | `[1]` | 删掉倒数第 1 个（值 2） |

**提示：**
- 链表中结点数目为 `sz`，`1 <= sz <= 30`
- `0 <= Node.val <= 100`
- `1 <= n <= sz`

**链接：** https://leetcode.cn/problems/remove-nth-node-from-end-of-list/

---

## 二、核心观察 / 关键点

1. **删除单链表结点，必须拿到它的「前驱」**：要删 `X`，得让 `prev.next = X.next`。所以无论哪种解法，目标都是**先定位到待删结点的前一个结点**。
2. **倒数第 `n` 个 ↔ 正数第 `L-n+1` 个**：`L` 为长度。它的前驱是正数第 `L-n` 个（从头算）。
3. **哑结点（dummy）消除「删头结点」特例**：当要删的正是头结点时，它没有前驱。令 `dummy.next = head`，让所有结点都有前驱，最后返回 `dummy.next`，删头和删中间就能用同一套代码。
4. **一趟扫描的核心是「固定间距的双指针」**：让 `fast` 比 `slow` 先走 `n` 步，二者再同步前进；`fast` 到末尾时，`slow` 恰好落在待删结点的前驱上。

---

## 三、解法

### 解法一：快慢指针，一趟扫描（推荐，满足进阶要求）

**思路：**
1. 建哑结点 `dummy`，`fast = slow = dummy`。
2. `fast` 先走 `n` 步，使 `fast` 与 `slow` 之间保持 `n` 的间距。
3. 二者同步前进，直到 `fast.next == null`（`fast` 指向最后一个结点）。因为间距是 `n`，此时 `slow` 正好停在「倒数第 `n` 个」的前驱。
4. `slow.next = slow.next.next` 跳过待删结点，返回 `dummy.next`。

**关键代码片段：**

```java
ListNode dummy = new ListNode(0, head);
ListNode fast = dummy, slow = dummy;
for (int i = 0; i < n; i++) {   // fast 先走 n 步
    fast = fast.next;
}
while (fast.next != null) {      // 同步前进，slow 落到前驱
    fast = fast.next;
    slow = slow.next;
}
slow.next = slow.next.next;      // 删除
return dummy.next;
```

- **时间复杂度：** O(L)，只遍历一遍。
- **空间复杂度：** O(1)。
- **优点：** 一趟搞定，满足进阶；不需要预先知道长度。
- **缺点：** 双指针间距、循环终止条件要想清楚，差一位（off-by-one）容易出错。

**为什么间距是 `n` 而不是 `n+1`？**
因为 `fast`、`slow` 都从 `dummy`（头的前驱）出发。`fast` 走 `n` 步后，`slow` 与 `fast` 相隔 `n`；当 `fast` 停在最后一个结点（而非 `null`）时，`slow` 与末尾相隔 `n-1` 个结点，正好是倒数第 `n` 个的**前驱**。

---

### 解法二：两趟扫描（先求长度）

**思路：**
1. 第一趟遍历数出长度 `L`。
2. 待删结点是正数第 `L-n+1` 个，其前驱是第 `L-n` 个。
3. 从 `dummy` 走 `L-n` 步到前驱，`prev.next = prev.next.next` 删除，返回 `dummy.next`。

**关键代码片段：**

```java
ListNode dummy = new ListNode(0, head);
int length = 0;
for (ListNode p = head; p != null; p = p.next) length++;  // 第一趟求长度

ListNode prev = dummy;
for (int i = 0; i < length - n; i++) prev = prev.next;     // 走到前驱
prev.next = prev.next.next;
return dummy.next;
```

- **时间复杂度：** O(L)（两趟，仍是线性）。
- **空间复杂度：** O(1)。
- **优点：** 思路最直白，`L-n` 一目了然，不易写错。
- **缺点：** 要遍历两趟，不满足「一趟扫描」的进阶要求。

---

## 四、解法对比表

| 解法 | 核心思路 | 时间 | 空间 | 优点 | 缺点 |
|------|----------|------|------|------|------|
| 一、快慢指针 | 双指针固定 `n` 间距，一趟定位前驱 | O(L) | O(1) | 一趟、满足进阶 | 间距/终止条件易差一位 |
| 二、两趟扫描 | 先求长度，再走 `L-n` 步到前驱 | O(L) | O(1) | 直白、不易错 | 需遍历两趟 |

---

## 五、易错点总结

1. **务必用哑结点**：否则删头结点时没有前驱，需要单独 `return head.next` 特判，代码更乱、易漏。
2. **一趟法的间距与终止条件**：`fast` 先走 `n` 步，循环条件用 `fast.next != null`（让 `slow` 停在前驱）。若写成 `fast != null`，`slow` 会多走一步，删错结点。
3. **两趟法走 `L-n` 步从 `dummy` 起算**：从 `dummy`（非 `head`）出发走 `L-n` 步才是前驱；若从 `head` 出发会差一位。
4. **删头结点边界**：`n == L` 时删的是头结点，哑结点能让这种情况自然成立（测试用例已覆盖 `[1],n=1` 与 `[1,2,3,4,5],n=5`）。
5. **返回值**：一定返回 `dummy.next` 而不是 `head`——因为 `head` 可能正是被删的结点。

---

## 六、关联代码

- 解法一（一趟快慢指针）+ 解法二（两趟）：`RemoveNthFromEnd.java`
- 两种解法对照测试：`RemoveNthFromEndTest.java`
- 链表结点定义复用：`com.hm.algorithm.ListNode`
- 同系列链表题：合并两个有序链表 `code21`、K 个一组翻转 `code25`

输入一个List<LinkedNode>，每一个元素都是升序的单链表，把List排序，输出合并后的一个单链表


### 自己硬写的方法

1. 将所有的LinkedNode的val 加入一个 List<Integer>，将 List<Integer> 排序，然后再转成 LinkedNode。



### 1.使用分治法

要解决这个问题，我们需要将一个包含多个升序单链表的列表（`List<LinkedNode>`）合并成一个升序的单链表。以下是一个清晰的解决方案，使用分治法（归并排序的思想）来合并链表。

### 解题思路
1. **问题分析**：
    - 输入是一个列表，列表中的每个元素是一个升序的单链表。
    - 目标是将所有链表合并成一个升序的单链表。
    - 假设链表节点定义为 `LinkedNode`，包含 `val`（节点值）和 `next`（指向下一个节点的指针）。
    - 需要高效地合并多个链表，同时保持结果链表的升序。

2. **方法选择**：
    - 一个直观的方法是逐一比较所有链表的头节点，选出最小的节点加入结果链表，但这种方法需要维护一个优先队列，复杂度较高。
    - 更高效的方法是使用**分治法**，将链表列表分成两部分递归合并，最后将两个合并后的链表再合并。
    - 分治法可以将问题分解为两两合并链表的子问题，类似于归并排序。

3. **步骤**：
    - 如果输入列表为空，返回 `null`。
    - 如果输入列表只有一个链表，直接返回该链表。
    - 否则，将列表分成两半，递归合并左右两部分。
    - 最后，将两个合并后的链表进行两两合并（类似合并两个升序链表）。
    - 合并两个链表时，比较头节点值，依次挑选较小的节点加入结果链表。

4. **复杂度分析**：
    - 假设有 `k` 个链表，平均每个链表长度为 `n`。
    - 分治法将 `k` 个链表分成两部分，递归深度为 `log k`。
    - 每次合并两个链表的复杂度为 `O(n)`，总共有 `k/2` 次合并（每层）。
    - 总时间复杂度：`O(n * k * log k)`。
    - 空间复杂度：递归调用栈为 `O(log k)`，不考虑输出链表的存储空间。

### 代码实现
以下是 Java 代码实现，假设链表节点类为 `LinkedNode`：

```java
class LinkedNode {
    int val;
    LinkedNode next;
    LinkedNode(int x) { val = x; }
}

public class Solution {
    public LinkedNode mergeKLists(List<LinkedNode> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        return mergeKListsHelper(lists, 0, lists.size() - 1);
    }
    
    // 分治法递归合并
    private LinkedNode mergeKListsHelper(List<LinkedNode> lists, int start, int end) {
        // 基本情况：只有一个链表
        if (start == end) {
            return lists.get(start);
        }
        // 基本情况：空区间
        if (start > end) {
            return null;
        }
        // 分成两半
        int mid = start + (end - start) / 2;
        // 递归合并左半部分
        LinkedNode left = mergeKListsHelper(lists, start, mid);
        // 递归合并右半部分
        LinkedNode right = mergeKListsHelper(lists, mid + 1, end);
        // 合并两个链表
        return mergeTwoLists(left, right);
    }
    
    // 合并两个升序链表
    private LinkedNode mergeTwoLists(LinkedNode l1, LinkedNode l2) {
        LinkedNode dummy = new LinkedNode(0); // 虚拟头节点
        LinkedNode current = dummy;
        
        // 比较并合并
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        
        // 处理剩余节点
        if (l1 != null) {
            current.next = l1;
        }
        if (l2 != null) {
            current.next = l2;
        }
        
        return dummy.next;
    }
}
```

### 代码说明
- **`mergeKLists`**：主函数，接收 `List<LinkedNode>` 作为输入，调用分治法的辅助函数。
- **`mergeKListsHelper`**：分治法的核心，通过递归将链表列表分成两半，最终合并。
- **`mergeTwoLists`**：合并两个升序链表的函数，使用虚拟头节点（`dummy`）简化操作，逐一比较节点值，构造结果链表。
- **输入处理**：
    - 如果输入列表为空，返回 `null`。
    - 如果只有一个链表，直接返回。
- **合并过程**：
    - 比较两个链表的头节点值，将较小的节点加入结果链表。
    - 继续处理剩余节点，直到某个链表为空。
    - 将非空链表的剩余部分直接接到结果链表末尾。

### 示例
**输入**：
```
lists = [[1->3->5], [2->4->6], [0->7]]
```

**步骤**：
1. 分治法将列表分成：
    - 第一部分：`[1->3->5], [2->4->6]`
    - 第二部分：`[0->7]`
2. 合并第一部分：
    - 合并 `[1->3->5]` 和 `[2->4->6]`：
        - 比较 1 和 2，选 1，得到 `1->...`
        - 比较 3 和 2，选 2，得到 `1->2->...`
        - 比较 3 和 4，选 3，得到 `1->2->3->...`
        - 继续，得到 `1->2->3->4->5->6`
3. 合并第二部分：
    - 只有 `[0->7]`，直接返回 `0->7`
4. 合并结果：
    - 合并 `1->2->3->4->5->6` 和 `0->7`：
        - 比较 1 和 0，选 0，得到 `0->...`
        - 比较 1 和 7，选 1，得到 `0->1->...`
        - 继续，得到 `0->1->2->3->4->5->6->7`

**输出**：
```
0->1->2->3->4->5->ære6->7
```

### 复杂度总结
- **时间复杂度**：`O(n * k * log k)`，其中 `k` 是链表数量，`n` 是平均链表长度。
- **空间复杂度**：`O(log k)`（递归栈空间）。

### 优化考虑
- 如果链表数量 `k` 较小，可以直接使用优先队列（最小堆）来优化，复杂度为 `O(n * k * log k)`，但常数更小。
- 如果内存有限，可以考虑逐一合并链表（两两合并），但时间复杂度会退化为 `O(n * k^2)`。

如果有其他约束（例如内存限制、链表数量特别大等），请告诉我，我可以进一步优化方案！
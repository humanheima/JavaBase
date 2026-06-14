package com.hm.leetcode.kt.code160

import com.hm.algorithm.ListNode

/**
 * Created by dumingwei on 2026/06/14.
 *
 * Desc: 160. 相交链表（Kotlin 版）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * 复用 Java 的 com.hm.algorithm.ListNode（Kotlin/Java 互操作，无需重写）。
 * 提供两种解法：HashSet 记录法 与 双指针法。
 */
class LeetCode160 {

    /**
     * 解法一：HashSet
     * 先把链表 A 全部节点存入集合，再遍历 B，第一个出现在集合中的节点即为相交点。
     * 比较的是节点引用（同一对象），而非 val。
     */
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        val visited = hashSetOf<ListNode>()
        var temp = headA
        while (temp != null) {
            visited.add(temp)
            temp = temp.next
        }
        temp = headB
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp
            }
            temp = temp.next
        }

        return null
    }

    /**
     * 解法二：双指针
     * pA 走完 A 接着走 B，pB 走完 B 接着走 A，两者走过的总长度相等。
     * 若相交，会在相交点相遇；若不相交，会同时变为 null 退出。
     */
    fun getIntersectionNodeTwoPoint(headA: ListNode?, headB: ListNode?): ListNode? {
        if (headA == null || headB == null) return null

        var pA: ListNode? = headA
        var pB: ListNode? = headB

        // 循环直到 pA == pB（相交节点或同时为 null）
        while (pA !== pB) {
            pA = if (pA == null) headB else pA.next
            pB = if (pB == null) headA else pB.next
        }
        return pA
    }
}

fun main() {
    val solution = LeetCode160()

    // 构造相交链表：
    // A: 4 -> 1 -> 8 -> 4 -> 5
    // B: 5 -> 6 -> 1 -> 8 -> 4 -> 5（从 8 开始与 A 共享节点）
    val a2 = ListNode(8)
    val a3 = ListNode(4)
    val a4 = ListNode(5)
    a2.next = a3
    a3.next = a4

    val headA = ListNode(4)
    headA.next = ListNode(1)
    headA.next.next = a2

    val headB = ListNode(5)
    headB.next = ListNode(6)
    headB.next.next = ListNode(1)
    headB.next.next.next = a2 // 共享 8 -> 4 -> 5

    val r1 = solution.getIntersectionNode(headA, headB)
    println("HashSet 法相交节点 val = ${r1?.`val`}")        // 期望 8

    val r2 = solution.getIntersectionNodeTwoPoint(headA, headB)
    println("双指针法相交节点 val = ${r2?.`val`}")           // 期望 8

    // 不相交场景
    val x = ListNode(1).apply { next = ListNode(2) }
    val y = ListNode(3).apply { next = ListNode(4) }
    println("不相交 HashSet 法 = ${solution.getIntersectionNode(x, y)}")       // 期望 null
    println("不相交 双指针法 = ${solution.getIntersectionNodeTwoPoint(x, y)}") // 期望 null
}

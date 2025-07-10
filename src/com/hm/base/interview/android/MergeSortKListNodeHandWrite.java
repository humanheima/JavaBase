package com.hm.base.interview.android;


import com.hm.algorithm.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by p_dmweidu on 2025/4/11
 * Desc: 输入一个List<LinkedNode>，每一个元素都是升序的单链表，把List排序，输出合并后的一个单链表
 *
 * 合并排序List<LinkedNode>.md
 */
public class MergeSortKListNodeHandWrite {

    /**
     * 手写方法，哈哈，感觉这种方法挺巧妙的。
     * @param lists
     * @return
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
       List<Integer> list = new ArrayList<>();
        for (ListNode listNode : lists) {
            while (listNode != null) {
                list.add(listNode.val);
                listNode = listNode.next;
            }
        }
        Collections.sort(list);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int num : list) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dummy.next;
    }


}
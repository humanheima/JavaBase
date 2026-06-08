package com.hm.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by dumingwei on 2017/6/6.
 * <p>
 * Desc: PriorityQueue（优先队列）用法示例集合。
 * <p>
 * PriorityQueue 底层是「二叉堆」，默认小顶堆：堆顶 peek() 永远是当前最小元素。
 * 常用操作：offer/add 入队 O(log n)，poll 出队堆顶 O(log n)，peek 看堆顶 O(1)。
 * 注意：直接打印 / 迭代 PriorityQueue，顺序是「堆的数组布局」而非有序，
 * 只有不断 poll 才能得到有序序列。
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        demo1_minHeapBasic();
        demo2_maxHeap();
        demo3_pollUntilEmptyIsSorted();
        demo4_customComparatorObject();
        demo5_topKLargest();
        demo6_kthSmallest();
        demo7_mergeBySize();
        demo8_peekVsPollVsIteration();
    }

    /**
     * 示例 1：默认小顶堆的基本操作
     */
    private static void demo1_minHeapBasic() {
        System.out.println("=== 示例1：默认小顶堆基本操作 ===");
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(2);
        queue.offer(4);
        queue.offer(3);
        queue.offer(7);
        queue.offer(5);
        System.out.println("内部布局(非有序): " + queue);
        System.out.println("peek 堆顶(最小): " + queue.peek());
        System.out.println("poll 弹出最小: " + queue.poll());
        System.out.println("弹出后: " + queue);
        System.out.println("size = " + queue.size());
        System.out.println();
    }

    /**
     * 示例 2：大顶堆——传入 Comparator.reverseOrder()，堆顶变成最大值
     */
    private static void demo2_maxHeap() {
        System.out.println("=== 示例2：大顶堆(reverseOrder) ===");
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int v : new int[]{2, 4, 3, 7, 5}) {
            maxHeap.offer(v);
        }
        System.out.println("peek 堆顶(最大): " + maxHeap.peek());
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            sb.append(maxHeap.poll()).append(" ");
        }
        System.out.println("依次 poll(降序): " + sb.toString().trim());
        System.out.println();
    }

    /**
     * 示例 3：全进全出 == 堆排序，poll 到空即得升序序列
     */
    private static void demo3_pollUntilEmptyIsSorted() {
        System.out.println("=== 示例3：全进全出 = 一次堆排序 ===");
        int[] arr = {9, 1, 8, 2, 7, 3, 6, 4, 5};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int v : arr) {
            pq.offer(v);
        }
        int[] sorted = new int[arr.length];
        int i = 0;
        while (!pq.isEmpty()) {
            sorted[i++] = pq.poll(); // 每次取最小
        }
        System.out.println("原数组: " + Arrays.toString(arr));
        System.out.println("排序后: " + Arrays.toString(sorted));
        System.out.println();
    }

    /**
     * 示例 4：自定义对象 + 自定义排序规则（按年龄升序，年龄相同按名字字典序）
     */
    private static void demo4_customComparatorObject() {
        System.out.println("=== 示例4：自定义对象排序(按年龄→名字) ===");
        PriorityQueue<Person> pq = new PriorityQueue<>(
                Comparator.comparingInt((Person p) -> p.age)
                        .thenComparing(p -> p.name));
        pq.offer(new Person("Tom", 30));
        pq.offer(new Person("Amy", 25));
        pq.offer(new Person("Bob", 25));
        pq.offer(new Person("Joe", 40));
        while (!pq.isEmpty()) {
            System.out.println("出队: " + pq.poll());
        }
        System.out.println();
    }

    /**
     * 示例 5：Top-K 最大元素——维护大小为 k 的「小顶堆」，O(n log k)
     * 堆里始终保留当前最大的 k 个，堆顶是这 k 个里最小的，新元素比堆顶大才替换。
     */
    private static void demo5_topKLargest() {
        System.out.println("=== 示例5：Top-K 最大(小顶堆维护) ===");
        int[] nums = {4, 10, 3, 5, 1, 8, 7, 9, 2, 6};
        int k = 3;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 默认小顶堆
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // 多了就弹掉最小的，留下较大的 k 个
            }
        }
        System.out.println("数组: " + Arrays.toString(nums));
        System.out.println("最大的 " + k + " 个(堆内): " + minHeap);
        System.out.println();
    }

    /**
     * 示例 6：第 K 小的元素——维护大小为 k 的「大顶堆」，堆顶即第 k 小
     */
    private static void demo6_kthSmallest() {
        System.out.println("=== 示例6：第 K 小元素(大顶堆维护) ===");
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 3;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 弹掉最大的，留下最小的 k 个
            }
        }
        System.out.println("数组: " + Arrays.toString(nums));
        System.out.println("第 " + k + " 小 = " + maxHeap.peek());
        System.out.println();
    }

    /**
     * 示例 7：用集合一次性建堆（heapify），按字符串长度排序
     */
    private static void demo7_mergeBySize() {
        System.out.println("=== 示例7：集合构造(heapify)+按长度排序 ===");
        List<String> words = Arrays.asList("banana", "kiwi", "apple", "fig", "cherry");
        // 用初始集合构造，内部一次性 heapify，O(n)
        PriorityQueue<String> pq = new PriorityQueue<>(
                Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));
        pq.addAll(words);
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }
        System.out.println("原列表: " + words);
        System.out.println("按长度升序出队: " + sb.toString().trim());
        System.out.println();
    }

    /**
     * 示例 8：peek / poll / 迭代 的区别（易错点）
     */
    private static void demo8_peekVsPollVsIteration() {
        System.out.println("=== 示例8：peek vs poll vs 迭代 ===");
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Collections.addAll(pq, 50, 10, 40, 20, 30);
        System.out.println("peek(): " + pq.peek() + "（只看不删，仍是最小）");
        System.out.println("size: " + pq.size());
        // 直接 for-each 迭代：顺序是堆的内部数组布局，并非有序！
        StringBuilder iter = new StringBuilder();
        for (int v : pq) {
            iter.append(v).append(" ");
        }
        System.out.println("for-each 迭代(非有序): " + iter.toString().trim());
        // 想要有序必须 poll
        StringBuilder ordered = new StringBuilder();
        while (!pq.isEmpty()) {
            ordered.append(pq.poll()).append(" ");
        }
        System.out.println("poll 到空(有序): " + ordered.toString().trim());
        System.out.println();
    }

    /**
     * 简单的实体类，用于自定义排序演示
     */
    private static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}

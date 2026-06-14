package com.hm.leetcode.kt.code1

/**
 * Crete by dumingwei on 2026-06-14
 * 1. 两数之和
 * Desc: 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，
 * 并返回它们的数组下标。你可以假设每种输入只会对应一个答案，且同一个元素不能重复使用。
 *
 * 解题思路：
 * 1. 暴力枚举：双重循环遍历所有元素组合，和为 target 时返回下标，O(n²)。
 * 2. 哈希表（最优）：边遍历边把「值 -> 下标」存入 map。对当前元素 nums[i]，
 *    先查 target - nums[i] 是否已在 map 中，命中即得到答案，一次遍历 O(n)。
 *    关键点：先查再存，天然规避同一元素重复使用（如 [3,3], target=6）。
 *
 * 链接：https://leetcode-cn.com/problems/two-sum/
 */
class LeetCode1 {

    /**
     * 解法一：暴力枚举
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     */
    fun twoSumBruteForce(nums: IntArray, target: Int): IntArray {
        for (i in 0 until nums.size - 1) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        return intArrayOf()
    }

    /**
     * 解法二：哈希表（最优解）
     * 时间复杂度：O(n)，只需一次遍历
     * 空间复杂度：O(n)，哈希表的额外开销
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        // key 是元素值，value 是它的下标
        val indexByValue = HashMap<Int, Int>(nums.size)
        for (i in nums.indices) {
            val complement = target - nums[i]
            // 先查：若差值已出现过，立即返回答案，避免无用遍历
            indexByValue[complement]?.let { return intArrayOf(it, i) }
            // 再存：放到查询之后，避免把当前元素自身当成配对项
            indexByValue[nums[i]] = i
        }
        return intArrayOf()
    }

}

fun main() {
    val solution = LeetCode1()

    // 普通用例：3 + 7 == 10，期望 [0, 2]
    val nums = intArrayOf(3, 2, 7, 11, 15)
    val target = 10
    println("暴力枚举：${solution.twoSumBruteForce(nums, target).contentToString()}")
    println("哈希表  ：${solution.twoSum(nums, target).contentToString()}")

    // 边界用例：含重复元素 [3, 3], target = 6，期望 [0, 1]
    println("重复元素：${solution.twoSum(intArrayOf(3, 3), 6).contentToString()}")

    // 无解用例：返回空数组
    println("无解    ：${solution.twoSum(intArrayOf(1, 2, 3), 100).contentToString()}")
}

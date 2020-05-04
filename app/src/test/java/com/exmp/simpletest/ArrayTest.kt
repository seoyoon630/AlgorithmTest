package com.exmp.simpletest

import org.junit.Test

class ArrayTest {
    @Test
    fun removeDuplicates1() {
//        val nums: IntArray = intArrayOf(0,0,1,1,1,2,2,3,3,4)
        val nums = intArrayOf(1, 1, 2)
        val numSet: HashSet<Int> = hashSetOf()
        numSet.addAll(nums.toTypedArray())
        numSet.forEachIndexed { index, it ->
            nums[index] = it
        }
        println(numSet.size)
    }

    @Test
    fun removeDuplicates2() {
        val nums: IntArray = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
//        val nums = intArrayOf(1,1,2)
        var current = 0

        for (next in 1 until nums.size) {
            if (nums[current] != nums[next]) {
                nums[++current] = nums[next]
            }
        }

        println(current + 1)
        println(nums.joinToString(" "))
    }
}
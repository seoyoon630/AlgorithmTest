package com.exmp.simpletest

import org.junit.Test
import java.util.*

class GreedyTest {
    @Test
    fun greedyMain() {
//        println("체육복(5, [2, 4], [1, 3, 5]) = ${greedy2(5, intArrayOf(2, 4), intArrayOf(1, 3, 5))}")
        println("체육복(5, [2, 4], [3]) = ${greedy1(5, intArrayOf(2, 4), intArrayOf(3))}")
    }

    // 체육복
    fun greedy1(n: Int, lost: IntArray, reserve: IntArray): Int {
        val students: HashMap<Int, Int> = hashMapOf()
        for (i in 0 until n) {
            var count = 1
            if (lost.contains(i + 1)) count--
            if (reserve.contains(i + 1)) count++
            students[i] = count
        }
        var attend = 0
        for (i in 0 until n) {
            if (students[i]!! >= 1) attend++
            else {
                val prev: Int = if (i > 0) students[i - 1]!! else 0
                val next: Int = if (i < n - 1) students[i + 1]!! else 0
                if (prev > 1) {
                    students[i - 1] = students[i - 1]!! - 1
                    students[i] = 1
                    attend++
                } else if (next > 1) {
                    students[i + 1] = students[i + 1]!! - 1
                    students[i] = 1
                    attend++
                }
            }
        }
        return attend
    }
}
package com.exmp.simpletest

import org.junit.Test

class BinarySearchTest {

    @Test
    fun binarySearchMain() {
//        println("입국심사(6, [7,10]) = ${binarySearch1(6, intArrayOf(7, 10))}")
//        println("입국심사(11, [7,8,10]) = ${binarySearch1(11, intArrayOf(7, 8, 10))}")
//        println("입국심사(10, [1,5]) = ${binarySearch1(10, intArrayOf(1, 5))}")
//        println("입국심사(3,  [1000000000,1000000000,1000000000]) = ${binarySearch1(3, intArrayOf(1000000000, 1000000000, 1000000000))}")
        println(
            "예산([120, 110, 140, 150], 485) = ${binarySearch2(
                intArrayOf(120, 110, 140, 150),
                485
            )}"
        )
    }

    // 입국심사
    private fun binarySearch1(n: Int, times: IntArray): Long {
        times.sort()
        var left = n / times.size * times[0].toLong()
        var right = n / times.size * times[times.lastIndex].toLong()
        while (left < right) {
            val mid = (left + right) / 2
            var complete: Long = 0

            for (time in times) {
                complete += mid / time
                if (n <= complete) break
            }

            if (complete >= n) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }

    // 예산
    private fun binarySearch2(budgets: IntArray, m: Int): Int {
        budgets.sort()
        var left = budgets[0]
        var right = budgets[budgets.lastIndex]

        while (left < right) {
            val mid = (left + right) / 2
            var total = 0
            budgets.forEach { budget ->
                total += Math.min(budget, mid)
            }
            if (total / budgets.size >= m / budgets.size) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}
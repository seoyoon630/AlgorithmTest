package com.exmp.simpletest

import org.junit.Test
import java.lang.StringBuilder
import java.util.*

class GreedyTest {
    @Test
    fun greedyMain() {
//        println("체육복(5, [2, 4], [1, 3, 5]) = ${greedy2(5, intArrayOf(2, 4), intArrayOf(1, 3, 5))}")
//        println("체육복(5, [2, 4], [3]) = ${greedy1(5, intArrayOf(2, 4), intArrayOf(3))}")
//        println("큰 수 만들기(1924, 2) = ${greedy2("1924", 2)}")
//        println("큰 수 만들기(1231234, 3) = ${greedy2("1231234", 3)}")
        println("큰 수 만들기(4177252841, 4) = ${greedy2("4177252841", 4)}")
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

    fun greedy2(number: String, k: Int): String {
        val array = IntArray(number.length)
        for(i in number.indices){
            array[i] = number.substring(i, i+1).toInt()
        }
        var complete = 0
        var start = 0
        var end = k + 1
        val result = StringBuilder()
        while (complete < number.length - k) {
            var max = -1
            val prev = start
            for(index in start until end){
                val num = array[index]
                if (num > max) {
                    max = num
                    start = index + 1
                }
            }
            end++
            result.append(max)
            complete++
        }
        return result.toString()
    }
}
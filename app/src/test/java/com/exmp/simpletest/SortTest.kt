package com.exmp.simpletest

import org.junit.Test

class SortTest {
    @Test
    fun sortMain() {
//        println("K번째수([1, 5, 2, 6, 3, 7, 4], [[2, 5, 3], [4, 4, 1], [1, 7, 3]]) = " +
//                sort1(intArrayOf(1, 5, 2, 6, 3, 7, 4), arrayOf(intArrayOf(2, 5, 3), intArrayOf(4, 4, 1), intArrayOf(1, 7, 3))).joinToString(" "))
//        println("가장 큰 수([3, 30, 34, 5, 9]) = ${sort2(intArrayOf(3, 30, 34, 5, 9))}")
//        println("H-Index[3, 0, 6, 1, 5] = ${sort3(intArrayOf(3, 0, 6, 1, 5))}")
        println("H-Index[5, 5, 5, 5, 5] = ${sort3(intArrayOf(5, 5, 5, 5, 5))}")
    }

    // K번째수
    fun sort1(array: IntArray, commands: Array<IntArray>): IntArray {
        val answer = ArrayList<Int>()
        val original = array.toList()

        commands.forEach { command ->
            val start = command[0] - 1
            val end = command[1] - 1
            val index = command[2] - 1
            val list = original.subList(start, end + 1).sorted()
            answer.add(list[index])
        }
        return answer.toIntArray()
    }

    // 가장 큰 수
    fun sort2(numbers: IntArray): String {
        val sorted = numbers.sortedWith(Comparator<Int> { o1, o2 ->
            val s1 = o1.toString().repeat(4).substring(0, 4).toInt()
            val s2 = o2.toString().repeat(4).substring(0, 4).toInt()
            s2 - s1
        })

        var result = sorted.joinToString("")
        for (c in result) {
            if (c == '0') result = result.substring(1, result.length)
            else break
        }
        if (result.isEmpty()) result = "0"
        return result
    }

    // H-Index
    fun sort3(citations: IntArray): Int {
        citations.sortDescending()

        for (i in citations.indices) {
            if (citations[i] < i + 1) {
                return i
            } else if (i == citations.lastIndex && citations[i] >= citations.size) return citations.size
        }
        return 0
    }
}
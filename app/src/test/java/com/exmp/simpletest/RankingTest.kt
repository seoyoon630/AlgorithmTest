package com.exmp.simpletest

import org.junit.Test

class RankingTest {
    @Test
    fun rankingTestMain() {
        val array = arrayOf(intArrayOf(4, 3), intArrayOf(4, 2), intArrayOf(3, 2), intArrayOf(1, 2), intArrayOf(2, 5))
        array.forEach {
            println("input = ${it.joinToString(" ")}")
        }

        rankingTest(5, array)
//        println("rankingTest = ${rankingTest(5, array)}")

    }

    private fun rankingTest(n: Int, results: Array<IntArray>) {
        val winSets = Array<HashSet<Int>>(n + 1) { hashSetOf() }
        results.forEach {
            winSets[it[0]].add(it[1])
        }
        winSets.forEach { set ->
            set.forEach { value ->
                set.addAll(winSets[value])
            }
        }

        winSets.forEachIndexed { index, it ->
            println("index : ${it.joinToString(" ")}")
        }
    }
}
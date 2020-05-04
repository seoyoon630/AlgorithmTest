package com.exmp.simpletest

import org.junit.Test

class Part2Test {
    @Test
    fun medium6_stockExchangeLossesMain() {
        val array = intArrayOf(3, 2, 4, 2, 1, 5)
        println("input = ${array.joinToString(" ")}")
        println("medium6_stockExchangeLossesMain = ${medium6_stockExchangeLosses(array)}")
    }

    private fun medium6_stockExchangeLosses(input: IntArray): Int {
        var max = 0
        var loss = 0
        input.forEach {
            if (max < it) {
                max = it
            } else {
                val curLoss = it - max
                if (curLoss < loss) {
                    loss = curLoss
                }
            }
        }
        return loss
    }

    @Test
    fun medium6_stockExchangeProfitsMain() {
        val array = intArrayOf(3, 2, 4, 2, 1, 5)
        println("input = ${array.joinToString(" ")}")
        println("medium6_stockExchangeProfits = ${medium6_stockExchangeProfits(array)}")
    }

    private fun medium6_stockExchangeProfits(input: IntArray): Int {
        var min = Int.MAX_VALUE
        var profit = 0
        input.forEach {
            if (min > it) {
                min = it
            } else {
                val curProfit = it - min
                if (curProfit > profit) {
                    profit = curProfit
                }
            }
        }
        return profit
    }

    /**
     * 얻을 수 있는 최대 수익
     */
    @Test
    fun medium6_stockExchangeBestProfitsMain() {
        val array = intArrayOf(3, 2, 4, 2, 1, 5, 6)
//        val array = intArrayOf(3, 2, 4, 2, 1, 5)
        println("input = ${array.joinToString(" ")}")
        println("medium6_stockExchangeBestProfits = ${medium6_stockExchangeBestProfits(array)}")
    }

    private fun medium6_stockExchangeBestProfits(input: IntArray): Int {
        // 2개 이하 예외 처리
        val n = input.size
        if (n <= 1) {
            return 0
        } else if (n == 2) {
            val profit = input[1] - input[0]
            return if (profit > 0) profit else 0
        }
        var max = -1
        var min = -1
        var profit = 0
        for (i in 1 until n - 1) {
            val prev = input[i - 1]
            val cur = input[i]
            val next = input[i + 1]
            if (prev > cur && cur <= next) {    //min
                min = cur
            } else if (prev < cur && cur >= next) {
                max = cur
                if (min > 0) {
                    profit += max - min
                    max = -1
                    min = -1
                }
            }
        }
        if (input[n - 2] < input[n - 1]) {
            max = input[n - 1]
            if (min > 0) {
                profit += max - min
            }
        }
        return profit
    }
}
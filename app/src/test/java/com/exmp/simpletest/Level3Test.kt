package com.exmp.simpletest

import org.junit.Test
import java.util.*

class Level3Test {

    @Test
    fun level3TestMain() {
        println("종이접기(3) : " + 종이접기(3).joinToString(" "))
    }

    fun 종이접기(n: Int): IntArray {
        var answer = IntArray(Math.pow(2.0, n.toDouble()).toInt() - 1)

        var count: Double = 1.toDouble()
        while (count <= n) {
            val start = Math.pow(2.0, (count - 1)).toInt() - 1
            val step = Math.pow(2.0, count).toInt()
            var isIn = true
            for (i in start until answer.size step step) {
                answer[i] = if (isIn) 0 else 1
                println("answer[$i] = ${answer[i]}")
                isIn = !isIn
            }
            count++
        }
        return answer
    }

    @Test
    fun 올바른_괄호() {
        for (n in 1..14) {
            println(fac(2 * n, n + 1) / fac(n))
        }
    }

    private fun fac(n: Int, limit: Int = 1): Long {
        if (n == 1 || n == limit) return 1.toLong()
        return n * fac(n - 1, limit)
    }
}
package com.exmp.simpletest

import org.junit.Test
import kotlin.math.pow

class FoldingPaperTest {
    @Test
    fun foldingPaperMain() {
        val n = 4
        println("foldingPaper($n) = ${foldingPaper(n).joinToString(" ")}")
    }

    private fun foldingPaper(n: Int): IntArray {
        val result = IntArray(Math.pow(2.toDouble(), n.toDouble()).toInt() - 1) { -1 }
        for (i in 1..n) {
            var index = Math.pow(2.toDouble(), (i-1).toDouble()).toInt() - 1
            var isIn = true
            val interval = Math.pow(2.toDouble(), i.toDouble()).toInt()
            while (index < result.size) {
                result[index] = if (isIn) 0 else 1
                isIn = !isIn
                index += interval
            }
        }
        return result
    }
}
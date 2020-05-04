package com.exmp.simpletest

import org.junit.Test
import java.lang.StringBuilder
import kotlin.math.absoluteValue

class Part1Test {
    @Test
    fun easy3_theDescentMain() {
        val array = intArrayOf(3, 4, 7, 9, 1, 2, 5, 6, 8, 2)
        println("input = ${array.joinToString(" ")}")
        println("easy3_theDescent = ${easy3_theDescent(array)}")
    }

    private fun easy3_theDescent(input: IntArray): Int {
        var maxHeight: Int = -1
        var maxIndex: Int = -1

        input.forEachIndexed { index, it ->
            if (it > maxHeight) {
                maxHeight = it
                maxIndex = index
            }
        }

        return maxIndex
    }

    @Test
    fun easy4_temperaturesMain() {
        val array = intArrayOf(3, 4, 7, 9, -2, 5, 6, 8, 2, -1)
        println("input = ${array.joinToString(" ")}")
        println("easy4_temperatures = ${easy4_temperatures(array)}")
    }

    private fun easy4_temperatures(input: IntArray): Int {
        var minTemperature = 10000
        if (input.isEmpty()) return 0
        input.forEach {
            val absInput = it.absoluteValue
            when {
                absInput == minTemperature.absoluteValue -> if (it > 0) minTemperature = it
                absInput < minTemperature.absoluteValue -> minTemperature = it
            }
        }
        return minTemperature
    }


    @Test
    fun easy5_chunkNorrisMain() {
        val target = "ABCD"
        println("input = $target")
        println("easy5_chunkNorris = ${easy5_chunkNorris(target)}")
    }

    private fun easy5_chunkNorris(t: String): String {
        val chars = t.toCharArray()
        val binaryString = StringBuilder().apply {
            chars.forEach {
                append(charToBinary(it))
            }
        }.toString()
        println(binaryString)

        return binaryToZero(binaryString.toCharArray())
    }

    private fun charToBinary(c: Char): String {
        val result = StringBuilder()
        var target = c.toInt()
        println(target)
        while (target > 0) {
            val share = target / 2
            if (share > 0) {
                val rest = target % share
                result.insert(0, rest)
            } else result.insert(0, target)
            target = share
        }
        return result.toString()
    }

    private fun binaryToZero(binaryChars : CharArray) : String{
        return StringBuilder().apply {
            if (binaryChars.isNotEmpty()) {
                val currentChar = binaryChars[0]
                var currentCount = 1
                for (i in 1..binaryChars.size) {
                    if (i < binaryChars.size && binaryChars[i - 1] == binaryChars[i] ) {
                        currentCount++
                    } else {
                        append(" ")
                        if (binaryChars[i - 1] == '0') append("00")
                        else append("0")
                        append(" ")
                        for (j in 0 until currentCount) {
                            append("0")
                        }
                        currentCount = 1
                    }
                }
            }
        }.toString()
    }
}
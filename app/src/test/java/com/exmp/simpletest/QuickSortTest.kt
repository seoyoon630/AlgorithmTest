package com.exmp.simpletest

import org.junit.Test

class QuickSortTest {
    @Test
    fun quickSortMain() {
        val array = intArrayOf(1, 6, 3, 45, 8, 9, 3, 122, 1)
        println(array.joinToString(" "))
        var count = 0
        val indice = IntArray(array.size) { count++ }
        val start = 0
        val end = array.lastIndex
        quickSort(array, indice, start, end)
        println(array.joinToString(" "))
        println(indice.joinToString(" "))
    }

    private fun quickSort(array: IntArray, indice: IntArray, start: Int, end: Int) {
//        println("quickSort start===================================")
//        println(array.joinToString(" "))
        val p = divide(array, indice, start, end)
        if (start < p - 1) {
            quickSort(array, indice, start, p - 1)
        }
        if (p < end) {
            quickSort(array, indice, p, end)
        }
//        println("quickSort result==================================")
//        println(array.joinToString(" "))
//        println("quickSort end=====================================")
    }

    private fun divide(array: IntArray, indice: IntArray, start: Int, end: Int): Int {
        var left = start
        var right = end
        val pivot = (left + right) / 2
//        println("조사 범위 : $left ~ $right / pivot : $pivot ")
        while (left < right) {
            while (array[pivot] > array[left]) {
                left++
            }
            while (array[pivot] < array[right]) {
                right--
            }
//            println("left[$left] = ${array[left]} / pivot[$pivot] = ${array[pivot]} /right[$right] = ${array[right]}")
            if (left <= right) {
                val temp = array[right]
                array[right] = array[left]
                array[left] = temp

                val indexTemp = indice[right]
                indice[right] = indice[left]
                indice[left] = indexTemp

                left++
                right--
            }
        }
//        println(array.joinToString(" "))
        return left
    }

}
package com.exmp.simpletest

import org.junit.Test
import java.util.LinkedList
import java.util.Queue
import java.util.Stack
import kotlin.collections.ArrayList

// 프로그래머스 스택/큐
class StackTest {
    @Test
    fun stackMain() {
//        println("stack1 = " + stack1(intArrayOf(6, 9, 5, 7, 4)).joinToString(" "))
//        println("stack2 = " + stack2(2, 10, intArrayOf(7, 4, 5, 6)))
        println("stack3 = " + stack3(intArrayOf(93, 30, 55), intArrayOf(1, 30, 5)).joinToString(" "))
    }

    // 탑
    private fun stack1(heights: IntArray): IntArray {
        val result = IntArray(heights.size) { 0 }

        val stack: Stack<Int> = Stack()
        stack.addAll(heights.toTypedArray())

        while (stack.isNotEmpty()) {
            val targetIndex = stack.lastIndex
            val target = stack.pop()
            for (i in stack.lastIndex downTo 0) {
                if (stack[i] > target) {
                    result[targetIndex] = i + 1
                    break
                }
            }
        }
        return result
    }

    // 다리를 지나는 트럭
    private fun stack2(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var time = 0
        val wait: Queue<Int> = LinkedList(truck_weights.toList())
        val onBridge: Queue<Int> = LinkedList(List(bridge_length) { 0 })

        while (onBridge.isNotEmpty()) {
            time++
            onBridge.poll()
            if (wait.isNotEmpty()) {
                if (onBridge.sum() + wait.peek() <= weight) {
                    onBridge.add(wait.poll())
                } else onBridge.add(0)
            }
        }
        return time
    }

    // 기능 개발
    private fun stack3(progresses: IntArray, speeds: IntArray): IntArray {
        val result = ArrayList<Int>()
        val complete: Queue<Int> = LinkedList()
        progresses.forEachIndexed { index, progress ->
            val rest = 100 - progress
            complete.add(Math.ceil(rest.toDouble() / speeds[index]).toInt())
        }
        var count = 1
        var current = complete.poll()
        while (complete.isNotEmpty()) {
            val next = complete.poll()
            if (next <= current) {
                count++
            } else {
                result.add(count)
                count = 1
                current = next
            }
        }
        result.add(count)
        return result.toIntArray()
    }
}
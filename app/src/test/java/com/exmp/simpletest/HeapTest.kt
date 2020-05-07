package com.exmp.simpletest

import org.junit.Test
import java.util.*

class HeapTest {
    @Test
    fun heapMain() {
//        println("더맵게([1, 2, 3, 9, 10, 12], 7) = ${heap1(intArrayOf(1, 2, 3, 9, 10, 12), 7)}")
//        println("라면공장(4, [4,10,15], [20,5,10], 30) = ${heap2(4, intArrayOf(4, 10, 15), intArrayOf(20, 5, 10), 30)}")
        println("디스크 컨트롤러[[0, 3], [1, 9], [2, 6]] = ${heap3(arrayOf(intArrayOf(0, 3), intArrayOf(1, 9), intArrayOf(2, 6)))}")
    }

    // 더 맵게
    private fun heap1(scoville: IntArray, K: Int): Int {
        val queue: PriorityQueue<Int> = PriorityQueue()
        queue.addAll(scoville.toList())

        var count = 0
        println(queue.joinToString(" "))
        while (queue.size >= 2 && queue.peek() < K) {
            val t1 = queue.poll()
            val t2 = queue.poll()
            val mix = t1 + t2 * 2
            queue.offer(mix)
            count++
            println(queue.joinToString(" "))
        }
        return if (queue.poll() < K) -1 else count
    }

    // 라면공장
    private fun heap2(stock: Int, dates: IntArray, supplies: IntArray, k: Int): Int {
        var start = 0
        var end = stock
        var count = 0
        while (end < k) {
            val queue: PriorityQueue<Int> = PriorityQueue() { t1, t2 -> t2 - t1 }
            for (i in dates.indices) {
                if (dates[i] in start..end) {
                    queue.add(supplies[i])
                } else if (dates[i] > end) break
            }
            start = end + 1
            end += queue.poll()
            count++
        }
        return count
    }

    // 디스크 컨트롤러
    private fun heap3(jobs: Array<IntArray>): Int {
        val queue: PriorityQueue<Job> = PriorityQueue()
        jobs.forEach { job -> queue.offer(Job(job[0], job[1])) }
        var end = 0
        var sum = 0
        val list = arrayListOf<Job>()
        while (queue.size > 0) {
            list.add(queue.poll())
        }
        while (list.size > 0) {
            for (i in list.indices) {
                if (list[i].start <= end) {
                    end += list[i].time
                    sum += end-list[i].start
                    list.removeAt(i)
                    break
                }
                if (i == list.lastIndex) {
                    end++
                }
            }
        }
        return sum / jobs.size
    }
}

data class Job(val start: Int, val time: Int) : Comparable<Job> {
    override fun compareTo(other: Job): Int {
        val timeDiff = this.time - other.time
        return if (timeDiff == 0) return this.start - other.start
        else timeDiff
    }

}
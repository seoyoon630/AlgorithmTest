package com.exmp.simpletest

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /**
     * 다익스트라 알고리즘
     */
    @Test
    fun dijkstraAlgorithm() {
        val n = 5
        val road = arrayOf(intArrayOf(1, 2, 1), intArrayOf(2, 3, 3), intArrayOf(5, 2, 2), intArrayOf(1, 4, 2), intArrayOf(5, 3, 1), intArrayOf(5, 4, 2))
//        val road = arrayOf(intArrayOf(1, 2, 1), intArrayOf(1, 3, 2), intArrayOf(2, 3, 2), intArrayOf(3, 4, 3), intArrayOf(3, 5, 2), intArrayOf(3, 5, 3), intArrayOf(5, 6, 1))
        val roadMap = convertToMap(n, road)
        val limitTime = 3

        val distance = IntArray(n + 1) { 500000 }
        val check = BooleanArray(n + 1) { false }

        check[0] = true
        distance[1] = 0
        check[1] = true

        // initialize
        roadMap[1].forEach { distance[it.key] = it.value }

        while (true) {
            val target = getNextTarget(distance, check)
            println(target)
            if (target > 0) {
                check[target] = true
                if (distance[target] < limitTime) {
                    val map = roadMap[target]
                    for (j in target + 1..n) {
                        map[j]?.let { distanceFromCurrentTown ->
                            val newDistance = distance[target] + distanceFromCurrentTown
                            if (distance[j] > newDistance) distance[j] = newDistance
                        }
                    }
                }
            } else break
        }

        val result = distance.filter { d -> d <= limitTime }.count()
        println(result)
    }

    private fun getNextTarget(distance: IntArray, check: BooleanArray): Int {
        var targetIndex = -1
        var currentMinDistance = 500000 + 1
        distance.forEachIndexed { index, d ->
            if (!check[index] && d < currentMinDistance) {
                targetIndex = index
                currentMinDistance = d
            }
        }
        return targetIndex
    }

    private fun convertToMap(n: Int, road: Array<IntArray>): Array<HashMap<Int, Int>> {
        val result = Array<HashMap<Int, Int>>(n + 1) { hashMapOf() }
        road.forEach { data ->
            result[data[0]][data[1]]?.let { originalValue ->
                if (originalValue > data[2]) {
                    result[data[0]][data[1]] = data[2]
                    result[data[1]][data[0]] = data[2]
                }
            } ?: run {
                result[data[0]][data[1]] = data[2]
                result[data[1]][data[0]] = data[2]
            }
        }
        return result
    }

    /**
     * 다익스트라 알고리즘 끝
     */

    @Test
    fun test(){
        val expl = "Say your prayers, little one"
        println(expl.reversed())
    }
}

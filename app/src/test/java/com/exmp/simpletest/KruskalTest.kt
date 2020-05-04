package com.exmp.simpletest

import org.junit.Test

class KruskalTest {

    @Test
    fun kruskalTestMain() {
        val array = arrayOf(intArrayOf(0, 1, 1), intArrayOf(0, 2, 2), intArrayOf(1, 2, 5), intArrayOf(1, 3, 1), intArrayOf(2, 3, 8))
        array.forEach {
            println("input = ${it.joinToString(" ")}")
        }

        println("kruskalTest = ${kruskalTest(4, array)}")

    }

    private fun kruskalTest(n: Int, costs: Array<IntArray>): Int {
        /**
         * 1. 다리 건설 비용 오름차순으로 정렬
         * 2. 건설한 다리 배열, 방문한 섬 배열 선언
         * 3. 최소 건설 비용 섬부터 출발(초기화)
         * 4. 방문한 섬의 크기가 n이 될 때까지 반복
         *  - 다리가 건설된 적이 없고, 한쪽 다리만 건설되었을 때 다리 건설
         */

        // 1
        costs.sortWith(Comparator { t1, t2 -> t1[2] - t2[2] })

        // 2
        val constructedBridges = BooleanArray(costs.size) { false }
        var result = 0
        val visitedIslands = BooleanArray(n) { false }
        var visitedCount = 0

        if (costs.size == 1) {
            return costs[0][2]
        }

        // 3
        visitedIslands[costs[0][0]] = true
        visitedIslands[costs[0][1]] = true
        constructedBridges[0] = true
        result += costs[0][2]
        visitedCount = 2

        while (n > visitedCount) {
            for (i in 1 until costs.size) {
                val targetIsland1 = visitedIslands[costs[i][0]]
                val targetIsland2 = visitedIslands[costs[i][1]]

                // 건설한 적 없는 다리이며 두 섬 중 한 섬만 연결되어있을 경우
                if (!constructedBridges[i] && targetIsland1 != targetIsland2) {
                    visitedIslands[costs[i][0]] = true
                    visitedIslands[costs[i][1]] = true
                    constructedBridges[i] = true
                    result += costs[i][2]
                    visitedCount++
                    break
                }
            }
        }
        return result
    }
}

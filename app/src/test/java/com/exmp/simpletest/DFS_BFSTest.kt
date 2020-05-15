package com.exmp.simpletest

import org.junit.Test

class DFS_BFSTest {
    @Test
    fun dfsBfsMain() {
//        println("타겟 넘버([1, 1, 1, 1, 1], 3) = ${dfsBsf1(intArrayOf(1, 1, 1, 1, 1), 3)}")
//        println("네트워크([[1, 1, 0], [1, 1, 0], [0, 0, 1]]) = ${dfsBsf2(3, arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 0), intArrayOf(0, 0, 1)))}")
//        println("네트워크([[1, 1, 0], [1, 1, 1], [0, 1, 1]]) = ${dfsBsf2(3, arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 1), intArrayOf(0, 1, 1)))}")
//        println("단어 변환(hit, cog, [hot, dot, dog, lot, log, cog]) = ${dfsBsf3("hit", "cog", arrayOf("hot", "dot", "dog", "lot", "log", "cog"))}")
    }

    private fun dfsBsf1(numbers: IntArray, target: Int): Int {
        return dfs1(numbers, -1, 0, target)
    }

    // 타겟 넘버
    private fun dfs1(numbers: IntArray, height: Int, sum: Int, target: Int): Int {
        return if (height == numbers.lastIndex) {
            if (sum == target) 1
            else 0
        } else {
            (dfs1(numbers, height + 1, sum + numbers[height + 1], target)
                    + dfs1(numbers, height + 1, sum - numbers[height + 1], target))
        }
    }

    private fun dfsBsf2(n: Int, computers: Array<IntArray>): Int {
        val visited = BooleanArray(n)
        var count = 0
        for (i in 0 until n) {
            if (!visited[i]) {
                count++
                dfs2(i, n, visited, computers)
            }
        }
        return count
    }

    private fun dfs2(parent: Int, n: Int, visited: BooleanArray, computers: Array<IntArray>) {
        visited[parent] = true
        for (j in 0 until n) {
            if (!visited[j] && computers[parent][j] == 1) {
                dfs2(j, n, visited, computers)
            }
        }
    }

    //    private fun dfsBsf3(begin: String, target: String, words: Array<String>): Int {
//        val childrens = Array<HashSet<String>>(words.size) { hashSetOf() }
//        val initialChildren = HashSet<String>()
//        words.forEach { if (canTransfer(begin, it)) initialChildren.add(it) }
//        words.forEachIndexed { index, s1 ->
//            words.forEach { s2 -> if (canTransfer(s1, s2)) childrens[index].add(s2) }
//            if (canTransfer(s1, target)) childrens[index].add(target)
//        }
//        childrens.forEachIndexed { index, it ->
//            println("${words[index]} children = ${childrens[index].joinToString(" ")}")
//        }
//
//        initialChildren.forEach {
//
//        }
//
//        var answer = 0
//        return answer
//    }
//
//    private fun dfs3(){}
//
//    private fun canTransfer(s1: String, s2: String): Boolean {
//        if (s1 == s2) return false
//        if (s1.length != s2.length) return false
//        var count = 0
//        for (i in s1.indices) {
//            if (s1[i] != s2[i]) {
//                count++
//                if (count > 1) return false
//            }
//        }
//        return true
//    }
    @Test
    fun test() {
//        println(test1(arrayOf("I 7", "I 5", "I -5", "D -1")).joinToString(" "))
        println(test2(5, arrayOf(intArrayOf(4, 3), intArrayOf(4, 2), intArrayOf(3, 2), intArrayOf(1, 2), intArrayOf(2, 5))))
    }

    fun test1(operations: Array<String>): IntArray {
        val list = arrayListOf<Int>()
        var answer = IntArray(2)
        operations.forEach { operation ->
            val num = operation.substring(2).toInt()
            when (operation[0]) {
                'I' -> list.add(num)
                'D' -> {
                    if (num > 0) list.max()?.let { list.remove(it) }
                    else list.min()?.let { list.remove(it) }
                }
            }
        }
        answer[0] = list.max() ?: 0
        answer[1] = list.min() ?: 0
        return answer
    }

    fun test2(n: Int, results: Array<IntArray>): Int {
        val players = Array(n + 1) { Player() }
        results.forEach { result ->
            players[result[0]].win.add(result[1])
            players[result[1]].lose.add(result[0])
        }
        players.forEachIndexed { index, player ->
            player.win.addAll(winDFS(index, hashSetOf(), hashSetOf(), players))
            player.lose.addAll(loseDFS(index, hashSetOf(), hashSetOf(), players))
        }
        return players.filter { it.win.size+ it.lose.size == n -1 }.count()
    }

    fun winDFS(target: Int, result : HashSet<Int>, winCheck: HashSet<Int>, players: Array<Player>) : HashSet<Int> {
        players[target].win.forEach {
            if(!winCheck.contains(it)){
                result.add(it)
                result.addAll(winDFS(it, result, winCheck, players))
                winCheck.add(it)
            }
        }
        return result
    }
    fun loseDFS(target: Int, result : HashSet<Int>, loseCheck: HashSet<Int>, players: Array<Player>) : HashSet<Int> {
        players[target].lose.forEach {
            if(!loseCheck.contains(it)){
                result.add(it)
                result.addAll(loseDFS(it, result, loseCheck, players))
                loseCheck.add(it)
            }
        }
        return result
    }
}


data class Player(val win: HashSet<Int> = hashSetOf(), val lose: HashSet<Int> = hashSetOf())

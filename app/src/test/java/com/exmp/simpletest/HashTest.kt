package com.exmp.simpletest

import org.junit.Test
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class HashTest {
    @Test
    fun hashMain() {
//        println("hash1 = " + hash1(arrayOf("leo", "kiki", "kiki", "eden"), arrayOf("leo", "kiki", "eden")))
//        println("hash2 = " + hash2(arrayOf("119", "97674223", "1195524421", "123", "12", "1235")))
//        println("hash3 = " + hash3(arrayOf(arrayOf("yellow_hat", "headgear"), arrayOf("blue_sunglasses", "earring"), arrayOf("green_turban", "headgear"))))
        println("hash4 = " + hash4(arrayOf("classic", "pop", "classic", "classic", "pop"), intArrayOf(500, 600, 150, 800, 2500)).joinToString(" "))

    }

    // 완주하지 못한 선수
    private fun hash1(participant: Array<String>, completion: Array<String>): String {
        val map: HashMap<String, Int> = hashMapOf()
        participant.forEach { p -> map[p]?.let { map[p] = it + 1 } ?: run { map[p] = 1 } }
        completion.forEach { c -> map[c] = (map[c] ?: 1) - 1 }
        map.forEach { if (it.value == 1) return it.key }
        return ""
    }

    // 전화번호 목록
    private fun hash2(phoneBook: Array<String>): Boolean {
        val queue: Queue<String> = LinkedList(phoneBook.sortedWith(Comparator { o1, o2 -> o1.length - o2.length }))
        while (queue.isNotEmpty()) {
            val target = queue.poll()
            if (queue.filter { it.startsWith(target) }.count() > 0) return true
        }
        return false
    }

    // 위장
    private fun hash3(clothes: Array<Array<String>>): Int {
        val map = hashMapOf<String, Int>()
        clothes.forEach { c -> map[c[1]]?.let { map[c[1]] = it + 1 } ?: run { map[c[1]] = 1 } }
        var result = 1
        map.values.forEach { result *= (it + 1) }
        result--
        return result
    }

//    // 베스트앨범
//    private fun hash4(genres: Array<String>, plays: IntArray): IntArray {
//        val map = hashMapOf<String, Pair<Int, HashMap<Int, Int>>>()
//        genres.forEachIndexed { index, genre ->
//            map[genre]?.let {
//                val totalCount = it.first + plays[index]
//                val mostPlayedList = it.second
//                if(mostPlayedList.size < 2) mostPlayedList[index] = plays[index]
//                else {
//                    mostPlayedList
//                }
////                map[genre] = it + plays[index]
//            } ?: run {
//                map[genre] = Pair(plays[index], hashMapOf(Pair(index, plays[index])))
//            }
//        }
//        val result = intArrayOf()
//        return result
//    }

    // 베스트앨범
    private fun hash4(genres: Array<String>, plays: IntArray): IntArray {
//        val countMap = hashMapOf<String, Int>()
//        val indexMap = hashMapOf<String, ArrayList<Int>>()
//        val minMap = hashMapOf<String, Queue<Int>>()
//        genres.forEachIndexed { index, genre ->
//            countMap[genre]?.let {
//                countMap[genre] = it + plays[index]
//                indexMap[genre]?.let { indexMap ->
//                    if (indexMap.size == 1) {
//                        if (minMap[genre]!!.peek() < plays[index]) {
//                            indexMap.add(index)
//                        } else indexMap.add(0, index)
//                    } else {
//                        if (minMap[genre]!!.peek() < plays[index]) {
//                            minMap[genre]!!.poll()
//                            minMap[genre]!!.add(plays[index])
//                        } else {}
//                    }
//                }
//
//            } ?: run {
//                val playCount = plays[index]
//                countMap[genre] = playCount
//                minMap[genre] = LinkedList<Int>().apply { add(plays[index]) }
//                indexMap[genre] = arrayListOf(index)
//            }
//        }
        val result = intArrayOf()
        return result
    }
}
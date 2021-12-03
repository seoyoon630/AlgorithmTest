package com.bri.androidstudy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        val queueSize = 5
        val sizes = listOf(30, 31, 0, 5)
        sizes.forEach { size ->
            repeat(size / queueSize + (size % queueSize).coerceAtMost(1) ) {
                println(it * queueSize..(it + 1) * queueSize)
            }
            println("=======================================")
        }
//        val queueSize = 5
//                repeat(users.size / queueSize + (users.size % queueSize % 1)) {
//                    val bitmaps =
//                        users.filterIndexed { index, _ -> index in (it * queueSize..(it + 1) * queueSize) }
    }
}
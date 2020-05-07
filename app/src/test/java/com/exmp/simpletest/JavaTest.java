package com.exmp.simpletest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class JavaTest {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int s : scoville) {
            list.add(s);
        }
        queue.addAll(list);
        int count = 0;
        while (queue.size() >= 2 && queue.peek() < K) {
            int t1 = queue.poll();
            int t2 = queue.poll();
            int mix = t1 + t2 * 2;
            queue.offer(mix);
            count++;
        }
        if (queue.poll() < K) return -1;
        else return count;
    }

//    private fun heap2(stock: Int, dates: IntArray, supplies: IntArray, k: Int): Int {
//        var start = 0
//        var end = stock
//        var count = 0
//        while (end < k) {
//            val queue: PriorityQueue<Int> = PriorityQueue() { t1, t2 -> t2 - t1 }
//            for (i in dates.indices) {
//                if (dates[i] in start..end) {
//                    queue.add(supplies[i])
//                } else if (dates[i] > end) break
//            }
//            start = end + 1
//            end += queue.poll()
//            count++
//        }
//        return count
//    }

    private int heap2(int stock, int[] dates, int[] supplies, int k) {
        int start = 0;
        int end = stock;
        int count = 0;
        while (end < k - 1) {

            PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
            for (int i = 0; i < dates.length; i++) {
                if (dates[i] >= start) {
                    if (dates[i] <= end) queue.add(supplies[i]);
                    else break;
                }
            }
            start = end + 1;
            end += queue.poll();
            count++;
        }
        return count;
    }
}
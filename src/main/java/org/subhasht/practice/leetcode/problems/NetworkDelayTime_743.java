package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/*
You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.



Example 1:


Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1


Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
public class NetworkDelayTime_743 {

    public static void main(String[] args) {
        NetworkDelayTime_743 solution = new NetworkDelayTime_743();

        Assertions.assertEquals(2, solution.networkDelayTime(new int [][] {
                {2,1,1},
                {2,3,1},
                {3,4,1}
        }, 4, 2));

        Assertions.assertEquals(1, solution.networkDelayTime(new int [][] {
                {1,2,1}
        }, 2, 1));

        Assertions.assertEquals(-1, solution.networkDelayTime(new int [][] {
                {1,2,1}
        }, 2, 2));
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<int[]>[] map = buildMap(times, n);

        Set<Integer> destinations = new HashSet<>();
        for(int i = 1; i <= n; i++) {
            destinations.add(i);
        }
        destinations.remove(k);
        PriorityQueue<Tuple> heap = new PriorityQueue<>(Comparator.comparingInt(t -> t.time));
        if(map[k] != null) {
            for(int [] arr : map[k]) {
                heap.add(new Tuple(arr[2], arr[1]));
            }
        }
        Tuple tp;
        while(!heap.isEmpty()) {
            tp = heap.remove();
            destinations.remove(tp.dest);
            if(destinations.isEmpty()) {
                return tp.time;
            }

            if(map[tp.dest] != null) {
                for(int [] arr : map[tp.dest]) {
                    if(destinations.contains(arr[1]))
                        heap.add(new Tuple(tp.time + arr[2], arr[1]));
                }
            }
        }

        return -1;
    }

    static class Tuple {
        int time, dest;
        public Tuple(int t, int d) {
            time = t;
            dest = d;
        }
    }

    ArrayList<int[]>[] buildMap(int [][] times, int n) {
        ArrayList<int[]>[] arr = new ArrayList[n+1];
        for(int [] ar : times) {
            if(arr[ar[0]] == null)
                arr[ar[0]] = new ArrayList();
            arr[ar[0]].add(ar);
        }
        return arr;
    }
}

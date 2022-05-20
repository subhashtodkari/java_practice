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
        Map<Integer, Map<Integer, Integer>> map = buildMap(times);

        Set<Integer> destinations = new HashSet<>();
        for(int i = 1; i <= n; i++) {
            destinations.add(i);
        }
        destinations.remove(k);
        PriorityQueue<Tuple> heap = new PriorityQueue<>(Comparator.comparingInt(t -> t.time));
        if(map.containsKey(k)) {
            for(Map.Entry<Integer,Integer> e : map.get(k).entrySet()) {
                heap.add(new Tuple(e.getValue(), e.getKey()));
            }
        }
        Tuple tp;
        while(!heap.isEmpty()) {
            tp = heap.remove();
            destinations.remove(tp.dest);
            if(destinations.isEmpty()) {
                return tp.time;
            }

            if(map.containsKey(tp.dest)) {
                for(Map.Entry<Integer,Integer> e : map.get(tp.dest).entrySet()) {
                    if(destinations.contains(e.getKey()))
                        heap.add(new Tuple(tp.time + e.getValue(), e.getKey()));
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

    Map<Integer, Map<Integer, Integer>> buildMap(int [][] times) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for(int [] arr : times) {
            map.putIfAbsent(arr[0], new HashMap<>());
            map.get(arr[0]).put(arr[1], arr[2]);
        }

        return map;
    }
}

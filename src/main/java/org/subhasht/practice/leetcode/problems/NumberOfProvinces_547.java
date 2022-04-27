package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Deque;
import java.util.LinkedList;

/*
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.



Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3


Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces_547 {

    public static void main(String[] args) {
        NumberOfProvinces_547 solution = new NumberOfProvinces_547();
        Assertions.assertEquals(0, solution.findCircleNum(new int [][] {}));
        Assertions.assertEquals(2, solution.findCircleNum(new int [][] {{1,1,0}, {1,1,0}, {0,0,1}}));
        Assertions.assertEquals(3, solution.findCircleNum(new int [][] {{1,0,0}, {0,1,0}, {0,0,1}}));
    }

    public int findCircleNum(int[][] isConnected) {
        DisjointSets dj = new DisjointSets(isConnected.length);
        for(int i = 0; i < isConnected.length; i++) {
            for(int j = 0; j < isConnected.length; j++) {
                if(i != j && isConnected[i][j] == 1) {
                    dj.unite(i, j);
                }
            }
        }
        return dj.groupsCount;
    }


    static class DisjointSets {
        int [] parents;
        int groupsCount;

        public DisjointSets(int total) {
            parents = new int [total];
            for(int i = 0; i < total; i++) {
                parents[i] = i;
            }
            groupsCount = total;
        }

        public int find(int i) {
            int parent = parents[i];
            Deque<Integer> q = new LinkedList<>();
            while(parent != i) {
                q.add(i);
                i = parents[i];
                parent = parents[i];
            }
            while(!q.isEmpty()) {
                parents[q.remove()] = parent;
            }
            return parent;
        }

        public void unite(int city1, int city2) {
            int parent1 = find(city1);
            int parent2 = find(city2);

            if(parent1 == parent2) {
                return;
            }

            groupsCount--;
            if(parent1 < parent2) {
                parents[parent2] = parent1;
            } else {
                parents[parent1] = parent2;
            }
        }
    }
}

package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

/*
In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position, you can walk one step to the left, right, up, or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.


Example 1:

Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.
Example 2:

Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold.
 */

public class PathWithMaximumGold_1219 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertEquals(24, solution.getMaximumGold(new int [][] {
                {0,6,0},{5,8,7},{0,9,0}
        }));
        Assertions.assertEquals(28, solution.getMaximumGold(new int [][] {
                {1,0,7},{2,0,6},{3,4,5},{0,3,0},{9,0,20}
        }));
    }

    //basic all scanning with DFS
    static class Solution {
        public int getMaximumGold(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            int max = 0;
            boolean [][] v = new boolean [rows][cols];
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    max = Math.max(max, calc(grid, rows, cols, i, j, v));
                }
            }
            return max;
        }

        int calc(int[][] g, int rows, int cols, int i, int j, boolean [][] v) {
            if(i < 0 || i >= rows || j < 0 || j >= cols || v[i][j] || g[i][j] == 0) return 0;
            v[i][j] = true;
            int l = calc(g, rows, cols, i, j-1, v);
            int r = calc(g, rows, cols, i, j+1, v);
            int u = calc(g, rows, cols, i-1, j, v);
            int d = calc(g, rows, cols, i+1, j, v);
            v[i][j] = false;
            return Math.max(l, Math.max(r, Math.max(u, d))) + g[i][j];
        }
    }
}

package org.subhasht.practice.leetcode.problems;

import java.util.Deque;
import java.util.LinkedList;

/*
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.



Example 1:


Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.
 */
public class MaxAreaOfIsland_695 {

    //faster
    static class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            int m = grid.length;
            int n = grid[0].length;
            boolean [][] visited = new boolean[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {

                    if (grid[i][j] == 1) {
                        max = Math.max(max, getArea(grid, i, j, visited));
                    }

                }
            }

            return max;
        }

        int getArea(int [][] grid, int i, int j, boolean [][] visited) {
            if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length ||
                    grid[i][j] == 0 || visited[i][j]) {
                return 0;
            }
            visited[i][j] = true;

            return 1 +
                    getArea(grid, i+1, j, visited) +
                    getArea(grid, i-1, j, visited) +
                    getArea(grid, i, j+1, visited) +
                    getArea(grid, i, j-1, visited);
        }
    }

    //little slower
    class Solution2 {
        public int maxAreaOfIsland(int[][] grid) {
            return bfs(grid);
        }

        public int bfs(int[][] grid) {
            int max = 0;
            boolean [][] v = new boolean [grid.length][grid[0].length];
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    if(grid[i][j] == 1 && v[i][j] == false) {
                        Deque<int[]> q = new LinkedList<>();
                        int [] point = new int [] {i, j};
                        q.add(point);
                        v[point[0]][point[1]] = true;
                        int sz = 0, area = 0;
                        while(!q.isEmpty()) {
                            sz = q.size();
                            for(int k = 0; k < sz; k++) {
                                point = q.remove();

                                area++;

                                if(point[0] > 0 && grid[point[0]-1][point[1]] == 1 && v[point[0]-1][point[1]] == false) {
                                    q.add(new int[] {point[0]-1, point[1]});
                                    v[point[0]-1][point[1]] = true;
                                }
                                if(point[0] < grid.length-1 && grid[point[0]+1][point[1]] == 1 && v[point[0]+1][point[1]] == false) {
                                    q.add(new int[] {point[0]+1, point[1]});
                                    v[point[0]+1][point[1]] = true;
                                }
                                if(point[1] > 0 && grid[point[0]][point[1]-1] == 1 && v[point[0]][point[1]-1] == false) {
                                    q.add(new int[] {point[0], point[1]-1});
                                    v[point[0]][point[1]-1] = true;
                                }
                                if(point[1] < grid[0].length-1 && grid[point[0]][point[1]+1] == 1 && v[point[0]][point[1]+1] == false) {
                                    q.add(new int[] {point[0], point[1]+1});
                                    v[point[0]][point[1]+1] = true;
                                }

                            }
                        }
                        if(area > max) {
                            max = area;
                        }
                    }
                }
            }

            return max;
        }
    }
}

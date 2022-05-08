package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

/*
Given an m x n matrix grid where each cell is either a wall 'W', an enemy 'E' or empty '0', return the maximum enemies you can kill using one bomb. You can only place the bomb in an empty cell.

The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since it is too strong to be destroyed.



Example 1:


Input: grid = [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3
Example 2:


Input: grid = [["W","W","W"],["0","0","0"],["E","E","E"]]
Output: 1


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] is either 'W', 'E', or '0'.
 */
public class BombEnemy_361 {

    public static void main(String[] args) {
        BombEnemy_361 solution = new BombEnemy_361();
        Assertions.assertEquals(3, solution.maxKilledEnemies(new char [][] {
                {'0','E','0','0'},
                {'E','0','W','E'},
                {'0','E','0','0'}
        }));
        Assertions.assertEquals(1, solution.maxKilledEnemies(new char [][] {
                {'W','W','W'},
                {'0','0','0'},
                {'E','E','E'}
        }));
    }

    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        int[][] col = new int[n][3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 3; j++)
                col[i][j] = -1;
        for (int i = 0; i < m; i++) {
            int left = -1;
            int right = -1;
            int rowCount = -1;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    int count = 0;
                    if (left <= j && j <= right)
                        count = rowCount;
                    else {
                        int k = j;
                        while (k >= 0 && grid[i][k] != 'W') {
                            if (grid[i][k] == 'E') count++;
                            k--;
                        }
                        left = k+1;
                        k = j;
                        while (k < n && grid[i][k] != 'W') {
                            if (grid[i][k] == 'E') count++;
                            k++;
                        }
                        right = k-1;
                        rowCount = count;
                    }
                    if (col[j][0] <= i && i <= col[j][1])
                        count += col[j][2];
                    else {
                        int k = i;
                        int colCount = 0;
                        while (k >= 0 && grid[k][j] != 'W') {
                            if (grid[k][j] == 'E') colCount++;
                            k--;
                        }
                        col[j][0] = k+1;
                        k = i;
                        while (k < m && grid[k][j] != 'W') {
                            if (grid[k][j] == 'E') colCount++;
                            k++;
                        }
                        col[j][1] = k-1;
                        col[j][2] = colCount;
                        count += colCount;
                    }
                    res = Math.max(res, count);
                }
            }
        }
        return res;
    }

    public int sol2(char[][] grid) {

        int count, max = 0, x;
        int left = 0, right = 0, top = 0, down = 0;

        int [][] g = new int [grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++) {
            count = 0;
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 'W') {
                    count = 0;
                } else if(grid[i][j] == 'E') {
                    count++;
                } else if(grid[i][j] == '0') {
                    g[i][j] += count;
                    if(g[i][j] > max)
                        max = g[i][j];
                }
            }
            count = 0;
            for(int j = grid[0].length-1; j >= 0; j--) {
                if(grid[i][j] == 'W') {
                    count = 0;
                } else if(grid[i][j] == 'E') {
                    count++;
                } else if(grid[i][j] == '0') {
                    g[i][j] += count;
                    if(g[i][j] > max)
                        max = g[i][j];
                }
            }
        }

        for(int j = 0; j < grid[0].length; j++) {
            count = 0;
            for(int i = 0; i < grid.length; i++) {
                if(grid[i][j] == 'W') {
                    count = 0;
                } else if(grid[i][j] == 'E') {
                    count++;
                } else if(grid[i][j] == '0') {
                    g[i][j] += count;
                    if(g[i][j] > max)
                        max = g[i][j];
                }
            }
            count = 0;
            for(int i = grid.length-1; i >= 0; i--) {
                if(grid[i][j] == 'W') {
                    count = 0;
                } else if(grid[i][j] == 'E') {
                    count++;
                } else if(grid[i][j] == '0') {
                    g[i][j] += count;
                    if(g[i][j] > max)
                        max = g[i][j];
                }
            }
        }

        return max;
    }

    public int sol1(char[][] grid) {
        int count, max = 0, x;
        int left = 0, right = 0, top = 0, down = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '0') {
                    count = 0;

                    x = i;
                    while(x >= 0 && grid[x][j] != 'W') {
                        if(grid[x][j] == 'E')
                            count++;
                        x--;
                    }

                    x = i;
                    while(x < grid.length && grid[x][j] != 'W') {
                        if(grid[x][j] == 'E')
                            count++;
                        x++;
                    }

                    x = j;
                    while(x >= 0 && grid[i][x] != 'W') {
                        if(grid[i][x] == 'E')
                            count++;
                        x--;
                    }

                    x = j;
                    while(x < grid[0].length && grid[i][x] != 'W') {
                        if(grid[i][x] == 'E')
                            count++;
                        x++;
                    }

                    if(count > max)
                        max = count;

                }
            }
        }

        return max;
    }
}

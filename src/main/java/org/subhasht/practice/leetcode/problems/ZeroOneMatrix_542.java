package org.subhasht.practice.leetcode.problems;

import java.util.Arrays;

/*
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.



Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.
 */
public class ZeroOneMatrix_542 {

    public int[][] updateMatrix(int[][] mat) {
        int [][] arr = clone(mat);
        boolean [][] visited = new boolean [mat.length][mat[0].length];
        int [][] processed = new int [mat.length * mat[0].length][2];
        int pWrite = 0, pRead = 0;

        for(int i = 0; i < mat.length; i++) {
            for(int j =0; j < mat[0].length; j++) {
                if(mat[i][j] == 0) {
                    processed[pWrite++] = new int [] {i, j};
                    visited[i][j] = true;
                }
            }
        }

        int x, y;
        while(pRead < mat.length * mat[0].length) {
            int [] p = processed[pRead++];
            int val = arr[p[0]][p[1]];

            x = p[0];
            y = p[1];

            if(x > 0 && !visited[x-1][y]) {
                arr[x-1][y] = val+1;
                visited[x-1][y] = true;
                processed[pWrite++] = new int [] {x-1, y};
            }

            if(x < arr.length-1 && !visited[x+1][y]) {
                arr[x+1][y] = val+1;
                visited[x+1][y] = true;
                processed[pWrite++] = new int [] {x+1, y};
            }

            if(y > 0 && !visited[x][y-1]) {
                arr[x][y-1] = val+1;
                visited[x][y-1] = true;
                processed[pWrite++] = new int [] {x, y-1};
            }

            if(y < arr[0].length-1 && !visited[x][y+1]) {
                arr[x][y+1] = val+1;
                visited[x][y+1] = true;
                processed[pWrite++] = new int [] {x, y+1};
            }
        }

        return arr;
    }

    int [][] clone(int [][] arr) {
        int [][] c = new int [arr.length][];
        for(int i = 0; i < arr.length; i++) {
            c[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return c;
    }
}

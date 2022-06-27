package org.subhasht.practice.leetcode.problems;

import org.subhasht.util.ArrayComparator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
This question is about implementing a basic elimination algorithm for Candy Crush.

Given an m x n integer array board representing the grid of candy where board[i][j] represents the type of candy. A value of board[i][j] == 0 represents that the cell is empty.

The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

If three or more candies of the same type are adjacent vertically or horizontally, crush them all at the same time - these positions become empty.
After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. No new candies will drop outside the top boundary.
After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
If there does not exist more candies that can be crushed (i.e., the board is stable), then return the current board.
You need to perform the above rules until the board becomes stable, then return the stable board.



Example 1:


Input: board = [[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]
Output: [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]
Example 2:

Input: board = [[1,3,5,5,2],[3,4,3,3,1],[3,2,4,5,2],[2,4,4,5,5],[1,4,4,1,1]]
Output: [[1,3,0,0,0],[3,4,0,5,2],[3,2,0,3,1],[2,4,0,5,2],[1,4,3,1,1]]


Constraints:

m == board.length
n == board[i].length
3 <= m, n <= 50
1 <= board[i][j] <= 2000
 */
public class CandyCrush_723 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int [][] arr = new int [][] {
                {110,5,112,113,114},
                {210,211,5,213,214},
                {310,311,3,313,314},
                {410,411,412,5,414},
                {5,1,512,3,3},
                {610,4,1,613,614},
                {710,1,2,713,714},
                {810,1,2,1,1},
                {1,1,2,2,2},
                {4,1,4,4,1014}
        };

        solution.candyCrush(arr);

        ArrayComparator.compare(new int [][] {
                {     0 ,      0 ,      0 ,      0 ,      0 },
                {     0 ,      0 ,      0 ,      0 ,      0 },
                {     0 ,      0 ,      0 ,      0 ,      0 },
                {   110 ,      0 ,      0 ,      0 ,    114 },
                {   210 ,      0 ,      0 ,      0 ,    214 },
                {   310 ,      0 ,      0 ,    113 ,    314 },
                {   410 ,      0 ,      0 ,    213 ,    414 },
                {   610 ,    211 ,    112 ,    313 ,    614 },
                {   710 ,    311 ,    412 ,    613 ,    714 },
                {   810 ,    411 ,    512 ,    713 ,   1014 }
        }, arr, false, false);
    }


    static class Solution {
        public int[][] candyCrush(int[][] board) {

            while(crush(board)) {

            }

            return board;
        }

        boolean crush(int[][] b) {
            int rows = b.length;
            int cols = b[0].length;

            //print(b);

            int [] rowCounter = new int [ cols ];
            int [] colCounter = new int [ rows ];

            Map<Integer, int []> map = new HashMap<>();

            for(int r = 0; r < rows; r++) {
                for(int c = 0; c < cols; c++) {

                    if(b[r][c] == 0) continue;

                    if(c > 0 && b[r][c] == b[r][c-1]) {
                        colCounter[r]++;
                    } else {
                        colCounter[r] = 0;
                    }

                    if(r > 0 && b[r][c] == b[r-1][c]) {
                        rowCounter[c]++;
                    } else {
                        rowCounter[c] = 0;
                    }

                    if(colCounter[r] >= 2) {
                        int start = c - colCounter[r];
                        int index = r * cols + start;
                        map.putIfAbsent(index, new int [] {0, 0});
                        map.get(index)[0] = colCounter[r];
                    }

                    if(rowCounter[c] >= 2) {
                        int start = r - rowCounter[c];
                        int index = start * cols + c;
                        map.putIfAbsent(index, new int [] {0, 0});
                        map.get(index)[1] = rowCounter[c];
                    }
                }
            }

            if(map.isEmpty()) return false;

            //mark
            for(int index : map.keySet()) {
                int r = index / cols;
                int c = index % cols;
                int[] skip = map.get(index);
                //System.out.println("crushing at ("+r+", "+c+") :: " + skip[0] + ", " + skip[1]);

                if(skip[0] > 0) {//horizontal skip
                    for(int i = c; i <= c + skip[0]; i++) {
                        b[r][i] = -1;
                    }
                }

                if(skip[1] > 0) {//vertical skip
                    for(int i = r; i <= r + skip[1]; i++) {
                        b[i][c] = -1;
                    }
                }
            }

            //print(b);

            //sweep
            for (int i = 0; i < cols; i++) {
                int write = rows - 1, read = write;
                while(read >= 0) {
                    if(b[read][i] != -1) {
                        b[write--][i] = b[read--][i];
                    } else {
                        read--;
                    }
                }
                while(write >= 0) {
                    b[write--][i] = 0;
                }
            }

            //print(b);

            return true;
        }

        void print(int [][] arr) {
            System.out.println("------ arr -------");
            for (int [] a : arr) {
                System.out.println(Arrays.stream(a).boxed().map(num -> String.format(" %5d ", num)).collect(Collectors.toList()));
            }
            System.out.println("------ arr -------");
        }

    }
}

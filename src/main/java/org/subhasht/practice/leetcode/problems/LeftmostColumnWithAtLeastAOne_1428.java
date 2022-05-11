package org.subhasht.practice.leetcode.problems;

import java.util.List;

public class LeftmostColumnWithAtLeastAOne_1428 {

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);

        int min = cols;

        for(int i = 0; i < rows; i++) {
            if(binaryMatrix.get(i, cols-1) == 0)
                continue;

            int l = 0, m, r = cols-1, v;
            while(l < r) {
                m = (l + r) / 2;
                v = binaryMatrix.get(i, m);
                if(v == 0)
                    l = m+1;
                else
                    r = m;
            }

            if(r < min)
                min = r;
        }

        return min == cols ? -1 : min;
    }

    // This is the BinaryMatrix's API interface.
    // You should not implement it, or speculate about its implementation
    static class BinaryMatrix {
        int [][] grid;

        public BinaryMatrix(int[][] grid) {
            this.grid = grid;
        }

        public int get(int row, int col) {
            return grid[row][col];
        }

        public List<Integer> dimensions() {
            return List.of(grid.length, grid[0].length);
        }
    }
}

package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

/*
In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino. (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.

Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are the same.

If it cannot be done, return -1.



Example 1:


Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
Output: 2
Explanation:
The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
Example 2:

Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
Output: -1
Explanation:
In this case, it is not possible to rotate the dominoes to make one row of values equal.


Constraints:

2 <= tops.length <= 2 * 104
bottoms.length == tops.length
1 <= tops[i], bottoms[i] <= 6
 */
public class MinimumDominoRotationsForEqualRow_1007 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertEquals(2, solution.minDominoRotations(
                new int [] {1,2,1,3,1,1},
                new int [] {1,1,4,1,5,6}
        ));

        Assertions.assertEquals(-1, solution.minDominoRotations(
                new int [] {2,1,3,1,1},
                new int [] {1,4,2,5,6}
        ));

        Assertions.assertEquals(0, solution.minDominoRotations(
                new int [] {2,2,2,2,2},
                new int [] {1,4,2,5,6}
        ));
    }


    //faster
    static class Solution {
        public int minDominoRotations(int[] tops, int[] bottoms) {
            int n = tops.length;
            int [][] dp = new int [7][3];

            for(int i = 0; i < n; i++) {
                dp[tops[i]][0]++;
                dp[bottoms[i]][1]++;

                if(tops[i] == bottoms[i]) {
                    dp[tops[i]][2]++; //overlapping or domino with same number on both side
                }
            }

            int min = Integer.MAX_VALUE;
            for(int i = 1; i < 7; i++) {
                if(dp[i][0] + dp[i][1] - dp[i][2] >= n) {
                    min = Math.min(min, Math.min(dp[i][0], dp[i][1]) - (dp[i][0] + dp[i][1] - n));
                }
            }
            return min == Integer.MAX_VALUE ? -1 : min;
        }
    }

    //quite slower
    static class Solution1 {
        public int minDominoRotations(int[] tops, int[] bottoms) {
            int n = tops.length;
            Set<Integer>[][] dp = new Set [7][3];
            for(int i = 0; i < 7; i++)
                for(int j = 0; j < 3; j++)
                    dp[i][j] = new HashSet<>();


            for(int i = 0; i < n; i++) {
                dp[tops[i]][0].add(i);
                dp[bottoms[i]][1].add(i);

                dp[tops[i]][2].add(i);
                dp[bottoms[i]][2].add(i);
            }

            int min = Integer.MAX_VALUE;
            for(int i = 1; i < 7; i++) {
                if(dp[i][2].size() >= n) {
                    min = Math.min(min, Math.min(dp[i][0].size(), dp[i][1].size())) - (dp[i][0].size() + dp[i][1].size() - n);
                }
            }
            return min == Integer.MAX_VALUE ? -1 : min;
        }
    }
}

package org.subhasht.practice.leetcode.problems;

import java.util.Arrays;

/*
The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagaram:

A chess knight can move as indicated in the chess diagram below:


We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).


Given an integer n, return how many distinct phone numbers of length n we can dial.

You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.

As the answer may be very large, return the answer modulo 109 + 7.



Example 1:

Input: n = 1
Output: 10
Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
Example 2:

Input: n = 2
Output: 20
Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
Example 3:

Input: n = 3131
Output: 136006598
Explanation: Please take care of the mod.


Constraints:

1 <= n <= 5000
 */
public class KnightDialer_935 {

    static class Solution {

        static final int REM = 1000000007;

        public int knightDialer(int n) {
            long [] prev = new long [10];
            long [] next = new long [10];
            long [] temp;
            for(int i = 0; i < n; i++) {
                if(i == 0) {
                    Arrays.fill(prev, 1);
                } else {
                    Arrays.fill(next, 0);
                    for (int j = 0; j < 10; j++) {
                        switch (j) {
                            case 0:
                                next[4] += prev[j];
                                next[4] %= REM;
                                next[6] += prev[j];
                                next[6] %= REM;
                                break;
                            case 1:
                                next[6] += prev[j];
                                next[6] %= REM;
                                next[8] += prev[j];
                                next[8] %= REM;
                                break;
                            case 2:
                                next[7] += prev[j];
                                next[7] %= REM;
                                next[9] += prev[j];
                                next[9] %= REM;
                                break;
                            case 3:
                                next[4] += prev[j];
                                next[4] %= REM;
                                next[8] += prev[j];
                                next[8] %= REM;
                                break;
                            case 4:
                                next[3] += prev[j];
                                next[3] %= REM;
                                next[9] += prev[j];
                                next[9] %= REM;
                                next[0] += prev[j];
                                next[0] %= REM;
                                break;
                            case 6:
                                next[1] += prev[j];
                                next[1] %= REM;
                                next[7] += prev[j];
                                next[7] %= REM;
                                next[0] += prev[j];
                                next[0] %= REM;
                                break;
                            case 7:
                                next[2] += prev[j];
                                next[2] %= REM;
                                next[6] += prev[j];
                                next[6] %= REM;
                                break;
                            case 8:
                                next[1] += prev[j];
                                next[1] %= REM;
                                next[3] += prev[j];
                                next[3] %= REM;
                                break;
                            case 9:
                                next[2] += prev[j];
                                next[2] %= REM;
                                next[4] += prev[j];
                                next[4] %= REM;
                                break;
                        }
                    }
                    temp = prev;
                    prev = next;
                    next = temp;
                }
            }

            long total = 0;
            for(int i = 0; i < 10; i++) {
                total += prev[i];
                total %= REM;
            }

            return (int) total;
        }
    }
}

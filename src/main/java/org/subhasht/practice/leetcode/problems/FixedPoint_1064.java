package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

/*
Given an array of distinct integers arr, where arr is sorted in ascending order, return the smallest index i that satisfies arr[i] == i. If there is no such index, return -1.



Example 1:

Input: arr = [-10,-5,0,3,7]
Output: 3
Explanation: For the given array, arr[0] = -10, arr[1] = -5, arr[2] = 0, arr[3] = 3, thus the output is 3.
Example 2:

Input: arr = [0,2,5,8,17]
Output: 0
Explanation: arr[0] = 0, thus the output is 0.
Example 3:

Input: arr = [-10,-5,3,4,7,9]
Output: -1
Explanation: There is no such i that arr[i] == i, thus the output is -1.


Constraints:

1 <= arr.length < 104
-109 <= arr[i] <= 109


Follow up: The O(n) solution is very straightforward. Can we do better?
 */
public class FixedPoint_1064 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertEquals(3, solution.fixedPoint(new int [] {-10,-5,0,3,7}));
        Assertions.assertEquals(0, solution.fixedPoint(new int [] {0,2,5,8,17}));
        Assertions.assertEquals(-1, solution.fixedPoint(new int [] {-10,-5,3,4,7,9}));
    }

    static class Solution {

        //O(Log N) - binary search
        public int fixedPoint(int[] arr) {
            int l = 0, r = arr.length-1, m, min = arr.length;
            while(l < r) {

                m = l + ((r-l)/2);
                //System.out.println("l: " + l + ", r: " + r + ", m: " + m);
                if( arr[m] == m) {
                    if (m < min)
                        min = m;
                    r = m;
                }
                if(m == l) {
                    if( r == arr[r] && r < min)
                        min = r;
                    break;
                }

                if(arr[m] > m) {
                    r = m ;
                } else if (arr[m] < m) {
                    l = m ;
                }
            }
            return min == arr.length ? -1 : min;
        }
    }
}

package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;
import org.subhasht.util.ArrayComparator;

/*
You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.

In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.

Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.

Each answer[i] is calculated considering the initial state of the boxes.



Example 1:

Input: boxes = "110"
Output: [1,1,3]
Explanation: The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first box in one operation.
2) Second box: you will have to move one ball from the first box to the second box in one operation.
3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
Example 2:

Input: boxes = "001011"
Output: [11,8,5,4,3,4]


Constraints:

n == boxes.length
1 <= n <= 2000
boxes[i] is either '0' or '1'.
 */
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox_1769 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayComparator.compare(new int [] {1,1,3}, solution.minOperations("110"), false);
        ArrayComparator.compare(new int [] {11,8,5,4,3,4}, solution.minOperations("001011"), false);
    }

    //Time : O(N)
    static class Solution {
        public int[] minOperations(String boxes) {
            int [] ans = new int [boxes.length()];

            int numOfOnes = 0;
            int prevCount = 0;
            for(int i = 0; i < boxes.length(); i++) {
                ans[i] = prevCount + numOfOnes;
                prevCount = prevCount + numOfOnes;
                if (boxes.charAt(i) == '1') {
                    numOfOnes++;
                }
            }
            numOfOnes = 0;
            prevCount = 0;
            for(int i = boxes.length()-1; i > -1; i--) {
                ans[i] += prevCount + numOfOnes;
                prevCount = prevCount + numOfOnes;
                if (boxes.charAt(i) == '1') {
                    numOfOnes++;
                }
            }

            return ans;
        }
    }

    //extra memory used
    //Time : O(N)
    static class Solution1 {
        public int[] minOperations(String boxes) {
            int n = boxes.length();
            int [] left = new int [n];
            int [] right = new int [n];
            int [] ans = new int [n];

            int numOfBalls = 0;
            for(int i = 0; i < n; i++) {
                left[i] += (i > 0 ? left[i-1] : 0 ) + numOfBalls;

                if(boxes.charAt(i) == '1') {
                    numOfBalls++;
                }
            }

            numOfBalls = 0;
            for(int i = n-1; i >= 0;  i--) {
                right[i] += (i < n-1 ? right[i+1] : 0 ) + numOfBalls;

                if(boxes.charAt(i) == '1') {
                    numOfBalls++;
                }

                ans[i] = left[i] + right[i];
            }

            return ans;
        }
    }
}

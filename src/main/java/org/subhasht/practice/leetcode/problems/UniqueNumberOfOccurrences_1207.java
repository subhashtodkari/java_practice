package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

/*
Given an array of integers arr, return true if the number of occurrences of each value in the array is unique, or false otherwise.



Example 1:

Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
Example 2:

Input: arr = [1,2]
Output: false
Example 3:

Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true


Constraints:

1 <= arr.length <= 1000
-1000 <= arr[i] <= 1000
 */
public class UniqueNumberOfOccurrences_1207 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertTrue(solution.uniqueOccurrences(new int [] {1,2,2,3,3,3,4,4,4,4}));
        Assertions.assertFalse(solution.uniqueOccurrences(new int [] {1,2,2,3,3,3,4,4,4}));
    }

    static class Solution {
        public boolean uniqueOccurrences(int[] arr) {
            int [] countArray = new int [2001];
            for(int num : arr) {
                countArray[num + 1000]++;
            }
            Set<Integer> set = new HashSet<>();
            for(int num : countArray) {
                if(num > 0) {
                    if(!set.add(num))
                        return false;
                }
            }
            return true;
        }
    }
}

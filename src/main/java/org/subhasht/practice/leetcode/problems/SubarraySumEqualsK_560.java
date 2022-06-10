package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2


Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
 */
public class SubarraySumEqualsK_560 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertEquals(2, solution.subarraySum(new int [] {1,1,1}, 2));
        Assertions.assertEquals(2, solution.subarraySum(new int [] {1,2,3}, 3));
    }

    static class Solution {

        //O(N)
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            int runningSum = 0;
            Map<Integer, Integer> sumCount = new HashMap<>();
            sumCount.put(0, 1);
            for (int num : nums) {
                runningSum += num;

                if (sumCount.containsKey(runningSum - k)) {
                    count += sumCount.get(runningSum - k);
                }

                sumCount.put(runningSum, sumCount.getOrDefault(runningSum, 0) + 1);
            }
            return count;
        }

        //O(N)
        public int subarraySum1(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            int sum = 0, count = 0;
            for (int num : nums) {
                sum += num;
                count += map.getOrDefault(sum - k, 0);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }

        //slower O(N^2)
        public int subarraySum2(int[] nums, int k) {
            int count = 0;
            int sum;
            for(int i = 0; i < nums.length; i++) {
                sum = 0;
                for(int j = i; j < nums.length; j++) {
                    sum += nums[j];
                    if ( sum == k ) {
                        count++;
                    }
                }
            }
            return count;
        }
    }
}

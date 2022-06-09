package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

You may assume the input array always has a valid answer.



Example 1:

Input: nums = [3,5,2,1,6,4]
Output: [3,5,1,6,2,4]
Explanation: [1,6,2,5,3,4] is also accepted.
Example 2:

Input: nums = [6,6,5,6,3,8]
Output: [6,6,5,6,3,8]


Constraints:

1 <= nums.length <= 5 * 104
0 <= nums[i] <= 104
It is guaranteed that there will be an answer for the given input nums.
 */
public class WiggleSort_280 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int [] nums = new int[] {3};
        solution.wiggleSort(nums);
        Assertions.assertTrue(validate(nums));

        int [] nums1 = new int[] {3,2,1};
        solution.wiggleSort(nums1);
        Assertions.assertTrue(validate(nums1));

        int [] nums2 = new int[] {1,2,3};
        solution.wiggleSort(nums2);
        Assertions.assertTrue(validate(nums2));

        int [] nums3 = new int[] {3,2,1,0,-1};
        solution.wiggleSort(nums3);
        Assertions.assertTrue(validate(nums3));

        int [] nums4 = new int[] {4,4,4,4,4};
        solution.wiggleSort(nums4);
        Assertions.assertTrue(validate(nums4));
    }

    static boolean validate(int [] nums) {
        for(int i = 1; i < nums.length; i++) {
            if ( (i % 2 == 0 && nums[i] > nums[i-1]) || (nums[i] < nums[i]) )
                return false;
        }
        return true;
    }

    static class Solution {
        public void wiggleSort(int[] nums) {
            Arrays.sort(nums);
            for(int i = 1; i < nums.length-1; i+=2) {
                int t = nums[i];
                nums[i] = nums[i+1];
                nums[i+1] = t;
            }
        }
    }

    static class Solution1 {
        public void wiggleSort(int[] nums) {

            PriorityQueue<Integer> heap = new PriorityQueue<>(nums.length);
            for(int n : nums)
                heap.add(n);

            nums[0] = heap.remove();

            for(int i = 1; i < nums.length-1; i+=2) {
                int t = heap.remove();
                nums[i] = heap.remove();
                nums[i+1] = t;
            }

            if(nums.length > 1 && nums.length % 2 == 0) {
                nums[nums.length-1] = heap.remove();
            }
        }
    }
}

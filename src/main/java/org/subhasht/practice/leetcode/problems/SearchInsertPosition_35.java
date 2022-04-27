package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

/*
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1
Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4


Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums contains distinct values sorted in ascending order.
-104 <= target <= 104
 */
public class SearchInsertPosition_35 {

    public static void main(String[] args) {
        SearchInsertPosition_35 solution = new SearchInsertPosition_35();
        Assertions.assertEquals(0, solution.searchInsert(new int [] {}, 2));
        Assertions.assertEquals(0, solution.searchInsert(new int [] {2}, 2));
        Assertions.assertEquals(1, solution.searchInsert(new int [] {1}, 2));
        Assertions.assertEquals(0, solution.searchInsert(new int [] {10, 20, 30}, -2));
        Assertions.assertEquals(0, solution.searchInsert(new int [] {10, 20, 30}, 10));
        Assertions.assertEquals(1, solution.searchInsert(new int [] {10, 20, 30}, 11));
        Assertions.assertEquals(1, solution.searchInsert(new int [] {10, 20, 30}, 19));
        Assertions.assertEquals(1, solution.searchInsert(new int [] {10, 20, 30}, 20));
        Assertions.assertEquals(2, solution.searchInsert(new int [] {10, 20, 30}, 21));
        Assertions.assertEquals(2, solution.searchInsert(new int [] {10, 20, 30}, 29));
        Assertions.assertEquals(2, solution.searchInsert(new int [] {10, 20, 30}, 30));
        Assertions.assertEquals(3, solution.searchInsert(new int [] {10, 20, 30}, 31));
    }


    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, middle;
        while(left <= right) {
            middle = (left + right) / 2;
            if(nums[middle] == target) {
                return middle;
            } else {
                if(middle == left) {
                    if(target < nums[left])
                        return left;
                    else if (target <= nums[right])
                        return right;
                    else
                        return right+1;
                } else if (target < nums[middle]) {
                    right = middle - 1;
                } else if (target > nums[middle]) {
                    left = middle + 1;
                }
            }
        }
        return 0;
    }
}

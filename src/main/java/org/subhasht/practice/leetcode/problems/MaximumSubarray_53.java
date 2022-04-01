package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

public class MaximumSubarray_53 {

    public static void main(String[] args) {
        MaximumSubarray_53 solution = new MaximumSubarray_53();
        Assertions.assertEquals(6, solution.maxSubArray(new int [] {-2,1,-3,4,-1,2,1,-5,4}));
        Assertions.assertEquals(-2, solution.maxSubArray(new int [] {-2}));
        Assertions.assertEquals(0, solution.maxSubArray(new int [] {0}));
        Assertions.assertEquals(2, solution.maxSubArray(new int [] {2}));
        Assertions.assertEquals(23, solution.maxSubArray(new int [] {5,4,-1,7,8}));
    }


    public int maxSubArray(int[] nums) {
        return sol3(nums);
    }

    //O(N)
    public int sol3(int[] nums) {
        int mx = nums[0], currSum = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(currSum < 0) {
                if(nums[i] > currSum) {
                    currSum = nums[i];
                }
            } else {
                if(currSum + nums[i] > 0) {
                    currSum += nums[i];
                } else {
                    currSum = 0;//reset
                }
            }

            if(currSum > mx) {
                mx = currSum;
            }
        }
        return mx;
    }

    //timeout 204/209 - O(N^2)
    public int sol2(int[] nums) {
        int mx = Integer.MIN_VALUE, currSum;
        for(int i = 0; i < nums.length; i++) {
            currSum = 0;
            for(int j = i; j < nums.length; j++) {
                currSum += nums[j];
                mx = Math.max(mx, currSum);
            }
        }
        return mx;
    }

    //timeout 200/209 - O(N^3)
    public int sol1(int[] nums) {
        int mx = Integer.MIN_VALUE, currSum;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i; j < nums.length; j++) {
                currSum = 0;
                for(int k = i; k <= j; k++) {
                    currSum += nums[k];
                }
                mx = Math.max(mx, currSum);
            }
        }
        return mx;
    }
}

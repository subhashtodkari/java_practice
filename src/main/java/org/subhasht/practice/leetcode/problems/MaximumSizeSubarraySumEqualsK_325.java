package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

public class MaximumSizeSubarraySumEqualsK_325 {

    public static void main(String[] args) {
        MaximumSizeSubarraySumEqualsK_325 solution = new MaximumSizeSubarraySumEqualsK_325();
        Assertions.assertEquals(4, solution.maxSubArrayLen(new int [] {1,-1,5,-2,3}, 3));
    }

    //hardly passing
    public int maxSubArrayLen(int[] nums, int k) {
        int [] lSum = new int [nums.length+1];
        for(int i = 0; i < nums.length; i++) {
            lSum[i+1] = lSum[i] + nums[i];
        }
        int total = lSum[lSum.length-1];
        if(total == k)
            return nums.length;

        int [] rSum = new int [nums.length+1];

        int start, end, sum, len = nums.length - 1;
        while(len > 0) {
            start = 0;
            end = start + len - 1;
            rSum[len] = rSum[len+1] + nums[len];
            while(end < nums.length) {
                sum = total - lSum[start] - rSum[end+1];
                if(sum == k) {
                    return len;
                }
                start++;
                end++;
            }
            len--;
        }
        return 0;
    }

    //timeout
    public int solution2(int[] nums, int k) {
        int max = 0, total = 0;
        int [] lSum = new int [nums.length];
        int [] rSum = new int [nums.length];
        for(int i = 0; i < nums.length; i++) {
            total += nums[i];
            lSum[i] = total;
        }

        if(total == k)
            return nums.length;

        total = 0;
        for(int i = nums.length-1; i >= 0; i--) {
            rSum[i] = total - (i == 0 ? 0 : lSum[i-1]);
        }

        int start, len = nums.length - 1, end;
        while(len > 0) {
            start = 0;
            end = start + len - 1;
            while(end < nums.length) {
                int sum = total - (start == 0 ? 0 : lSum[start-1]) - (end == nums.length - 1 ? 0 : rSum[end + 1]);
                if(sum == k) {
                    return len;
                }
                start++;
                end++;
            }
            len--;
        }

        return max;
    }

    //obviously timeout
    public int solution1(int[] nums, int k) {
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            int sum = 0;
            for(int j = i; j < nums.length; j++) {
                sum += nums[j];
                if(sum == k && (j - i + 1) > max) {
                    max = j - i + 1;
                }
            }
        }
        return max;
    }
}

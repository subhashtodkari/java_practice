package org.subhasht.practice.leetcode.problems;

public class HouseRobber_198 {

    public int rob(int[] nums) {
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return Math.max(nums[0], nums[1]);
        int last = nums.length - 1;
        nums[last-2] += nums[last];

        last -= 3;
        while(last >= 0) {
            nums[last] += Math.max(nums[last+2], nums[last+3]);
            last--;
        }
        return Math.max(nums[0], nums[1]);
    }
}

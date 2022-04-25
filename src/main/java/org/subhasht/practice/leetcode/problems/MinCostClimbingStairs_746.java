package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/*
You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.



Example 1:

Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.
Example 2:

Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.


Constraints:

2 <= cost.length <= 1000
0 <= cost[i] <= 999
 */
public class MinCostClimbingStairs_746 {

    public static void main(String[] args) {
        MinCostClimbingStairs_746 solution = new MinCostClimbingStairs_746();
        Assertions.assertEquals(15, solution.minCostClimbingStairs(new int [] {10,15,20}));
        Assertions.assertEquals(6, solution.minCostClimbingStairs(new int [] {1,100,1,1,1,100,1,1,100,1}));
    }

    public int minCostClimbingStairs(int[] cost) {
        //return minCostClimbingStairsRecursively(cost);
        return minCostClimbingStairsUsingDP(cost);
    }

    public int minCostClimbingStairsUsingDP(int[] cost) {
        if(cost.length == 2)
            return Math.min(cost[cost.length-1], cost[cost.length-2]);
        int [] dp = new int [cost.length];
        dp[dp.length-1] = cost[cost.length-1];
        dp[dp.length-2] = cost[cost.length-2];

        for(int i = cost.length-3; i >= 0; i--) {
            dp[i] = cost[i] + Math.min(dp[i+1], dp[i+2]);
        }
        return Math.min(dp[0], dp[1]);
    }

    public int minCostClimbingStairsRecursively(int[] cost) {
        int c0 = minCostClimbingStairs(cost, 0);
        int c1 = minCostClimbingStairs(cost, 1);
        return Math.min(c0, c1);
    }

    Map<Integer, Integer> cache = new HashMap<>();
    public int minCostClimbingStairs(int[] cost, int from) {
        if(from >= cost.length)
            return 0;
        if(from >= cost.length - 2)
            return cost[from];
        if(cache.containsKey(from))
            return cache.get(from);
        int c1 = minCostClimbingStairs(cost, from+1);
        int c2 = minCostClimbingStairs(cost, from+2);
        int c = cost[from] + Math.min(c1, c2);
        cache.put(from, c);
        return c;
    }

}

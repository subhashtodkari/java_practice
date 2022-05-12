package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
You have n dice and each die has k faces numbered from 1 to k.

Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.



Example 1:

Input: n = 1, k = 6, target = 3
Output: 1
Explanation: You throw one die with 6 faces.
There is only one way to get a sum of 3.
Example 2:

Input: n = 2, k = 6, target = 7
Output: 6
Explanation: You throw two dice, each with 6 faces.
There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
Example 3:

Input: n = 30, k = 30, target = 500
Output: 222616187
Explanation: The answer must be returned modulo 109 + 7.


Constraints:

1 <= n, k <= 30
1 <= target <= 1000
 */
public class NumberOfDiceRollsWithTargetSum_1155 {

    public static void main(String[] args) {
        NumberOfDiceRollsWithTargetSum_1155 solution = new NumberOfDiceRollsWithTargetSum_1155();
        //Assertions.assertEquals(1, solution.numRollsToTarget(1, 6, 3));
        //Assertions.assertEquals(6, solution.numRollsToTarget(2, 6, 7));
        Assertions.assertEquals(1, solution.numRollsToTarget(30, 30, 30));
        Assertions.assertEquals(30, solution.numRollsToTarget(30, 30, 31));
        Assertions.assertEquals(222616187, solution.numRollsToTarget(30, 30, 500));

    }

    public int numRollsToTarget(int n, int k, int target) {
        return (int) find(n, k, target) % 1000000007;
    }

    Map<String, Long> cache = new HashMap<>();

    public long find(int n, int k, int target) {
        if(target < n || target > n * k)
            return 0L;
        if(n == 1)
            return 1L;

        String key = n + "-" + target;
        if(cache.containsKey(key))
            return cache.get(key);

        long total = 0;
        if(n == 2) {
            long max = Math.min(target-1, k);
            long min = target - max;
            total = max - min + 1;
        } else {
            for (int i = 1; i <= k; i++) {
                long t = find(n - 1, k, target - i);
                if (t > 0) {
                    total += t;
                    total = total % 1000000007;
                }
            }
        }
        cache.put(key, total);
        return total;
    }



    public int numRollsToTarget1(int n, int k, int target) {
        int [] dice = new int [n];
        Arrays.fill(dice, 1);
        int sum;
        long count = 0;
        do {
            sum = 0;
            for(int f : dice)
                sum += f;
            if(sum == target)
                count++;
        } while(next(dice, k, 0));
        return (int) count % 1000000007;
    }

    boolean next(int [] dice, int k, int d) {
        if(d == dice.length) return false;
        dice[d]++;
        if(dice[d] > k) {
            dice[d] = 1;
            return next(dice, k, d+1);
        }
        return true;
    }
}

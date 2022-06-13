package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

If multiple answers are possible, return any of them.

It is guaranteed that the length of the answer string is less than 104 for all the given inputs.



Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 4, denominator = 333
Output: "0.(012)"


Constraints:

-231 <= numerator, denominator <= 231 - 1
denominator != 0
 */
public class FractionToRecurringDecimal_166 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertEquals("2", solution.fractionToDecimal(2, 1));
        Assertions.assertEquals("-0.58(3)", solution.fractionToDecimal(7, -12));
        Assertions.assertEquals("-0.58(3)", solution.fractionToDecimal(-7, 12));
        Assertions.assertEquals("0.0000000004656612873077392578125", solution.fractionToDecimal(-1, -2147483648));
    }

    static class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            StringBuilder sb = new StringBuilder();
            if(numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0)
                sb.append("-");

            long n = numerator;
            n = Math.abs(n);
            long d = denominator;
            d = Math.abs(d);

            long q = n / d;
            long r = n % d;
            //System.out.println("Q: " + q + ", R " + r + ", D: " + denominator + ", long D: " + d);

            sb.append(q);
            if(r != 0)
                sb.append(".");

            Map<Long, Integer> remIndexMap = new HashMap<>();
            Deque<Long> list = new LinkedList<>();
            int i = 0;
            r *= 10;
            int repeatFromIdx = -1;
            while(r != 0) {
                if(remIndexMap.containsKey(r)) {
                    repeatFromIdx = remIndexMap.get(r);
                    break;
                }
                q = r / d;
                remIndexMap.put(r, i++);
                //System.out.println("map r: " + r + " == i " + (i-1));

                if(q == 0) {
                    r *= 10;
                } else {
                    r = (r % d) * 10;
                }
                list.add(q);
                //System.out.println("list >> " + q + ", next r : " + r);
            }
            i = 0;
            while(!list.isEmpty()) {
                if(repeatFromIdx == i++)
                    sb.append("(");
                sb.append(list.removeFirst());
            }
            if(repeatFromIdx != -1)
                sb.append(")");
            return sb.toString();
        }
    }
}

package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;


/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.

 */
public class RomanToInteger_13 {

    public static void main(String[] args) {
        RomanToInteger_13 solution = new RomanToInteger_13();
        Assertions.assertEquals(1, solution.romanToInt("I"));
        Assertions.assertEquals(5, solution.romanToInt("V"));
        Assertions.assertEquals(10, solution.romanToInt("X"));
        Assertions.assertEquals(50, solution.romanToInt("L"));
        Assertions.assertEquals(100, solution.romanToInt("C"));
        Assertions.assertEquals(500, solution.romanToInt("D"));
        Assertions.assertEquals(1000, solution.romanToInt("M"));

        Assertions.assertEquals(3, solution.romanToInt("III"));
        Assertions.assertEquals(4, solution.romanToInt("IV"));
        Assertions.assertEquals(9, solution.romanToInt("IX"));
        Assertions.assertEquals(11, solution.romanToInt("XI"));
        Assertions.assertEquals(14, solution.romanToInt("XIV"));
        Assertions.assertEquals(30, solution.romanToInt("XXX"));
        Assertions.assertEquals(40, solution.romanToInt("XL"));
        Assertions.assertEquals(90, solution.romanToInt("XC"));
        Assertions.assertEquals(400, solution.romanToInt("CD"));
        Assertions.assertEquals(900, solution.romanToInt("CM"));

        Assertions.assertEquals(58, solution.romanToInt("LVIII"));
        Assertions.assertEquals(1994, solution.romanToInt("MCMXCIV"));

    }

    static Map<Character, Integer> valueMap = new HashMap<>();

    static {
        valueMap.put('I', 1);
        valueMap.put('V', 5);
        valueMap.put('X', 10);
        valueMap.put('L', 50);
        valueMap.put('C', 100);
        valueMap.put('D', 500);
        valueMap.put('M', 1000);
    }


    public int romanToInt(String s) {
        int val = 0;

        for(int i = 0; i < s.length(); i++) {
            val += valueMap.get(s.charAt(i));
            if(i > 0 && valueMap.get(s.charAt(i)) > valueMap.get(s.charAt(i-1))) {
                val -= 2 * valueMap.get(s.charAt(i-1));
            }
        }
        return val;
    }
}

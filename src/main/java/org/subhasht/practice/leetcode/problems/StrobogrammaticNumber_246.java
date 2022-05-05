package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/*
Given a string num which represents an integer, return true if num is a strobogrammatic number.

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).



Example 1:

Input: num = "69"
Output: true
Example 2:

Input: num = "88"
Output: true
Example 3:

Input: num = "962"
Output: false


Constraints:

1 <= num.length <= 50
num consists of only digits.
num does not contain any leading zeros except for zero itself.
 */
public class StrobogrammaticNumber_246 {

    public static void main(String[] args) {
        StrobogrammaticNumber_246 solution = new StrobogrammaticNumber_246();
        Assertions.assertTrue(solution.isStrobogrammatic("0"));
        Assertions.assertTrue(solution.isStrobogrammatic("1"));
        Assertions.assertFalse(solution.isStrobogrammatic("6"));
        Assertions.assertTrue(solution.isStrobogrammatic("8"));
        Assertions.assertFalse(solution.isStrobogrammatic("9"));
        Assertions.assertTrue(solution.isStrobogrammatic("69"));
        Assertions.assertTrue(solution.isStrobogrammatic("96"));
        Assertions.assertTrue(solution.isStrobogrammatic("11"));
        Assertions.assertFalse(solution.isStrobogrammatic("10"));
        Assertions.assertFalse(solution.isStrobogrammatic("16"));
        Assertions.assertFalse(solution.isStrobogrammatic("629"));
    }

    static Map<Character, Character> map = new HashMap<>();

    static {
        map.put('0', '0');
        map.put('1', '1');
        //map.put('2', '5');
        //map.put('5', '2');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
    }
    public boolean isStrobogrammatic(String num) {
        for(int i = 0; i < num.length(); i++) {
            if(map.containsKey(num.charAt(i))) {
                if(map.get(num.charAt(i)) != num.charAt(num.length() - 1 - i))
                    return false;
            } else {
                return false;
            }
        }
        return true;
    }
}

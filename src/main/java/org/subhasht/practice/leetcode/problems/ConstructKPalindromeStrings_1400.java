package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/*
Given a string s and an integer k, return true if you can use all the characters in s to construct k palindrome strings or false otherwise.



Example 1:

Input: s = "annabelle", k = 2
Output: true
Explanation: You can construct two palindromes using all characters in s.
Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
Example 2:

Input: s = "leetcode", k = 3
Output: false
Explanation: It is impossible to construct 3 palindromes using all the characters of s.
Example 3:

Input: s = "true", k = 4
Output: true
Explanation: The only possible solution is to put each character in a separate string.


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= 105
 */
public class ConstructKPalindromeStrings_1400 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertTrue(solution.canConstruct("annabelle", 2));
        Assertions.assertTrue(solution.canConstruct("annabelle", 9));
        Assertions.assertFalse(solution.canConstruct("leetcode", 3));
    }

    static class Solution {
        public boolean canConstruct(String s, int k) {
            if(k > s.length()) return false;

            Map<Character, Integer> map = new HashMap<>();
            for(char c : s.toCharArray()) {
                map.putIfAbsent(c, 0);
                map.put(c, map.get(c) + 1);
            }

            int odds = 0;
            for(char c : map.keySet()) {
                if(map.get(c) % 2 == 1) {
                    odds++;
                }
            }
            return odds <= k;
        }
    }
}

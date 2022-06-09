package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;


/*
Given a string s, find the length of the longest substring without repeating characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatingCharacters_3 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertEquals(0, solution.lengthOfLongestSubstring(""));
        Assertions.assertEquals(4, solution.lengthOfLongestSubstring("abcabcd"));
        Assertions.assertEquals(1, solution.lengthOfLongestSubstring("aaaaaa"));
    }

}

//fastest - sliding window
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int [] indexMap = new int [256];
        Arrays.fill(indexMap, -1);

        char [] arr = s.toCharArray();
        int start = 0, max = 0;
        for(int i = 0; i < arr.length; i++) {
            if( indexMap[ arr[i] ] != -1 ) {
                while( start <= indexMap[ arr[i] ] ) {
                    indexMap[ arr[start++] ] = -1;
                }
            } else {
                if( i - start + 1 > max)
                    max = i - start + 1;
            }
            indexMap[ arr[i] ] = i;
        }

        return max;
    }
}

//fastest - sliding window
class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        int start = 0, l, max = 0;
        int [] positions = new int [127];
        Arrays.fill(positions, -1);
        //System.out.println(Arrays.toString(positions));
        for(int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if( positions[c] != -1 && positions[c] >= start) {
                l = i - start;
                max = Math.max(max, l);
                start = positions[c] + 1;
            }
            positions[c] = i;
            //System.out.println(Arrays.toString(positions));
        }
        return Math.max(max, s.length() - start);
    }
}

//slower - linear search
class Solution1 {
    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int max = 0, l = 0;
        for(int i = 0; i < s.length(); i++) {
            int idx = s.indexOf(s.charAt(i), start);
            if (idx >= start && idx < i) {
                max = Math.max(max, l);
                start = idx + 1;
                l = i - start + 1;
            } else {
                l++;
            }
            System.out.println(i + "] start: " + start + ", l : " + l + ", max: " + max);
        }
        return Math.max(max, l);
    }
}

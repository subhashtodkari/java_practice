package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/*
Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.



Example 1:

Input: pattern = "abba", s = "dog cat cat dog"
Output: true
Example 2:

Input: pattern = "abba", s = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false


Constraints:

1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lowercase English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.
 */
public class WordPattern_290 {

    public static void main(String[] args) {
        WordPattern_290 solution = new WordPattern_290();
        Assertions.assertTrue(solution.wordPattern("abba", "a b b a"));
        Assertions.assertFalse(solution.wordPattern("abba", "a b b c"));
        Assertions.assertFalse(solution.wordPattern("aaaa", "a b b c"));
        Assertions.assertFalse(solution.wordPattern("abba", "a b b"));
    }

    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();
        String [] words = s.split(" ");

        if(words.length != pattern.length())
            return false;

        for(int i = 0; i < words.length; i++) {
            Character c = pattern.charAt(i);
            if(charToWord.containsKey(c) && !charToWord.get(c).equals(words[i]))
                return false;

            if(!charToWord.containsKey(c) && wordToChar.containsKey(words[i]))
                return false;

            charToWord.put(c, words[i]);
            wordToChar.put(words[i], c);
        }

        return true;
    }
}

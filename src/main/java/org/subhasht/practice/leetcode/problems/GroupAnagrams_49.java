package org.subhasht.practice.leetcode.problems;

import java.util.*;

/*
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]


Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams_49 {

    public static void main(String[] args) {

    }

    static class Solution {

        static String sort(String w) {
            char [] arr = w.toCharArray();
            Arrays.sort(arr);
            return String.valueOf(arr);
        }

        public static List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> lists = new ArrayList<>();
            Map<String, List<String>> map = new HashMap<>();
            for(String s : strs) {
                String sorted = sort(s);
                if(!map.containsKey(sorted)) {
                    map.put(sorted, new LinkedList<>());
                    lists.add(map.get(sorted));
                }
                map.get(sorted).add(s);
            }
            return lists;
        }
    }
}

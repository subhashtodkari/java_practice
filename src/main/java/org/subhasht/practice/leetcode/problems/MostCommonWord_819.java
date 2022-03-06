package org.subhasht.practice.leetcode.problems;

import java.util.*;

/*
Given a string paragraph and a string array of the banned words banned, return the most frequent word that is not banned. It is guaranteed there is at least one word that is not banned, and that the answer is unique.

The words in paragraph are case-insensitive and the answer should be returned in lowercase.



Example 1:

Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
Output: "ball"
Explanation:
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"),
and that "hit" isn't the answer even though it occurs more because it is banned.
Example 2:

Input: paragraph = "a.", banned = []
Output: "a"


Constraints:

1 <= paragraph.length <= 1000
paragraph consists of English letters, space ' ', or one of the symbols: "!?',;.".
0 <= banned.length <= 100
1 <= banned[i].length <= 10
banned[i] consists of only lowercase English letters.
 */
public class MostCommonWord_819 {

    public static void main(String[] args) {
        MostCommonWord_819 solution = new MostCommonWord_819();

        assert solution.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.",
                new String [] {"hit"}).equals("ball") : "Expected - ball";

        assert solution.mostCommonWord("a.",
                new String [] {}).equals("a") : "Expected - a";
    }



    int max = 0;
    String ans = null;

    public String mostCommonWord(String p, String[] banned) {
        max = 0;
        ans = null;

        Set<Character> delimiters = new HashSet<>();
        delimiters.add(' ');
        delimiters.add('!');
        delimiters.add('?');
        delimiters.add('\'');
        delimiters.add(';');
        delimiters.add(',');
        delimiters.add('.');

        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        //System.out.println("Banned: " + String.join(", ", bannedSet));

        Map<String, Integer> countMap = new HashMap<>();
        p = p.toLowerCase();
        int start = 0, i;
        for(i = 0; i < p.length(); i++) {
            if(delimiters.contains(p.charAt(i))) {
                //not a word
                if(i > start) {
                    String w = p.substring(start, i);
                    process(w, bannedSet, countMap);
                }
                start = i+1;
            }
        }
        if(i > start) {
            String w = p.substring(start, i);
            process(w, bannedSet, countMap);
        }
        return ans;
    }

    void process(String w, Set<String> bannedSet, Map<String, Integer> countMap) {
        if(w.length() > 0 && !bannedSet.contains(w)) {
            countMap.put(w, countMap.getOrDefault(w, 0)+1);
            int c = countMap.get(w);
            if(c >= max) {
                max = c;
                ans = w;
            }
        }
    }
}

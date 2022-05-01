package org.subhasht.practice.leetcode.problems;

/*
Given an array of strings wordsDict and two different strings that already exist in the array word1 and word2, return the shortest distance between these two words in the list.



Example 1:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
Output: 3
Example 2:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
Output: 1


Constraints:

1 <= wordsDict.length <= 3 * 104
1 <= wordsDict[i].length <= 10
wordsDict[i] consists of lowercase English letters.
word1 and word2 are in wordsDict.
word1 != word2
 */
public class ShortestWordDistance_243 {

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int w1Idx = -1, w2Idx = -1, dist, min = Integer.MAX_VALUE;
        for(int i = 0; i < wordsDict.length; i++) {
            if(word1.equals(wordsDict[i])) {
                w1Idx = i;
                if(w2Idx != -1) {
                    dist = Math.abs(w1Idx - w2Idx);
                    if(dist < min)
                        min = dist;
                }
            }
            else if(word2.equals(wordsDict[i])) {
                w2Idx = i;
                if(w1Idx != -1) {
                    dist = Math.abs(w1Idx - w2Idx);
                    if(dist < min)
                        min = dist;
                }
            }
        }
        return min;
    }
}

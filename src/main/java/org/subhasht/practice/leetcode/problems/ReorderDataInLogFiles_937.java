package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
/*
You are given an array of logs. Each log is a space-delimited string of words, where the first word is the identifier.

There are two types of logs:

Letter-logs: All words (except the identifier) consist of lowercase English letters.
Digit-logs: All words (except the identifier) consist of digits.
Reorder these logs so that:

The letter-logs come before all digit-logs.
The letter-logs are sorted lexicographically by their contents. If their contents are the same, then sort them lexicographically by their identifiers.
The digit-logs maintain their relative ordering.
Return the final order of the logs.



Example 1:

Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
Explanation:
The letter-log contents are all different, so their ordering is "art can", "art zero", "own kit dig".
The digit-logs have a relative order of "dig1 8 1 5 1", "dig2 3 6".
Example 2:

Input: logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]


Constraints:

1 <= logs.length <= 100
3 <= logs[i].length <= 100
All the tokens of logs[i] are separated by a single space.
logs[i] is guaranteed to have an identifier and at least one word after the identifier.
*/

public class ReorderDataInLogFiles_937 {

    public static void main(String[] args) {
        ReorderDataInLogFiles_937 solution = new ReorderDataInLogFiles_937();
        String [] input = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        String [] expected = {"let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"};
        Assertions.assertArrayEquals(expected, solution.reorderLogFiles(input));

        input = new String [] {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};
        expected = new String []  {"g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"};
        Assertions.assertArrayEquals(expected, solution.reorderLogFiles(input));
    }

    public String[] reorderLogFiles(String[] logs) {

        Log [] arr = new Log[logs.length];
        for(int i = 0; i < logs.length; i++) {
            arr[i] = new Log(logs[i]);
        }

        Arrays.sort(arr, (l1, l2) -> {
            if(l1.type == LogType.LETTER && l2.type == LogType.LETTER) {
                int contentCompare = l1.content.compareTo(l2.content);
                if(contentCompare == 0)
                    return l1.id.compareTo(l2.id);
                return contentCompare;
            }
            if(l1.type == LogType.DIGIT && l2.type == LogType.DIGIT) {
                return 0;
            }
            if(l1.type == LogType.DIGIT) {
                return 1;
            }
            return -1;
        });

        String [] result = new String [logs.length];
        for(int i = 0; i < logs.length; i++) {
            result[i] = arr[i].log;
        }

        return result;
        /*
        Arrays.sort(logs, (s1, s2) -> {
            int s1Idx = s1.indexOf(" ");
            String s1Id = s1.substring(0, s1Idx);
            String s1Content = s1.substring(s1Idx+1);
            int s2Idx = s2.indexOf(" ");
            String s2Id = s2.substring(0, s2Idx);
            String s2Content = s2.substring(s2Idx+1);
            if(Character.isAlphabetic(s1Content.charAt(0)) && Character.isAlphabetic(s2Content.charAt(0))) {
                int contentCompare = s1Content.compareTo(s2Content);
                if(contentCompare == 0)
                    return s1Id.compareTo(s2Id);
                return contentCompare;
            }
            if(Character.isDigit(s1Content.charAt(0)) && Character.isDigit(s2Content.charAt(0))) {
                return 0;
            }
            if(Character.isDigit(s1Content.charAt(0))) {
                return 1;
            }
            return -1;
        });
        return logs;
        */
    }

    enum LogType {LETTER, DIGIT}

    static class Log {
        String log, id, content;
        LogType type;

        public Log(String log) {
            this.log = log;
            int idx = log.indexOf(" ");
            id = log.substring(0, idx);
            content = log.substring(idx+1);
            type = Character.isAlphabetic(content.charAt(0)) ? LogType.LETTER : LogType.DIGIT;
        }
    }
}

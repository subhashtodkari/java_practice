package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]


Constraints:

1 <= n <= 8
 */
public class GenerateParentheses_22 {

    public static void main(String[] args) {
        GenerateParentheses_22 solution = new GenerateParentheses_22();
        List<String> actual = solution.generateParenthesis(3);
        List<String> expected = List.of("((()))","(()())","(())()","()(())","()()()");
        Assertions.assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
        actual = solution.generateParenthesis(1);
        expected = List.of("()");
        Assertions.assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }

    public List<String> generateParenthesis(int n) {
        //return solution1(n);
        return solution2(n);
    }

    public List<String> solution2(int n) {
        char [] arr = new char [n * 2];//n pairs
        List<String> result = new LinkedList<>(); //collect results
        generate(arr, n, 0, 0, 0, result);
        return result;
    }

    void generate(char [] arr, int n, int openCount, int closeCount, int idx, List<String> result) {
        if(n == closeCount) {
            //combination is complete
            result.add(String.valueOf(arr));
        } else {
            if(openCount > closeCount) {
                //do close
                arr[idx] = ')';
                generate(arr, n, openCount, closeCount+1, idx+1, result);
            }
            if(openCount < n) {
                //open one more
                arr[idx] = '(';
                generate(arr, n, openCount+1, closeCount, idx+1, result);
            }
        }
    }

    public List<String> solution1(int n) {
        if(n == 1) {
            List<String> list = new ArrayList<>();
            list.add("()");
            return list;
        }
        List<String> list = solution1(n-1);
        Set<String> uniques = new HashSet<>();
        for(String s : list) {
            for(int i = 0; i <= s.length(); i++) {
                uniques.add(s.substring(0, i) + "()" + s.substring(i));
            }
        }
        return new ArrayList<>(uniques);
    }

}

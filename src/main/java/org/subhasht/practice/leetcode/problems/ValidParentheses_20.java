package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false


Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
 */
public class ValidParentheses_20 {

    public static void main(String[] args) {
        Assertions.assertTrue(Solution1.isValid(""));
        Assertions.assertTrue(Solution1.isValid("([{}()])"));
        Assertions.assertFalse(Solution1.isValid("([{})"));
        Assertions.assertFalse(Solution1.isValid("{]()[}"));
    }


    static class Solution1 {
        public static boolean isValid(String s) {
            Deque<Character> stack = new LinkedList<>();
            for(char c : s.toCharArray()) {
                if(c == '(' || c == '[' || c =='{') {
                    stack.push(c);
                } else {
                    if(stack.isEmpty()) return false;
                    char top = stack.pop();
                    if ( (c == ')' && top != '(') ||
                            (c == ']' && top != '[') ||
                            (c == '}' && top != '{') )
                        return false;
                }
            }
            return stack.isEmpty();
        }
    }

    static class Solution2 {

        public static boolean isValid(String s) {

            Set<Character> openBraces = new HashSet<>();
            Map<Character, Character> map = new HashMap<>();

            openBraces.add('[');
            openBraces.add('(');
            openBraces.add('{');

            map.put('}', '{');
            map.put(']', '[');
            map.put(')', '(');

            Stack<Character> stack = new Stack<>();

            for(char c : s.toCharArray()) {
                if(openBraces.contains(c)) {
                    stack.push(c);
                } else if (map.containsKey(c)) {
                    if(stack.isEmpty() || stack.pop() != map.get(c)) {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
    }
}

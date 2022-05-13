package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

public class LongestPalindromicSubstring_5 {

    public static void main(String[] args) {
        LongestPalindromicSubstring_5 solution = new LongestPalindromicSubstring_5();
        Assertions.assertEquals("abba", solution.longestPalindrome("nnabbarf"));
        Assertions.assertEquals("abxba", solution.longestPalindrome("nnabxbarf"));
    }


    public String longestPalindrome(String s) {
        //return longestPalindromeUsingRecursion(s, 0, s.length()-1);
        return longestPalindromeWithoutRecursion(s);
    }

    public String longestPalindromeWithoutRecursion(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int max = 1, l, r, maxL = 0, maxR = 0;
        for(int i = 1; i < s.length(); i++) {
            // check even
            l = i -1;
            r = i;
            while(l > -1 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            if (r - l - 1 > max) {
                max = r - l - 1;
                maxL = l+1;
                maxR = r-1;
            }

            // check odd
            l = i;
            r = i;
            while(l > -1 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            if (r - l - 1 > max) {
                max = r - l - 1;
                maxL = l+1;
                maxR = r-1;
            }
        }


        return s.substring(maxL, maxR+1);
    }


    int maxLength = 0;

    public String longestPalindromeUsingRecursion(String s, int left, int right) {
        if(right - left + 1 <= maxLength) {
            return "";
        }
        if(isPalindromic(s, left, right)) {
            return s.substring(left, right+1);
        }
        String leftPalindrome = longestPalindromeUsingRecursion(s, left, right-1);
        String rightPalindrome = longestPalindromeUsingRecursion(s, left + 1, right);
        return leftPalindrome.length() > rightPalindrome.length() ? leftPalindrome : rightPalindrome;
    }

    boolean isPalindromic(String s, int left, int right) {
        //System.out.println("checking: " + left + ", " + right);
        int l = left, r = right;
        while(l < r) {
            if(s.charAt(l) != s.charAt(r)) {
                //System.out.println(": false");
                return false;
            }
            l++;
            r--;
        }
        //System.out.println(": true");
        maxLength = Math.max(maxLength, right - left + 1);
        return true;
    }
}

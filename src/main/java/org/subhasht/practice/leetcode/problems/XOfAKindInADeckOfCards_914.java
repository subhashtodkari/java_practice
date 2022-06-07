package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/*
In a deck of cards, each card has an integer written on it.

Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:

Each group has exactly X cards.
All the cards in each group have the same integer.


Example 1:

Input: deck = [1,2,3,4,4,3,2,1]
Output: true
Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
Example 2:

Input: deck = [1,1,1,2,2,2,3,3]
Output: false
Explanation: No possible partition.


Constraints:

1 <= deck.length <= 104
0 <= deck[i] < 104
 */
public class XOfAKindInADeckOfCards_914 {

    public static void main(String[] args) {
        XOfAKindInADeckOfCards_914 solution = new XOfAKindInADeckOfCards_914();
        Assertions.assertFalse(solution.hasGroupsSizeX(new int [] {}));
        Assertions.assertTrue(solution.hasGroupsSizeX(new int [] {1,1}));
        Assertions.assertFalse(solution.hasGroupsSizeX(new int [] {1,2,3}));
        Assertions.assertTrue(solution.hasGroupsSizeX(new int [] {1,2,3,4,4,3,2,1}));
        Assertions.assertTrue(solution.hasGroupsSizeX(new int [] {1,2,1,2,2,2}));
        Assertions.assertFalse(solution.hasGroupsSizeX(new int [] {1,1,1,2,2,2,3,3}));
    }

    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : deck) {
            map.putIfAbsent(n, 0);
            map.put(n, map.get(n) + 1);
        }
        int commonDivisor = -1;
        for(int k : map.keySet()) {
            if(commonDivisor == -1)
                commonDivisor = map.get(k);
            else {
                commonDivisor = gcd(commonDivisor, map.get(k));
            }
        }
        return commonDivisor >= 2;
    }

    int gcd(int n1, int n2) {
        int min, max;
        if(n1 < n2) {
            min = n1;
            max = n2;
        } else {
            min = n2;
            max = n1;
        }
        return min == 0 ? max : gcd(max % min, min);
    }
}

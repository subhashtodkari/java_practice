package org.subhasht.practice.leetcode.problems;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:




Example 1:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Example 2:

Input: numRows = 1
Output: [[1]]


Constraints:

1 <= numRows <= 30
 */
public class PascalsTriangle_118 {

    public static void main(String[] args) {
        PascalsTriangle_118 solution = new PascalsTriangle_118();
        int numOfRows = 1;
        List<List<Integer>> expected = ImmutableList.of(ImmutableList.of(1));
        List<List<Integer>> actual = solution.generate(numOfRows);
        Assertions.assertEquals(expected, actual);
        numOfRows = 5;
        expected = ImmutableList.of(
                ImmutableList.of(1),
                ImmutableList.of(1, 1),
                ImmutableList.of(1, 2, 1),
                ImmutableList.of(1, 3, 3, 1),
                ImmutableList.of(1, 4, 6, 4, 1));
        actual = solution.generate(numOfRows);
        Assertions.assertEquals(expected, actual);

    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> tree = new ArrayList<>(numRows);
        Integer [] arr, prevArr = null;
        int i, j, k, num;

        for(i = 0; i < numRows; i++) {
            arr = new Integer [i+1];
            //System.out.println("i: " + i);
            for(j = 0, k = i; j <= k; j++, k--) {
                //System.out.println("j: " + j + ", k: " + k);
                if(j == 0) {
                    num = 1;
                } else {
                    num = prevArr[j-1] + prevArr[j];
                }
                //System.out.println("num: " + num);
                //System.out.println("");
                arr[j] = num;
                arr[k]= num;
            }
            tree.add(Arrays.asList(arr));
            prevArr = arr;
        }
        return tree;
    }
}

package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class PancakeSorting_969 {

    public static void main(String[] args) {
        PancakeSorting_969 solution = new PancakeSorting_969();
        test_01(solution);
        test_02(solution);
    }

    public static void test_01(PancakeSorting_969 solution) {
        int [] arr = {3,2,4,1};
        List<Integer> expected = List.of(3,4,2,3,2);
        List<Integer> actual = solution.pancakeSort(arr);
        Assertions.assertEquals(expected, actual);
    }

    public static void test_02(PancakeSorting_969 solution) {
        int [] arr = {1,2,3,4};
        List<Integer> expected = List.of();
        List<Integer> actual = solution.pancakeSort(arr);
        Assertions.assertEquals(expected, actual);
    }

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        int [] pos = new int [arr.length+1];
        for(int i = 0; i < arr.length; i++) {
            pos[arr[i]] = i;
        }

        for(int i = arr.length; i > 0; i--) {
            if(pos[i] != i-1) {
                if(pos[i] != 0) {
                    ans.add(pos[i] + 1);
                    reverse(arr, 0, pos[i], pos);
                }
                ans.add(i);
                reverse(arr, 0, i-1, pos);
            }
        }
        return ans;
    }

    boolean isSorted(int [] arr) {
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] < arr[i-1])
                return false;
        }
        return true;
    }

    void reverse(int [] arr, int from, int to, int [] pos) {
        int t;
        while(from < to) {
            t = arr[from];
            arr[from] = arr[to];
            pos[arr[from]] = from;
            arr[to] = t;
            pos[arr[to]] = to;
            from++;
            to--;
        }
    }
}

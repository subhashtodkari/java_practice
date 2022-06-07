package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]


Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */
public class Permutations_46 {

    public static void main(String[] args) {
        Permutations_46 solution = new Permutations_46();
        int [] input = new int [] {1,2,3,4};
        List<List<Integer>> expected = List.of(
                List.of(1,2,3,4),
                List.of(1,2,4,3),
                List.of(1,3,2,4),
                List.of(1,3,4,2),
                List.of(1,4,3,2),
                List.of(1,4,2,3),
                List.of(2,1,3,4),
                List.of(2,1,4,3),
                List.of(2,3,1,4),
                List.of(2,3,4,1),
                List.of(2,4,3,1),
                List.of(2,4,1,3),
                List.of(3,2,1,4),
                List.of(3,2,4,1),
                List.of(3,1,2,4),
                List.of(3,1,4,2),
                List.of(3,4,1,2),
                List.of(3,4,2,1),
                List.of(4,2,3,1),
                List.of(4,2,1,3),
                List.of(4,3,2,1),
                List.of(4,3,1,2),
                List.of(4,1,3,2),
                List.of(4,1,2,3)
        );
        Assertions.assertIterableEquals(expected, solution.permute(input));
    }


    static class PermGen {
        Integer [] arr;
        int n;

        public PermGen(int [] nums) {
            this.n = nums.length;
            arr = new Integer[n];
            for(int i = 0; i < n; i++) {
                arr[i] = nums[i];
            }
        }

        List<List<Integer>> generate() {
            List<List<Integer>> list = new LinkedList<>();
            generate(0, list);
            return list;
        }

        void generate(int n, List<List<Integer>> list) {
            if(n == arr.length-1) {
                Integer [] copy = Arrays.copyOf(arr, this.n);
                list.add(Arrays.asList(copy));
                return;
            }

            for(int i = n; i < this.n; i++) {
                swap(n, i);
                generate(n+1, list);
                swap(n, i);
            }
        }

        void swap(int i, int j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }


    public List<List<Integer>> permute(int[] nums) {
        PermGen permGen = new PermGen(nums);
        return permGen.generate();
    }
}

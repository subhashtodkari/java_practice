package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class LongestArithmeticSubsequence_1027 {

    public int longestArithSeqLength(int[] nums) {
        int [][] dp = new int[1000][1002];
        int max = 0, diffIdx;
        for(int i = nums.length-1; i >= 1 ; i--) {
            for(int j = i - 1; j >= 0; j--) {
                diffIdx = nums[i] - nums[j] + 500;
                dp[j][diffIdx] = dp[i][diffIdx] + 1;
                if(dp[j][diffIdx] > max) {
                    max = dp[j][diffIdx];
                }
                if(nums[i] == nums[j]) break;
            }
        }

        return max+1;
    }

    public int longestArithSeqLength2(int[] nums) {

        int [][] dp = new int[1000][1002];

        for(int i = nums.length-1; i >= 1 ; i--) {
            for(int j = i - 1; j >= 0; j--) {
                dp[j][ nums[i] - nums[j] + 500] = i;
                if(nums[i] == nums[j]) break;
            }
        }

        int max = 2, len, diff, prevIdx;

        for(int i = 0; i < nums.length-1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                len = 2;
                diff = nums[j] - nums[i] + 500;
                prevIdx = j;

                //String key = prevIdx + "-" + diff;
                //if(cache.containsKey(key)) {
                //    len = cache.get(key);
                //} else {
                while(dp[prevIdx][diff] != 0) {
                    len++;
                    prevIdx = dp[prevIdx][diff];
                }
                //}
                //cache.put(key, len);
                max = Math.max(max, len);
            }
        }

        return max;
    }

    //caching repetitive search
    Map<String, Integer> cache = new HashMap<>();

    public int longestArithSeqLength1(int[] nums) {

        int max = 2, len, diff, prevIdx;
        Map<Integer, List<Integer>> map = arrToIndexMap(nums);

        //List<Integer> ll = new ArrayList<>();

        for(int i = 0; i < nums.length-1; i++) {

            for(int j = i + 1; j < nums.length; j++) {
                //ll.clear();
                //ll.add(i);

                if( nums.length - j <= max - 2) break;

                len = 2;
                diff = nums[j] - nums[i];
                prevIdx = j;

                //ll.add(j);

                String key = prevIdx + "-" + diff;
                if(cache.containsKey(key)) {
                    len = cache.get(key);
                } else {
                    while(map.containsKey(nums[prevIdx] + diff)) {
                        List<Integer> indices = map.get(nums[prevIdx] + diff);
                        if(indices.get(indices.size()-1) <= prevIdx) {
                            break;
                        }
                        /*
                        //linear search
                        for(int k = 0; k < indices.size(); k++) {
                            if(indices.get(k) > prevIdx) {
                                prevIdx = indices.get(k);
                                len++;
                                ll.add(prevIdx);
                                break;
                            }
                        }
                        */
                        //binary search
                        int l = 0, r = indices.size()-1, m;
                        while(l <= r) {
                            m = (l + r) / 2;
                            if(prevIdx < indices.get(m)) {
                                r = m - 1;
                            } else if (prevIdx > indices.get(m)) {
                                l = m + 1;
                            } else {
                                l = m + 1;
                                break;
                            }
                        }

                        prevIdx = indices.get(l);
                        len++;
                        //ll.add(prevIdx);

                    }
                    cache.put(key, len);
                }

                //if(len > max) {
                //    ll.stream().map(ii -> ", " + nums[ii]).forEach(System.out::print);
                //    System.out.println("");
                //}
                max = Math.max(max, len);
            }
        }
        return max;
    }

    Map<Integer, List<Integer>> arrToIndexMap(int [] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList<>());
            }
            map.get(arr[i]).add(i);
        }
        return map;
    }

    public static void main(String[] args) {
        LongestArithmeticSubsequence_1027 solution = new LongestArithmeticSubsequence_1027();
        Assertions.assertEquals(2, solution.longestArithSeqLength(new int [] {3,6,10,12}));
        Assertions.assertEquals(4, solution.longestArithSeqLength(new int [] {3,6,9,12}));
        Assertions.assertEquals(5, solution.longestArithSeqLength(new int [] {3,6,7,8,9,10,12}));
    }
}

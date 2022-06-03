package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class PairsOfSongsWithTotalDurationsDivisibleBy60_1010 {

    public static void main(String[] args) {
        PairsOfSongsWithTotalDurationsDivisibleBy60_1010 sol = new PairsOfSongsWithTotalDurationsDivisibleBy60_1010();
        Assertions.assertEquals(0, sol.sol5(new int [] {10, 60, 30}));
        Assertions.assertEquals(3, sol.sol5(new int [] {30, 20, 150, 100, 40}));
        Assertions.assertEquals(3, sol.sol5(new int [] {60, 60, 60}));
    }

    public int numPairsDivisibleBy60(int[] time) {
        return sol5(time);
    }

    // time complexity: O(n)
    // space complexity: O(60 + n) = O(n)
    public int sol6(int[] times) {
        final int DURATION = 60;
        int [] map = new int [60];
        int [] ans = new int [times.length + 1];
        for(int i = times.length-1; i >= 0; i--) {//O(n)
            int r = times[i] % DURATION;
            int reqRem = r == 0 ? 0 : 60 - r;
            ans[i] = map[reqRem] + ans[i+1];
            map[r]++;
        }
        return ans[0];
    }

    // time complexity: O(n + 31) = O(n)
    // space complexity: O(60) = O(1)
    public int sol5(int[] times) {
        long count = 0;
        int [] remSize = new int [60];
        for(int t: times) { //O(n)
            remSize[t % 60]++;
        }
        for(int r = 0; r < 31; r++) {// for 31 times
            int bal = 60 - r;
            if(bal == 60) {
                bal = 0;
            }

            if(bal == r) {
                long x = (remSize[bal] - 1);
                count +=  (x * (x + 1) / 2);

            } else {
                count += (long) remSize[bal] * remSize[r];
            }
        }

        return (int) count;
    }

    // time complexity: O(n + 60) = O(n)
    // space complexity: O(m), m = distinct map keys < 60
    public int sol4(int[] times) {
        long count = 0;
        Map<Integer, Integer> remMap = new TreeMap<>();
        for(int t: times) {
            int r = t % 60;
            remMap.put(r, remMap.getOrDefault(r, 0) + 1);//O(Log 60) - constant time = O(1)
        }
        for(int r: remMap.keySet()) {//at max 60 times
            if(r > 30) {
                //we have processed possible combinations
                break;
            }
            int bal = 60 - r;
            if(bal == 60) {
                bal = 0;
            }

            if(bal == r) {
                long x = (remMap.get(bal) - 1);//O(Log 60) - constant time = O(1)
                count +=  (x * (x + 1) / 2);

            } else {
                count += remMap.get(bal) == null ? 0 : (long) remMap.get(bal) * remMap.get(r);//O(Log 60) - constant time = O(1)
            }
        }

        return (int) count;
    }

    // time complexity: O(n + 60) = O(n)
    // space complexity: O(n + m), m = distinct map keys < 60
    public int sol3(int[] times) {
        long count = 0;
        Map<Integer, List<Integer>> remMap = new TreeMap<>();
        for(int t: times) {
            int r = t % 60;
            remMap.putIfAbsent(r, new ArrayList<>());//O(Log 60) - constant time = O(1)
            remMap.get(r).add(t);//O(Log 60) - constant time = O(1)
        }
        for(int r: remMap.keySet()) {//at max 60 times
            if(r > 30) {
                //we have processed possible combinations
                break;
            }
            int bal = 60 - r;
            if(bal == 60) {
                bal = 0;
            }

            if(bal == r) {
                long x = (remMap.get(bal).size() - 1);//O(Log 60) - constant time = O(1)
                count +=  (x * (x + 1) / 2);

            } else {
                count += remMap.get(bal) == null ? 0 : (long) remMap.get(bal).size() * remMap.get(r).size();//O(Log 60) - constant time = O(1)
            }
        }

        return (int) count;
    }

    // time complexity: 2 x O(n) = O(n)
    // space complexity: O(n + m), m = distinct map keys < 60
    public int sol2(int[] times) {
        long count = 0;
        Map<Integer, List<Integer>> remMap = new HashMap<>();
        for(int t: times) {
            int r = t % 60;
            remMap.putIfAbsent(r, new ArrayList<>());//O(1)
            remMap.get(r).add(t);//O(1)
        }
        for(int t: times) {
            int bal = 60 - (t % 60);
            if(bal == 60) {
                bal = 0;
            }
            count += remMap.get(bal) == null ? 0 : remMap.get(bal).size();//O(1)
            if(bal == t % 60) {
                count--;
            }
        }

        return (int) (count/2);
    }

    // time complexity: O(n^2)
    // space complexity: O(1)
    public int sol1(int[] times) {
        int count = 0;
        for(int i = 0; i < times.length; i++) {
            for(int j = 0; j < times.length; j++) {
                if(j != i && (times[i] + times[j]) % 60 == 0 ) {
                    count++;
                }
            }
        }
        return count/2;
    }
}

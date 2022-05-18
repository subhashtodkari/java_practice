package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Map;
import java.util.TreeMap;

public class CountIntegersInIntervals_2276 {

    public static void main(String[] args) {
        test(new Integer[][] {{2,3}, {7,10}, {6}, {5,8}, {8}});
        test(new Integer[][] {{10,27}, {46,50}, {15,35}, {12,32}, {7,15}, {49,49}, {34}});
    }

    static void test(Integer [][] arr) {
        CountIntervals countIntervals = new CountIntervals();
        for(Integer [] a : arr) {
            if(a.length == 1) {
                Assertions.assertEquals(a[0], countIntervals.count());
            } else {
                countIntervals.add(a[0], a[1]);
            }
        }
    }

    static class CountIntervals {

        TreeMap<Integer,Integer> map = new TreeMap<>();
        int count = 0;

        public CountIntervals() {

        }

        public void add(int left, int right) {
            int pl, pr = -1, nl, nr;
            Map.Entry<Integer, Integer> prev = map.floorEntry(left);
            if(prev != null && prev.getValue() >= left) {
                //it overlaps
                pl = prev.getKey();
                pr = prev.getValue();
                left = pl;
            }
            if(pr > right) {
                return;
            } else {
                count += right - (pr == -1 ? left - 1 : pr);
            }
            Map.Entry<Integer, Integer> next;
            while( (next = map.higherEntry(left)) != null && next.getKey() <= right) {
                //it overlaps
                nl = next.getKey();
                nr = next.getValue();
                count -= (Math.min(right, nr) - nl + 1);
                if(nr > right)
                    right = nr;
                map.remove(nl);
            }

            map.put(left, right);

        }

        public int count() {
            return count;
        }
    }
}

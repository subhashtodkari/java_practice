package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;
import org.subhasht.util.ArrayComparator;

import java.util.PriorityQueue;

public class KClosestPointsToOrigin_973 {

    public static void main(String[] args) {
        KClosestPointsToOrigin_973 solution = new KClosestPointsToOrigin_973();
        int [][] expected = {{-2,2}};
        int [][] actual = solution.kClosest(new int [][] { {1,3}, {-2,2}}, 1);
        Assertions.assertTrue(ArrayComparator.compare(expected, actual, true, false));
    }


    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> heap = new PriorityQueue<>((p1, p2) -> Double.compare(p2.d, p1.d));

        for (int[] point : points) {
            Point p = new Point(point);
            if (heap.size() < k) {
                heap.add(p);
            } else {
                //heap is full
                if (p.d < heap.peek().d) {
                    //p is closed to origin than top
                    heap.remove();//remove top
                    heap.add(p);//add p
                }
            }
        }

        int[][] ans = new int [k][];
        for(int i = 0; i < k; i++) {
            ans[i] = heap.remove().arr;
        }
        return ans;
    }

    static class Point {
        int x;
        int y;
        int[] arr;
        double d;
        public Point(int[] array) {
            arr = array;
            x = array[0];
            y = array[1];
            d = Math.sqrt((x*x) + (y*y));
        }
    }
}

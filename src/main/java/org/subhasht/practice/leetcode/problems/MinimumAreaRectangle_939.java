package org.subhasht.practice.leetcode.problems;

import java.util.*;

/*
You are given an array of points in the X-Y plane points where points[i] = [xi, yi].

Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. If there is not any such rectangle, return 0.



Example 1:


Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:


Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Constraints:

1 <= points.length <= 500
points[i].length == 2
0 <= xi, yi <= 4 * 104
All the given points are unique.
 */
public class MinimumAreaRectangle_939 {


    //quite faster - online solution checked
    static class Solution {

        public int minAreaRect(int[][] points) {

            Map<Integer, Set<Integer>> xmap = new HashMap<>();
            for (int[] point : points) {
                xmap.putIfAbsent(point[0], new HashSet<>());
                xmap.get(point[0]).add(point[1]);
            }

            int min = Integer.MAX_VALUE;

            for(int i = 0; i < points.length; i++) {
                for(int j = i + 1; j < points.length; j++) {
                    //changing scope of local variables inside loops to scope outside of loops does not make any +ve difference
                    //also creating more variables to hold each array individual values also does not make any +ve difference
                    int [] p1 = points[i];
                    int [] p2 = points[j];

                    //it might be testcases specific performance fix to test area before validating points availability
                    int area = Math.abs( (p1[0] - p2[0]) * (p1[1] - p2[1]) );

                    if(area != 0 && area < min &&
                            xmap.get(p1[0]).contains(p2[1]) &&
                            xmap.get(p2[0]).contains(p1[1])
                    ) {
                        min = area;
                    }
                }
            }

            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }

    //slower
    static class Solution1 {

        public int minAreaRect(int[][] points) {
            Set<Integer> all = new HashSet<>();
            for (int[] point : points) {
                all.add(Objects.hash(point[0], point[1]));
            }

            int min = Integer.MAX_VALUE;

            for(int i = 0; i < points.length; i++) {
                for(int j = i + 1; j < points.length; j++) {
                    if(points[i][0] != points[j][0] && points[i][1] != points[j][1] &&
                            all.contains(Objects.hash( points[i][0], points[j][1] ) ) &&
                            all.contains(Objects.hash( points[j][0], points[i][1] ) )
                    ) {
                        min = Math.min( min, Math.abs(points[j][0] - points[i][0]) * Math.abs(points[j][1] - points[i][1]));
                    }
                }
            }

            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }
}

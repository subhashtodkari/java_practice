package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.PriorityQueue;

/*
You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.

You start your journey from building 0 and move to the next building by possibly using bricks or ladders.

While moving from building i to building i+1 (0-indexed),

If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.



Example 1:


Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
Output: 4
Explanation: Starting at building 0, you can follow these steps:
- Go to building 1 without using ladders nor bricks since 4 >= 2.
- Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
- Go to building 3 without using ladders nor bricks since 7 >= 6.
- Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
Example 2:

Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
Output: 7
Example 3:

Input: heights = [14,3,19,3], bricks = 17, ladders = 0
Output: 3


Constraints:

1 <= heights.length <= 105
1 <= heights[i] <= 106
0 <= bricks <= 109
0 <= ladders <= heights.length
 */
public class FurthestBuildingYouCanReach_1642 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertEquals(0,solution.furthestBuilding(new int [] {100}, 0, 0));
        Assertions.assertEquals(0,solution.furthestBuilding(new int [] {100, 101}, 0, 0));
        Assertions.assertEquals(1,solution.furthestBuilding(new int [] {100, 100}, 0, 0));
        Assertions.assertEquals(1,solution.furthestBuilding(new int [] {100, 0}, 0, 0));
        Assertions.assertEquals(4,solution.furthestBuilding(new int [] {4,2,7,6,9,14,12}, 5, 1));
        Assertions.assertEquals(7,solution.furthestBuilding(new int [] {4,12,2,7,3,18,20,3,19}, 10, 2));
        Assertions.assertEquals(3,solution.furthestBuilding(new int [] {14,3,19,3}, 17, 0));
    }


    static class Solution {
        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            return furthestBuildingUsingHeap(heights, bricks, ladders);

            //return furthestBuilding(heights, bricks, ladders, 1);
        }


        int furthestBuildingUsingHeap(int[] heights, int bricks, int ladders) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>();//size = number of ladders
            int i = 1, d;
            while(i < heights.length) {
                d = heights[i] - heights[i-1];
                if(d > 0) {

                    if(maxHeap.size() < ladders) {
                        maxHeap.add(d);
                    } else if(bricks > 0) {
                        if(!maxHeap.isEmpty() && d > maxHeap.peek()) {
                            maxHeap.add(d);
                            d = maxHeap.remove();
                        }
                        if(bricks >= d) {
                            bricks -= d;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }

                i++;
            }

            return i-1;
        }

        //knap sack algo - timed out
        int furthestBuilding(int[] heights, int bricks, int ladders, int i) {
            if(i == heights.length || (heights[i] > heights[i-1] && ladders == 0 && bricks < (heights[i] - heights[i-1]) )) return i-1;
            if(heights[i] < heights[i-1])
                return furthestBuilding(heights, bricks, ladders, i + 1);
            int countWithLadder = ladders > 0 ? furthestBuilding(heights, bricks, ladders-1, i + 1) : i-1;
            int countWithBrick = bricks >= heights[i] - heights[i-1] ? furthestBuilding(heights, bricks - (heights[i] - heights[i-1]), ladders, i + 1) : i-1;
            return Math.max(countWithLadder, countWithBrick);
        }
    }
}

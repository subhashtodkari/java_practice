package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/*
You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:

numberOfBoxesi is the number of boxes of type i.
numberOfUnitsPerBoxi is the number of units in each box of the type i.
You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.

Return the maximum total number of units that can be put on the truck.



Example 1:

Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
Output: 8
Explanation: There are:
- 1 box of the first type that contains 3 units.
- 2 boxes of the second type that contain 2 units each.
- 3 boxes of the third type that contain 1 unit each.
You can take all the boxes of the first and second types, and one box of the third type.
The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
Example 2:

Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
Output: 91


Constraints:

1 <= boxTypes.length <= 1000
1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
1 <= truckSize <= 106
 */
public class MaximumUnitsOnATruck_1710 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertEquals(8, solution.maximumUnits(new int [][] {
                {1,3},{2,2},{3,1}
        }, 4));
        Assertions.assertEquals(91, solution.maximumUnits(new int [][] {
                {5,10},{2,5},{4,7}, {3,9}
        }, 10));
    }


    //faster with use of constraints O(N)
    static class Solution {
        public int maximumUnits(int[][] boxTypes, int truckSize) {
            int units = 0;

            int [] unitsVsBoxesCount = new int[1001];//value = number of boxes with i units per box
            for(int [] boxType : boxTypes) { //O(N)
                unitsVsBoxesCount[ boxType[1] ] += boxType[0];
            }
            for(int i = unitsVsBoxesCount.length - 1; i > 0; i--) { //O(1001) = O(1) // start with max units per box
                if(unitsVsBoxesCount[i] > truckSize) {
                    units += (truckSize * i);
                    break;
                } else if (unitsVsBoxesCount[i] > 0) {
                    units += (unitsVsBoxesCount[i] * i);
                    truckSize -= unitsVsBoxesCount[i];
                }
            }
            return units;
        }
    }

    //slower O(N Log N)
    static class Solution1 {
        public int maximumUnits(int[][] boxTypes, int truckSize) {
            Arrays.sort(boxTypes, (a1, a2) -> a2[1] - a1[1]); // O(N Log N)
            //for(int [ ]a : boxTypes) {
            //    System.out.println(Arrays.toString(a));
            //}
            int units = 0;
            int i = 0;
            while(truckSize > 0 && i < boxTypes.length) { // O(N)
                //System.out.println(truckSize + ", " + Arrays.toString(boxTypes[i]));
                int b = Math.min(truckSize, boxTypes[i][0]);
                truckSize -= b;
                units += (b * boxTypes[i][1]);
                i++;
                //System.out.println(units);
            }
            return units;
        }
    }
}

package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

/*
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.


Example 1:


Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true
Example 2:


Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
Output: false


Constraints:

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matrix[i][j] <= 109
All the integers in each row are sorted in ascending order.
All the integers in each column are sorted in ascending order.
-109 <= target <= 109
 */
public class SearchA2DMatrixII_240 {

    public static void main(String[] args) {
        SearchA2DMatrixII_240 solution = new SearchA2DMatrixII_240();
        int [][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 5;
        Assertions.assertTrue(solution.searchMatrix(matrix, target));
        Assertions.assertTrue(solution.searchMatrix(new int [][] {{1,3,5}}, target));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int right = matrix[0].length-1, pos;
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] > target)
                break;
            pos = findInsertPosition(matrix[i], target, 0, right);
            if(pos != matrix[0].length && matrix[i][pos] == target)
                return true;
            right = pos - 1;
        }
        return false;
    }

    int findInsertPosition(int [] arr, int num, int left, int right) {
        int mid;
        while(left <= right) {
            mid = (left + right) / 2;
            if(arr[mid] == num)
                return mid;
            if(mid == left) {
                if(num <= arr[left])
                    return left;
                if(num <= arr[right])
                    return right;
                else
                    return right+1;
            }
            if(num < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

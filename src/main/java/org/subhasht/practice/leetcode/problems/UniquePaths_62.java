package org.subhasht.practice.leetcode.problems;

import java.util.Arrays;

/*
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.



Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down


Constraints:

1 <= m, n <= 100
 */
public class UniquePaths_62 {

    public static void main(String[] args) {
        UniquePaths_62 solution = new UniquePaths_62();
        //enable assertions via VM arg -ea
        assert solution.uniquePaths(7, 1) ==  1 : "Expected 1";
        assert solution.uniquePaths(1, 3) ==  1 : "Expected 1";
        assert solution.uniquePaths(7, 3) ==  28 : "Expected 8";
        assert solution.uniquePaths(3, 7) ==  28 : "Expected 8";
    }

    public int uniquePaths(int m, int n) {
        if(m == 1 || n == 1) return 1;
        //return uniquePathsUsingArraysMxN(m, n);
        //return uniquePathsUsingRecursion(m, n, 0, 0);//timeout, caching may help but overhead
        //return uniquePathsUsingArrays2xN(m, n);
        //return uniquePathsUsingArrays1xN(m, n);
        return uniquePathsUsingCombination(m, n);
    }

    public int uniquePathsUsingCombination(long m, long n) {
        long mx = Math.max(m, n);
        long mn = Math.min(m, n);
        n = mx + mn - 2;
        m = mn -1;
        mx = Math.max(m, n -m);
        mn = Math.min(m, n -m);

        for(m = n-1; m > mx; m--) {
            n *= m;
            //avoid long number overflow
            if(mn > 1 && n % mn == 0) {
                n = n / mn;
                mn--;
            }
        }
        for(m = mn - 1; m > 0; m--) {
            mn *= m;
        }
        return (int)( n / mn );
    }

    public int uniquePathsUsingRecursion(int m, int n, int x, int y) {
        if (x == m || y == n) return 0;
        if (x == m-1 && y == n-1) return 1;
        return uniquePathsUsingRecursion(m, n, x+1, y) + uniquePathsUsingRecursion(m, n, x, y+1);
    }

    public int uniquePathsUsingArrays1xN(int m, int n) {
        int [] curr = new int [n];
        Arrays.fill(curr, 1);
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                curr[j] = curr[j-1] + curr[j];
            }
        }
        return curr[n-1];
    }

    public int uniquePathsUsingArrays2xN(int m, int n) {
        int [] curr = new int [n];
        int [] prev = new int [n];
        Arrays.fill(prev, 1);
        for(int i = 1; i < m; i++) {
            curr[0] = 1;
            for(int j = 1; j < n; j++) {
                curr[j] = curr[j-1] + prev[j];
            }
            prev = curr;
        }
        return prev[n-1];
    }

    public int uniquePathsUsingArraysMxN(int m, int n) {
        int [][] arr = new int [m][n];
        for(int i = 0; i < m; i++) {
            arr[i][n-1] = 1;
        }
        for(int i = 0; i < n; i++) {
            arr[m-1][i] = 1;
        }
        for(int i = m-2; i > -1; i--) {
            for(int j = n-2; j > -1; j--) {
                arr[i][j] = arr[i+1][j] + arr[i][j+1];
            }
        }
        return arr[0][0];
    }
}

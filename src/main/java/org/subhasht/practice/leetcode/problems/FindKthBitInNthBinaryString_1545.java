package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

/*
Given two positive integers n and k, the binary string Sn is formed as follows:

S1 = "0"
Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).

For example, the first four strings in the above sequence are:

S1 = "0"
S2 = "011"
S3 = "0111001"
S4 = "011100110110001"
Return the kth bit in Sn. It is guaranteed that k is valid for the given n.



Example 1:

Input: n = 3, k = 1
Output: "0"
Explanation: S3 is "0111001".
The 1st bit is "0".
Example 2:

Input: n = 4, k = 11
Output: "1"
Explanation: S4 is "011100110110001".
The 11th bit is "1".


Constraints:

1 <= n <= 20
1 <= k <= 2n - 1
 */
public class FindKthBitInNthBinaryString_1545 {

    public static void main(String[] args) {
        FindKthBitInNthBinaryString_1545 solution = new FindKthBitInNthBinaryString_1545();
        Assertions.assertEquals(ZERO, solution.findKthBit(4, 1));
        Assertions.assertEquals(ZERO, solution.findKthBit(1, 1));
        Assertions.assertEquals(ONE, solution.findKthBit(4, 2));
        Assertions.assertEquals(ONE, solution.findKthBit(5, 31));
        Assertions.assertEquals(ZERO, solution.findKthBit(5, 14));
    }

    private static final char ZERO = '0';
    private static final char ONE = '1';

    public char findKthBit(int n, int k) {
        k--;
        int l = (int) Math.pow(2, n) - 1;
        //return findRecursively(k, l, false);
        return findUsingLoop(k, l);
    }

    char findRecursively(int k, int l, boolean invert) {
        if(k == 0)
            return invert ? ONE : ZERO;
        if(k == l/2 || k == l - 1)
            return invert ? ZERO :ONE;

        if(k > l/2) {
            k = l - k - 1;
            invert = !invert;
        } else {
            l = ((l+1)/2)-1;
        }
        return findRecursively(k, l, invert);
    }

    char findUsingLoop(int k, int l) {
        boolean invert = false;
        while(k > 0) {
            if(k == l/2 || k == l - 1)
                return invert ? ZERO :ONE;
            if(k > l/2) {
                k = l - k - 1;
                invert = !invert;
            } else {
                l = ((l+1)/2)-1;
            }
        }
        return invert ? ONE : ZERO;
    }
}

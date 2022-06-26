package org.subhasht.practice.leetcode.problems;

/*
Given the root of a binary tree, return the number of uni-value subtrees.

A uni-value subtree means all nodes of the subtree have the same value.



Example 1:


Input: root = [5,1,5,5,5,null,5]
Output: 4
Example 2:

Input: root = []
Output: 0
Example 3:

Input: root = [5,5,5,5,5,null,5]
Output: 6


Constraints:

The number of the node in the tree will be in the range [0, 1000].
-1000 <= Node.val <= 1000
 */
public class CountUnivalueSubtrees_250 {

    static class Solution {
        public int countUnivalSubtrees(TreeNode root) {
            return (int) calc(root)[1];
        }

        public Object[] calc(TreeNode node) {
            if(node == null) return new Object [] {true, 0};
            Object [] left = calc(node.left);
            Object [] right = calc(node.right);
            if((boolean)left[0] && (boolean) right[0]) {
                if(
                        ( (node.left != null && node.right != null) && (node.left.val == node.val && node.right.val == node.val) ) ||
                                (node.left == null && node.right == null) ||
                                (node.left == null && node.right.val == node.val) ||
                                (node.right == null && node.left.val == node.val) ) {
                    return new Object[] {true, (int)left[1] + (int)right[1] + 1};
                }
            }
            return new Object[] {false, (int)left[1] + (int)right[1]};
        }
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

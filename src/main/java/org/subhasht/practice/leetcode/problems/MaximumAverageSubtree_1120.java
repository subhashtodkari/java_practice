package org.subhasht.practice.leetcode.problems;

/*
Given the root of a binary tree, return the maximum average value of a subtree of that tree. Answers within 10-5 of the actual answer will be accepted.

A subtree of a tree is any node of that tree plus all its descendants.

The average value of a tree is the sum of its values, divided by the number of nodes.
 */
public class MaximumAverageSubtree_1120 {

    private double max = 0;

    public double maximumAverageSubtree(TreeNode root) {
        getSumAndCount(root);
        return max;
    }


    private int[] getSumAndCount(TreeNode node) {
        int [] sumAndCount = {0, 0};
        if(node != null) {
            int [] left = getSumAndCount(node.left);
            int [] right = getSumAndCount(node.right);
            sumAndCount[0] = node.val + left[0] + right[0];
            sumAndCount[1] = 1 + left[1] + right[1];
            double avg = ((double) sumAndCount[0]) / sumAndCount[1];
            if(avg > max) {
                max = avg;
            }
        }
        return sumAndCount;
    }

    static class TreeNode {
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




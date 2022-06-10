package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/*
Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Example 2:


Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]
Example 3:


Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]


Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */
public class BinaryTreeVerticalOrderTraversal_314 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertIterableEquals(
                List.of(
                        List.of(4),
                        List.of(9,5),
                        List.of(3,0,1),
                        List.of(8,2),
                        List.of(7)
                ),
                solution.verticalOrder(buildTree(new Integer [] {3,9,8,4,0,1,7,null,null,null,2,5}, 0)));

        Assertions.assertIterableEquals(
                List.of(
                        List.of(4),
                        List.of(9),
                        List.of(3,0,1),
                        List.of(8),
                        List.of(7)
                ),
                solution.verticalOrder(buildTree(new Integer [] {3,9,8,4,0,1,7}, 0)));

        Assertions.assertIterableEquals(
                List.of(
                        List.of(9),
                        List.of(3,15),
                        List.of(20),
                        List.of(7)
                ),
                solution.verticalOrder(buildTree(new Integer [] {3,9,20,null,null,15,7}, 0)));
    }



    static TreeNode buildTree(Integer [] arr, int i) {
        return i >= arr.length || arr[i] == null
                ? null
                : new TreeNode(arr[i], buildTree(arr, (i*2) +1), buildTree(arr, (i*2) + 2));
    }


    static class Solution {

        int minVi = Integer.MAX_VALUE, maxVi = Integer.MIN_VALUE;

        public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> ans = new LinkedList<>();
            if(root == null) return ans;

            Map<Integer, List<Integer>> map = new HashMap<>();
            List<Integer> list;

            Deque<TreeNode> nodeQ = new LinkedList<>();
            Deque<Integer> viQ = new LinkedList<>();

            TreeNode node = root;
            int vi = 0, sz;

            nodeQ.add(node);
            viQ.add(vi);

            while(!nodeQ.isEmpty()) {
                sz = nodeQ.size();
                for(int i = 0; i < sz; i++) {
                    node = nodeQ.remove();
                    vi = viQ.remove();

                    if(vi == 0 && map.isEmpty()) {
                        list = new LinkedList<>();
                        ans.add(list);
                        map.put(vi, list);
                        minVi = vi;
                        maxVi = vi;
                    } else if(vi < minVi) {
                        list = new LinkedList<>();
                        ans.add(0, list);
                        map.put(vi, list);
                        minVi = vi;
                    } else if(vi > maxVi) {
                        list = new LinkedList<>();
                        ans.add(list);
                        map.put(vi, list);
                        maxVi = vi;
                    }

                    map.get(vi).add(node.val);

                    if(node.left != null) {
                        nodeQ.add(node.left);
                        viQ.add(vi-1);
                    }

                    if(node.right != null) {
                        nodeQ.add(node.right);
                        viQ.add(vi+1);
                    }
                }
            }

            return ans;
        }
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

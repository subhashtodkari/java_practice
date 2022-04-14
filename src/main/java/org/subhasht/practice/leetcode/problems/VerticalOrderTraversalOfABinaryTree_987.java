package org.subhasht.practice.leetcode.problems;

import java.util.*;

/*
Given the root of a binary tree, calculate the vertical order traversal of the binary tree.

For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1)
and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index
starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row
and same column. In such a case, sort these nodes by their values.

Return the vertical order traversal of the binary tree.
 */
public class VerticalOrderTraversalOfABinaryTree_987 {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();

        TreeMap<Point, List<Integer>> pointsMap = new TreeMap<>(Comparator.comparingInt((Point o) -> o.y).thenComparingInt(o -> o.x));
        process(root, 0, 0, pointsMap);

        Point prev = null;
        List<Integer> list = null;

        for(Point p : pointsMap.keySet()) {
            if(prev == null || prev.y != p.y) {
                list = new LinkedList<>();
                ans.add(list);
            }
            List<Integer> temp = pointsMap.get(p);
            Collections.sort(temp);
            list.addAll(temp);
            prev = p;
        }
        return ans;
    }

    void process(TreeNode node, int x, int y, TreeMap<Point, List<Integer>> pointsMap) {
        if(node == null) {
            return;
        }
        Point p = new Point(x, y);
        pointsMap.putIfAbsent(p, new ArrayList<>());
        List<Integer> list = pointsMap.get(p);
        list.add(node.val);
        process(node.left, x+1, y-1, pointsMap);
        process(node.right, x+1, y+1, pointsMap);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static  class TreeNode {
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

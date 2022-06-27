package org.subhasht.practice.leetcode.problems;

/*
Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.

Return the root of the Quad-Tree representing the grid.

Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.

A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:

val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
}
We can construct a Quad-Tree from a two-dimensional area using the following steps:

If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
Recurse for each of the children with the proper sub-grid.

If you want to know more about the Quad-Tree, you can refer to the wiki.

Quad-Tree format:

The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.

It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].

If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.



Example 1:


Input: grid = [[0,1],[1,0]]
Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
Explanation: The explanation of this example is shown below:
Notice that 0 represnts False and 1 represents True in the photo representing the Quad-Tree.

Example 2:



Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
The topLeft, bottomLeft and bottomRight each has the same value.
The topRight have different values so we divide it into 4 sub-grids where each has the same value.
Explanation is shown in the photo below:



Constraints:

n == grid.length == grid[i].length
n == 2x where 0 <= x <= 6
 */
public class ConstructQuadTree_427 {

    static class Solution {
        public Node construct(int[][] grid) {
            return construct(grid, 0, 0, grid.length);
        }

        public Node construct(int[][] grid, int i, int j, int len) {
            if (len == 0) return null;
            if (len == 1) {
                return new Node(grid[i][j] == 1, true);
            }

            int w = len / 2;
            Node lt = construct(grid, i + 0, j + 0, w);
            Node rt = construct(grid, i + 0, j + w, w);
            Node lb = construct(grid, i + w, j + 0, w);
            Node rb = construct(grid, i + w, j + w, w);

            boolean val = true;
            boolean leaf = false;
            if( lt.val == rt.val && lt.val == lb.val && lt.val == rb.val &&
                    lt.isLeaf && lb.isLeaf && rt.isLeaf && rb.isLeaf) {
                leaf = true;
                val = lt.val;
            }
            return leaf ? new Node(val, leaf) : new Node(val, leaf, lt, rt, lb, rb);
        }
    }

    static class Solution1 {
        public Node construct(int[][] grid) {
            return buildQuadTree(grid, 0, 0, grid.length);
        }

        Node buildQuadTree(int [][] grid, int x, int y, int l) {
            if (l == 1) {
                return new Node(grid[x][y] == 1, true);
            } else {
                Node tl = buildQuadTree(grid, x,     y,       l/2);
                Node tr = buildQuadTree(grid, x,     y+l/2,   l/2);
                Node bl = buildQuadTree(grid, x+l/2, y,       l/2);
                Node br = buildQuadTree(grid, x+l/2, y+l/2,   l/2);


                if( tl.isLeaf && tr.isLeaf && bl.isLeaf && br.isLeaf) {
                    if (tl.val == tr.val &&
                            tl.val == br.val &&
                            tl.val == bl.val) {
                        return new Node(tl.val, true);
                    }
                }
                return new Node(false, false, tl, tr, bl, br);
            }
        }
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}

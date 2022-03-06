package org.subhasht.practice.leetcode.problems;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/*
You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix. In this matrix:

0 means the cell cannot be walked through.
1 represents an empty cell that can be walked through.
A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
In one step, you can walk in any of the four directions: north, east, south, and west. If you are standing in a cell with a tree, you can choose whether to cut it off.

You must cut off the trees in order from shortest to tallest. When you cut off a tree, the value at its cell becomes 1 (an empty cell).

Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. If you cannot cut off all the trees, return -1.

You are guaranteed that no two trees have the same height, and there is at least one tree needs to be cut off.



Example 1:


Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
Output: 6
Explanation: Following the path above allows you to cut off the trees from shortest to tallest in 6 steps.
Example 2:


Input: forest = [[1,2,3],[0,0,0],[7,6,5]]
Output: -1
Explanation: The trees in the bottom row cannot be accessed as the middle row is blocked.
Example 3:

Input: forest = [[2,3,4],[0,0,5],[8,7,6]]
Output: 6
Explanation: You can follow the same path as Example 1 to cut off all the trees.
Note that you can cut off the first tree at (0, 0) before making any steps.


Constraints:

m == forest.length
n == forest[i].length
1 <= m, n <= 50
0 <= forest[i][j] <= 109
 */
public class CutOffTreesForGolfEvent_675 {

    static List<List<Integer>> stringToList(String str) {
        List<List<Integer>> list = new ArrayList<>();
        String [] tokens = str.split("],\\[");
        for(String token : tokens) {
            List<Integer> intList = new ArrayList<>();
            for(String t : token.split(",")) {
                t = t.replaceAll("\\[", "").replaceAll("]", "");
                intList.add(Integer.parseInt(t));
            }
            list.add(intList);
        }
        return list;
    }

    public static void main(String[] args) {
        CutOffTreesForGolfEvent_675 solution = new CutOffTreesForGolfEvent_675();

        int expected = 6;
        int actual = solution.cutOffTree(stringToList("[[1,2,3],[0,0,4],[7,6,5]]"));
        assert actual == expected : String.format("Expected: %d, actual: %d", expected, actual);

        expected = -1;
        actual = solution.cutOffTree(stringToList("[[1,2,3],[0,0,0],[7,6,5]]"));
        assert actual == expected : String.format("Expected: %d, actual: %d", expected, actual);
    }

    int rows, cols;
    int [][] arr;


    static class Cell {
        int x, y, h, p;

        Cell(int x, int y, int h, int p) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.p = p;
        }

        @Override
        public int hashCode() {
            return p;
        }

        @Override
        public boolean equals(Object other) {
            if(this == other) return true;
            Cell o = (Cell) other;
            return this.p == o.p;
        }
    }

    static class GroupCounter {
        int groups;
        int [] parents;
        int [] needUpdate;
        int p, t1, t2;

        public GroupCounter(int total) {
            parents = new int [total];
            needUpdate = new int [total];

            for(int i = 0; i < total; i++) {
                parents[i] = i;
            }

            groups = total;
        }


        int findUltimateParent(int i) {
            //System.out.println(":findUltimateParent " + i);
            t1 = 0;
            p = parents[i];
            while(parents[p] != p) {
                //System.out.println("needUpdate " + i);
                needUpdate[t1++] = p;
                p = parents[p];
            }
            for(t2 = 0; t2 < t1; t2++) {
                parents[needUpdate[t2]] = p;
            }
            return p;
        }

        void join(int i, int j) {
            //System.out.println(">> joining " + i + ", " + j);
            int pi = findUltimateParent(i);
            int pj = findUltimateParent(j);

            if(pi != pj) {
                if(pi < pj) {
                    parents[pj] = pi;
                } else {
                    parents[pi] = pj;
                }
                groups--;
            }
            //System.out.println("  parents " + Arrays.toString(parents));
        }
    }

    void printList(List<List<Integer>> forest) {
        AtomicInteger n = new AtomicInteger(10);
        for(List<Integer> list : forest) {
            System.out.println(list.stream().map(num -> String.format("%5d", (num == 0 ? 0 : n.incrementAndGet()))).collect(Collectors.toList()));
        }
    }

    void printGCParents(GroupCounter gc) {
        System.out.println();
        for(int i = 0; i < gc.parents.length; i++) {
            if(i % cols == 0) {
                System.out.println();
            }
            System.out.printf("%5d, ", gc.parents[i]);
        }
    }

    public int cutOffTree(List<List<Integer>> forest) {
        //printList(forest);
        if(forest.get(0).get(0) == 0) return -1;

        rows = forest.size();
        cols = forest.get(0).size();
        arr = new int[rows][cols];

        PriorityQueue<Cell> heap = new PriorityQueue<>(Comparator.comparingInt(c -> c.h));
        int zeros = 0;
        int p, h;

        GroupCounter gc = new GroupCounter(rows * cols);

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                h = forest.get(i).get(j);
                arr[i][j] = h;
                p = (i * cols) + j;
                if(h == 0) {
                    zeros++;
                } else {
                    if(h != 1) {
                        heap.add(new Cell(i, j, h, p));
                    }

                    if(i > 0 && arr[i-1][j] != 0) {
                        gc.join(p, ((i-1) * cols) + j);
                    }
                    if(j > 0 && arr[i][j-1] != 0) {
                        gc.join(p, (i * cols) + j - 1);
                    }
                }
            }
            //printGCParents(gc);
        }
        //System.out.println(">> zeros: " + zeros + ", gc.groups: " + gc.groups);

        if ((gc.groups - zeros) > 1) return -1; //disconnected groups found

        boolean zeroFound = zeros > 0;
        int total = 0, i;
        int src = 0, tgt;
        Cell c;
        while(!heap.isEmpty()) {
            //c = heap.poll();
            tgt = heap.poll().p;
            //calc steps from src to tgt
            if(zeroFound) {

                Set<Integer> srcNw = new HashSet<>();
                srcNw.add(src);
                Set<Integer> tgtNw = new HashSet<>();
                tgtNw.add(tgt);
                int lvl = 0;
                boolean [] visited = new boolean[rows * cols];
                boolean tgtFound = src == tgt;

                while(!srcNw.isEmpty() && !tgtNw.isEmpty()) {

                    if(tgtFound) {
                        break;
                    }

                    if(lvl++ % 2 == 0) {
                        Set<Integer> next = new HashSet<>();
                        if(getNextLevelNetwork(srcNw, visited, next, tgtNw)) {
                            tgtFound = true;
                            break;
                        }
                        srcNw = next;
                    } else {
                        Set<Integer> next = new HashSet<>();
                        if(getNextLevelNetwork(tgtNw, visited, next, srcNw)) {
                            tgtFound = true;
                            break;
                        }
                        tgtNw = next;
                    }

                }

                if(tgtFound) {
                    total += lvl;
                } else {
                    return -1;
                }
            } else {
                total += Math.abs((src / cols) - (tgt / cols)) + Math.abs((src % cols) - (tgt % cols));
            }
            src = tgt;
        }
        return total;
    }


    boolean getNextLevelNetwork(Set<Integer> nw, boolean [] visited, Set<Integer> newNw, Set<Integer> otherNw) {

        for(Integer p : nw) {
            visited[p] = true;
            int x = p / cols;
            int y = p % cols;
            int nbr = x > 0 ? (((x-1) * cols) + y) : -1;
            if(nbr != -1 && arr[x-1][y] != 0 && !visited[nbr]) {
                if(otherNw.contains(nbr))
                    return true;
                newNw.add(nbr);
            }
            nbr = x < rows-1 ? (((x+1) * cols) + y) : -1;
            if(nbr != -1 && arr[x+1][y] != 0 && !visited[nbr]) {
                if(otherNw.contains(nbr))
                    return true;
                newNw.add(nbr);
            }
            nbr = y > 0 ? ((x * cols) + y - 1) : -1;
            if(nbr != -1 && arr[x][y-1] != 0 && !visited[nbr]) {
                if(otherNw.contains(nbr))
                    return true;
                newNw.add(nbr);
            }
            nbr = y < cols-1 ? ((x * cols) + y + 1) : -1;
            if(nbr != -1 && arr[x][y+1] != 0 && !visited[nbr]) {
                if(otherNw.contains(nbr))
                    return true;
                newNw.add(nbr);
            }
        }
        return false;
    }
}

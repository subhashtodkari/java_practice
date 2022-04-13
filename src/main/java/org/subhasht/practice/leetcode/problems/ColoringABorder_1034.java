package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class ColoringABorder_1034 {

    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();
    }

    static void test01() {
        ColoringABorder_1034 solution = new ColoringABorder_1034();
        int [][] grid = {
                {1}
        };
        int [][] expected = {
                {2}
        };
        int [][] actual = solution.colorBorder(grid, 0, 0, 2);
        Assertions.assertEquals(expected.length, actual.length);
        for(int i = 0; i < actual.length; i++) {
            Assertions.assertArrayEquals(expected[i], actual[i], "Row# " + i + " mismatched");
        }
    }

    static void test02() {
        ColoringABorder_1034 solution = new ColoringABorder_1034();
        int [][] grid = {
                {1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1}
        };
        int [][] expected = {
                {2,2,2,2,2,2,2,2,2,2},
                {2,1,1,1,1,1,1,1,1,2},
                {2,1,1,1,1,1,1,1,1,2},
                {2,1,1,1,1,1,1,1,1,2},
                {2,2,2,2,2,2,2,2,2,2}
        };
        int [][] actual = solution.colorBorder(grid, 0, 0, 2);
        Assertions.assertEquals(expected.length, actual.length);
        for(int i = 0; i < actual.length; i++) {
            Assertions.assertArrayEquals(expected[i], actual[i], "Row# " + i + " mismatched");
        }
    }

    static void test03() {
        ColoringABorder_1034 solution = new ColoringABorder_1034();
        int [][] grid = {
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1}
        };
        int [][] expected = {
                {2,2,2,2,0,2,2,2,2,2},
                {2,1,1,2,0,2,1,1,1,2},
                {2,1,1,1,2,1,1,1,1,2},
                {2,1,1,1,1,1,1,1,1,2},
                {2,2,2,2,2,2,2,2,2,2}
        };
        int [][] actual = solution.colorBorder(grid, 0, 0, 2);
        Assertions.assertEquals(expected.length, actual.length);
        for(int i = 0; i < actual.length; i++) {
            Assertions.assertArrayEquals(expected[i], actual[i], "Row# " + i + " mismatched");
        }
    }

    static void test04() {
        ColoringABorder_1034 solution = new ColoringABorder_1034();
        int [][] grid = {
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,2,2,2,2,1},
                {1,1,1,1,0,1,1,1,1,2},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1}
        };
        int [][] expected = {
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,0,2,2,2,2,1},
                {1,1,1,1,0,2,2,2,2,2},
                {1,1,1,1,0,2,1,1,1,2},
                {1,1,1,1,0,2,2,2,2,2}
        };
        int [][] actual = solution.colorBorder(grid, 3, 6, 2);
        Assertions.assertEquals(expected.length, actual.length);
        for(int i = 0; i < actual.length; i++) {
            Assertions.assertArrayEquals(expected[i], actual[i], "Row# " + i + " mismatched");
        }
    }


    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int oldColor = grid[row][col];
        int h = grid.length;
        int w = grid[0].length;
        Set<Point> visited = new HashSet<>();
        Set<Point> border = new HashSet<>();
        Deque<Point> q = new LinkedList<>();
        Point p = new Point(row, col);
        q.add(p);
        visited.add(p);

        while(!q.isEmpty()) {
            p = q.remove();

            if(onBorder(p, grid, h, w, oldColor)) {
                border.add(p);
            }
            for(Point neighbor : getUnvisitedNeighbors(p, grid, h, w, visited)) {
                q.add(neighbor);
                visited.add(neighbor);
            }
        }

        for(Point p1 : border) {
            grid[p1.x][p1.y] = color;
        }
        return grid;
    }

    boolean onBorder(Point p, int [][] grid, int h, int w, int oldColor) {
        if(p.x == 0 || p.x == h-1 || p.y == 0 || p.y == w-1) {
            return true;
        }
        return grid[p.x-1][p.y] != oldColor || grid[p.x+1][p.y] != oldColor || grid[p.x][p.y-1] != oldColor || grid[p.x][p.y+1] != oldColor;
    }

    List<Point> getUnvisitedNeighbors(Point p, int [][] grid, int h, int w, Set<Point> visited) {
        List<Point> neighbors = new ArrayList<>(4);
        Point n;
        if(p.x > 0 && grid[p.x-1][p.y] == grid[p.x][p.y]) {
            n = new Point(p.x-1, p.y);
            if(!visited.contains(n)) {
                neighbors.add(n);
            }
        }
        if(p.x < h-1 && grid[p.x+1][p.y] == grid[p.x][p.y]) {
            n = new Point(p.x+1, p.y);
            if(!visited.contains(n)) {
                neighbors.add(n);
            }
        }
        if(p.y > 0 && grid[p.x][p.y-1] == grid[p.x][p.y]) {
            n = new Point(p.x, p.y-1);
            if(!visited.contains(n)) {
                neighbors.add(n);
            }
        }
        if(p.y < w-1 && grid[p.x][p.y+1] == grid[p.x][p.y]) {
            n = new Point(p.x, p.y+1);
            if(!visited.contains(n)) {
                neighbors.add(n);
            }
        }
        return neighbors;
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
}

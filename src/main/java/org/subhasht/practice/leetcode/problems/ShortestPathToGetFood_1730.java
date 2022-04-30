package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class ShortestPathToGetFood_1730 {

    public static void main(String[] args) {
        ShortestPathToGetFood_1730 solution = new ShortestPathToGetFood_1730();
        solution.test01();
        solution.test02();
        solution.test03();
    }

    void test01() {
        char [][] grid = {{'X','X','X','X','X','X'},{'X','*','O','O','O','X'},{'X','O','O','#','O','X'},{'X','X','X','X','X','X'}};
        Assertions.assertEquals(3, getFood(grid));
    }

    void test02() {
        char [][] grid = {{'X','X','X','X','X'},{'X','*','X','O','X'},{'X','O','X','#','X'},{'X','X','X','X','X'}};
        Assertions.assertEquals(-1, getFood(grid));
    }

    void test03() {
        char [][] grid = {{'X','X','X','X','X','X','X','X'},{'X','*','O','X','O','#','O','X'},{'X','O','O','X','O','O','X','X'},{'X','O','O','O','O','#','O','X'},{'X','X','X','X','X','X','X','X'}};
        Assertions.assertEquals(6, getFood(grid));
    }

    public int getFood(char[][] grid) {
        Pos origin = null, p;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '*') {
                    origin = new Pos(i, j);
                    break;
                }
            }
        }

        int dist = 1;
        Set<Pos> visited = new HashSet<>();
        Deque<Pos> q = new LinkedList<>();
        q.add(origin);
        visited.add(origin);
        int cntLvlSize = 1;

        while(!q.isEmpty()) {
            cntLvlSize = q.size();
            for(int i = 0; i < cntLvlSize; i++) {
                p = q.remove();

                Pos [] nbrs = new Pos[] {
                        new Pos(p.x-1, p.y),
                        new Pos(p.x+1, p.y),
                        new Pos(p.x, p.y-1),
                        new Pos(p.x, p.y+1)
                };

                for(Pos nbr : nbrs) {
                    if(nbr.x >= 0 && nbr.x < grid.length &&
                            nbr.y >= 0 && nbr.y < grid[0].length &&
                            grid[nbr.x][nbr.y] != 'X' && !visited.contains(nbr)) {
                        if(grid[nbr.x][nbr.y] == '#') return dist;
                        q.add(nbr);
                        visited.add(nbr);
                    }
                }
            }

            dist++;
        }

        return -1;
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object other) {
            if(this == other) return true;
            if(other == null || other.getClass() != this.getClass()) return false;
            Pos o = (Pos) other;
            return this.x == o.x && this.y == o.y;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}

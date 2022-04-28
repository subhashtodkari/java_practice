package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/*
There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
The ball can go through the empty spaces by rolling up, down, left or right,
but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination,
where start = [startrow, startcol] and
destination = [destinationrow, destinationcol],
return true if the ball can stop at the destination, otherwise return false.

You may assume that the borders of the maze are all walls (see examples).


Example 1:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
Example 2:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
Output: false
Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
Example 3:

Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
Output: false


Constraints:

m == maze.length
n == maze[i].length
1 <= m, n <= 100
maze[i][j] is 0 or 1.
start.length == 2
destination.length == 2
0 <= startrow, destinationrow <= m
0 <= startcol, destinationcol <= n
Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
The maze contains at least 2 empty spaces.
 */
public class TheMaze_490 {

    public static void main(String[] args) {
        TheMaze_490 solution = new TheMaze_490();
        test_01(solution);
    }

    public static void test_01(TheMaze_490 solution) {
        int [][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        int [] start = {0,4};
        int [] destination = {4,4};
        Assertions.assertTrue(solution.hasPath(maze, start, destination));
    }

    public static void test_02(TheMaze_490 solution) {
        int [][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        int [] start = {0,4};
        int [] destination = {3,2};
        Assertions.assertFalse(solution.hasPath(maze, start, destination));
    }

    public static void test_03(TheMaze_490 solution) {
        int [][] maze = {{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
        int [] start = {4,3};
        int [] destination = {0,1};
        Assertions.assertFalse(solution.hasPath(maze, start, destination));
    }


    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Position pos = new Position(start[0], start[1]);
        Position tgt = new Position(destination[0], destination[1]);
        if(pos.equals(tgt))
            return true;
        Set<Position> visited = new HashSet<>();
        Deque<Position> q = new LinkedList<>();

        q.add(pos);
        visited.add(pos);

        int i, j;
        Position nextPos;

        while(!q.isEmpty()) {
            pos = q.remove();
            //System.out.println("Checking: " + pos);
            //check
            if(pos.equals(tgt))
                return true;

            //else check top
            i = pos.x;
            j = pos.y;
            while(i >= 0 && maze[i][j] == 0) {
                i--;
            }
            i++;
            nextPos = new Position(i, j);
            if(!visited.contains(nextPos)) {
                //System.out.println("Adding: " + nextPos);
                visited.add(nextPos);
                q.add(nextPos);
            }

            //check bottom
            i = pos.x;
            j = pos.y;
            while(i < maze.length && maze[i][j] == 0) {
                i++;
            }
            i--;
            nextPos = new Position(i, j);
            if(!visited.contains(nextPos)) {
                //System.out.println("Adding: " + nextPos);
                visited.add(nextPos);
                q.add(nextPos);
            }

            //check left
            i = pos.x;
            j = pos.y;
            while(j >= 0 && maze[i][j] == 0) {
                j--;
            }
            j++;
            nextPos = new Position(i, j);
            if(!visited.contains(nextPos)) {
                //System.out.println("Adding: " + nextPos);
                visited.add(nextPos);
                q.add(nextPos);
            }

            //check right
            i = pos.x;
            j = pos.y;
            while(j < maze[0].length && maze[i][j] == 0) {
                j++;
            }
            j--;
            nextPos = new Position(i, j);
            if(!visited.contains(nextPos)) {
                //System.out.println("Adding: " + nextPos);
                visited.add(nextPos);
                q.add(nextPos);
            }

        }
        return false;
    }


    static class Position {
        int x, y;
        public Position(int i, int j) {
            x = i;
            y = j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || o.getClass() != this.getClass()) return false;
            Position other = (Position) o;
            return Objects.equals(x, other.x) && Objects.equals(y, other.y);
        }

        @Override
        public String toString() {
            return "Position["+x+", "+y+"]";
        }
    }
}

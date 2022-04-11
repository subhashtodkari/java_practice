package org.subhasht.practice.misc;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class IslandPerimeter {

    public static void main(String[] args) {
        IslandPerimeter solution = new IslandPerimeter();

        int [][] matrix = {
                {1,0,1,1,1},
                {1,0,1,1,1},
                {0,0,0,1,1}
        };

        Assertions.assertEquals(7, solution.calcMaxIslandPerimeter(matrix));

        int [][]  matrix1 = {
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1}
        };

        Assertions.assertEquals(12, solution.calcMaxIslandPerimeter(matrix1));

        int [][]  matrix2 = {
                {0,0,1,1,1},
                {0,1,1,1,1},
                {1,1,1,1,1}
        };

        Assertions.assertEquals(10, solution.calcMaxIslandPerimeter(matrix2));

        int [][]  matrix3 = {
                {1,1,1,1,1},
                {1,0,1,0,1},
                {1,1,1,1,1}
        };

        Assertions.assertEquals(13, solution.calcMaxIslandPerimeter(matrix3));
    }


    int calcMaxIslandPerimeter(int [][] matrix) {
        int maxPerimeter = 0;
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 1) {
                    int perimeter = 0;
                    Deque<Point> deque = new LinkedList<>();
                    Set<Point> visited = new HashSet<>();

                    deque.add(new Point(i, j));

                    while (!deque.isEmpty()) {
                        Point p = deque.remove();
                        visited.add(p);

                        matrix[p.x][p.y] = 2;

                        if(isPerimeter(p, matrix)) {
                            perimeter++;
                        }

                        if(p.x > 0 && matrix[p.x-1][p.y] == 1) {
                            Point top = new Point(p.x-1, p.y);
                            if(!visited.contains(top)) {
                                deque.add(top);
                                visited.add(top);
                            }
                        }

                        if(p.x < matrix.length-1 && matrix[p.x+1][p.y] == 1) {
                            Point bottom = new Point(p.x+1, p.y);
                            if(!visited.contains(bottom)) {
                                deque.add(bottom);
                                visited.add(bottom);
                            }
                        }

                        if(p.y > 0 && matrix[p.x][p.y-1] == 1) {
                            Point left = new Point(p.x, p.y-1);
                            if(!visited.contains(left)) {
                                deque.add(left);
                                visited.add(left);
                            }
                        }

                        if(p.y < matrix[0].length-1 && matrix[p.x][p.y+1] == 1) {
                            Point right = new Point(p.x, p.y+1);
                            if(!visited.contains(right)) {
                                deque.add(right);
                                visited.add(right);
                            }
                        }
                    }

                    if(perimeter > maxPerimeter) {
                        maxPerimeter = perimeter;
                    }
                }
            }
        }
        return maxPerimeter;
    }

    boolean isPerimeter(Point p, int [][] matrix) {
        if(p.x == 0 || p.y == 0 || p.x == matrix.length-1 || p.y == matrix[0].length-1)
            return true;

        return matrix[p.x-1][p.y] == 0 || matrix[p.x+1][p.y] == 0 || matrix[p.x][p.y-1] == 0 || matrix[p.x][p.y+1] == 0;
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

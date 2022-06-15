package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/*
You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.

In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.

Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.



Example 1:


Input: n = 3, relations = [[1,3],[2,3]]
Output: 2
Explanation: The figure above represents the given graph.
In the first semester, you can take courses 1 and 2.
In the second semester, you can take course 3.
Example 2:


Input: n = 3, relations = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: No course can be studied because they are prerequisites of each other.


Constraints:

1 <= n <= 5000
1 <= relations.length <= 5000
relations[i].length == 2
1 <= prevCoursei, nextCoursei <= n
prevCoursei != nextCoursei
All the pairs [prevCoursei, nextCoursei] are unique.
 */
public class ParallelCourses_1136 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertEquals(1, solution.minimumSemesters(1, new int [][] {}));
        Assertions.assertEquals(2, solution.minimumSemesters(2, new int [][] {{1,2}}));
        Assertions.assertEquals(2, solution.minimumSemesters(3, new int [][] {{1,2}, {3,2}}));
        Assertions.assertEquals(-1, solution.minimumSemesters(2, new int [][] {{1,2}, {2,1}}));
    }

    //best from my side
    static class Solution {

        public int minimumSemesters(int n, int[][] relations) {
            List[] adjList = new List[n];
            int[] prerequisite = new int[n];

            for (int i = 0; i < n; ++i) {
                adjList[i] = new ArrayList<Integer>();
            }

            Deque<Integer> q = new LinkedList<>();

            for (int[] relation : relations) {
                adjList[relation[0] - 1].add(relation[1] - 1);
                prerequisite[relation[1] - 1]++;
            }

            int visited = 0;
            for (int i = 0; i < n; ++i) {
                if (prerequisite[i] == 0) {
                    q.add(i);
                }
            }

            int node;
            int [] sems = new int[n];
            int max = 0;
            while(!q.isEmpty()) {
                node = q.remove();
                visited++;
                for(int next : (List<Integer>)adjList[node]) {
                    prerequisite[next]--;
                    if(prerequisite[next] == 0) {
                        q.add(next);
                        if(sems[node]+1 > sems[next])
                            sems[next] = sems[node]+1;
                        if(sems[next] > max)
                            max = sems[next];
                    }
                }
            }

            return visited == n ? max + 1 : -1;
        }
    }

    static class Solution1 {

        public int minimumSemesters(int n, int[][] relations) {
            int [] pre = new int [n+1];
            Map<Integer, List<Integer>> adj = new HashMap<>();
            Deque<Integer> roots = new LinkedList<>();

            for(int [] r : relations) {
                adj.putIfAbsent(r[0], new LinkedList<>());
                adj.get(r[0]).add(r[1]);
                pre[r[1]]++;
            }

            for(int i = 1; i <= n; i++) {
                if(pre[i] == 0)
                    roots.add(i);
            }

            if(roots.isEmpty()) return -1;

            int sems = 0;
            while(!roots.isEmpty()) {
                int sz = roots.size();
                for(int i = 0; i < sz; i++) {
                    int completed = roots.remove();
                    if(adj.containsKey(completed)) {
                        for(int successor : adj.get(completed)) {
                            pre[successor]--;
                            if(pre[successor] == 0)
                                roots.add(successor);
                        }
                        adj.remove(completed);
                    }
                }
                sems++;
            }

            return adj.isEmpty() ? sems : -1;
        }
    }
}

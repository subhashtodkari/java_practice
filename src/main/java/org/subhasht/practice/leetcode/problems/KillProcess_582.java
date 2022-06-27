package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/*
You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid, where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.

Each process has only one parent process but may have multiple children processes. Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).

When a process is killed, all of its children processes will also be killed.

Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes that will be killed. You may return the answer in any order.



Example 1:


Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
Output: [5,10]
Explanation: The processes colored in red are the processes that should be killed.
Example 2:

Input: pid = [1], ppid = [0], kill = 1
Output: [1]


Constraints:

n == pid.length
n == ppid.length
1 <= n <= 5 * 104
1 <= pid[i] <= 5 * 104
0 <= ppid[i] <= 5 * 104
Only one process has no parent.
All the values of pid are unique.
kill is guaranteed to be in pid.
 */
public class KillProcess_582 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assertions.assertIterableEquals(List.of(5, 10), solution.killProcess(List.of(1,3,10,5), List.of(3,0,5,3), 5));
    }

    //recursive
    static class Solution {
        public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
            Map<Integer, List<Integer>> adjListMap = new HashMap<>();
            for(int i = 0; i < pid.size(); i++) {
                adjListMap.putIfAbsent(ppid.get(i), new ArrayList<>());
                adjListMap.get(ppid.get(i)).add(pid.get(i));
            }

            List<Integer> killed = new ArrayList<>();
            populate(adjListMap, kill, killed);
            return killed;
        }

        void populate(Map<Integer, List<Integer>> adjListMap, int kill, List<Integer> killed) {
            killed.add(kill);
            if(adjListMap.containsKey(kill)) {
                for(int child : adjListMap.get(kill)) {
                    populate(adjListMap, child, killed);
                }
            }
        }
    }

    //BSF
    static class Solution1 {
        public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
            Map<Integer, List<Integer>> adjListMap = new HashMap<>();
            for(int i = 0; i < pid.size(); i++) {
                adjListMap.putIfAbsent(ppid.get(i), new ArrayList<>());
                adjListMap.get(ppid.get(i)).add(pid.get(i));
            }

            List<Integer> killed = new ArrayList<>();
            Deque<Integer> q = new LinkedList<>();
            q.add(kill);
            while(!q.isEmpty()) {
                int k = q.remove();
                killed.add(k);
                if(adjListMap.containsKey(k)) {
                    q.addAll(adjListMap.get(k));
                }
            }

            return killed;
        }
    }
}

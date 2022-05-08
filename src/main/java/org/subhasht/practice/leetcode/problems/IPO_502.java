package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Comparator;

public class IPO_502 {

    public static void main(String[] args) {
        IPO_502 solution = new IPO_502();
        solution.test01();
        solution.test02();
    }

    void test01() {
        int k = 2;
        int w = 0;
        int [] profits = {1,2,3};
        int [] initialCapital = {0,1,1};
        Assertions.assertEquals(4, findMaximizedCapital(k, w, profits, initialCapital));
    }

    void test02() {
        int k = 3;
        int w = 0;
        int [] profits = {1,2,3};
        int [] initialCapital = {0,1,2};
        Assertions.assertEquals(6, findMaximizedCapital(k, w, profits, initialCapital));
    }

    public int findMaximizedCapital(int k, int w, int [] profits, int [] initialCapital) {
        //return findMaximizedCapitalRecursively(k, w, profits, initialCapital, 0);
        return findMaximizedCapital1(k, w, profits, initialCapital);
    }

    public int findMaximizedCapital1(int k, int w, int [] profits, int [] initialCapital) {
        Project [] arr = new Project[profits.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = new Project(profits[i], initialCapital[i]);
        }

        Arrays.sort(arr, Comparator.comparingInt(p -> p.initialCapital));

        ProjectNode head = createNode(arr, 0);

        return findMaximizedCapital1(k, w, head);
    }

    int findMaximizedCapital1(int k, int w, ProjectNode head) {
        if(k == 0 || head == null || head.project.initialCapital > w)
            return w;

        int max = 0, cap;
        ProjectNode trav = head, temp;
        while(trav != null && trav.project.initialCapital <= w) {
            temp = null;
            if(trav == head) {
                temp = head;
                head = head.next;
            } else {
                trav.prev.next = trav.next;
                if(trav.next != null)
                    trav.next.prev = trav.prev;
            }

            cap = findMaximizedCapital1(k-1, w + trav.project.profit, head);
            if(cap > max)
                max = cap;

            if(temp != null) {
                head = temp;
            } else {
                trav.prev.next = trav;
                if(trav.next != null)
                    trav.next.prev = trav;
            }

            trav = trav.next;
        }
        return max;
    }

    ProjectNode createNode(Project [] arr, int i) {
        if(i == arr.length)
            return null;
        return new ProjectNode(arr[i], createNode(arr, i+1));
    }

    static class Project {
        int profit, initialCapital;

        public Project(int profit, int initialCapital) {
            this.profit = profit;
            this.initialCapital = initialCapital;
        }
    }

    static class ProjectNode {
        Project project;
        ProjectNode prev, next;

        public ProjectNode(Project project) {
            this.project = project;
        }

        public ProjectNode(Project project, ProjectNode next) {
            this.project = project;
            this.next = next;
            if(next != null)
                next.prev = this;
        }
    }

    public int findMaximizedCapitalRecursively(int k, int w, int [] profits, int [] initialCapital, int i) {
        if (k == 0 || i == profits.length)
            return w;
        int capitalWithoutProjectI = findMaximizedCapitalRecursively(k, w, profits, initialCapital, i+1);
        int capitalWithProjectI = w;
        if(w >= initialCapital[i]) {
            capitalWithProjectI = findMaximizedCapitalRecursively(k-1, w + profits[i], profits, initialCapital, i+1);
        }
        return Math.max(capitalWithoutProjectI, capitalWithProjectI);
    }
}

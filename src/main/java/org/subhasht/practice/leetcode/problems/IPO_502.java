package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

public class IPO_502 {

    public static void main(String[] args) {
        IPO_502 solution = new IPO_502();
        solution.test01();
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
        return findMaximizedCapital(k, w, profits, initialCapital, 0);
    }

    public int findMaximizedCapital(int k, int w, int [] profits, int [] initialCapital, int i) {
        if (k == 0 || i == profits.length)
            return w;
        int capitalWithoutProjectI = findMaximizedCapital(k, w, profits, initialCapital, i+1);
        int capitalWithProjectI = w;
        if(w >= initialCapital[i]) {
            capitalWithProjectI = findMaximizedCapital(k-1, w + profits[i], profits, initialCapital, i+1);
        }
        return Math.max(capitalWithoutProjectI, capitalWithProjectI);
    }
}

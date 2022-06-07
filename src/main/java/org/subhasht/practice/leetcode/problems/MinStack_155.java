package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.


Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2


Constraints:

-231 <= val <= 231 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */
public class MinStack_155 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-30);
        minStack.push(30);
        Assertions.assertEquals(30, minStack.top());
        Assertions.assertEquals(-30, minStack.getMin());
        minStack.push(-50);
        minStack.push(0);
        Assertions.assertEquals(0, minStack.top());
        Assertions.assertEquals(-50, minStack.getMin());
        minStack.pop();
        minStack.pop();
        Assertions.assertEquals(-30, minStack.getMin());
    }
}

//this is the best one from my end
class MinStack {

    int [] nums;
    int [] mins;
    int top;

    public MinStack() {
        nums = new int[10000];
        mins = new int[10000];
        top = 0;
        mins[0] = Integer.MAX_VALUE;
    }

    public void push(int val) {
        nums[++top] = val;
        mins[top] = Math.min(val, mins[top-1]);
    }

    public void pop() {
        top--;
    }

    public int top() {
        return nums[top];
    }

    public int getMin() {
        return mins[top];
    }
}

//this one I really liked and happy about
class MinStack_Better {

    static class Node {
        int data;
        Node next;
        int min;
    }

    Node top;

    /** initialize your data structure here. */
    public MinStack_Better() {

    }

    public void push(int val) {
        Node n = new Node();
        n.data = val;

        if(top == null) {
            n.min = val;
            top = n;
        } else {
            n.min = Math.min(val, top.min);
            n.next = top;
            top = n;
        }
    }

    public void pop() {
        top = top.next;
    }

    public int top() {
        return top.data;
    }

    public int getMin() {
        return top.min;
    }
}

//typical implementation but not top notch
class MinStack_Typical {

    Deque<Integer> stack;
    TreeMap<Integer, Integer> countMap;

    public MinStack_Typical() {
        stack = new LinkedList<>();
        countMap = new TreeMap<>();
    }

    public void push(int val) {
        stack.push(val);
        countMap.putIfAbsent(val, 0);
        countMap.put(val, countMap.get(val) + 1);
    }

    public void pop() {
        int top = stack.pop();
        if(countMap.get(top) == 1) {
            countMap.remove(top);
        } else {
            countMap.put(top, countMap.get(top)-1);
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return countMap.firstKey();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
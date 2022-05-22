package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.TreeSet;

public class RangeSumQueryMutable_307 {

    public static void main(String[] args) {
        test(
                new String [] {"NumArray","update","update","update","sumRange","update","sumRange","update","sumRange","sumRange","update"},
                new Object[] {
                        new int [] {7,2,7,2,0},
                        new int [] {4,6},
                        new int [] {0,2},
                        new int [] {0,9},
                        new int [] {4,4},
                        new int [] {3,8},
                        new int [] {0,4},
                        new int [] {4,1},
                        new int [] {0,3},
                        new int [] {0,4},
                        new int [] {0,4}
                },
                new Object[] {null,null,null,null,6,null,32,null,26,27,null});
    }

    static void test(String [] commands, Object [] input, Object [] expected) {
        NumArray numArray = null;
        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            switch (command) {
                case "NumArray":
                    numArray = new NumArray((int[]) (input[i]));
                    break;
                case "update":
                    numArray.update(((int[])input[i])[0], ((int[])input[i])[1]);
                    break;
                case "sumRange":
                    Assertions.assertEquals((int)expected[i], numArray.sumRange(((int[])input[i])[0], ((int[])input[i])[1]));
                    break;
            }
        }
    }

    static class NumArray {

        static class Node {
            int start, end;
            int val;
            Node parent, left, right;

            public Node(int [] nums, int start, int end, Node parent, Node [] arr) {
                this.start = start;
                this.end = end;
                this.parent = parent;
                if(start == end) {
                    val = nums[start];
                    arr[start] = this;
                } else {
                    int mid = (start + end)/2;
                    left = new Node(nums, start, mid, this, arr);
                    right = new Node(nums, mid+1, end, this, arr);
                    val = left.val + right.val;
                }
            }

            void update(int delta) {
                val += delta;
                if(parent != null)
                    parent.update(delta);
            }

            int sum(int start, int end) {
                if(start == this.start && end == this.end)
                    return this.val;

                if(start > end || start < this.start || end > this.end)
                    throw new RuntimeException("Invalid bounds (" + start + ", " + end +") for node (" + this.start + ", " + this.end + ")");

                int leftSum = 0, rightSum = 0;
                if(left.end >= start)
                    leftSum = left.sum(start, Math.min(end, left.end));
                if(right.start <= end)
                    rightSum = right.sum(Math.max(start, right.start), end);

                return  leftSum + rightSum;
            }
        }

        static Node root;
        static Node [] arr;

        public NumArray(int[] nums) {
            arr = new Node[nums.length];
            root = new Node(nums, 0, nums.length-1, null, arr);
        }

        public void update(int index, int val) {
            arr[index].update(val - arr[index].val);
        }

        public int sumRange(int left, int right) {
            return root.sum(left, right);
        }
    }

    static class NumArray1 {

        TreeSet<Integer>[] arr = new TreeSet[201];
        int [] nums;

        public NumArray1(int[] nums) {
            this.nums = nums;
            for(int i = 0; i < arr.length; i++) {
                arr[i] = new TreeSet<>();
            }
            for(int i = 0; i < nums.length; i++) {
                arr[nums[i] + 100].add(i);
            }
        }

        public void update(int index, int val) {
            arr[nums[index] + 100].remove(index);
            nums[index] = val;
            arr[val + 100].add(index);
        }

        public int sumRange(int left, int right) {
            int total = 0, val;
            for(int i = 0; i < arr.length; i++) {
                val = i - 100;
                total += (val * arr[i].subSet(left, true, right, true).size());
            }
            return total;
        }
    }

}

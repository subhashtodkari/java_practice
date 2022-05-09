package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Objects;

/*
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.



Example 1:


Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: list1 = [], list2 = []
Output: []
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]


Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoSortedLists_21 {

    public static void main(String[] args) {
        MergeTwoSortedLists_21 solution = new MergeTwoSortedLists_21();
        ListNode list1 = createList(1,2,4);
        ListNode list2 = createList(1,3,4);
        ListNode expected = createList(1,1,2,3,4,4);
        Assertions.assertEquals(expected, solution.mergeTwoLists(list1, list2));

        list1 = createList(1,2,3);
        list2 = createList(3,4,5);
        expected = createList(1,2,3,3,4,5);
        Assertions.assertEquals(expected, solution.mergeTwoLists(list1, list2));

        list1 = createList(1,2,3);
        list2 = createList();
        expected = createList(1,2,3);
        Assertions.assertEquals(expected, solution.mergeTwoLists(list1, list2));
    }

    ListNode head = null, trav = head;

    void add(ListNode node) {
        if(head == null) {
            head = node;
            trav = node;
        } else {
            trav.next = node;
            trav = trav.next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        head = null;
        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                add(list1);
                list1 = list1.next;
            } else {
                add(list2);
                list2 = list2.next;
            }
        }

        while(list1 != null) {
            add(list1);
            list1 = list1.next;
        }

        while(list2 != null) {
            add(list2);
            list2 = list2.next;
        }

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListNode listNode = (ListNode) o;
            return val == listNode.val && Objects.equals(next, listNode.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, next);
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    static ListNode createList(int... nums) {
        ListNode head = null, trav = head;
        for(int i = 0; i < nums.length; i++) {
            if(i == 0) {
                head = new ListNode(nums[i]);
                trav = head;
            } else {
                trav.next = new ListNode(nums[i]);
                trav = trav.next;
            }
        }
        return head;
    }
}

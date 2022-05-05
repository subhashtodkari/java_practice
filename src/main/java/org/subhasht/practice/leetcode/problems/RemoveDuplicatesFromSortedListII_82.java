package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Objects;

/*
Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.



Example 1:


Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]
Example 2:


Input: head = [1,1,1,2,3]
Output: [2,3]


Constraints:

The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.
 */
public class RemoveDuplicatesFromSortedListII_82 {

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedListII_82 solution = new RemoveDuplicatesFromSortedListII_82();
        Assertions.assertEquals(numberToListNode(0), solution.deleteDuplicates(numberToListNode(0)));
        Assertions.assertEquals(numberToListNode(123), solution.deleteDuplicates(numberToListNode(123)));
        Assertions.assertNull(solution.deleteDuplicates(numberToListNode(1122)));
        Assertions.assertEquals(numberToListNode(234), solution.deleteDuplicates(numberToListNode(11234)));
        Assertions.assertEquals(numberToListNode(2), solution.deleteDuplicates(numberToListNode(112)));
        Assertions.assertEquals(numberToListNode(1), solution.deleteDuplicates(numberToListNode(122)));
        Assertions.assertEquals(numberToListNode(13), solution.deleteDuplicates(numberToListNode(1223)));
    }

    static ListNode numberToListNode(int number) {
        if(number == 0)
            return new ListNode(number);
        return new ListNode(number % 10, number > 10 ? numberToListNode(number / 10) : null);
    }

    public ListNode deleteDuplicates(ListNode head) {
        return recursive(head, null);
    }

    public ListNode recursive(ListNode node, ListNode prev) {
        if(node == null)
            return null;

        if((prev == null || prev.val != node.val) && (node.next == null || node.next.val != node.val))
            return new ListNode(node.val, recursive(node.next, node));
        else
            return recursive(node.next, node);

    }

    public ListNode deleteDuplicates1(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode prev = null, trav = head, next;
        while(trav != null) {
            next = trav.next;
            while(next != null && trav.val == next.val) {
                next = next.next;
            }
            if(trav.next != next) {
                //deletion needed from trav to till prev of next
                if(prev == null) {
                    head = next;
                } else {
                    prev.next = next;
                }

            } else {
                //no deletion
                prev = trav;
            }
            trav = next;

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
    }
}

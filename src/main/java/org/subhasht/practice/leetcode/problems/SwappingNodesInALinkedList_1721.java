package org.subhasht.practice.leetcode.problems;

/*
You are given the head of a linked list, and an integer k.

Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).



Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]
Example 2:

Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
Output: [7,9,6,6,8,7,3,0,9,5]


Constraints:

The number of nodes in the list is n.
1 <= k <= n <= 105
0 <= Node.val <= 100
 */
public class SwappingNodesInALinkedList_1721 {

    static class Solution {
        public ListNode swapNodes(ListNode head, int k) {
            ListNode kFromBeginning = null, kFromEnd = null, trav = head;
            int i = 1;

            while(trav != null) {
                if(i == k) {
                    kFromBeginning = trav;//hold ref to Kth node from beginning
                    kFromEnd = head;
                }
                trav = trav.next;
                if(i > k)
                    kFromEnd = kFromEnd.next;//kFromEnd will follow trav with keeping distance of K
                i++;
            }

            if(kFromBeginning != null && kFromEnd != null) {
                int temp = kFromBeginning.val;
                kFromBeginning.val = kFromEnd.val;
                kFromEnd.val = temp;
            }

            return head;
        }
    }
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

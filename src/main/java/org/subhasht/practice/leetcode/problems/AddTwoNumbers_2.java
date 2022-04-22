package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.Objects;

/*
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
*/
public class AddTwoNumbers_2 {

    public static void main(String[] args) {
        AddTwoNumbers_2 solution = new AddTwoNumbers_2();
        Assertions.assertEquals(numberToListNode(0), solution.addTwoNumbers(numberToListNode(0), numberToListNode(0)));
        Assertions.assertEquals(numberToListNode(123), solution.addTwoNumbers(numberToListNode(0), numberToListNode(123)));
        Assertions.assertEquals(numberToListNode(121), solution.addTwoNumbers(numberToListNode(55), numberToListNode(66)));
        Assertions.assertEquals(numberToListNode(121), solution.addTwoNumbers(numberToListNode(66), numberToListNode(55)));
    }

    static ListNode numberToListNode(int number) {
        if(number == 0)
            return new ListNode(number);
        return new ListNode(number % 10, number > 10 ? numberToListNode(number / 10) : null);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //return addTwoNumbersNonRecursively(l1, l2);
        return addTwoNumbersRecursively(l1, l2, 0);
    }

    public ListNode addTwoNumbersRecursively(ListNode l1, ListNode l2, int carry) {
        if(l1 == null && l2 == null)
            return carry > 0 ? new ListNode(carry) : null;

        int n1 = l1 == null ? 0 : l1.val;
        int n2 = l2 == null ? 0 : l2.val;
        int addition = n1 + n2 + carry;
        carry = addition / 10;
        addition = addition % 10;
        ListNode next = addTwoNumbersRecursively(l1 == null ? null : l1.next,
                l2 == null ? null : l2.next, carry);
        return new ListNode(addition, next);
    }


    public ListNode addTwoNumbersNonRecursively(ListNode l1, ListNode l2) {
        ListNode ans = null, prev = null;

        int addition, carry = 0;
        while(l1 != null && l2 != null) {
            addition = l1.val + l2.val + carry;
            carry = addition / 10;
            addition = addition % 10;
            if(ans == null) {
                ans = new ListNode(addition);
                prev = ans;
            } else {
                prev.next = new ListNode(addition);
                prev = prev.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null) {
            addition = l1.val + carry;
            carry = addition / 10;
            addition = addition % 10;
            prev.next = new ListNode(addition);
            prev = prev.next;
            l1 = l1.next;
        }

        while(l2 != null) {
            addition = l2.val + carry;
            carry = addition / 10;
            addition = addition % 10;
            prev.next = new ListNode(addition);
            prev = prev.next;
            l2 = l2.next;
        }

        if(carry != 0) {
            prev.next = new ListNode(carry);
        }

        return ans;
    }
}

/**
 * Definition for singly-linked list.
 *
 */
class ListNode {
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

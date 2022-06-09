package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/*
Design a phone directory that initially has maxNumbers empty slots that can store numbers. The directory should store numbers, check if a certain slot is empty or not, and empty a given slot.

Implement the PhoneDirectory class:

PhoneDirectory(int maxNumbers) Initializes the phone directory with the number of available slots maxNumbers.
int get() Provides a number that is not assigned to anyone. Returns -1 if no number is available.
bool check(int number) Returns true if the slot number is available and false otherwise.
void release(int number) Recycles or releases the slot number.


Example 1:

Input
["PhoneDirectory", "get", "get", "check", "get", "check", "release", "check"]
[[3], [], [], [2], [], [2], [2], [2]]
Output
[null, 0, 1, true, 2, false, null, true]

Explanation
PhoneDirectory phoneDirectory = new PhoneDirectory(3);
phoneDirectory.get();      // It can return any available phone number. Here we assume it returns 0.
phoneDirectory.get();      // Assume it returns 1.
phoneDirectory.check(2);   // The number 2 is available, so return true.
phoneDirectory.get();      // It returns 2, the only number that is left.
phoneDirectory.check(2);   // The number 2 is no longer available, so return false.
phoneDirectory.release(2); // Release number 2 back to the pool.
phoneDirectory.check(2);   // Number 2 is available again, return true.


Constraints:

1 <= maxNumbers <= 104
0 <= number < maxNumbers
At most 2 * 104 calls will be made to get, check, and release.
 */
public class DesignPhoneDirectory_379 {

    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory(50);
        Assertions.assertEquals(0, phoneDirectory.get());
        Assertions.assertEquals(1, phoneDirectory.get());
        Assertions.assertEquals(2, phoneDirectory.get());
        Assertions.assertTrue(phoneDirectory.check(3));
        Assertions.assertEquals(3, phoneDirectory.get());
        Assertions.assertFalse(phoneDirectory.check(3));
        Assertions.assertFalse(phoneDirectory.check(1));
        phoneDirectory.release(1);
        Assertions.assertTrue(phoneDirectory.check(1));
        Assertions.assertEquals(1, phoneDirectory.get());
    }
}

class PhoneDirectory {

    TreeSet<Integer> empty;
    //Set<Integer> booked;
    //int max;

    public PhoneDirectory(int maxNumbers) {
        empty = new TreeSet<>();
        //booked = new HashSet<>();
        //max = maxNumbers;
        for(int i = 0; i < maxNumbers; i++) {//O(N)
            empty.add(i);//O(Log N)
        }
    }

    public int get() {
        if(empty.isEmpty())
            return -1;
        int book = empty.first();//O(Log N)
        //booked.add(book);
        empty.remove(book);//O(Log N)
        return book;
    }

    public boolean check(int number) {
        return empty.contains(number);//O(Log N)
    }

    public void release(int number) {
        //booked.remove(number);
        empty.add(number);//O(Log N)
    }
}

class PhoneDirectory1 {

    PriorityQueue<Integer> heap;
    Set<Integer> booked;
    //int max;

    public PhoneDirectory1(int maxNumbers) {
        heap = new PriorityQueue<>(maxNumbers);
        booked = new HashSet<>();
        //max = maxNumbers;
        for(int i = 0; i < maxNumbers; i++) {
            heap.add(i);
        }
    }

    public int get() {
        if(heap.isEmpty())
            return -1;
        int book = heap.remove();//O(1)
        booked.add(book);//O(1)
        return book;
    }

    public boolean check(int number) {
        return !booked.contains(number);//O(1)
    }

    public void release(int number) {
        if(booked.remove(number))//O(1)
            heap.add(number);//O(Log N)
    }
}


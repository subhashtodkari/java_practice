package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.LinkedHashMap;
import java.util.Map;

/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache_146 class:

LRUCache_146(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.



Example 1:

Input
["LRUCache_146", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache_146 lRUCache = new LRUCache_146(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4


Constraints:

1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.
 */
public class LRUCache_146 {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(5);

        Assertions.assertEquals(-1, cache.get(1));

        cache.put(1, 100);
        Assertions.assertEquals(100, cache.get(1));
        cache.put(2, 200);
        Assertions.assertEquals(200, cache.get(2));
        cache.put(3, 300);
        Assertions.assertEquals(300, cache.get(3));
        cache.put(4, 400);
        Assertions.assertEquals(400, cache.get(4));
        cache.put(5, 500);
        Assertions.assertEquals(500, cache.get(5));

        Assertions.assertEquals(100, cache.get(1));
        Assertions.assertEquals(200, cache.get(2));
        Assertions.assertEquals(300, cache.get(3));
        Assertions.assertEquals(400, cache.get(4));
        Assertions.assertEquals(500, cache.get(5));

        cache.put(6, 600);
        Assertions.assertEquals(-1, cache.get(1));
        Assertions.assertEquals(200, cache.get(2));
        Assertions.assertEquals(300, cache.get(3));
        Assertions.assertEquals(400, cache.get(4));
        Assertions.assertEquals(500, cache.get(5));
        Assertions.assertEquals(600, cache.get(6));

        Assertions.assertEquals(200, cache.get(2));
        cache.put(7, 700);
        Assertions.assertEquals(-1, cache.get(1));
        Assertions.assertEquals(200, cache.get(2));
        Assertions.assertEquals(-1, cache.get(3));
        Assertions.assertEquals(400, cache.get(4));
        Assertions.assertEquals(500, cache.get(5));
        Assertions.assertEquals(600, cache.get(6));
        Assertions.assertEquals(700, cache.get(7));
    }

    static class LRUCache {
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if(cache.containsKey(key)) {
                int val = cache.get(key);
                cache.remove(key);
                cache.put(key, val);
                return val;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if(cache.containsKey(key)) {
                cache.remove(key);
            } else if(cache.size() >= capacity) {
                Map.Entry<Integer, Integer> oldestEntry = cache.entrySet().iterator().next();
                cache.remove(oldestEntry.getKey());
            }
            cache.put(key, value);
        }
    }

}

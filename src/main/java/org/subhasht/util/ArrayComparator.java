package org.subhasht.util;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class ArrayComparator {

    public static void main(String[] args) {
        Assertions.assertTrue(compare(null, null, true));
        Assertions.assertTrue(compare(null, null, false));
        Assertions.assertFalse(compare(new int [] {}, null, true));
        Assertions.assertFalse(compare(new int [] {}, null, false));
        Assertions.assertTrue(compare(new int [] {}, new int [] {}, true));
        Assertions.assertTrue(compare(new int [] {}, new int [] {}, false));

        Assertions.assertTrue(compare(new int [] {1,2,3}, new int [] {3,2,1}, true));
        Assertions.assertFalse(compare(new int [] {1,2,3}, new int [] {3,2,1}, false));

        Assertions.assertTrue(compare(new int [] {1,2,3,1,1}, new int [] {1,3,1,2,1}, true));
        Assertions.assertFalse(compare(new int [] {1,2,3,1,1}, new int [] {1,3,1,2,1}, false));

        Assertions.assertTrue(compare(new int [] {1,2,3,1,1}, new int [] {1,2,3,1,1}, true));
        Assertions.assertTrue(compare(new int [] {1,2,3,1,1}, new int [] {1,2,3,1,1}, false));

        Assertions.assertFalse(compare(null, new int [] {3,2,1}, false));
        Assertions.assertFalse(compare(new int [] {}, new int [] {3,2,1}, false));
        Assertions.assertFalse(compare(new int [] {1}, new int [] {3,2,1}, false));

        Assertions.assertFalse(compare(new int [] {3,2,1}, null, false));
        Assertions.assertFalse(compare(new int [] {3,2,1}, new int [] {},false));
        Assertions.assertFalse(compare(new int [] {3,2,1}, new int [] {1},false));


        Assertions.assertTrue(
                compare(
                        new int [][] {
                                {1,2,3,1,1}
                        },
                        new int [][] {
                                {1,2,3,1,1}
                        },
                        true,
                        true));
        Assertions.assertTrue(
                compare(
                        new int [][] {
                                {1,2,3,1,1}
                        },
                        new int [][] {
                                {1,2,3,1,1}
                        },
                        false,
                        false));
        Assertions.assertTrue(
                compare(
                        new int [][] {
                                {1,2,3,1,1},
                                {-2,2}
                        },
                        new int [][] {
                                {-2,2},
                                {2,1,1,1,3}
                        },
                        true,
                        true));
        Assertions.assertFalse(
                compare(
                        new int [][] {
                                {1,2,3,1,1},
                                {-2,2}
                        },
                        new int [][] {
                                {-2,2},
                                {2,1,1,1,3}
                        },
                        true,
                        false));
    }

    public static boolean compare(int [][] a, int [][] b, boolean ignoreOrder0, boolean ignoreOrder1) {
        if(a == null && b == null) return true;
        if(a == null || b == null) return false;
        if(a.length != b.length) return false;

        if(ignoreOrder0) {
            Set<Integer> available = new HashSet<>();
            for (int i = 0; i < b.length; i++) {
                available.add(i);
            }
            for (int[] j : a) {
                int matched = -1;
                for (int i : available) {
                    if(compare(j, b[i], ignoreOrder1)) {
                        matched = i;
                        break;
                    }
                }
                if(matched == -1)
                    return false;
                available.remove(matched);
            }
        } else {
            for(int i = 0; i < a.length; i++) {
                if(!compare(a[i], b[i], ignoreOrder1))
                    return false;
            }
        }
        return true;
    }

    public static boolean compare(int [] a, int [] b, boolean ignoreOrder) {
        if(a == null && b == null) return true;
        if(a == null || b == null) return false;
        if(a.length != b.length) return false;

        if(ignoreOrder) {
            Map<Integer, Deque<Integer>> map = new HashMap<>();
            for (int i = 0; i < b.length; i++) {
                map.putIfAbsent(b[i], new LinkedList<>());
                map.get(b[i]).add(i);
            }
            for (int j : a) {
                if (!map.containsKey(j) || map.get(j).isEmpty())
                    return false;
                map.get(j).remove();
            }
        } else {
            for(int i = 0; i < a.length; i++) {
                if(a[i] != b[i])
                    return false;
            }
        }
        return true;
    }
}

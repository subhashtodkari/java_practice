package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;
import org.subhasht.util.ArrayComparator;

import java.util.Arrays;

public class BookingConcertTicketsInGroups2_2286 {

    public static void main(String[] args) {
        BookMyShow show = new BookMyShow(5, 10);
        System.out.println(Arrays.toString(show.gather(8, 2))); //0 0
        System.out.println(Arrays.toString(show.gather(8, 2))); //1 0
        System.out.println(Arrays.toString(show.gather(2, 2))); //0 8
        System.out.println(Arrays.toString(show.gather(3, 2))); //2 0
        System.out.println(Arrays.toString(show.gather(4, 2))); //2 3
        System.out.println(Arrays.toString(show.gather(4, 2))); //null
        System.out.println(Arrays.toString(show.gather(11, 4))); //null
        System.out.println(Arrays.toString(show.gather(10, 4))); //3 0
        System.out.println(Arrays.toString(show.gather(1, 4))); //1 8
        System.out.println(Arrays.toString(show.gather(2, 2))); //2 7
        System.out.println(show.scatter(13, 2)); // false
        System.out.println(show.scatter(12, 2)); // false
        System.out.println(show.scatter(12, 4)); // true

        BookMyShow show2 = new BookMyShow(5, 10);
        ArrayComparator.compare(show2.gather(8, 2), new int [] {0, 0}, false);
        ArrayComparator.compare(show2.gather(8, 2), new int [] {1, 0}, false);//1 0
        ArrayComparator.compare(show2.gather(2, 2), new int [] {0, 8}, false);//0 8
        ArrayComparator.compare(show2.gather(3, 2), new int [] {2, 0}, false);//2 0
        ArrayComparator.compare(show2.gather(4, 2), new int [] {2, 3}, false);//2 3
        ArrayComparator.compare(show2.gather(4, 2), new int [] {}, false);//null
        ArrayComparator.compare(show2.gather(11, 4), new int [] {}, false);//null
        ArrayComparator.compare(show2.gather(10, 4), new int [] {3, 0}, false);//3 0
        ArrayComparator.compare(show2.gather(1, 4), new int [] {1, 8}, false);//1 8
        ArrayComparator.compare(show2.gather(2, 2), new int [] {2, 7}, false);//2 7
        Assertions.assertFalse(show2.scatter(13, 2));
        Assertions.assertFalse(show2.scatter(12, 2));
        Assertions.assertTrue(show2.scatter(12, 4));

    }

    static class BookMyShow {

        Row head;

        static class Row {
            int rn;
            int idx;
            int cap;
            Row next, prev;

            public Row(int rn, int cap) { this.rn = rn; this.cap = cap;}
            int available() { return cap - idx; }
            int allocate(int k, boolean all) {
                if(all && available() < k) return -1;
                int possible = Math.min(available(), k);
                idx += possible;
                return idx - possible;
            }
        }

        public BookMyShow(int n, int m) {
            Row prev = null, row;
            for(int i = 0; i < n; i++) {
                row = new Row(i, m);
                if(prev == null) {
                    head = row;
                } else {
                    prev.next = row;
                    row.prev = prev;
                }
                prev = row;
            }
        }

        public int[] gather(int k, int maxRow) {
            //System.out.println("gather - k: " + k + ", maxRow: " + maxRow);
            Row r = head;
            while(r != null && r.rn <= maxRow) {
                int pos = r.allocate(k, true);
                if(pos != -1) {

                    if(r.available() == 0) {//purge node
                        Row prev = r.prev, next = r.next;
                        if(next != null) {
                            next.prev = prev;
                        }
                        if(prev != null) {
                            prev.next = next;
                        }
                        if(r.equals(head)) {
                            head = next;
                        }
                    }

                    return new int [] {r.rn, pos};
                }
                r = r.next;
            }
            return new int [] {};
        }

        public boolean scatter(int k, int maxRow) {
            //System.out.println("scatter - k: " + k + ", maxRow: " + maxRow);
            long a = 0;
            Row r = head;
            while(r != null && r.rn <= maxRow) {
                a += r.available();
                r = r.next;
            }
            //System.out.println("a: " + a);
            if(a < (long) k) return false;

            r = head;
            while(r != null && r.rn <= maxRow && k > 0) {
                int pos = r.allocate(k, false);
                //System.out.println("allocated: row[" + r.rn + "] from: " + pos + ", new idx: " + r.idx + ", avaialble: " + r.available() + ", new k: " + (k - (r.cap - pos)));
                if(pos != -1) {
                    k = k - (r.cap - pos);
                    if(r.available() == 0) {//purge node
                        Row prev = r.prev, next = r.next;
                        if(next != null) {
                            next.prev = prev;
                        }
                        if(prev != null) {
                            prev.next = next;
                        }
                        if(r.equals(head)) {
                            head = next;
                        }
                    }
                }
                r = r.next;
            }
            return true;
        }
    }

}

package org.subhasht.practice.leetcode.problems;

import java.util.Arrays;

public class BookingConcertTicketsInGroups_2286 {

    public static void main(String[] args) {
        BookMyShow show = new BookMyShow(5, 10);
        System.out.println(show.theatre);
        System.out.println();
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
        System.out.println(show.scatter(13, 2)); // true

    }

    static class BookMyShow {

        Range theatre;

        public BookMyShow(int n, int m) {
            theatre = new Range(0, n-1, m);
        }

        public int[] gather(int k, int maxRow) {
            System.out.println("Booking GATHER request: seats: " + k +", max row num: " + maxRow);
            int [] ans = theatre.bookInTheSameRow(k, 0, maxRow);
            System.out.println();
            System.out.println(theatre);
            return ans;
        }

        public boolean scatter(int k, int maxRow) {
            System.out.println("Booking SCATTER request: seats: " + k +", max row num: " + maxRow);
            boolean ans = theatre.bookAnywhereRow(k, 0, maxRow);
            System.out.println();
            System.out.println(theatre);
            return ans;
        }
    }

    static class Range {
        int from, to;
        long capacity, occupied;
        Range left, right;

        public Range(int from, int to, long capacity) {
            this.from = from;
            this.to = to;
            if(from == to) {
                this.capacity = capacity;
            } else {
                int mid = (from + to) / 2;
                left = new Range(from, mid, capacity);
                right = new Range(mid+1, to, capacity);
                this.capacity = left.capacity + right.capacity;
            }
        }

        int[] bookInTheSameRow(int seats, int minRowNum, int maxRowNum) {
            if(minRowNum < from || maxRowNum > to) throw new RuntimeException("OutOfRange request: Range available["+from+", "+to+"], Range requested: ["+minRowNum+", "+maxRowNum+"]");
            if(seats > capacity - occupied) {
                System.out.println("Range ["+from+", "+to+"] cannot book " + seats + " seats as availability is only " + (capacity - occupied));
                return null;
            }
            if(from == to) {
                if(capacity - occupied >= seats) {
                    occupied += seats;
                    return new int[]{from, (int) occupied - seats};
                }
            } else {
                if (minRowNum <= left.to) {
                    int [] ans = left.bookInTheSameRow(seats, Math.max(minRowNum, left.from), Math.min(left.to, maxRowNum));
                    if(ans == null) {
                        if(maxRowNum >= right.from)
                            ans = right.bookInTheSameRow(seats, Math.max(minRowNum, right.from), Math.min(right.to, maxRowNum));
                    }
                    if(ans != null) {
                        this.occupied += seats;
                        return ans;
                    }
                }
            }
            return null;
        }

        boolean bookAnywhereRow(int seats, int minRowNum, int maxRowNum) {
            if(minRowNum < from || maxRowNum > to) throw new RuntimeException("OutOfRange request: Range available["+from+", "+to+"], Range requested: ["+minRowNum+", "+maxRowNum+"]");
            if(seats > capacity - occupied) {
                System.out.println("Range ["+from+", "+to+"] cannot book " + seats + " seats as availability is only " + (capacity - occupied));
                return false;
            }
            if (from != to && minRowNum <= left.to) {
                int possible = (int) Math.min(seats, left.capacity - left.occupied);
                if(possible > 0)
                    left.bookAnywhereRow(possible, Math.max(minRowNum, left.from), Math.min(left.to, maxRowNum));
                if(seats > possible) {
                    right.bookAnywhereRow(seats - possible, Math.max(minRowNum, right.from), Math.min(right.to, maxRowNum));
                }
            }
            occupied += seats;
            return true;
        }

        @Override
        public String toString() {
            StringBuilder row = new StringBuilder();
            if(from == to) {
                row.append("\n");
                row.append(from);
                row.append(": ");
                for(int i = 0; i < capacity; i++) {
                    row.append(i < occupied ? "X ": "- ");
                }
            } else {
                row.append(left);
                row.append(right);
            }
            return row.toString();
        }
    }

}

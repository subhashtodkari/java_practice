package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

public class NumberOfDaysBetweenTwoDates_1360 {

    public static void main(String[] args) {
        NumberOfDaysBetweenTwoDates_1360 solution = new NumberOfDaysBetweenTwoDates_1360();
        Assertions.assertEquals(1, solution.daysBetweenDates("2100-03-01", "2100-02-28"));
        Assertions.assertEquals(14331, solution.daysBetweenDates("1971-06-29", "2010-09-23"));
    }

    public int daysBetweenDates(String date1, String date2) {
        int c = date1.compareTo(date2);
        if ( c == 0 ) return 0;
        Date d2 = c > 0 ? new Date(date1) : new Date(date2);
        Date d1 = c < 0 ? new Date(date1) : new Date(date2);
        return dateDiff(d1, d2);
    }

    static class Date {
        int year, month, day;
        String str;
        public Date(String d) {
            str = d;
            year = Integer.parseInt(d.substring(0, 4));
            month = Integer.parseInt(d.substring(5, 7));
            day = Integer.parseInt(d.substring(8, 10));
        }
    }

    static int dateDiff(Date earlier, Date later) {
        int count = 0;
        if(later.year == earlier.year) {
            if(later.month == earlier.month)
                return later.day - earlier.day;
            else {
                count += later.day;
                while(--later.month > earlier.month) {
                    count += getDaysInAMonth(later.month, later.year);
                }
                count += getDaysInAMonth(earlier.month, earlier.year) - earlier.day;
            }
        } else if (later.year > earlier.year) {
            count += later.day;
            while(--later.month > 0) {
                count += getDaysInAMonth(later.month, later.year);
            }
            while(--later.year > earlier.year) {
                count += getDaysInAnYear(later.year);
            }
            later.month = 12;
            later.day = 31;
            return count + dateDiff(earlier, later);
        }
        return count;
    }

    static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || (year % 400 == 0));
    }

    static int getDaysInAnYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    static int getDaysInAMonth(int month, int year) {
        switch(month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
        }
        throw new RuntimeException("Invalid month: " + month);
    }
}

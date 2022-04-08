package org.subhasht.practice.misc;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class LiveStreamCacheSimulator {

    public static void main(String[] args) {

    TimeBoundCache<Integer> cache = new TimeBoundCache<>(10, 0);

    cache.addValue(12, 120);
    Assertions.assertEquals(0, cache.getUpToDateValue(5));
    Assertions.assertEquals(120, cache.getUpToDateValue(12));
    Assertions.assertNull(cache.getUpToDateValue(2)); //time lesser than cache hold restriction
    Assertions.assertNull(cache.getUpToDateValue(15)); //time greater than last entry

    cache.addValue(15, 150);
    Assertions.assertEquals(150, cache.getUpToDateValue(15));

    cache.addValue(16, 160);
    cache.addValue(17, 170);
    cache.addValue(25, 250);

    Assertions.assertNull(cache.getUpToDateValue(15)); //time lesser than cache hold restriction
    Assertions.assertEquals(160, cache.getUpToDateValue(16));
    Assertions.assertEquals(170, cache.getUpToDateValue(21));


        cache.addValue(26, 260);
        cache.addValue(27, 270);
        cache.addValue(28, 280);
        cache.addValue(29, 290);
        cache.addValue(30, 300);
        cache.addValue(45, 450);
        Assertions.assertEquals(1, cache.map.size());
        cache.addValue(46, 460);
        cache.addValue(47, 470);
        cache.addValue(48, 480);
        cache.addValue(49, 490);
        cache.addValue(50, 500);
        cache.addValue(52, 520);
        cache.addValue(52, 5200);
        cache.addValue(54, 540);
        cache.addValue(56, 560);
        cache.addValue(58, 580);
        Assertions.assertEquals(6, cache.map.size());
        Assertions.assertEquals(500, cache.getUpToDateValue(51));
        cache.addValue(60, 600);
        Assertions.assertEquals(5, cache.map.size());
        Assertions.assertEquals(0, cache.getUpToDateValue(51));
        Assertions.assertEquals(5200, cache.getUpToDateValue(52));
        Assertions.assertEquals(5200, cache.getUpToDateValue(53));//should return value for 52
        Assertions.assertEquals(540, cache.getUpToDateValue(54));
        Assertions.assertEquals(540, cache.getUpToDateValue(55));//should return value for 54
        Assertions.assertEquals(560, cache.getUpToDateValue(56));
        Assertions.assertEquals(580, cache.getUpToDateValue(58));
        Assertions.assertEquals(600, cache.getUpToDateValue(60));
    }
}

class TimeBoundCache<V> implements LiveStream<V> {

    int interval;
    int [] array;
    int start, end;
    V defaultVal;

    Map<Integer,V> map;

    public TimeBoundCache(int interval, V defaultVal) {
        this.interval = interval;
        array = new int[interval * 2];
        start = -1;
        end = -1;
        this.defaultVal = defaultVal;
        map = new HashMap<>(interval);
    }

    @Override
    public void addValue(int time, V v) {
        map.put(time, v);//O(1)
        if(end != -1 && array[end] == time) {
            return;
        }
        if(end + 1 == array.length) {
            //shift start - end to 0
            if (end - start >= 0) {
                System.arraycopy(array, start, array, 0, end - start + 1);//O(active entries)
            }
            end = end - start;
            start = 0;
        }
        if(start == -1) {
            start = 0;
        }
        end++;
        array[end] = time;

        while(array[end] - array[start] >= interval) {//O(outdated entries)
            map.remove(array[start]);//O(1)
            start++;
        }
    }

    @Override
    public V getUpToDateValue(int time) {
        if(time <= (array[end] - interval) || time > array[end]) {
            return null;
        }
        if(map.containsKey(time)) {
            return map.get(time); //O(1)
        }
        if(time < array[start]) {
            return defaultVal;
        }
        int left = start, right = end, mid, pos = -1;
        //binary search O(log N)
        while(left <= right) {
            if(right - left < 2) {
                if(time < array[left]) {
                    pos = left - 1;
                } else if(time < array[right]) {
                    pos = right - 1;
                } else {
                    pos = right;
                }
                break;
            }
            mid = (left + right) / 2;
            if(time < array[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return map.get(array[pos]);
    }
}

interface LiveStream<V> {
    void addValue(int time, V v);
    V getUpToDateValue(int time);
}


package org.subhasht.practice.misc;

import org.junit.jupiter.api.Assertions;

public class LiveStreamCacheSimulator {

    public static void main(String[] args) {
        TimeBoundCache<Integer> cache = new TimeBoundCache<>(10);

        cache.addValue(12, 120);
        Assertions.assertEquals(0, cache.getUpToDateValue(5));
        Assertions.assertEquals(120, cache.getUpToDateValue(12));
        Assertions.assertNull(cache.getUpToDateValue(2));
        Assertions.assertEquals(120, cache.getUpToDateValue(15));

        cache.addValue(15, 150);
        Assertions.assertEquals(150, cache.getUpToDateValue(15));

        cache.addValue(16, 160);
        cache.addValue(17, 170);
        cache.addValue(25, 250);

        Assertions.assertNull(cache.getUpToDateValue(15));
        Assertions.assertEquals(160, cache.getUpToDateValue(16));
        Assertions.assertEquals(170, cache.getUpToDateValue(21));
    }
}

class TimeBoundCache<V> implements LiveStream<V> {

    int interval;

    public TimeBoundCache(int interval) {
        this.interval = interval;
    }

    @Override
    public void addValue(long time, V v) {

    }

    @Override
    public V getUpToDateValue(long time) {
        return null;
    }
}

interface LiveStream<V> {
    void addValue(long time, V v);
    V getUpToDateValue(long time);
}

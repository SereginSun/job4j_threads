package ru.job4j.concurrent.cache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAddOneBaseThenTrue() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        assertThat(cache.add(base), is(true));
    }

    @Test
    public void whenUpdateBaseThenExist() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        cache.update(new Base(1, 0));
        assertThat(cache.get(1).getVersion(), is(1));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateOneBaseNotEqualVersionThenException() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        cache.update(new Base(1, 1));
    }

    @Test
    public void whenDeleteOneBaseThenNull() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        cache.delete(base);
        assertNull(cache.get(1));
    }
}
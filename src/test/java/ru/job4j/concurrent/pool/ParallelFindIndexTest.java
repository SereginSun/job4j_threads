package ru.job4j.concurrent.pool;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParallelFindIndexTest {

    @Test
    public void whenFindIndexIn10Elements() {
        Integer[] array = {55, 5, 32, 15, 8, 48, 21, 1, 2, 10};
        assertThat(ParallelFindIndex.findIndex(array, 8), is(4));
    }

    @Test
    public void whenFindIndexIn12Elements() {
        Character[] array = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
        assertThat(ParallelFindIndex.findIndex(array, 'd'), is(3));
    }

    @Test
    public void whenIndexDontFindIn12Elements() {
        Character[] array = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
        assertThat(ParallelFindIndex.findIndex(array, 'm'), is(-1));
    }
}
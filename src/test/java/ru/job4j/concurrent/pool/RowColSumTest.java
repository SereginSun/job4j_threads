package ru.job4j.concurrent.pool;

import org.junit.Test;
import ru.job4j.concurrent.pool.RowColSum.Sums;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static ru.job4j.concurrent.pool.RowColSum.asyncSum;
import static ru.job4j.concurrent.pool.RowColSum.sum;

public class RowColSumTest {

    @Test
    public void whenSum() {
        int[][] matrix = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        };
        Sums first = new Sums();
        first.setRowSum(3);
        first.setColSum(9);
        Sums second = new Sums();
        second.setRowSum(12);
        second.setColSum(12);
        Sums third = new Sums();
        third.setRowSum(21);
        third.setColSum(15);
        Sums[] expected = {
                first,
                second,
                third
        };
        assertThat(sum(matrix), is(expected));
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums first = new Sums();
        first.setRowSum(6);
        first.setColSum(12);
        Sums second = new Sums();
        second.setRowSum(15);
        second.setColSum(15);
        Sums third = new Sums();
        third.setRowSum(24);
        third.setColSum(18);
        Sums[] expected = {
                first,
                second,
                third
        };
        assertThat(asyncSum(matrix), is(expected));
    }
}
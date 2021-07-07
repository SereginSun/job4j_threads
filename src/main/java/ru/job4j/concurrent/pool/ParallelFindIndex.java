package ru.job4j.concurrent.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;
    private final T element;

    public ParallelFindIndex(T[] array, int from, int to, T element) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.element = element;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= 10) {
            for (int i = 0; i < to; i++) {
                if (array[i].equals(element)) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (from + to) / 2;
        ParallelFindIndex<T> leftFind = new ParallelFindIndex<>(array, from, mid, element);
        ParallelFindIndex<T> rightFind = new ParallelFindIndex<>(array, mid + 1, to, element);
        leftFind.fork();
        rightFind.fork();
        Integer left = leftFind.join();
        Integer right = rightFind.join();
        return left != -1 ? left : right;
    }

    public static <T> Integer findIndex(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelFindIndex<>(array, 0, array.length - 1, element));
    }
}

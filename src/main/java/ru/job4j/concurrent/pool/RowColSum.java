package ru.job4j.concurrent.pool;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {

    public static class Sums {
        private int rowSum;
        private int colSum;

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum
                    && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        int sumRow;
        int sumCol;

        for (int i = 0; i < matrix.length; i++) {
            sumRow = 0;
            sumCol = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sumRow += matrix[i][j];
                sumCol += matrix[j][i];
            }
            Sums sum = new Sums();
            sum.setRowSum(sumRow);
            sum.setColSum(sumCol);
            result[i] = sum;
        }
        return result;
    }

    private static CompletableFuture<Sums> getSum(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(
                () -> {
                    Sums sum = new Sums();
                    int sumRow = 0;
                    int sumCol = 0;
                    for (int i = 0; i < matrix.length; i++) {
                        sumRow += matrix[index][i];
                        sumCol += matrix[i][index];
                    }
                    sum.setRowSum(sumRow);
                    sum.setColSum(sumCol);
                    return sum;
                }
        );
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] result = new Sums[matrix.length];
        for (int index = 0; index < matrix.length; index++) {
            result[index] = getSum(matrix, index).get();
        }
        return result;
    }
}

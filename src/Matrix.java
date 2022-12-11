import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Matrix {
    private int height = 0;
    private int width = 0;
    private int[][] matrix;

    public int getHeight() {
       return height;
    }

    public int getWidth() {
        return width;
    }

    public Matrix() {
    }

    public Matrix(int h, int w) {
        height = h; width = w;
        matrix = new int[h][w];
    }

    public Matrix(@NotNull Matrix m) {
        height = m.height; width = m.width;
        matrix = new int[height][width];
        for(int i = 0; i < height; i++)
            matrix[i] = m.matrix[i].clone();
    }

    public void setCell(int x, int y, int value) throws Exception {
        matrix[x][y] = value;
    }

    public void setRow(int row_number, int[] row, int size) throws Exception {
        if (size >= 0) System.arraycopy(row, 0, matrix[row_number], 0, Math.min(size, width));
    }

    public void setColumn(int col_number, int[] column, int size) throws Exception {
        for(int i = 0; i < Math.min(size, height); i++)
            matrix[i][col_number] = column[i];
    }

    public int getCell(int x, int y) {
        return matrix[x][y];
    }

    public int[] getRow(int row_number) {
        return matrix[row_number];
    }

    public int[] getColumn(int col_number) {
        int[] col = new int[height];
        for(int i = 0; i < height; i++)
            col[i] = matrix[i][col_number];
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix1 = (Matrix) o;

        if (height != matrix1.height) return false;
        if (width != matrix1.width) return false;
        return Arrays.deepEquals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        Integer result = height;
        result = 31 * result + width;
        result = 31 * result + Arrays.deepHashCode(matrix);
        return result;
    }


    public static Matrix unitMatrix(int s) {
        Matrix unit_matrix = new Matrix(s,s);
        for (int i = 0; i < s; i++) {
                    unit_matrix.matrix[i][i] = 1;
        }
        return unit_matrix;
    }

    public static Matrix sum_matrix(Matrix matrix_1, Matrix matrix_2) {
        if (matrix_1.width == matrix_2.width && matrix_1.height == matrix_2.height) {
            Matrix summary_matrix = new Matrix(matrix_1.height, matrix_1.width);
            for (int i = 0; i < matrix_1.height; i++) {
                for (int j = 0; j < matrix_1.width; j++) {
                    summary_matrix.matrix[i][j] = matrix_1.matrix[i][j] + matrix_2.matrix[i][j];
                }
            }
            return summary_matrix;
        }
        else {
            throw new ArithmeticException("різні розміри матриць :(");
        }

    }

    public static Matrix multiplyByNum(int number, Matrix matrix) {
        Matrix new_matrix = new Matrix(matrix.height, matrix.width);
        for (int i = 0; i < matrix.height; i++) {
            for(int j = 0; j < matrix.width; j++) {
                new_matrix.matrix[i][j] = matrix.matrix[i][j] * number;
            }
        }
        return new_matrix;
    }

}

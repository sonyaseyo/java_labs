import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Scanner;

public class MatrixUI extends Matrix {

    public MatrixUI(int h, int w) {
        super(h, w);
    }

    public MatrixUI() {
        super();
    }

    public MatrixUI(@NotNull Matrix m) {
        super(m);
    }

    private static int getWidth(Integer number) {
        return number.toString().length();
    }

     private static String align(Integer number, int widest_number) {
         int need_space = widest_number - getWidth(number);
         String aligned = number.toString();
         for (int i = 0; i < need_space; i++) {
                aligned += " ";
         }
         return aligned;
     }


        public static MatrixUI getMatrix() throws Exception {
            Scanner myScanner = new Scanner(System.in);
            int width; int height;
            System.out.println("Enter width: ");
            width = myScanner.nextInt();
            System.out.println("Enter height: ");
            height = myScanner.nextInt();
            MatrixUI inputMatrix = new MatrixUI(height, width);
            System.out.println("Now enter rows of your matrix.");
            Scanner myRowScanner = new Scanner(System.in);
            for (int r = 0; r < height; r ++) {
                String input_row = myRowScanner.nextLine();
                String[] matrix_row = input_row.split(" ");
                int size = matrix_row.length;
                int[] row = new int [size];
                for(int i=0; i<size; i++) {
                    row[i] = Integer.parseInt(matrix_row[i]);
                }
                inputMatrix.setRow(r, row, size);
            }
            return inputMatrix;
        }

    public static void printMatrix(Matrix matrix) {
        int widest_number = 0;
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                if (getWidth(matrix.getCell(i, j)) <= widest_number) {
                    continue;
                }
                widest_number = getWidth(matrix.getCell(i, j));
            }
        }
        for (int i = 0; i < matrix.getHeight(); i++) {
            String currentRow = "";
            for (int j = 0; j < matrix.getWidth(); j++) {
                currentRow += align(matrix.getCell(i, j), widest_number);
                if (j != matrix.getWidth() - 1 ) currentRow += " ";
            }
            System.out.println(currentRow);
        }
    }

}

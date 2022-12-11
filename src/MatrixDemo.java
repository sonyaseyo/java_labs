import java.util.Scanner;

public class MatrixDemo {

        public static void multiplyByConst() throws Exception {
        Matrix input = MatrixUI.getMatrix();
        System.out.println("Enter your const: ");
        Scanner myScanner = new Scanner(System.in);
        int c = myScanner.nextInt();
        Matrix output = Matrix.multiplyByNum(c, input);
        MatrixUI.printMatrix(output);
    }

    public static void matrixSummary() throws Exception {
        System.out.println("Enter first matrix.");
        Matrix matrix_1 = MatrixUI.getMatrix();
        System.out.println("Enter second matrix.");
        Matrix matrix_2 = MatrixUI.getMatrix();
        Matrix summary = Matrix.sum_matrix(matrix_1, matrix_2);
        System.out.println("Summary of given matrices would be: ");
        MatrixUI.printMatrix(summary);
    }

    public static void areEqual() throws Exception {
        System.out.println("Enter first matrix.");
        Matrix matrix_1 = MatrixUI.getMatrix();
        System.out.println("Enter second matrix.");
        Matrix matrix_2 = MatrixUI.getMatrix();
        if (matrix_1.equals(matrix_2)) {
            System.out.println("They're equal.");
        }
        else {
            System.out.println("They're not equal.");
        }
    }

    public static void createUnitMatrix() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter dimension for matrix: ");
        int dim = myScanner.nextInt();
        Matrix unitMatrix = Matrix.unitMatrix(dim);
        System.out.println("Unit matrix with this dim would be: ");
        MatrixUI.printMatrix(unitMatrix);
    }

    public static void makeHashcode() throws Exception {
        Matrix input = MatrixUI.getMatrix();
        Integer hc = input.hashCode();
        System.out.println("HashCode for this matrix would be: " + hc.toString());
    }

    public static void isImmutable() throws Exception {
        Matrix input = MatrixUI.getMatrix();
        immutableMatrix immutableM = new immutableMatrix(input);
        immutableM.setCell(0, 0, -1);
    }

}

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class MatrixMenu {
    ArrayList<MatrixMenuItem> menu;
    public MatrixMenu() {
        menu = new ArrayList<MatrixMenuItem>();
        menu.add(new MatrixMenuItem("1", "We will multiply matrix by given const.", "multiplyByConst"));
        menu.add(new MatrixMenuItem("2", "We will sum up two matrices.", "matrixSummary"));
        menu.add(new MatrixMenuItem("3", "We will create unit matrix.", "createUnitMatrix"));
        menu.add(new MatrixMenuItem("4", "We will check if 2 matrices are equal.", "areEqual"));
    }

    public void go() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (;;) {
            for(MatrixMenuItem item : menu) {
                System.out.println(item.key + " - " + item.description);
            }
            System.out.println("enter 0 to exit");
            Scanner myScanner = new Scanner(System.in);
            String decision = myScanner.nextLine();
            if (decision.equals("0")) break;
            for(MatrixMenuItem item : menu) {
                if (item.key.equals(decision)) {
                    System.out.println(item.description);
                    MatrixDemo demo = new MatrixDemo();
                    Method m = demo.getClass().getMethod(item.method);
                    m.invoke(demo);
                    break;
                }
            }
        }
    }
}

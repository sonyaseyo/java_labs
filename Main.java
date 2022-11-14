import java.lang.reflect.InvocationTargetException;


public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Matrix. Chumak Sofiia, KM-12.");
        MatrixMenu menu = new MatrixMenu();
        menu.go();
    }
}
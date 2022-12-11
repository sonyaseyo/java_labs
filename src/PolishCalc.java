import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PolishCalc {

    private Stack<Atoms> stk;

    private Map<String, Matrix> storage;

    public PolishCalc() {
        storage = new HashMap<String, Matrix>();
    }

    private static Atoms nextAtom(String s, int i) {
        if (i >= s.length() - 1) return new Atoms(Atoms.TYPES.END, "0");
        Atoms res = new Atoms(s.toCharArray()[i + 1]);
        if (res.type != Atoms.TYPES.VARIABLE) {
            res.type = Atoms.TYPES.WRONG;
        }
        return res;
    }

    private static void rewriteMatrix(Matrix a, Matrix b) throws Exception {
        for (int i = 0; i < Math.min(a.getHeight(), b.getHeight()); i++) {
            b.setRow(i, a.getRow(i), a.getWidth());
        }
    }

    private void saveToStorage(Matrix m, String name) throws Exception {
        if (storage.get(name) != null) {
            System.out.println("we will rewrite matrix " + name + " with type: " + storage.get(name).getClass().getName() + " by matrix with type: " + m.getClass().getName());
            rewriteMatrix(m, storage.get(name));
        }
        else {
            System.out.println("we will create new matrix " + name + "; type: " + m.getClass().getName());
            storage.put(name, m);}
    }

    private boolean summary(String s, int position) throws Exception {
        Atoms a1 = stk.pop();
        Atoms a2 = stk.pop();
        if (a1.type != a2.type || a1.type != Atoms.TYPES.VARIABLE) {
            System.out.println("Wrong type.");
            return false;}
        Matrix m1 = storage.get(a1.value);
        Matrix m2 = storage.get(a2.value);
        if (m1 == null || m2 == null) {return false;}
        Matrix res = Matrix.sum_matrix(m1, m2);
        System.out.println("result: ");
        MatrixUI.printMatrix(res);
        Atoms destination = nextAtom(s, position);
        if (destination.type == Atoms.TYPES.VARIABLE) {
            saveToStorage(res, destination.value);}
        return true;
    }

    private boolean input(String s, int position) throws Exception {
        Matrix m = new Matrix(MatrixUI.getMatrix());
        Atoms destination = nextAtom(s, position);
        if (destination.type == Atoms.TYPES.VARIABLE) {
            saveToStorage(m, destination.value);}
        else {return false;}
        return true;
    }

    private boolean multiply(String s, int position) throws Exception {
        Atoms a_const = stk.pop();
        Atoms c_matrix = stk.pop();
        if (c_matrix.type != Atoms.TYPES.VARIABLE || a_const.type != Atoms.TYPES.CONST) {
            System.out.println("Wrong input type.");
            return false;}
        Matrix m = storage.get(c_matrix.value);
        Integer num = Integer.valueOf(a_const.value);
        if (m == null) {
            System.out.println("empty matrix.");
            return false;}
        Matrix res = Matrix.multiplyByNum(num, m);
        System.out.println("result:");
        MatrixUI.printMatrix(res);
        Atoms destination = nextAtom(s, position);
        if (destination.type == Atoms.TYPES.VARIABLE) {
            saveToStorage(res, destination.value);}
        return true;
    }

    private boolean hashcode(String s, int position) {
        Atoms a = stk.pop();
        if (a.type != Atoms.TYPES.VARIABLE) return false;
        Matrix m = storage.get(a.value);
        if (m == null) return false;
        System.out.println("hashcode: " + m.hashCode());
        return true;
    }

    private boolean unitM(String s, int position) throws Exception {
        Atoms dim = stk.pop();
        if (dim.type != Atoms.TYPES.CONST) return false;
        Matrix res = Matrix.unitMatrix(Integer.parseInt(dim.value));
        System.out.println("result:");
        MatrixUI.printMatrix(res);
        Atoms destination = nextAtom(s, position);
        if (destination.type == Atoms.TYPES.VARIABLE) {
            saveToStorage(res, destination.value);}
        return true;
    }

    private boolean areEqual(String s, int position) {
        Atoms a1 = stk.pop();
        Atoms a2 = stk.pop();
        if (a1.type != a2.type || a1.type != Atoms.TYPES.VARIABLE) {return false;}
        Matrix m1 = storage.get(a1.value);
        Matrix m2 = storage.get(a2.value);
        if (m1 == null || m2 == null) {return false;}
        if (!m1.equals(m2)) {
            System.out.println("matrices are not equal.");
            return true;}
        System.out.println("matrices are equal.");
        return true;
    }

    private boolean makeImmutable(String s, int position) throws Exception {
        Atoms a = stk.pop();
        if (a.type != Atoms.TYPES.VARIABLE) {return false;}
        immutableMatrix m = new immutableMatrix(storage.get(a.value));
        if (m == null) {return false;}
        System.out.println("result:");
        MatrixUI.printMatrix(m);
        Atoms destination = nextAtom(s, position);
        if (destination.type == Atoms.TYPES.VARIABLE) {
            saveToStorage(m, destination.value);
        }
        return true;
    }


    public boolean calculate(String expression) throws Exception {
        stk = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.toCharArray()[i];
            Atoms a = new Atoms(c);
            if (a.type == Atoms.TYPES.VARIABLE) {stk.push(a);}
            else if (a.type == Atoms.TYPES.CONST) {stk.push(a);}
            else if (a.type == Atoms.TYPES.OPERATION) {
                if (a.value.equals("+")) {
                    if (stk.size() < 2) {
                        System.out.println("not enough elements in stack.");
                        return false;}
                    if (!summary(expression, i)) return false;
                }
                if (a.value.equals("?")) {
                    if (!input(expression, i)) return false;
                }
                if (a.value.equals("*")) {
                    if (stk.size() < 2) {
                        System.out.println("not enough elements in stack.");
                        return false;}
                    if (!multiply(expression, i)) return false;
                }
                if (a.value.equals("#")) {
                    if (stk.size() < 1) {
                        System.out.println("not enough elements in stack.");
                        return false;}
                    if (!hashcode(expression, i)) return false;
                }
                if (a.value.equals("=")) {
                    if (stk.size() < 2) {
                        System.out.println("not enough elements in stack.");
                        return false;}
                    if (!areEqual(expression, i)) return false;
                }
                if (a.value.equals("!")) {
                    if (!unitM(expression, i)) return false;
                }
                if (a.value.equals("@")) {
                    if (!makeImmutable(expression, i)) return false;
                }
            }
        }
        return true;
    }
}

public class Atoms {
    public enum TYPES {
        VARIABLE, CONST, OPERATION, WRONG, END
    }

    public TYPES type;

    public String value;

    public Atoms(TYPES t, String v) {
        type = t; value = v;
    }

    public Atoms(char c) {
        if ("?!#*=+@".indexOf(c) != -1) {type = TYPES.OPERATION;}
        else if ("QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm".indexOf(c) != -1) {type = TYPES.VARIABLE;}
        else if ("1234567890".indexOf(c) != -1) {type = TYPES.CONST;}
        else type = TYPES.WRONG;
        value = String.valueOf(c);
    }
}

import java.util.ArrayList;
import java.util.List;


class ArithmeticException_ext extends ArithmeticException {
        List<Integer> negatives;

    public ArithmeticException_ext(String message) {
        super(message);
    }

    public void set_list(List<Integer> negative_numbers) {
            negatives = negative_numbers;
        }
        public List<Integer> get_list() {
          return negatives;
        }
    }

public class  StringCalculator {

    private String star_esc(String input) {
        StringBuilder out = new StringBuilder();
        for (String s : input.split("")) {
            if (s.equals("*")) {
                out.append("\\");
            }
            out.append(s);
        }
        return out.toString();
    }

    private String get_delimiter(String left_part) {
        left_part = left_part.substring(2);
        List<String> delimiters = new ArrayList<>();
        int pointer = 0;
        int pointer_for_close;
        String one_delim;
        while (pointer >= 0 && pointer < left_part.length()) {
            if (left_part.charAt(pointer) == '[') {
                pointer_for_close = left_part.indexOf("]");
                if (pointer_for_close == -1) {break;}
                one_delim = left_part.substring((pointer + 1), pointer_for_close);
                left_part = left_part.substring(pointer_for_close + 1);
                pointer = 0;
            }
            else {
                one_delim = String.valueOf(left_part.charAt(pointer));
                pointer++;
            }
            one_delim = star_esc(one_delim);
            one_delim = "(" + one_delim + ")";
            delimiters.add(one_delim);
        }
        return String.join("|", delimiters);
    }

    public int add(String numbers) {
        String delimiters = "[,\n]";
        if ((numbers.length() > 2) && (numbers.startsWith("//"))) {
                String[] split_data = numbers.split("\n", 2);
                numbers = split_data[1];
                delimiters = get_delimiter(split_data[0]);
        }
        String[] split_numbers = numbers.split(delimiters);
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String n : split_numbers) {
            if (n.isEmpty()) {continue;}
            int num = Integer.parseInt(n);
            if (num > 1000) {continue;}

            if (num < 0) {
                negatives.add(num);
                }
            else {
                sum += num;
                }
        }
        if (negatives.size() > 0) {
            ArithmeticException_ext exception = new ArithmeticException_ext("Negative numbers are not allowed");
            exception.set_list(negatives);
            throw exception;
        }
        return sum;
    }
}





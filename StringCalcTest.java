import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StringCalcTest {
    @Test
        void empty_string() {
            assertEquals(new StringCalculator().add(""), 0);
    }
    @Test
        void one_number() {
            assertEquals(new StringCalculator().add("100"), 100);
    }
    @Test
        void two_numbers() {
            assertEquals(new StringCalculator().add("10,1"), 11);
    }
    @Test
        void three_numbers() {
            assertEquals(new StringCalculator().add("10\n1,4"), 15);
    }
    @Test
        void custom_delimiter() {
            assertEquals(new StringCalculator().add("//;\n10;1"), 11);
    }
    @Test
    void negative_numbers() {
        StringCalculator calc = new StringCalculator();
        ArithmeticException_ext exception =
                assertThrows(ArithmeticException_ext.class, () -> calc.add("-10,2,3"));
        assertEquals("Negative numbers are not allowed", exception.getMessage());
        List<Integer> negatives = new ArrayList<>();
        negatives.add(-10);
        assertEquals(negatives, exception.get_list());
    }
    @Test
    void numbers_less_than_1000() {
        assertEquals(new StringCalculator().add("100,999,1001,1"), 1100);
    }
    @Test
    void custom_any_length_delimiter() {
        assertEquals(new StringCalculator().add("//[***]\n1***2***3"), 6);
    }
    @Test
    void custom_any_count_delimiter() {
        assertEquals(new StringCalculator().add("//[*][%]\n1*2%3"), 6);
    }
    @Test
    void custom_any_length_any_count_delimiter() {
        assertEquals(new StringCalculator().add("//[***][**][33]\n1***2**2331"), 6);
    }
    @Test
    void slash_meta_symb_delimiter() {
        assertEquals(new StringCalculator().add("//[\\]\n1\\2\\2\\1"), 6);
    }
    @Test
    void circumflex_meta_symb_delimiter() {
        assertEquals(new StringCalculator().add("//[^]\n1^2^2^1"), 6);
    }
    @Test
    void dollar_meta_symb_delimiter() {
        assertEquals(new StringCalculator().add("//[$]\n1$2$2$1"), 6);
    }
    @Test
    void dot_meta_symb_delimiter() {
        assertEquals(new StringCalculator().add("//[.]\n1.2.2.1"), 6);
    }
    @Test
    void plus_meta_symb_delimiter() {
        assertEquals(new StringCalculator().add("//[+]\n1+2+2+1"), 6);
    }
    @Test
    void question_mark_meta_symb_delimiter() {
        assertEquals(new StringCalculator().add("//[?]\n1?2?2?1"), 6);
    }
    @Test
    void braces_meta_symb_delimiter() {
        assertEquals(new StringCalculator().add("//[(][)][|][{][}]\n1)2(2|1{2}2"), 10);
    }
}

import com.sun.tools.javac.tree.JCTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.expectThrows;

/**
 * Created by steve on 25/01/2017.
 */
public class StringCalculatorTests {

    @Test
    void testOneDigit()
    {
        assertEquals(0, StringCalculator.Add(""));
        assertEquals(0, StringCalculator.Add("0"));
        assertEquals(1, StringCalculator.Add("1"));
        assertEquals(2, StringCalculator.Add("2"));
    }

    @Test
    void testTwoDigits()
    {
        assertEquals(0, StringCalculator.Add("0,0"));
        assertEquals(1, StringCalculator.Add("0,1"));
        assertEquals(2, StringCalculator.Add("0,2"));
        assertEquals(2, StringCalculator.Add("1,1"));
        assertEquals(3, StringCalculator.Add("1,2"));
        assertEquals(3, StringCalculator.Add("2,1"));
        assertEquals(4, StringCalculator.Add("2,2"));
        assertEquals(5, StringCalculator.Add("3,2"));
    }

    @Test
    void testThreeDigits()
    {
        assertEquals(0, StringCalculator.Add("0,0,0"));
        assertEquals(1, StringCalculator.Add("0,0,1"));
        assertEquals(1, StringCalculator.Add("0,1,0"));
        assertEquals(1, StringCalculator.Add("1,0,0"));
        assertEquals(2, StringCalculator.Add("0,1,1"));
        assertEquals(2, StringCalculator.Add("1,1,0"));
        assertEquals(2, StringCalculator.Add("1,0,1"));
        assertEquals(3, StringCalculator.Add("1,1,1"));
    }

    @Test
    void testFourDigits()
    {
        assertEquals(0, StringCalculator.Add("0,0,0,0"));
        assertEquals(1, StringCalculator.Add("0,0,0,1"));
        assertEquals(2, StringCalculator.Add("0,0,1,1"));
        assertEquals(3, StringCalculator.Add("0,1,1,1"));
        assertEquals(4, StringCalculator.Add("1,1,1,1"));
    }

    @Test
    void lineBreaksAndCommasSeparateNumbers()
    {
        assertEquals(0, StringCalculator.Add("0\n0,0"));
        assertEquals(1, StringCalculator.Add("0\n1,0"));
        assertEquals(2, StringCalculator.Add("1\n1,0"));
    }

    @Test
    void customDelimiters()
    {
        assertEquals(0, StringCalculator.Add("//;\n0;0"));
        assertEquals(1, StringCalculator.Add("//;\n0;1"));
        assertEquals(2, StringCalculator.Add("//;\n1;1"));
        assertEquals(0, StringCalculator.Add("//|\n0|0"));
        assertEquals(1, StringCalculator.Add("//|\n0|1"));
        assertEquals(2, StringCalculator.Add("//|\n1|1"));
    }

    @Test
    void negativeNumbersThrowException()
    {
        NumberFormatException ex;

        ex = assertThrows(NumberFormatException.class, () -> {
            StringCalculator.Add("-1,2");
        });
        assertEquals(ex.getMessage(), "Negatives not allowed: -1");

        ex = assertThrows(NumberFormatException.class, () -> {
            StringCalculator.Add("2,-4,3,-5");
        });
        assertEquals(ex.getMessage(), "Negatives not allowed: -4,-5");
    }

    @Test
    void numbersGreaterThan1000AreIgnored()
    {
        assertEquals(999, StringCalculator.Add("999,0"));
        assertEquals(1000, StringCalculator.Add("1000,0"));
        assertEquals(0, StringCalculator.Add("1001,0"));
        assertEquals(1, StringCalculator.Add("1001,1"));
    }

    @Test
    void customDelimitersOfAnyLength()
    {
        assertEquals(6, StringCalculator.Add("//[|||]\n1|||2|||3"));
    }

    @Test
    void multipleCustomDelimiters()
    {
        assertEquals(6, StringCalculator.Add("//[|][%]\n1|2%3"));
        assertEquals(6, StringCalculator.Add("//[||][%]\n1||2%3"));
        assertEquals(10, StringCalculator.Add("//[|][%][,]\n1|2%3,4"));
    }
}

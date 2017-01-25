import com.sun.tools.javac.tree.JCTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}

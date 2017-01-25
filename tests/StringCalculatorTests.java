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
    }

}

import com.sun.deploy.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by steve on 25/01/2017.
 */
public class StringCalculator {

    public static int Add(String s)
    {
        int number = 0;

        if (s.length() == 0)
        {
            return number;
        }

        String delimiter = delimiter(s);
        String[] numbers = numbers(s, delimiter);
        return getSum(numbers);
    }

    private static int getSum(String[] numbers)
    {
        int number = 0;
        List<String> negatives = new ArrayList();
        for (String digitString : numbers)
        {
            number += getNumber(digitString, negatives);
        }
        disallowNegatives(negatives);
        return number;
    }

    private static void disallowNegatives(List<String> negatives)
    {
        if (negatives.size() > 0)
        {
            String n = StringUtils.join(negatives, ",");
            throw new NumberFormatException("Negatives not allowed: " + n);
        }
    }

    private static int getNumber(String digitString, List<String> negatives)
    {
        if (digitString.startsWith("-"))
        {
            negatives.add(digitString);
            return 0;
        }
        else
        {
            return parseNumber(digitString);
        }
    }

    private static int parseNumber(String digitString)
    {
        int n = Integer.parseInt(digitString);
        return (n > 1000) ? 0 : n;
    }
    
    private static String delimiter(String s)
    {
        if (hasCustomDelimiter(s))
        {
            if (s.charAt(2) == '[')
            {
                int indexOfClosingBracket = s.indexOf("]");
                return Pattern.quote(s.substring(3, indexOfClosingBracket));
            }
            else
            {
                return Pattern.quote(s.substring(2, 3));
            }
        }
        return null;
    }

    private static boolean hasCustomDelimiter(String s)
    {
        return s.startsWith("//");
    }

    private static String[] numbers(String input, String delimiter)
    {
        String numbers = input;

        if (delimiter == null)
        {
            delimiter = "[\n,]";
        }
        else
        {
            String[] components = input.split("\n", 2);
            numbers = components[components.length - 1];
        }

        return numbers.split(delimiter);
    }

}

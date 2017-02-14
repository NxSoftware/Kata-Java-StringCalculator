import com.sun.deploy.util.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
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

        StringDelimiterParser parser = new StringDelimiterParser(s);

        List<String> delimiters = parser.getDelimiters();
        String n = parser.getNumbers();

        List<String> numbers;
        if (parser.getHasCustomDelimiters())
        {
            numbers = splitNumbersWithCustomDelimiters(n, delimiters);
        }
        else
        {
            numbers = splitNumbersWithStandardDelimiters(n, delimiters);
        }

        return getSum(numbers);
    }

    private static int getSum(List<String> numbers)
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

    private static List<String> splitNumbersWithCustomDelimiters(String input, List<String> delimiters)
    {
        List<String> numbers = new ArrayList<>();
        if (input.length() == 0)
        {
            return numbers;
        }

        if (delimiters.size() == 1)
        {
            numbers = Arrays.asList(input.split(delimiters.get(0)));
        }
        else
        {
            String remainingNumbers = input;

            for (String delimiter : delimiters)
            {
                String[] components = remainingNumbers.split(delimiter, 2);
                if (components.length > 0)
                {
                    numbers.add(components[0]);

                    if (components.length > 1)
                    {
                        remainingNumbers = components[1];
                    }
                }
            }
            numbers.add(remainingNumbers);
        }

        return numbers;
    }

    private static List<String> splitNumbersWithStandardDelimiters(String input, List<String> delimiters)
    {
        String delims = delimiters.stream().reduce("", String::concat);
        delims = Pattern.quote(delims);
        return Arrays.asList(input.split("[" + delims + "]"));
    }

}

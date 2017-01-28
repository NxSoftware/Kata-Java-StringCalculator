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
        for (String digitString : numbers)
        {
            number += Integer.parseInt(digitString);
        }
        return number;
    }

    private static String delimiter(String s)
    {
        if (hasCustomDelimiter(s))
        {
            int indexOfLineBreak = s.indexOf("\n");
            return "\\" + s.substring(2, indexOfLineBreak);
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

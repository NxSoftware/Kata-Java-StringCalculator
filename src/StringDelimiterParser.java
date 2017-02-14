import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by steve on 13/02/2017.
 */
public class StringDelimiterParser {

    private List<String> delimiters;
    private String numbers;
    private Boolean hasCustomDelimiters;

    public StringDelimiterParser(String input)
    {
        hasCustomDelimiters = hasCustomDelimiters(input);
        if (hasCustomDelimiters)
        {
            delimiters = extractCustomDelimiters(input);
            numbers = input.split("\n")[1];
        }
        if (delimiters == null || delimiters.size() == 0)
        {
            delimiters = standardDelimiters();
            numbers = input;
        }
    }

    private static boolean hasCustomDelimiters(String input)
    {
        return input.startsWith("//");
    }

    private static List<String> extractCustomDelimiters(String input)
    {
        List<String> delimiters = new ArrayList<>();

        Pattern p = Pattern.compile("(?:\\[(.*?)\\]|//(.)\n)");
        Matcher m = p.matcher(input);
        while (m.find())
        {
            delimiters.add(delimiterFromMatcher(m));
        }

        return delimiters;
    }

    private static String delimiterFromMatcher(Matcher m)
    {
        String delimiter = m.group(1);
        if (delimiter == null)
        {
            delimiter = m.group(2);
        }
        return Pattern.quote(delimiter);
    }

    private static List<String> standardDelimiters()
    {
        List<String> delimiters = new ArrayList<>();
        delimiters.add("\n");
        delimiters.add(",");
        return delimiters;
    }

    public List<String> getDelimiters()
    {
        return delimiters;
    }

    public String getNumbers()
    {
        return numbers;
    }

    public Boolean getHasCustomDelimiters()
    {
        return hasCustomDelimiters;
    }
}

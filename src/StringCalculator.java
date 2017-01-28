/**
 * Created by steve on 25/01/2017.
 */
public class StringCalculator {

    public static int Add(String s)
    {
        String delimiter = "\n,";

        if (s.startsWith("//"))
        {
            int indexOfLineBreak = s.indexOf("\n");
            delimiter = s.substring(2, indexOfLineBreak);
            s = s.substring(indexOfLineBreak + 1);
        }

        int number = 0;

        if (s.length() == 0)
        {
            return number;
        }

        String[] digitStrings = s.split("[" + delimiter + "]");
        for (String digitString : digitStrings)
        {
            number += Integer.parseInt(digitString);
        }
        return number;
    }

}

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

        String[] digitStrings = s.split("[\n,]");
        for (String digitString : digitStrings)
        {
            number += Integer.parseInt(digitString);
        }
        return number;
    }

}

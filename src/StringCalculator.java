/**
 * Created by steve on 25/01/2017.
 */
public class StringCalculator {

    public static int Add(String s)
    {
        int number = 0;
        String[] digitStrings = s.split(",");
        for (String digitString : digitStrings)
        {
            number += getInteger(digitString);
        }
        return number;
    }

    private static int getInteger(String s)
    {
        for (Integer num = 0; num < 9; num++)
        {
            if (s.equals(num.toString()))
            {
                return num;
            }
        }
        return 0;
    }

}

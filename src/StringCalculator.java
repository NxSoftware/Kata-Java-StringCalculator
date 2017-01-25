/**
 * Created by steve on 25/01/2017.
 */
public class StringCalculator {

    public static int Add(String s)
    {
        if (s.length() > 0)
        {
            int firstNum = getInteger(s, 0);
            int lastNum = 0;
            if (s.length() > 1)
            {
                lastNum = getInteger(s, s.length() - 1);
            }
            return firstNum + lastNum;
        }
        return 0;
    }

    private static int getInteger(String s, int characterPosition)
    {
        for (Integer num = 0; num < 9; num++)
        {
            char c = s.charAt(characterPosition);
            if (String.valueOf(c).equals(num.toString()))
            {
                return num;
            }
        }
        return 0;
    }

}

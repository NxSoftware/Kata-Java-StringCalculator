/**
 * Created by steve on 25/01/2017.
 */
public class StringCalculator {

    public static int Add(String s)
    {
        if (s.length() > 0)
        {
            for (Integer num = 0; num < 9; num++)
            {
                char c = s.charAt(s.length() - 1);
                if (String.valueOf(c).equals(num.toString()))
                {
                    return num;
                }
            }
        }
        return 0;
    }

}

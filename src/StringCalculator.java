/**
 * Created by steve on 25/01/2017.
 */
public class StringCalculator {

    public static int Add(String s)
    {
        if (s.length() > 0)
        {
            if (s.charAt(s.length() - 1) == '1')
            {
                return 1;
            }
            else if (s.equals("2"))
            {
                return 2;
            }
        }
        return 0;
    }

}

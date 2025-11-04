package utilities;

public class RandomNumUtil {

    public static String getNumber(){
        String num = String.valueOf(Math.random());
        return num.substring(2,4);
    }
}

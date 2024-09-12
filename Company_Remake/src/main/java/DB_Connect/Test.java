package DB_Connect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        /*
        Connect_Data cn = new Connect_Data();
        cn.LogIn("K1mikaze@proton.me","Chechito2110");
        */
        boolean coso = VerifyEmail("pacho@gmail.com");
        System.out.println(coso);


  }

  public static boolean VerifyEmail(String input){

        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

      Pattern emailpat = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
      Matcher matcher = emailpat.matcher(input);
      return matcher.find();
    }


}

package DB_Connect;
import javax.swing.*;
import java.security.MessageDigest;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author k1mikaze
 */
public class Connect_Data {
    
    private final String url = "jdbc:mysql://localhost:1051/K1";
    private final String Logged = "root";
    private final String Password = "";
    
    public Connect_Data(){}
    
    public void Creatinguser(int ID,boolean Admin,String Name,Long Phone, String Email,String Pass){
        try {

            Connection cn = DriverManager.getConnection(url,Logged,Password);
            PreparedStatement lookforuser = cn.prepareStatement("SELECT Email FROM Workers WHERE Email = ?");
            PreparedStatement lookforid = cn.prepareStatement("SELECT ID FROM Workers WHERE ID = ?");
            lookforuser.setString(1,Email);
            lookforid.setInt(1, ID);
            
            ResultSet resultid = lookforid.executeQuery();
            ResultSet resultuser = lookforuser.executeQuery();


            //Verifying if email is a emailAddres
            if(!VerifyEmail(Email)){
                JOptionPane.showMessageDialog(null,"It is not an email Adress");
            }
           
            Pass = HastingPass(Pass);// Encrypting the password to be saved inside the DB

            if (!resultuser.next() && !resultid.next()) {
                PreparedStatement pst = cn.prepareStatement("INSERT INTO Workers(ID,Admin,Created,Name,Phone,Email,Password) VALUES(?,?,NOW(),?,?,?,?)");
                pst.setInt(1,ID);
                pst.setBoolean(2,Admin);
                pst.setString(3,Name);
                pst.setString(4,String.valueOf(Phone));
                pst.setString(5,Email);
                pst.setString(6, Pass);
                pst.executeUpdate();
                PreparedStatement ps = cn.prepareStatement("INSERT INTO Logs(IDL) VALUES (?)");
                ps.setInt(1,ID);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null,"Email Created");
                
            } else {
                
                JOptionPane.showMessageDialog(null,"Email or Id already exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error while creating the user \n"+ e);
        }
    }
    
    public String[ ] LogIn(String Email,String Pass){
        
        String[] userfound = new String [3]; // 0 = Admin? ; 1 = Id ; 2 = Name
        try {
            if(!VerifyEmail(Email)){
                JOptionPane.showMessageDialog(null,"It is not an email Address");
            }else{
                Connection cn = DriverManager.getConnection(url, Logged, Password);
                PreparedStatement lookforuser = cn.prepareStatement("select * from Workers where Email = ? and Password = ?");
                lookforuser.setString(1, Email);
                Pass = HastingPass(Pass);//Encrypting password
                lookforuser.setString(2, Pass);


                ResultSet resultuser = lookforuser.executeQuery();
                if (resultuser.next()) {
                    userfound[0] = resultuser.getString("Admin");
                    userfound[1] = resultuser.getString("ID");
                    userfound[2] = resultuser.getString("Name");
                    JOptionPane.showMessageDialog(null, "Email found");

                    PreparedStatement Insert_log = cn.prepareStatement("INSERT INTO Logs(IDL,Log) VALUES (?,NOW())");
                    Insert_log.setInt(1, Integer.parseInt(userfound[1]));
                    Insert_log.executeUpdate();

                }else{
                    JOptionPane.showMessageDialog(null,"User or Password Invalid");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error looking for the user \n" + e);
        }
       return userfound;
    }

    public static boolean VerifyEmail(String input){

        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

        Pattern emailpat = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailpat.matcher(input);
        return matcher.find();
    }
    
    public static String HastingPass(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            
            messageDigest.update(password.getBytes());
            
            byte[] resultArray = messageDigest.digest();
            
            StringBuilder sb = new StringBuilder();
            for(byte b : resultArray){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Encryption failed");
            return "";
        }
        
        
    }
   
    
    
    
    
    
    
    
}

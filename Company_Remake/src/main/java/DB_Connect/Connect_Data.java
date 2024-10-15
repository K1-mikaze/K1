package DB_Connect;

import javax.swing.*;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
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
    
    public void CreatingWorker(int ID,boolean Admin,String Name,String Surname,Long Phone, String Email,String Pass){
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
                JOptionPane.showMessageDialog(null,"It is not an email Address");
            }else{
                Pass = HastingPass(Pass);// Encrypting the password to be saved inside the DB
                if (!resultuser.next() && !resultid.next()) {
                    PreparedStatement pst = cn.prepareStatement("INSERT INTO Workers(ID,Admin,Created,Name,Surname,Phone,Email,Password) VALUES(?,?,NOW(),?,?,?,?,?)");
                    pst.setInt(1,ID);
                    pst.setBoolean(2,Admin);
                    pst.setString(3,Name.toUpperCase());
                    pst.setString(4,Surname.toUpperCase());
                    pst.setString(5,String.valueOf(Phone));
                    pst.setString(6,Email);
                    pst.setString(7, Pass);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Email Created");
                } else {
                    JOptionPane.showMessageDialog(null,"Email or Id already exist");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error while creating the user \n"+ e);
        }
    }

    public int GetMaxObjectId(String Table){
        int Max = 0;
        String id_table;
        try{
            if(Table.equals("Objects")){
                id_table = "IDO";
            } else{
                id_table = "DriverID";
            }
            Connection cn = DriverManager.getConnection(url,Logged,Password);
            PreparedStatement lookMax = cn.prepareStatement("SELECT MAX("+id_table+") FROM "+ Table +";");
            ResultSet Items = lookMax.executeQuery();
            Items.next();
            Max = Items.getInt(1);
        }catch (Exception e){
            System.out.println( "Max object Error \n"+e);
        }
        return Max;
    }

    public ArrayList<String> AddToArray(int Maxvalue,String Table) {
        ArrayList<String> array = new ArrayList<>();
        String id_table;
        if(Table.equals("Objects")){
            id_table = "IDO";
        } else{
            id_table = "DriverID";
        }
        try {
            for (int i = 1; i <= Maxvalue; i++) {
                Connection cn = DriverManager.getConnection(url,Logged,Password);
                if(id_table.equals("DriverID")){
                    PreparedStatement lookName= cn.prepareStatement("SELECT Name FROM " + Table + " WHERE " + id_table + " = ?");
                    PreparedStatement lookSurname = cn.prepareStatement("SELECT Surname FROM "+ Table +" WHERE " + id_table + " = ?");
                    lookName.setInt(1, i);
                    lookSurname.setInt(1, i);
                    ResultSet Name = lookName.executeQuery();
                    ResultSet Surname = lookSurname.executeQuery();
                    Name.next();
                    Surname.next();
                    if (!(Name.getString(1).equals("REMOVED"))) {
                        array.add(Name.getString(1) + " " + Surname.getString(1));
                    }
                }else {
                    PreparedStatement lookItem = cn.prepareStatement("SELECT Name FROM " + Table + " Where " + id_table + " = ?");
                    lookItem.setInt(1, i);
                    ResultSet Item = lookItem.executeQuery();
                    Item.next();
                    if (!(Item.getString(1).equals("REMOVED"))) {
                        array.add(Item.getString(1));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Add array error\n" + e);
        }
        return array;
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

    public void CreatingUser(int CC,String[] Values){

        //0=Name,1=Surname,2=Address,3=Phone,4=Item_Name,5=Driver_Name,6=Driver_Surname
        try {
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            if(CC != 0) {
                PreparedStatement InsertUser = cn.prepareStatement("INSERT INTO Users(CC,Name,Surname,Phone) SELECT ?,?,?,? WHERE NOT EXISTS ( SELECT * FROM Users WHERE CC = ? OR Name = ?)");
                InsertUser.setInt(1, CC);
                InsertUser.setString(2, Values[0].toUpperCase());
                InsertUser.setString(3,Values[1].toUpperCase());
                InsertUser.setString(4, Values[3]);
                InsertUser.setInt(5, CC);
                InsertUser.setString(6, Values[0].toUpperCase());
                InsertUser.executeUpdate();
            }else{
                PreparedStatement InsertUser = cn.prepareStatement("INSERT INTO Users(Name,Surname,Phone) SELECT ?,?,? WHERE NOT EXISTS ( SELECT * FROM Users WHERE Name = ? AND Surname = ?)");
                InsertUser.setString(1, Values[0].toUpperCase());
                InsertUser.setString(2,Values[1].toUpperCase());
                InsertUser.setString(3, Values[3]);
                InsertUser.setString(4, Values[0].toUpperCase());
                InsertUser.setString(5,Values[1].toUpperCase());
                InsertUser.executeUpdate();

            }

            PreparedStatement Look_Object_ID = cn.prepareStatement("Select IDO FROM Objects WHERE Name = ?");
            Look_Object_ID.setString(1, Values[4]);
            ResultSet ObjectID = Look_Object_ID.executeQuery();
            ObjectID.next();

            PreparedStatement Look_User_Inserted_ID = cn.prepareStatement("SELECT IDU FROM Users WHERE Name = ? AND Surname = ? AND Phone = ?");
            Look_User_Inserted_ID.setString(1, Values[0].toUpperCase());
            Look_User_Inserted_ID.setString(2, Values[1].toUpperCase());
            Look_User_Inserted_ID.setString(3,Values[3]);
            ResultSet User_Inserted_ID = Look_User_Inserted_ID.executeQuery();
            User_Inserted_ID.next();

            PreparedStatement Look_Driver_Inserted_ID = cn.prepareStatement("SELECT DriverID from Drivers WHERE Name = ? AND Surname = ?");
            Look_Driver_Inserted_ID.setString(1,Values[5]);
            Look_Driver_Inserted_ID.setString(2,Values[6]);
            ResultSet Driver_ID = Look_Driver_Inserted_ID.executeQuery();
            Driver_ID.next();

            PreparedStatement Insert_Delivery = cn.prepareStatement("INSERT INTO Deliveries(Address,Driver) VALUES(?,?)");
            Insert_Delivery.setString(1,Values[2].toUpperCase());
            Insert_Delivery.setInt(2,Driver_ID.getInt(1));
            Insert_Delivery.executeUpdate();

            PreparedStatement Look_Delivery = cn.prepareStatement("SELECT IDD FROM Deliveries WHERE Address = ? AND Driver = ?");
            Look_Delivery.setString(1,Values[2].toUpperCase());
            Look_Delivery.setInt(2,Driver_ID.getInt(1));
            ResultSet Delivery_Found = Look_Delivery.executeQuery();
            Delivery_Found.next();


            PreparedStatement Insert_Users_Objects = cn.prepareStatement("INSERT INTO Users_Objects_Deliveries(IDU_1,IDO_1,IDD_1) VALUES (?,?,?)");
            Insert_Users_Objects.setInt(1, User_Inserted_ID.getInt(1));
            Insert_Users_Objects.setInt(2, ObjectID.getInt(1));
            Insert_Users_Objects.setInt(3,Delivery_Found.getInt(1));
            Insert_Users_Objects.executeUpdate();

        }catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null,"User Already exist");
        }catch (Exception e){
            System.out.println("Error Inserting new user \n"+e);
        }
    }

    public String Get_Item_Quantity(String item_name){
        String Quantity = "";
        try {
            if (item_name != null) {
                Connection cn = DriverManager.getConnection(url, Logged, Password);
                PreparedStatement Look_For_Item_Quantity = cn.prepareStatement("SELECT Number_Units.Remainings FROM Objects" +
                        " JOIN Number_Units ON Objects.IDO = Number_Units.IDO_2 WHERE Objects.Name = ? ");
                Look_For_Item_Quantity.setString(1, item_name);
                ResultSet Item_Quantity = Look_For_Item_Quantity.executeQuery();
                Item_Quantity.next();

                Quantity = Item_Quantity.getString(1);
            }
            }catch(Exception e){
                System.out.println("Problem getting Object Quantity\n" + e);
            }

        return Quantity;
    }

    public int Look_ID_Number_Units(String Item){
        int ID=0;
        try {
            if(Item != null) {
                Connection cn = DriverManager.getConnection(url, Logged, Password);
                PreparedStatement Look_Variable = cn.prepareStatement("SELECT IDO FROM Objects WHERE Name = ?");
                Look_Variable.setString(1,Item);
                ResultSet Item_ID = Look_Variable.executeQuery();
                Item_ID.next();
                ID = Item_ID.getInt(1);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error Looking for the Object\n"+e );
        }
        return ID;
    }

    public void Set_Number_Units(int ID_Item,int Units) {
        try{
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            PreparedStatement Look_Variable = cn.prepareStatement("UPDATE Number_Units SET Remainings = ? WHERE IDO_2 = ?");
            Look_Variable.setInt(1,Units);
            Look_Variable.setInt(2,ID_Item);
            Look_Variable.executeUpdate();
            System.out.println("Item Updated");
        }catch (Exception e){
           JOptionPane.showMessageDialog(null,"Error Setting new number of units\n"+ e);
        }
    }

    public void New_Items(String Name,String Type,int Quantity){
        try{
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            PreparedStatement Look_Variable = cn.prepareStatement("INSERT INTO Objects(Type,Name) VALUES(?,?)");
            Look_Variable.setString(1,Type);
            Look_Variable.setString(2,Name);
            Look_Variable.executeUpdate();

            PreparedStatement Look_New_Item_Id = cn.prepareStatement("SELECT IDO FROM Objects WHERE Name = ?");
            Look_New_Item_Id.setString(1,Name);
            ResultSet Item_id = Look_New_Item_Id.executeQuery();
            Item_id.next();
            int Item = Item_id.getInt(1);

            PreparedStatement Insert_Quantity = cn.prepareStatement("INSERT INTO Number_Units VALUES (?,?)");
            Insert_Quantity.setInt(1,Item);
            Insert_Quantity.setInt(2,Quantity);
            Insert_Quantity.executeUpdate();

        }catch (Exception e){
            JOptionPane.showInputDialog(null,"Error Creating New Item\n"+ e);
        }
    }

    public String More_Details_Item(String Item){
        String Detail = "";
        try {
            if(Item != null) {
                Connection cn = DriverManager.getConnection(url, Logged, Password);
                PreparedStatement Look_more_details = cn.prepareStatement("SELECT Type FROM Objects WHERE Name = ? ");
                Look_more_details.setString(1, Item);
                ResultSet Details = Look_more_details.executeQuery();
                Details.next();
                Detail = Details.getString(1);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error looking for more details\n" + e);
        }
        return Detail;
    }
    public void Delete_Item(String Item){
        try {
            if (Item!=null) {
                Connection cn = DriverManager.getConnection(url, Logged, Password);

                PreparedStatement Get_Item_id = cn.prepareStatement("SELECT IDO FROM Objects WHERE Name = ?");
                Get_Item_id.setString(1, Item);
                ResultSet Item_id = Get_Item_id.executeQuery();
                Item_id.next();

                PreparedStatement Delete_Item = cn.prepareStatement("UPDATE Objects SET Name ='REMOVED',Type ='REMOVED' WHERE Name = ?");
                Delete_Item.setString(1, Item);
                Delete_Item.executeUpdate();

                PreparedStatement Set_Item_Units_AS_0 = cn.prepareStatement("UPDATE Number_Units SET Remainings = 0 WHERE IDO_2 = ?");
                Set_Item_Units_AS_0.setInt(1,Item_id.getInt(1));
                Set_Item_Units_AS_0.executeUpdate();
                JOptionPane.showMessageDialog(null, "Item removed");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error Deleting Item\n"+ e);
        }
    }

    public String Search_User_By_ID_Phone(String PhoneorId,boolean Identificacion){
        String Fullname = "";
        try {
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            Integer.parseInt(PhoneorId);
            if (Identificacion) {
                PreparedStatement Look_for_User_Id = cn.prepareStatement("SELECT Name,Surname FROM Users WHERE CC = ? ");
                Look_for_User_Id.setInt(1,Integer.parseInt(PhoneorId));
                ResultSet User = Look_for_User_Id.executeQuery();
                User.next();
                Fullname = User.getString(1)+" "+User.getString(2);
            } else{
                PreparedStatement Look_for_User_Phone = cn.prepareStatement("SELECT Name,Surname FROM Users WHERE Phone = ?");
                Look_for_User_Phone.setString(1,PhoneorId);
                ResultSet User = Look_for_User_Phone.executeQuery();
                User.next();
                Fullname = User.getString(1)+" "+User.getString(2);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"User not Found");
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"It is not a number");
        }
        return Fullname;
    }

    public void Delete_user(String IDorPhone,boolean Identificacion){
        try {
            Integer.parseInt(IDorPhone);
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            if(Identificacion){
                PreparedStatement Delete_user= cn.prepareStatement("UPDATE Users SET CC = NULL,Name = 'REMOVED',Surname = 'REMOVED',Phone = 'REMOVED' WHERE CC = ?");
                Delete_user.setInt(1,Integer.parseInt(IDorPhone));
                Delete_user.executeUpdate();
            }else {
                PreparedStatement Delete_user= cn.prepareStatement("UPDATE Users SET CC = NULL,Name = 'REMOVED',Surname = 'REMOVED',Phone = 'REMOVED' WHERE Phone = ?");
                Delete_user.setString(1,IDorPhone);
                Delete_user.executeUpdate();
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error Deleting user");
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"It is not a number");
        }
    }


    public static boolean VerifyEmail(String input){
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailpat = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailpat.matcher(input);
        return matcher.find();
    }

    public static String HastingPass(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            byte[] resultArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultArray) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Encryption failed");
            return "";
        }
    }

    public String[] SliptName(String FullName){
        String regex = "[,\\.\\s]";
        return FullName.split(regex);
    }
}

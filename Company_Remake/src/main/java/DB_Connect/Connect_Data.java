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

    public Connect_Data() {
    }

    //Here we contain all the controller for the button on the left and the login

    public ArrayList<String> Get_All_User_Info() {
        ArrayList<String> User_info = new ArrayList<>();
        try {
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            PreparedStatement Look_User_Info = cn.prepareStatement(
                    "SELECT CONCAT(Users.Name,' ',Users.Surname),Users.Phone,Objects.Name,Deliveries.Address,CONCAT(Drivers.Name,' ',Drivers.Surname) FROM Users_Objects_Deliveries" +
                            " JOIN Users ON Users.IDU = Users_Objects_Deliveries.IDU_1 " +
                            "JOIN Objects ON Objects.IDO = Users_Objects_Deliveries.IDO_1 " +
                            "JOIN Deliveries ON Deliveries.IDD = Users_Objects_Deliveries.IDD_1 " +
                            "JOIN Drivers ON Deliveries.Driver = Drivers.DriverID");
            ResultSet Information = Look_User_Info.executeQuery();
            while (Information.next()){
                if(Information.getString(2) != null) {
                    User_info.add(Information.getString(1) + " " + Information.getString(2) + " " + Information.getString(3) + " " + Information.getString(4) + " " + Information.getString(5));
                }
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error getting Users information");
        }
        return User_info;
    }


    public ArrayList<String> GetItems() {
        ArrayList<String> Items = new ArrayList<>();
        try {
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            PreparedStatement LookForItems = cn.prepareStatement("Select Name FROM Objects");
            ResultSet Item = LookForItems.executeQuery();
            while (Item.next()) {
                if (!Item.getString(1).equals("REMOVED")) {
                    Items.add(Item.getString(1));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error getting Items");
        }
        return Items;
    }

    public ArrayList<String> GetDrivers() {
        ArrayList<String> Drivers = new ArrayList<>();
        try {
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            PreparedStatement LookForDrivers = cn.prepareStatement("Select CONCAT(Phone,' ',Name,' ',Surname) FROM Drivers");
            ResultSet Driver = LookForDrivers.executeQuery();

            while (Driver.next()) {
                Drivers.add(Driver.getString(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Getting Drivers\n" + e);
        }
        return Drivers;
    }

    public String[] LogIn(String Email, String Pass) {
        String[] userfound = new String[3]; // 0 = Admin? ; 1 = Id ; 2 = Name
        try {
            if (!VerifyEmail(Email)) {
                JOptionPane.showMessageDialog(null, "It is not an email Address");
            } else {
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

                    PreparedStatement Insert_log = cn.prepareStatement("INSERT INTO Logs(IDL,Log) VALUES (?,NOW())");
                    Insert_log.setInt(1, Integer.parseInt(userfound[1]));
                    Insert_log.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(null, "User or Password Invalid");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error looking for the user \n" + e);
        }
        return userfound;
    }

    public void Create_User(long CC, String[] Values) {

        //0=Name,1=Surname,2=Address,3=Phone,4=Item_Name,5=Driver_Phone
        try {
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            if (CC != 0) {
                PreparedStatement InsertUser = cn.prepareStatement("INSERT INTO Users(CC,Name,Surname,Phone) SELECT ?,?,?,? WHERE NOT EXISTS ( SELECT * FROM Users WHERE CC = ? OR Name = ?)");
                InsertUser.setLong(1, CC);
                InsertUser.setString(2, Values[0].toUpperCase());
                InsertUser.setString(3, Values[1].toUpperCase());
                InsertUser.setString(4, Values[3]);
                InsertUser.setLong(5, CC);
                InsertUser.setString(6, Values[0].toUpperCase());
                InsertUser.executeUpdate();
            } else {
                PreparedStatement InsertUser = cn.prepareStatement("INSERT INTO Users(Name,Surname,Phone) SELECT ?,?,? WHERE NOT EXISTS ( SELECT * FROM Users WHERE Phone = ?)");
                InsertUser.setString(1, Values[0].toUpperCase());
                InsertUser.setString(2, Values[1].toUpperCase());
                InsertUser.setString(3, Values[3]);
                InsertUser.setString(4, Values[3]);
                InsertUser.executeUpdate();
            }

            PreparedStatement Look_Object_ID = cn.prepareStatement("Select IDO FROM Objects WHERE Name = ?");
            Look_Object_ID.setString(1, Values[4]);
            ResultSet ObjectID = Look_Object_ID.executeQuery();
            ObjectID.next();

            PreparedStatement Look_User_Inserted_ID = cn.prepareStatement("SELECT IDU FROM Users WHERE Phone = ?");
            Look_User_Inserted_ID.setString(1, Values[3]);
            ResultSet User_Inserted_ID = Look_User_Inserted_ID.executeQuery();
            User_Inserted_ID.next();

            PreparedStatement Look_Driver_Inserted_ID = cn.prepareStatement("SELECT DriverID from Drivers WHERE Phone = ?");
            Look_Driver_Inserted_ID.setString(1, Values[5]);
            ResultSet Driver_ID = Look_Driver_Inserted_ID.executeQuery();
            Driver_ID.next();

            PreparedStatement Insert_Delivery = cn.prepareStatement("INSERT INTO Deliveries(Address,Driver) VALUES(?,?)");
            Insert_Delivery.setString(1, Values[2].toUpperCase());
            Insert_Delivery.setInt(2, Driver_ID.getInt(1));
            Insert_Delivery.executeUpdate();

            PreparedStatement Look_Delivery = cn.prepareStatement("SELECT IDD FROM Deliveries WHERE Address = ? AND Driver = ?");
            Look_Delivery.setString(1, Values[2].toUpperCase());
            Look_Delivery.setInt(2, Driver_ID.getInt(1));
            ResultSet Delivery_Found = Look_Delivery.executeQuery();
            Delivery_Found.next();


            PreparedStatement Insert_Users_Objects = cn.prepareStatement("INSERT INTO Users_Objects_Deliveries(IDU_1,IDO_1,IDD_1) VALUES (?,?,?)");
            Insert_Users_Objects.setInt(1, User_Inserted_ID.getInt(1));
            Insert_Users_Objects.setInt(2, ObjectID.getInt(1));
            Insert_Users_Objects.setInt(3, Delivery_Found.getInt(1));
            Insert_Users_Objects.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "User Already exist");
        } catch (Exception e) {
            System.out.println("Error Inserting new user \n" + e);
        }
    }

    public String Get_Item_Quantity(String item_name) {
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
        } catch (Exception e) {
            System.out.println("Problem getting Object Quantity\n" + e);
        }

        return Quantity;
    }

    public int Look_ID_Number_Units(String Item) {
        int ID = 0;
        try {
            if (Item != null) {
                Connection cn = DriverManager.getConnection(url, Logged, Password);
                PreparedStatement Look_Variable = cn.prepareStatement("SELECT IDO FROM Objects WHERE Name = ?");
                Look_Variable.setString(1, Item);
                ResultSet Item_ID = Look_Variable.executeQuery();
                Item_ID.next();
                ID = Item_ID.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Looking for the Object\n" + e);
        }
        return ID;
    }

    public void Set_Number_Units(int ID_Item, int Units) {
        try {
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            PreparedStatement Look_Variable = cn.prepareStatement("UPDATE Number_Units SET Remainings = ? WHERE IDO_2 = ?");
            Look_Variable.setInt(1, Units);
            Look_Variable.setInt(2, ID_Item);
            Look_Variable.executeUpdate();
            System.out.println("Item Updated");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Setting new number of units\n" + e);
        }
    }

    public void New_Items(String Name, String Type, int Quantity) {
        try {
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            PreparedStatement Look_Variable = cn.prepareStatement("INSERT INTO Objects(Type,Name) VALUES(?,?)");
            Look_Variable.setString(1, Type);
            Look_Variable.setString(2, Name);
            Look_Variable.executeUpdate();

            PreparedStatement Look_New_Item_Id = cn.prepareStatement("SELECT IDO FROM Objects WHERE Name = ?");
            Look_New_Item_Id.setString(1, Name);
            ResultSet Item_id = Look_New_Item_Id.executeQuery();
            Item_id.next();
            int Item = Item_id.getInt(1);

            PreparedStatement Insert_Quantity = cn.prepareStatement("INSERT INTO Number_Units VALUES (?,?)");
            Insert_Quantity.setInt(1, Item);
            Insert_Quantity.setInt(2, Quantity);
            Insert_Quantity.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showInputDialog(null, "Error Creating New Item\n" + e);
        }
    }

    public String More_Details_Item(String Item) {
        String Detail = "";
        try {
            if (Item != null) {
                Connection cn = DriverManager.getConnection(url, Logged, Password);
                PreparedStatement Look_more_details = cn.prepareStatement("SELECT Type FROM Objects WHERE Name = ? ");
                Look_more_details.setString(1, Item);
                ResultSet Details = Look_more_details.executeQuery();
                Details.next();
                Detail = Details.getString(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error looking for more details\n" + e);
        }
        return Detail;
    }

    public void Delete_Item(String Item) {
        try {
            if (Item != null) {
                Connection cn = DriverManager.getConnection(url, Logged, Password);

                PreparedStatement Get_Item_id = cn.prepareStatement("SELECT * FROM Objects WHERE Name = ?");
                Get_Item_id.setString(1, Item);
                ResultSet Item_id = Get_Item_id.executeQuery();
                Item_id.next();

                PreparedStatement InsertIntoRemoved = cn.prepareStatement("INSERT INTO REMOVED_OBJECTS VALUES(?,?,?)");
                InsertIntoRemoved.setInt(1, Item_id.getInt(1));
                InsertIntoRemoved.setString(2, Item_id.getString(2));
                InsertIntoRemoved.setString(3, Item_id.getString(3));
                InsertIntoRemoved.executeUpdate();

                PreparedStatement Delete_Item = cn.prepareStatement("UPDATE Objects SET Name = NULL ,Type ='REMOVED' WHERE Name = ?");
                Delete_Item.setString(1, Item);
                Delete_Item.executeUpdate();

                PreparedStatement Set_Item_Units_AS_0 = cn.prepareStatement("UPDATE Number_Units SET Remainings = 0 WHERE IDO_2 = ?");
                Set_Item_Units_AS_0.setInt(1, Item_id.getInt(1));
                Set_Item_Units_AS_0.executeUpdate();
                JOptionPane.showMessageDialog(null, "Item removed");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Deleting Item\n" + e);
        }
    }

    public String Search_User_By_ID_Phone(String PhoneorId, boolean Identificacion) {
        String Fullname = "";
        try {
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            Long.parseLong(PhoneorId);
            if (Identificacion) {
                PreparedStatement Look_for_User_Id = cn.prepareStatement("SELECT Name,Surname FROM Users WHERE CC = ? ");
                Look_for_User_Id.setLong(1, Long.parseLong(PhoneorId));
                ResultSet User = Look_for_User_Id.executeQuery();
                User.next();
                Fullname = User.getString(1) + " " + User.getString(2);
            } else {
                PreparedStatement Look_for_User_Phone = cn.prepareStatement("SELECT Name,Surname FROM Users WHERE Phone = ?");
                Look_for_User_Phone.setString(1, PhoneorId);
                ResultSet User = Look_for_User_Phone.executeQuery();
                User.next();
                Fullname = User.getString(1) + " " + User.getString(2);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "User not Found");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "It is not a number");
        }
        return Fullname;
    }

    public void Delete_user(String IDorPhone, boolean Identificacion) {
        try {
            Long.parseLong(IDorPhone);
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            if (Identificacion) {
                PreparedStatement Look_user = cn.prepareStatement("Select * From Users WHERE CC = ?");
                Look_user.setLong(1, Long.parseLong(IDorPhone));

                ResultSet UserInfo = Look_user.executeQuery();
                UserInfo.next();

                PreparedStatement InsertIntoRemoved = cn.prepareStatement("INSERT INTO REMOVED_USERS VALUES(?,?,?,?,?)");
                InsertIntoRemoved.setInt(1, UserInfo.getInt(1));
                InsertIntoRemoved.setLong(2, UserInfo.getLong(2));
                InsertIntoRemoved.setString(3, UserInfo.getString(3));
                InsertIntoRemoved.setString(4, UserInfo.getString(4));
                InsertIntoRemoved.setString(5, UserInfo.getString(5));

                PreparedStatement Delete_user = cn.prepareStatement("UPDATE Users SET CC = NULL,Name = 'REMOVED',Surname = 'REMOVED',Phone = NULL WHERE CC = ?");

                Delete_user.setInt(1, Integer.parseInt(IDorPhone));
                Delete_user.executeUpdate();
            } else {
                PreparedStatement Look_user = cn.prepareStatement("Select * From Users WHERE Phone = ?");
                Look_user.setString(1, IDorPhone);
                ResultSet UserInfo = Look_user.executeQuery();
                UserInfo.next();

                PreparedStatement InsertIntoRemoved = cn.prepareStatement("INSERT INTO REMOVED_USERS VALUES(?,?,?,?,?)");
                InsertIntoRemoved.setInt(1, UserInfo.getInt(1));
                InsertIntoRemoved.setLong(2, UserInfo.getLong(2));
                InsertIntoRemoved.setString(3, UserInfo.getString(3));
                InsertIntoRemoved.setString(4, UserInfo.getString(4));
                InsertIntoRemoved.setString(5, UserInfo.getString(5));
                InsertIntoRemoved.executeUpdate();

                PreparedStatement Delete_user = cn.prepareStatement("UPDATE Users SET CC = NULL,Name = 'REMOVED',Surname = 'REMOVED',Phone = NULL WHERE Phone = ?");
                Delete_user.setString(1, IDorPhone);
                Delete_user.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Deleting user\n" + e);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "It is not a number");
        }
    }


    public boolean VerifyEmail(String input) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailpat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailpat.matcher(input);
        return matcher.find();
    }

    public String HastingPass(String password) {
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
}

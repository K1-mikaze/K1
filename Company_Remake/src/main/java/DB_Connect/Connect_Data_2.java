package DB_Connect;

import com.mysql.cj.log.Log;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Connect_Data_2 {
    private final String url = "jdbc:mysql://localhost:1051/K1";
    private final String Logged = "root";
    private final String Password = "";

    public Connect_Data_2() {
    }

    public void Create_Worker(int ID,boolean Admin,String Name,String Surname,String Phone, String Email,String Pass){
        Connect_Data connectdata1  = new Connect_Data();
        try {
            Connection cn = DriverManager.getConnection(url,Logged,Password);
            PreparedStatement lookforuser = cn.prepareStatement("SELECT Email FROM Workers WHERE Email = ?");
            PreparedStatement lookforid = cn.prepareStatement("SELECT ID FROM Workers WHERE ID = ?");
            lookforuser.setString(1,Email);
            lookforid.setInt(1, ID);

            ResultSet resultid = lookforid.executeQuery();
            ResultSet resultuser = lookforuser.executeQuery();

            //Verifying if email is a emailAddres
            if(!connectdata1.VerifyEmail(Email)){
                JOptionPane.showMessageDialog(null,"It is not an email Address");
            }else{
                Pass = connectdata1.HastingPass(Pass);// Encrypting the password to be saved inside the DB
                if (!resultuser.next() && !resultid.next()) {
                    PreparedStatement pst = cn.prepareStatement("INSERT INTO Workers(ID,Admin,Created,Name,Surname,Phone,Email,Password) VALUES(?,?,NOW(),?,?,?,?,?)");
                    pst.setInt(1,ID);
                    pst.setBoolean(2,Admin);
                    pst.setString(3,Name.toUpperCase());
                    pst.setString(4,Surname.toUpperCase());
                    pst.setString(5,Phone);
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




    public ArrayList<String> Get_Logs_Worker(long ID) {
        ArrayList<String> Log = new ArrayList<>();
        try {
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            PreparedStatement lookforuser = cn.prepareStatement("Select Log FROM Logs WHERE IDL = ?");
            lookforuser.setLong(1, ID);
            ResultSet Logs = lookforuser.executeQuery();
            while (Logs.next()) {
                Log.add(Logs.getDate(1) + " " + Logs.getTime(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error Looking Logs");
        }
        return Log;
    }

    public ArrayList<String> Get_Workers_Ids() {
        ArrayList<String> Ids = new ArrayList<>();
        try {
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            PreparedStatement lookforWorkersIds = cn.prepareStatement("Select ID,Name FROM Workers");
            ResultSet Worker = lookforWorkersIds.executeQuery();

            while (Worker.next()) {
                if(!Worker.getString(2).equals("REMOVED")) {
                    Ids.add(Worker.getString(1));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error getting workers \n" + e);
        }
        return Ids;
    }

    public String[] Get_Name_Email_Worker(long id){
        String[] Name_Email = new String[2];
        try{
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            PreparedStatement lookforName_Email = cn.prepareStatement("Select CONCAT(Name,' ',Surname),Email FROM Workers WHERE ID = ?");
            lookforName_Email.setLong(1,id);
            ResultSet Info = lookforName_Email.executeQuery();
            Info.next();
            Name_Email[0]=Info.getString(1);
            Name_Email[1]=Info.getString(2);
        }catch (SQLException e){
           JOptionPane.showMessageDialog(null,"Error looking Name and Email from worker\n" + e);
        }
        return Name_Email;
    }

    public void Modify_Email_Worker(long Id,String Email){
        try{
            Connection cn = DriverManager.getConnection(url, Logged, Password);
            PreparedStatement UpdatingUser = cn.prepareStatement("UPDATE Workers SET Email = ? WHERE ID = ? " +
                    "AND ? NOT IN (SELECT Email FROM Workers)");
            UpdatingUser.setString(1,Email);
            UpdatingUser.setLong(2,Id);
            UpdatingUser.setString(3,Email);
            UpdatingUser.executeUpdate();
            JOptionPane.showMessageDialog(null,"Email Updated");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error modifying Email\n" +e );
        }

    }
    public void Modify_Password_Worker(long Id,String Password){
        try {
            Connection cn = DriverManager.getConnection(url, Logged,this.Password);
            PreparedStatement UpdatingUser = cn.prepareStatement("UPDATE Workers SET Password = ? WHERE ID = ? ");
            UpdatingUser.setString(1,Password);
            UpdatingUser.setLong(2,Id);
            UpdatingUser.executeUpdate();
            JOptionPane.showMessageDialog(null,"Password Updated");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error modifying Password\n"+e);

        }
    }

    public void Delete_Worker(long Id){
        try{
            Connection cn = DriverManager.getConnection(url, Logged,Password);
            PreparedStatement TAKING_ALL_INFO = cn.prepareStatement("SELECT * FROM Workers WHERE ID = ?");
            TAKING_ALL_INFO.setLong(1,Id);
            ResultSet info = TAKING_ALL_INFO.executeQuery();
            info.next();
            PreparedStatement INSERTING_INTO_REMOVED_TABLE = cn.prepareStatement("INSERT INTO REMOVED_WORKERS VALUES(?,?,?,?,?,?,?,?)");
            INSERTING_INTO_REMOVED_TABLE.setLong(1,info.getLong(1));
            INSERTING_INTO_REMOVED_TABLE.setInt(2,info.getInt(2));
            INSERTING_INTO_REMOVED_TABLE.setTimestamp(3,info.getTimestamp(3));
            INSERTING_INTO_REMOVED_TABLE.setString(4,info.getString(4));
            INSERTING_INTO_REMOVED_TABLE.setString(5,info.getString(5));
            INSERTING_INTO_REMOVED_TABLE.setLong(6,info.getLong(6));
            INSERTING_INTO_REMOVED_TABLE.setString(7,info.getString(7));
            INSERTING_INTO_REMOVED_TABLE.setString(8,info.getString(8));
            INSERTING_INTO_REMOVED_TABLE.executeUpdate();

            PreparedStatement DELETING_WORKER = cn.prepareStatement("UPDATE Workers SET Admin = 0,Name = 'REMOVED',Surname = 'REMOVED',Email = NULL,Phone = NULL,Password = 'REMOVED' WHERE ID = ?");
            DELETING_WORKER.setLong(1,Id);
            DELETING_WORKER.executeUpdate();
            JOptionPane.showMessageDialog(null,"Worker DELETED");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error deleting Worker\n"+ e);

        }
    }
}

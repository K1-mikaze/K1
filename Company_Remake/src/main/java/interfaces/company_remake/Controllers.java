package interfaces.company_remake;

import DB_Connect.Connect_Data;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Controllers {

    /*
    This class contain Application controllers like make it draggable, at the same time it has the controllers
     to use the login and create accounts no matter if it is to workers or to upload an user.
    */

    private double x;
    private double y;
    private String[] userinformation = new String[3];

    @FXML
    AnchorPane App;
    @FXML
    Label Logged_Header_label;
    //Login's Fields
    @FXML
    TextField User_txt;
    @FXML
    PasswordField Password_txt;

    //Create a worker
    @FXML
    TextField W_Name_Field;
    @FXML
    TextField W_Email_Field;
    @FXML
    TextField W_ID_Field;
    @FXML
    TextField W_Phone_Field;
    @FXML
    PasswordField W_Password_Field;
    @FXML
    PasswordField CW_Password_Field;
    @FXML
    CheckBox Administrator_CB;
    @FXML
    TextField W_Surname_Field;

    //Create Costumer
    @FXML
    TextField C_Name_Field;
    @FXML
    TextField C_Surname_Field;
    @FXML
    TextField C_ID_Field;
    @FXML
    TextField C_Phone_Field;
    @FXML
    TextField C_Address_Field;
    @FXML
    ChoiceBox<String> Drivers_ChoiceBox;
    @FXML
    ChoiceBox<String> Item_ChoiceBox;

    //Items
   @FXML
   TextField Item_Modify_Quantity_field;
   @FXML
   ChoiceBox<String> Item_ChoiceBox_2;
    @FXML
    TextField Item_Name_field;
     @FXML
    TextField Item_Quantity_field;
     @FXML
     Label Items_quantity_Label;
     @FXML
    TextField Type_Item_field;

     //Delete Items
    @FXML
    Label Quantity_label;
    @FXML
    Label Type_label;
    @FXML
    Label Name_label;
    @FXML
    ChoiceBox<String> Item_ChoiceBox_3;


    //Delete User
    @FXML
    Label Name_Deletion_label;
    @FXML
    RadioButton Ridentification;
    @FXML
    RadioButton Rphone;
    @FXML
    TextField Cellphone_Id_field;

    //Panels
    @FXML
    Pane User_Buttons;
    @FXML
    Pane Admin_Buttons;

    @FXML
    Pane Name_Header;
    @FXML
    AnchorPane Logged;
    @FXML
    Pane Log;
    @FXML
    Pane Log_Header;

    @FXML
    AnchorPane Add_Admin;
    @FXML
    AnchorPane Add_User;
    @FXML
    AnchorPane Products_Panel;
    @FXML
    AnchorPane Delete_User_panel;
    @FXML
    AnchorPane Delete_Product_panel;
    @FXML
    AnchorPane Logs_Workers_panel;
    @FXML
    AnchorPane Modify_workers_panel;
    @FXML
    AnchorPane Delete_worker_panel;

    Connect_Data cn = new Connect_Data();

    //Make the window draggable
    @FXML
    public void Dragg_AplicatiON(MouseEvent event) {
        Stage stage = (Stage) App.getScene().getWindow();
        stage.setY(event.getScreenY() - y);
        stage.setX(event.getScreenX() - x);
    }

    @FXML
    public void Press_Application(MouseEvent event) {
        y = event.getSceneY();
        x = event.getSceneX();
    }


    @FXML
    protected void OnExitButtonClicked() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void BackLogging() {
        AllPanelInvisible();
        Log.setVisible(true);
        Log_Header.setVisible(true);
    }

    @FXML
    protected void LogInbutton() {
        this.userinformation = cn.LogIn(User_txt.getText(), Password_txt.getText());
        User_txt.setText("");
        Password_txt.setText("");
        if (!(userinformation[0] == null)) {
            AllPanelInvisible();
            Logged_Panels(userinformation[2]);

            if (Integer.parseInt(userinformation[0]) == 1) {
                User_Buttons.setVisible(true);
                Admin_Buttons.setVisible(true);
            } else {
                User_Buttons.setVisible(true);
            }
        }
    }

    @FXML
    protected void CreateWorker() {
        if (W_Name_Field.getText().isBlank() || W_Email_Field.getText().isBlank()
                || W_ID_Field.getText().isBlank() || W_Password_Field.getText().isBlank()
                || CW_Password_Field.getText().isBlank() || W_Phone_Field.getText().isBlank() || W_Surname_Field.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "All the field must be filled");
        } else if (!W_Password_Field.getText().equals(CW_Password_Field.getText())) {
            JOptionPane.showMessageDialog(null, "Passwords aren't equal");
        } else {
            try {
                cn.CreatingWorker(Integer.parseInt(W_ID_Field.getText()), Administrator_CB.isSelected(),
                        W_Name_Field.getText(), W_Surname_Field.getText(), Long.parseLong(W_Phone_Field.getText()), W_Email_Field.getText(), W_Password_Field.getText());
                W_ID_Field.setText("");
                W_Name_Field.setText("");
                W_Phone_Field.setText("");
                W_Password_Field.setText("");
                CW_Password_Field.setText("");
                W_Email_Field.setText("");
                W_Surname_Field.setText("");

            } catch (NumberFormatException nf) {
                JOptionPane.showMessageDialog(null, "ID and Phone MUST BE ONLY NUMBERS");
            }
        }
    }

    @FXML
    protected void Create_worker_Button() {
        AllPanelInvisible();
        Logged_Header_label.setText("New Worker");
        Name_Header.setVisible(true);
        Add_Admin.setVisible(true);
        User_Buttons.setVisible(true);
        Admin_Buttons.setVisible(true);
    }

    @FXML
    protected void Create_Customer_Panel() {
        Item_ChoiceBox.getItems().clear();
        Drivers_ChoiceBox.getItems().clear();
        ArrayList<String> Items = cn.AddToArray(cn.GetMaxObjectId("Objects"), "Objects");
        Item_ChoiceBox.getItems().addAll(Items);
        ArrayList<String> drivers = cn.AddToArray(cn.GetMaxObjectId("Drivers"), "Drivers");
        Drivers_ChoiceBox.getItems().addAll(drivers);
        AllPanelInvisible();
        Logged_Header_label.setText("New Costumer");
        Name_Header.setVisible(true);
        Add_User.setVisible(true);
        User_Buttons.setVisible(true);
        Admin_Buttons.setVisible(true);
    }

    @FXML
    protected void Add_Customer_Button() {
        try {
            Long.parseLong(C_Phone_Field.getText());//Making sure if Phone is a real number
            if (C_Name_Field.getText().isBlank() || C_Surname_Field.getText().isBlank() || C_Address_Field.getText().isBlank() || C_Phone_Field.getText().isBlank() || Item_ChoiceBox.getSelectionModel().isEmpty() || Drivers_ChoiceBox.getSelectionModel().isEmpty()) {
                JOptionPane.showMessageDialog(null, "All the spaces must be filled in or selected");
            } else {
                String[] Name_Surname = cn.SliptName(Drivers_ChoiceBox.getValue());
                String[] Values = {C_Name_Field.getText(), C_Surname_Field.getText(), C_Address_Field.getText(), C_Phone_Field.getText(), Item_ChoiceBox.getValue(), Name_Surname[0], Name_Surname[1]};
                // 1=Name,2=Surname,3=Address,4=Phone,5=Item_Name,6=Driver_Name,7=Driver_Surname
                if (C_ID_Field.getText().isEmpty()) {
                    C_ID_Field.setText("0");
                }
                cn.CreatingUser(Integer.parseInt(C_ID_Field.getText()), Values);
                C_ID_Field.setText("");
                C_Name_Field.setText("");
                C_Surname_Field.setText("");
                C_Phone_Field.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CC must be a number and Phone\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving costumer on LoginControllern\n" + e);
        }
    }

   @FXML
    protected  void Create_Product_Panel(){
        Item_ChoiceBox_2.getItems().clear();
       ArrayList<String>  Items2 = cn.AddToArray(cn.GetMaxObjectId("Objects"), "Objects");
        Item_ChoiceBox_2.getItems().addAll(Items2);
        AllPanelInvisible();
        Logged_Header_label.setText("Items Panel");
        Name_Header.setVisible(true);
        Products_Panel.setVisible(true);
        User_Buttons.setVisible(true);
        Admin_Buttons.setVisible(true);
    }

    @FXML
    protected void Get_Item_Quantity_Set() {
        Items_quantity_Label.setText(cn.Get_Item_Quantity(Item_ChoiceBox_2.getValue()));
    }


    @FXML
    public void setNewNumberUnits_Button(){
        try{
            int Quantity = Integer.parseInt(Item_Modify_Quantity_field.getText());
            Connect_Data cn = new Connect_Data();
            int id = cn.Look_ID_Number_Units(Item_ChoiceBox_2.getValue());
            cn.Set_Number_Units(id,Quantity);
            Item_Modify_Quantity_field.setText("0");
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Must be a Integer Number");
        }
    }

    @FXML
    public void New_Object_Button() {
        try {

            Connect_Data cn = new Connect_Data();
            if(Item_Name_field.getText().isBlank() || Type_Item_field.getText().isBlank() || Item_Quantity_field.getText().isBlank()){
                JOptionPane.showMessageDialog(null,"All the Blanks must be filled");
            }else {
                int quantity = Integer.parseInt(Item_Quantity_field.getText());
                cn.New_Items(Item_Name_field.getText(), Type_Item_field.getText(), quantity);
                Item_Quantity_field.setText("");
                Item_Name_field.setText("");
                Type_Item_field.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity must be a number\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Creating Item Button\n" + e);
        }
    }
    @FXML
    public void Delete_item_panel(){
        Item_ChoiceBox_3.getItems().clear();
        ArrayList<String> Items3 = cn.AddToArray(cn.GetMaxObjectId("Objects"), "Objects");
        Item_ChoiceBox_3.getItems().addAll(Items3);
        AllPanelInvisible();
        Logged_Header_label.setText("Delete Items Panel");
        Name_Header.setVisible(true);
        Delete_Product_panel.setVisible(true);
        User_Buttons.setVisible(true);
        //if(Integer.parseInt(userinformation[0]) == 1){
           Admin_Buttons.setVisible(true);
        //}
    }
    @FXML
    public void Details_Item_button(){
        Quantity_label.setText(cn.Get_Item_Quantity(Item_ChoiceBox_3.getValue()));
        Type_label.setText(cn.More_Details_Item(Item_ChoiceBox_3.getValue()));
        Name_label.setText(Item_ChoiceBox_3.getValue());
    }
    @FXML
    public void Delete_Item(){
        cn.Delete_Item(Item_ChoiceBox_3.getValue());
        Item_ChoiceBox_3.getItems().clear();
        ArrayList<String> Items3 = cn.AddToArray(cn.GetMaxObjectId("Objects"), "Objects");
        Item_ChoiceBox_3.getItems().addAll(Items3);

    }

     @FXML
    public void Delete_User_panel(){
        AllPanelInvisible();
        Logged_Header_label.setText("Delete Users Panel");
        Name_Header.setVisible(true);
        Delete_User_panel.setVisible(true);
        User_Buttons.setVisible(true);
         //if(Integer.parseInt(userinformation[0]) == 1){
         Admin_Buttons.setVisible(true);
         //}
    }
    @FXML
    public void Search_User_button(){

        if(!Ridentification.isSelected() && !Rphone.isSelected()) {
            JOptionPane.showMessageDialog(null,"You must select one");
        }else if(Ridentification.isSelected()){
            Name_Deletion_label.setText(cn.Search_User_By_ID_Phone(Cellphone_Id_field.getText(),true));
        }else{
            Name_Deletion_label.setText(cn.Search_User_By_ID_Phone(Cellphone_Id_field.getText(),false));
        }
    }
   public void Delete_User_Button(){
       if(!Ridentification.isSelected() && !Rphone.isSelected() || Cellphone_Id_field.getText().isBlank()) {
           JOptionPane.showMessageDialog(null,"You must select one and the field must be filled in");
       }else if(Ridentification.isSelected()){
           cn.Delete_user(Cellphone_Id_field.getText(),true);
           JOptionPane.showMessageDialog(null,"User Removed");
       }else{
           cn.Delete_user(Cellphone_Id_field.getText(),false);
           JOptionPane.showMessageDialog(null,"User Removed");
       }
       Cellphone_Id_field.setText("");
   }


    public void AllPanelInvisible() {
        Log_Header.setVisible(false);
        Log.setVisible(false);
        Name_Header.setVisible(false);
        User_Buttons.setVisible(false);
        Admin_Buttons.setVisible(false);
        Add_Admin.setVisible(false);
        Logged.setVisible(false);
        Add_User.setVisible(false);
        Products_Panel.setVisible(false);
        Delete_Product_panel.setVisible(false);
        Delete_User_panel.setVisible(false);
        Logs_Workers_panel.setVisible(false);
        Modify_workers_panel.setVisible(false);
        Delete_worker_panel.setVisible(false);
    }

    protected void Logged_Panels(String Name) {
        Logged_Header_label.setText("Welcome " + Name);
        Logged.setVisible(true);
        Name_Header.setVisible(true);
    }
}
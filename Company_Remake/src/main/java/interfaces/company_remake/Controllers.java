package interfaces.company_remake;

import DB_Connect.Connect_Data;
import DB_Connect.Connect_Data_2;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;

public class Controllers {

    /*
    This class contain Application controllers like make it draggable, at the same time it has the controllers
     to use the login and create accounts no matter if it is to workers or to upload an user.
    */

    private double x;
    private double y;
    private String[] userinformation = new String[3]; // 0 = Admin? ; 1 = Id ; 2 = Name

    @FXML
    AnchorPane App;
    @FXML
    Label Logged_Header_label;

    //Home panel
    @FXML
    ListView<String>Users_info_Listview;

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

    // Logs panel
    @FXML
    ChoiceBox<String> Worker_ChoiceBox;
    @FXML
    ListView<String> Logs_view;

    //Modify Worker
    @FXML
    RadioButton Email_RadioButton;
    @FXML
    RadioButton Password_RadioButton;
    @FXML
    ChoiceBox<String> Worker_ChoiceBox_2;
    @FXML
    TextField New_Email_field;
    @FXML
    PasswordField New_Password_field;
    @FXML
    PasswordField New_CPassword_field;
    @FXML
    Label Name_Worker_label;
    @FXML
    Label Email_Worker_label;

    //Delete Workers
    @FXML
    ChoiceBox<String> Worker_ChoiceBox_3;

    @FXML
    Label Worker_name_label;
    @FXML
    Label Worker_email_label;

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
    AnchorPane Add_Worker_panel;
    @FXML
    AnchorPane Add_User_panel;
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
    Connect_Data_2 cn2 = new Connect_Data_2();

    protected void AllPanelInvisible() {
        Log_Header.setVisible(false);
        Log.setVisible(false);
        Name_Header.setVisible(false);
        User_Buttons.setVisible(false);
        Admin_Buttons.setVisible(false);
        Add_Worker_panel.setVisible(false);
        Logged.setVisible(false);
        Add_User_panel.setVisible(false);
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
        Users_info_Listview.getItems().addAll(cn.Get_All_User_Info());
    }

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
        int answer = JOptionPane.showConfirmDialog(null,"Do you want to close the application?");
        if(answer == 0){
            Platform.exit();
            System.exit(0);
        }

    }

    @FXML
    protected void Back_Home(){
        Users_info_Listview.getItems().clear();
        Users_info_Listview.getItems().addAll(cn.Get_All_User_Info());
        AllPanelInvisible();
        Logged.setVisible(true);
        User_Buttons.setVisible(true);
        Logged_Panels(userinformation[2]);
        if(userinformation[0].equals("1")){
            Admin_Buttons.setVisible(true);
        }
    }

    @FXML
    protected void BackLogging() {
        int answer = JOptionPane.showConfirmDialog(null,"Do you want to Log Out?");
        if(answer==0) {
            AllPanelInvisible();
            Log.setVisible(true);
            Log_Header.setVisible(true);
        }
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
    protected void Create_Customer_Panel() {
        Item_ChoiceBox.getItems().clear();
        Drivers_ChoiceBox.getItems().clear();
        ArrayList<String> Items = cn.GetItems();
        Item_ChoiceBox.getItems().addAll(Items);

        ArrayList<String> drivers = cn.GetDrivers();
        Drivers_ChoiceBox.getItems().addAll(drivers);
        AllPanelInvisible();
        Logged_Header_label.setText("New Costumer");
        Name_Header.setVisible(true);
        Add_User_panel.setVisible(true);
        User_Buttons.setVisible(true);
        if(userinformation[0].equals("1")) {
            Admin_Buttons.setVisible(true);
        }
    }

    @FXML
    protected void Add_Customer_Button() {
        try {
            Long.parseLong(C_Phone_Field.getText());//Making sure if Phone is a real number
            if (C_Name_Field.getText().isBlank() || C_Surname_Field.getText().isBlank() || C_Address_Field.getText().isBlank() || C_Phone_Field.getText().isBlank() || Item_ChoiceBox.getSelectionModel().isEmpty() || Drivers_ChoiceBox.getSelectionModel().isEmpty()) {
                JOptionPane.showMessageDialog(null, "All the spaces must be filled in or selected");
            } else {
                String[] Phone_Name_Surname = Drivers_ChoiceBox.getValue().split(" ");
                String[] Values = {C_Name_Field.getText(), C_Surname_Field.getText(), C_Address_Field.getText(), C_Phone_Field.getText(), Item_ChoiceBox.getValue(), Phone_Name_Surname[0]};
                // 1=Name,2=Surname,3=Address,4=Phone,5=Item_Name,6=Driver_Phone
                if (C_ID_Field.getText().isEmpty()) {
                    C_ID_Field.setText("0");
                }
                cn.Create_User(Long.parseLong(C_ID_Field.getText()), Values);
                C_ID_Field.setText("");
                C_Name_Field.setText("");
                C_Surname_Field.setText("");
                C_Phone_Field.setText("");
                C_Address_Field.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CC must be a number and Phone\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving costumer on LoginControllern\n" + e);
        }
    }

    @FXML
    protected void Create_Product_Panel() {
        Item_ChoiceBox_2.getItems().clear();
        ArrayList<String> Items2 = cn.GetItems();
        Item_ChoiceBox_2.getItems().addAll(Items2);
        AllPanelInvisible();
        Logged_Header_label.setText("Items Panel");
        Name_Header.setVisible(true);
        Products_Panel.setVisible(true);
        User_Buttons.setVisible(true);
        if(userinformation[0].equals("1")) {
            Admin_Buttons.setVisible(true);
        }
    }

    @FXML
    protected void Get_Item_Quantity_Set() {
        Items_quantity_Label.setText(cn.Get_Item_Quantity(Item_ChoiceBox_2.getValue()));
    }


    @FXML
    public void setNewNumberUnits_Button() {
        try {
            int Quantity = Integer.parseInt(Item_Modify_Quantity_field.getText());
            Connect_Data cn = new Connect_Data();
            int id = cn.Look_ID_Number_Units(Item_ChoiceBox_2.getValue());
            cn.Set_Number_Units(id, Quantity);
            Item_Modify_Quantity_field.setText("0");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Must be a Integer Number");
        }
    }

    @FXML
    public void New_Object_Button() {
        try {
            Connect_Data cn = new Connect_Data();
            if (Item_Name_field.getText().isBlank() || Type_Item_field.getText().isBlank() || Item_Quantity_field.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "All the Blanks must be filled");
            } else {
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
    public void Delete_item_panel() {
        Item_ChoiceBox_3.getItems().clear();
        ArrayList<String> Items3 = cn.GetItems();
        Item_ChoiceBox_3.getItems().addAll(Items3);
        AllPanelInvisible();
        Logged_Header_label.setText("Delete Items Panel");
        Name_Header.setVisible(true);
        Delete_Product_panel.setVisible(true);
        User_Buttons.setVisible(true);
        if(userinformation[0].equals("1")) {
            Admin_Buttons.setVisible(true);
        }
    }

    @FXML
    public void Details_Item_button() {
        Quantity_label.setText(cn.Get_Item_Quantity(Item_ChoiceBox_3.getValue()));
        Type_label.setText(cn.More_Details_Item(Item_ChoiceBox_3.getValue()));
        Name_label.setText(Item_ChoiceBox_3.getValue());
    }

    @FXML
    public void Delete_Item() {
        cn.Delete_Item(Item_ChoiceBox_3.getValue());
        Item_ChoiceBox_3.getItems().clear();
        ArrayList<String> Items3 = cn.GetItems();
        Item_ChoiceBox_3.getItems().addAll(Items3);

    }

    @FXML
    public void Delete_User_panel() {
        AllPanelInvisible();
        Logged_Header_label.setText("Delete Users Panel");
        Name_Header.setVisible(true);
        Delete_User_panel.setVisible(true);
        User_Buttons.setVisible(true);
        if(userinformation[0].equals("1")){
        Admin_Buttons.setVisible(true);
        }
    }

    @FXML
    public void Search_User_button() {

        if (!Ridentification.isSelected() && !Rphone.isSelected()) {
            JOptionPane.showMessageDialog(null, "You must select one");
        } else if (Ridentification.isSelected()) {
            Name_Deletion_label.setText(cn.Search_User_By_ID_Phone(Cellphone_Id_field.getText(), true));
        } else {
            Name_Deletion_label.setText(cn.Search_User_By_ID_Phone(Cellphone_Id_field.getText(), false));
        }
    }

    @FXML
    public void Delete_User_Button() {
        if (!Cellphone_Id_field.getText().isBlank()) {
            if (Ridentification.isSelected()) {
                cn.Delete_user(Cellphone_Id_field.getText(), true);
                JOptionPane.showMessageDialog(null, "User Removed");
            } else {
                cn.Delete_user(Cellphone_Id_field.getText(), false);
                JOptionPane.showMessageDialog(null, "User Removed");
            }
        } else{
            JOptionPane.showMessageDialog(null, "You must select one and the field must be filled in");
        }
        Cellphone_Id_field.setText("");
    }

    //RIGHT BUTTONS
    @FXML
    protected void Create_worker_panel() {
        AllPanelInvisible();
        Logged_Header_label.setText("New Worker");
        Name_Header.setVisible(true);
        Add_Worker_panel.setVisible(true);
        User_Buttons.setVisible(true);
        if(userinformation[0].equals("1")){
        Admin_Buttons.setVisible(true);
        }
    }
    @FXML
    protected void Create_Worker_Button() {
        if (W_Name_Field.getText().isBlank() || W_Email_Field.getText().isBlank()
                || W_ID_Field.getText().isBlank() || W_Password_Field.getText().isBlank()
                || CW_Password_Field.getText().isBlank() || W_Phone_Field.getText().isBlank() || W_Surname_Field.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "All the field must be filled");
        } else if (!W_Password_Field.getText().equals(CW_Password_Field.getText())) {
            JOptionPane.showMessageDialog(null, "Passwords aren't equal");
        } else {
            try {
                cn2.Create_Worker(Integer.parseInt(W_ID_Field.getText()), Administrator_CB.isSelected(),
                        W_Name_Field.getText(), W_Surname_Field.getText(), W_Phone_Field.getText(), W_Email_Field.getText(), W_Password_Field.getText());
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
    public void Log_Panel() {
        Worker_ChoiceBox.getItems().clear();
        Worker_ChoiceBox.getItems().addAll(cn2.Get_Workers_Ids());
        Logs_view.getItems().clear();
        AllPanelInvisible();
        Logged_Header_label.setText("Log's Worker Panel");
        Name_Header.setVisible(true);
        Logs_Workers_panel.setVisible(true);
        User_Buttons.setVisible(true);
        if(userinformation[0].equals("1")){
        Admin_Buttons.setVisible(true);
        }
    }

    @FXML
    public void Search_Log_Worker(){
        if(Worker_ChoiceBox.getValue() != null){
          ArrayList<String> logs = cn2.Get_Logs_Worker(Long.parseLong(Worker_ChoiceBox.getValue()));
            Logs_view.getItems().clear();
            Logs_view.getItems().addAll(logs);
        }
    }

    @FXML
    public void Modify_worker_panel() {
        Worker_ChoiceBox_2.getItems().clear();
        Worker_ChoiceBox_2.getItems().addAll(cn2.Get_Workers_Ids());
        AllPanelInvisible();
        Logged_Header_label.setText("Modify Worker Panel");
        Name_Header.setVisible(true);
        Modify_workers_panel.setVisible(true);
        User_Buttons.setVisible(true);
        if(userinformation[0].equals("1")){
            Admin_Buttons.setVisible(true);
        }
    }
    public void Search_Modify_Worker_Button(){
        if(Worker_ChoiceBox_2.getValue() != null){
            String[] Info = cn2.Get_Name_Email_Worker(Long.parseLong(Worker_ChoiceBox_2.getValue()));
            Name_Worker_label.setText(Info[0]);
            Email_Worker_label.setText(Info[1]);
        }
    }

    public void Modify_Worker_Button(){
        if (Email_RadioButton.isSelected() && !New_Email_field.getText().isBlank() && Worker_ChoiceBox_2.getValue() != null ){
           if (cn.VerifyEmail(New_Email_field.getText())){
               cn2.Modify_Email_Worker(Long.parseLong(Worker_ChoiceBox_2.getValue()),New_Email_field.getText());
               New_Email_field.setText("");
           }else {
               JOptionPane.showMessageDialog(null,"NOT VALID EMAIL");
           }
        } else if (Password_RadioButton.isSelected() && !New_Password_field.getText().isBlank() && !New_CPassword_field.getText().isBlank()
        && Worker_ChoiceBox_2.getValue() != null){
            if (New_Password_field.getText().equals(New_CPassword_field.getText())){
                cn2.Modify_Password_Worker(Long.parseLong(Worker_ChoiceBox_2.getValue()),cn.HastingPass(New_Password_field.getText()));
                New_Password_field.setText("");
                New_CPassword_field.setText("");
            }
        }
    }

    @FXML
    public void Delete_worker_panel() {
        Worker_ChoiceBox_3.getItems().clear();
        Worker_ChoiceBox_3.getItems().addAll(cn2.Get_Workers_Ids());
        AllPanelInvisible();
        Logged_Header_label.setText("Delete Worker panel");
        Name_Header.setVisible(true);
        Delete_worker_panel.setVisible(true);
        User_Buttons.setVisible(true);
        if(userinformation[0].equals("1")){
        Admin_Buttons.setVisible(true);
        }
    }
    @FXML
    public void Search_Delete_Worker_Button(){
        if(Worker_ChoiceBox_3.getValue() != null){
            String[] Info = cn2.Get_Name_Email_Worker(Long.parseLong(Worker_ChoiceBox_3.getValue()));
            Worker_name_label.setText(Info[0]);
            Worker_email_label.setText(Info[1]);
        }
    }

    @FXML
    public void Delete_Worker_Button(){
        if(Worker_ChoiceBox_3.getValue() != null) {
            int answer = JOptionPane.showConfirmDialog(null, "Do you want to delete the user with id\n" + Worker_ChoiceBox_3.getValue());
            if (answer == 0){
                cn2.Delete_Worker(Long.parseLong(Worker_ChoiceBox_3.getValue()));
                Worker_ChoiceBox_3.getItems().clear();
                Worker_ChoiceBox_3.getItems().addAll(cn2.Get_Workers_Ids());
            }
        }
    }
}
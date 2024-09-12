package interfaces.company_remake;

import DB_Connect.Connect_Data;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.beans.BeanProperty;
import java.util.jar.Attributes;


public class LoginController {

    private double x;
    private double y;
    private String[] userinformation = new String[3] ;

    @FXML AnchorPane App;

    @FXML Label Logged_Header_label;
    //Login's Fields
    @FXML TextField User_txt;
    @FXML PasswordField Password_txt;

    //Create a worker
    @FXML TextField W_Name_Field;
    @FXML TextField W_Email_Field;
    @FXML TextField W_ID_Field;
    @FXML TextField W_Phone_Field;
    @FXML PasswordField W_Password_Field;
    @FXML PasswordField CW_Password_Field;
    @FXML CheckBox Administrator_CB;

    //Panels
    @FXML Pane User_Buttons;
    @FXML Pane Admin_Buttons;

    @FXML Pane Name_Header;
    @FXML AnchorPane Logged;
    @FXML Pane Log;
    @FXML Pane Log_Header;

    @FXML AnchorPane Add_Admin;
    @FXML AnchorPane Add_User;

//Make the window draggable
    @FXML public void Dragg_AplicatiON(MouseEvent event){
        Stage stage = (Stage) App.getScene().getWindow();
        stage.setY(event.getScreenY() - y);
        stage.setX(event.getScreenX() - x);
    }
    @FXML public void Press_Application(MouseEvent event) {
        y = event.getSceneY();
        x = event.getSceneX();
    }



    @FXML protected void LogInbutton() {
        Connect_Data cn = new Connect_Data();
        userinformation = cn.LogIn(User_txt.getText(),Password_txt.getText());
        User_txt.setText("");
        Password_txt.setText("");
        if (!(userinformation[0] == null)){
            AllPanelInvisible();
            Logged_Panels(userinformation[2]);

            if(Integer.parseInt(userinformation[0]) == 1 ){ User_Buttons.setVisible(true); Admin_Buttons.setVisible(true); }else{ User_Buttons.setVisible(true);
            }
        }
    }



    @FXML protected void CreateWorker(){
        if(W_Name_Field.getText().isBlank()|| W_Email_Field.getText().isBlank()
                || W_ID_Field.getText().isBlank() || W_Password_Field.getText().isBlank()
                || CW_Password_Field.getText().isBlank() || W_Phone_Field.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "All the field must be filled");
        }else if(!W_Password_Field.getText().equals(CW_Password_Field.getText())){
            JOptionPane.showMessageDialog(null,"Passwords aren't equal");
        }else{
            try {
                Connect_Data cn = new Connect_Data();
                cn.Creatinguser(Integer.parseInt(W_ID_Field.getText()),Administrator_CB.isSelected(),
                        W_Name_Field.getText(), Long.parseLong(W_Phone_Field.getText()),W_Email_Field.getText(),W_Password_Field.getText()); }catch (NumberFormatException nf){ JOptionPane.showMessageDialog(null,"ID and Phone MUST BE ONLY NUMBERS"); }

        }

    }

    @FXML protected void OnExitButtonClicked(){
        Platform.exit();
        System.exit(0);
    }

    @FXML protected void Create_worker_Button(){
        AllPanelInvisible();
        Logged_Header_label.setText("New Worker");
        Name_Header.setVisible(true);
        Add_Admin.setVisible(true);
        User_Buttons.setVisible(true);
        Admin_Buttons.setVisible(true);
    }

    @FXML protected void BackLogging(){
        AllPanelInvisible();
        Log.setVisible(true);
        Log_Header.setVisible(true);
    }
    public void AllPanelInvisible(){
        Log_Header.setVisible(false);
        Log.setVisible(false);
        Name_Header.setVisible(false);
        User_Buttons.setVisible(false);
        Admin_Buttons.setVisible(false);
        Add_Admin.setVisible(false);
        Logged.setVisible(false);
        Add_User.setVisible(false);
    }
    protected void Logged_Panels(String Name){
        Logged_Header_label.setText("Welcome " + Name);
        Logged.setVisible(true);
        Name_Header.setVisible(true);
    }


    /*
    To Do List
    - Make the Add_User Panel usable with its own fields and button
    */


}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.ardms.business.BOFactory;
import lk.ijse.ardms.business.custom.LogInBO;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class LogInController implements Initializable {

    @FXML
    private AnchorPane logInAnchPane;
    @FXML
    private JFXButton logInBtn;
    @FXML
    private JFXTextField usernameTF;
    @FXML
    private JFXPasswordField passwordPF;
    @FXML
    private Label errorMessageLbl;
    
    LogInBO logInBO=(LogInBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);
    
    static String loggedDateTime;
    
    public static String username;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void logInBtn_onAction(ActionEvent event) throws IOException {
        try {
            boolean result=logInBO.isValid(usernameTF.getText(), passwordPF.getText());
            
            if(result){
                username=usernameTF.getText();
                Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
                Scene temp=new Scene(root);
                Stage stage=(Stage) this.logInAnchPane.getScene().getWindow();
                stage.setScene(temp);
                stage.centerOnScreen();
                
                loggedDateTime = new SimpleDateFormat("dd-MM-yyyy   HH:mm:ss").format(Calendar.getInstance().getTime());
                UserProfileWindowController.DateANDTime=loggedDateTime;
            }else{
                errorMessageLbl.setText("The Username or Password you \n entered  is incorrect.");
                usernameTF.setText("");
                passwordPF.setText("");
                usernameTF.requestFocus();
                
            }
        } catch (Exception ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void usernameTF_onAction(ActionEvent event) {
        passwordPF.requestFocus();
    }

    @FXML
    private void passwordPF_onAction(ActionEvent event) {
        logInBtn.fire();
    }
}

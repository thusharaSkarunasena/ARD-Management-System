/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.ardms.dao.custom.impl.BillingManagementDAOImpl;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class UserProfileWindowController implements Initializable {

    @FXML
    private AnchorPane userProfileWindowAnchPane;
    @FXML
    private JFXButton OKBtn;
    @FXML
    private Label DateNTime;
    @FXML
    private Label userNameTF;

    public static String DateANDTime;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DateNTime.setText(DateANDTime);
        
        OKBtn.requestFocus();
        setCashierName();
    }    

    @FXML
    private void disposeUserProfileWindow(MouseEvent event) {
        Stage stage = (Stage) OKBtn.getScene().getWindow();
        stage.close();
    }
    
    public void setCashierName(){
        try {
            String userName=BillingManagementDAOImpl.getUserName();
            userNameTF.setStyle("-fx-alignment:center");
            userNameTF.setText(userName);
        } catch (Exception ex) {
            Logger.getLogger(UserProfileWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

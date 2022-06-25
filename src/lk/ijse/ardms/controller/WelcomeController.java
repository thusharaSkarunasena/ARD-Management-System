/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.ardms.db.DBConnection;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class WelcomeController implements Initializable {

    @FXML
    private AnchorPane welcomeAnchPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        applyFadeTransition(welcomeAnchPane, Duration.seconds(2), (evt) ->{
            try {
                Parent root=FXMLLoader.load(getClass().getResource("/lk/ijse/ardms/view/logIn.fxml"));
                Scene temp=new Scene(root);
                Stage stage=(Stage) this.welcomeAnchPane.getScene().getWindow();
                stage.setScene(temp);
                stage.centerOnScreen();
                
                TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
                trans.setFromY(-temp.getHeight());
                trans.setToY(0);
                trans.play();
                    
            } catch (IOException ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    public void applyFadeTransition(Node node, Duration duration, EventHandler<ActionEvent>event){
        javafx.animation.FadeTransition fadeIn = new javafx.animation.FadeTransition(duration, node);
        fadeIn.setCycleCount(1);
        fadeIn.setFromValue(0.4);
        fadeIn.setToValue(1);
        fadeIn.setAutoReverse(true);
        fadeIn.setOnFinished(event);
        
        javafx.animation.FadeTransition fadeOut = new javafx.animation.FadeTransition(duration, node);
        fadeOut.setCycleCount(1);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0.4);
        fadeOut.setAutoReverse(true);
        
        fadeOut.play();
        fadeOut.setOnFinished((evt) -> {
            fadeIn.play();
        });
      
    }
    
}

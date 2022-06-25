/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class CustomerPaymentDetailsController implements Initializable {

    @FXML
    private AnchorPane customerPaymentDetailsAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox backHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private HBox viewMoreHBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadMainDash(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.customerPaymentDetailsAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(+temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void loadBillManagement(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/billingManagement.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.customerPaymentDetailsAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(+temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void showViewMoreWindow(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/viewMoreWindow.fxml"));
        Scene temp=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(temp);
        stage.setTitle("View More");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
    }

    @FXML
    private void showHelpWindow(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/helpWindow.fxml"));
        Scene temp=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(temp);
        stage.setTitle("Help");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
    }
    
}

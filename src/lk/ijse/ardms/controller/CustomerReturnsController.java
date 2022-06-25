/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.ardms.business.BOFactory;
import lk.ijse.ardms.business.custom.CustomerReturnsBO;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class CustomerReturnsController implements Initializable {

    @FXML
    private AnchorPane customerReturnsAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox backHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private HBox viewMoreWindow;
    @FXML
    private HBox returnDetailsHBox;
    @FXML
    private JFXTextField tel_homeTF1111;
    @FXML
    private JFXTextField tel_mobileTF1111;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private JFXButton newBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton restoreBtn;
    @FXML
    private JFXTextField refNoTF;
    @FXML
    private JFXTextField billDateTF;
    @FXML
    private JFXTextField itemcode1TF;
    @FXML
    private JFXTextField qty1TF;
    @FXML
    private JFXTextField customerNameTF;
    @FXML
    private JFXTextField itemcode2TF;
    @FXML
    private JFXTextField qty2TF;
    @FXML
    private JFXTextField itemcode3TF;
    @FXML
    private JFXTextField qty3TF;
    @FXML
    private JFXTextField itemcode4TF;
    @FXML
    private JFXTextField qty4TF;
    @FXML
    private JFXTextField statusTF;
    @FXML
    private JFXTextField CRNoTF;
    @FXML
    private JFXTextArea detailsTA;
    @FXML
    private JFXTextField releasedAmountTF;
    @FXML
    private TableView<?> customerReturnsTbl;
    @FXML
    private JFXButton billingBtn;
    
    CustomerReturnsBO customerReturnsBO=(CustomerReturnsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMERRETURNS);
    
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
        Stage stage=(Stage) this.customerReturnsAnchPane.getScene().getWindow();
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
        Stage stage=(Stage) this.customerReturnsAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(+temp.getHeight());
        trans.setToX(0);
        trans.play();
    }
    
    
    @FXML
    private void loadCustomerReturnDetails(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/customerReturnDetails.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.customerReturnsAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(-temp.getHeight());
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

    @FXML
    private void searchBox_onMouseClick(MouseEvent event) {
    }

    @FXML
    private void searchBox_onKeyReleased(KeyEvent event) {
    }


    @FXML
    private void newBtn_onAction(ActionEvent event) {
    }

    @FXML
    private void deleteBtn_onAction(ActionEvent event) {
    }

    @FXML
    private void updateBtn_onAction(ActionEvent event) {
    }


    @FXML
    private void restoreBtn_onAction(ActionEvent event) {
    }

    @FXML
    private void refNoTF_onKeyReleased(KeyEvent event) {
    }

    @FXML
    private void customerReturnsTbl_onMouseClick(MouseEvent event) {
    }

    @FXML
    private void billingBtn_onAction(ActionEvent event) {
    }
    
}

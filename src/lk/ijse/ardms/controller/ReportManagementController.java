/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class ReportManagementController implements Initializable {

    @FXML
    private HBox homeHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private AnchorPane reportManagementAnchPane;
    @FXML
    private ImageView customerReportsIcon;
    @FXML
    private ImageView itemReportsIcon;
    @FXML
    private ImageView supplierReportsIcon;
    @FXML
    private ImageView employeeReportsIcon;
    @FXML
    private Label CustomerReportsLbl;
    @FXML
    private Label customerPaymentReportsLbl;
    @FXML
    private Label itemReportsLbl;
    @FXML
    private Label supplierReportsLbl;
    @FXML
    private Label employeeReportsLbl;
    @FXML
    private Label reportsSettingsLbl;
    @FXML
    private ImageView ReportsSettingsIcon;
    @FXML
    private ImageView customerPaymentReportsIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //desplayMeaningLbl.setStyle("-fx-alignment:center");
    }    

    @FXML
    private void loadMainDash(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.reportManagementAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(+temp.getHeight());
        trans.setToX(0);
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
    private void mouseEnterReportIcon(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            
            switch(icon.getId()){
                case "customerReportsIcon":
                    CustomerReportsLbl.setStyle("-fx-text-fill: #0099CC");
                    CustomerReportsLbl.setStyle("-fx-font-size : 24px");
                    break;
                case "customerPaymentReportsIcon":
                    customerPaymentReportsLbl.setStyle("-fx-text-fill: #0099CC");
                    customerPaymentReportsLbl.setStyle("-fx-font-size : 24px");
                    break;
                case "itemReportsIcon":
                    itemReportsLbl.setStyle("-fx-text-fill: #0099CC");
                    itemReportsLbl.setStyle("-fx-font-size : 24px");
                    break;
                case "supplierReportsIcon":
                    supplierReportsLbl.setStyle("-fx-text-fill: #0099CC");
                    supplierReportsLbl.setStyle("-fx-font-size : 24px");
                    break;
                case "employeeReportsIcon":
                    employeeReportsLbl.setStyle("-fx-text-fill: #0099CC");
                    employeeReportsLbl.setStyle("-fx-font-size : 24px");
                    break;
                case "ReportsSettingsIcon":
                    reportsSettingsLbl.setStyle("-fx-text-fill: #0099CC");
                    reportsSettingsLbl.setStyle("-fx-font-size : 24px");
                    break;
            }
            
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.1);
            scaleT.setToY(1.1);
            scaleT.play(); 
            
            DropShadow glow = new DropShadow();
            glow.setColor(Color.rgb(0,153,204));
            glow.setWidth(5);
            glow.setHeight(5);
            glow.setRadius(5);
            icon.setEffect(glow);            
        }
    }


    @FXML
    private void mouseExitReportIcon(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play(); 
            icon.setEffect(null);
            CustomerReportsLbl.setStyle("-fx-font-size : 20px");
            customerPaymentReportsLbl.setStyle("-fx-font-size : 20px");
            itemReportsLbl.setStyle("-fx-font-size : 20px");
            supplierReportsLbl.setStyle("-fx-font-size : 20px");
            employeeReportsLbl.setStyle("-fx-font-size : 20px");
            reportsSettingsLbl.setStyle("-fx-font-size : 20px");
        }
    }

    @FXML
    private void customerReports_onMouseClicked(MouseEvent event) throws IOException {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/customerReportsWindow.fxml"));
            Scene temp = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(temp);
            stage.setResizable(false);
            stage.setTitle("Customer Reports");
            stage.getIcons().add(new Image("lk/ijse/ardms/asset/logo_icon.png"));
            stage.centerOnScreen();
            stage.show();
            
            TranslateTransition trans = new TranslateTransition(Duration.millis(400), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
            
    }

    @FXML
    private void customerPaymentReports_onMouseClicked(MouseEvent event) {
    }

    @FXML
    private void itemReports_onMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/itemReportsWindow.fxml"));
            Scene temp = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(temp);
            stage.setResizable(false);
            stage.setTitle("Item Reports");
            stage.getIcons().add(new Image("lk/ijse/ardms/asset/logo_icon.png"));
            stage.centerOnScreen();
            stage.show();
            
            TranslateTransition trans = new TranslateTransition(Duration.millis(400), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
    }

    @FXML
    private void supplierReports_onMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/supplierReportsWindow.fxml"));
            Scene temp = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(temp);
            stage.setResizable(false);
            stage.setTitle("Supplier Reports");
            stage.getIcons().add(new Image("lk/ijse/ardms/asset/logo_icon.png"));
            stage.centerOnScreen();
            stage.show();
            
            TranslateTransition trans = new TranslateTransition(Duration.millis(400), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
    }

    @FXML
    private void employeeReports_onMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/employeeReportsWindow.fxml"));
            Scene temp = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(temp);
            stage.setResizable(false);
            stage.setTitle("Employee Reports");
            stage.getIcons().add(new Image("lk/ijse/ardms/asset/logo_icon.png"));
            stage.centerOnScreen();
            stage.show();
            
            TranslateTransition trans = new TranslateTransition(Duration.millis(400), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
    }

    @FXML
    private void reportsSettings_onMouseClicked(MouseEvent event) {
    }
    
}

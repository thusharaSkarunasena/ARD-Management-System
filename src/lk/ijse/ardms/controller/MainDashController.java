

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class MainDashController implements Initializable {

    @FXML
    private AnchorPane mainDashAnchPane;
    @FXML
    private ImageView billIcon;
    @FXML
    private ImageView itemIcon;
    @FXML
    private ImageView customerIcon;
    @FXML
    private ImageView supplierIcon;
    @FXML
    private ImageView employeeIcon;
    @FXML
    private ImageView reportIcon;
    @FXML
    private Label descriptionLbl;
    @FXML
    private Label managementLbl;
    @FXML
    private ImageView companyLogo;
    @FXML
    private Label dateTxt;
    @FXML
    private Label timeTxt;
    @FXML
    private JFXHamburger mainDashHamburger;
    @FXML
    private JFXDrawer mainDashDrawer;
    
    static int firstTime=0;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // TODO
            if(firstTime==0){
                FadeTransition trans=new FadeTransition(Duration.millis(800),mainDashAnchPane);
                trans.setFromValue(0.0);
                trans.setToValue(1.00);
                trans.play();
                firstTime=1;
            }
            
            
            Timeline dateNtime = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                dateTxt.setText(LocalDate.now().toString());
                timeTxt.setText(LocalTime.now().toString().substring(0,8));
            }));
            
            dateNtime.setCycleCount(Animation.INDEFINITE);
            dateNtime.play();
          
        try {
            Pane drawerPane = FXMLLoader.load(getClass().getResource("/lk/ijse/ardms/view/mainDashDrawer.fxml"));
            mainDashDrawer.setSidePane(drawerPane);
            
            for(Node node : drawerPane.getChildren()){
                if(node.getAccessibleText() !=null){
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                        switch(node.getAccessibleText()){
                            
                            case "userHBox" : 
                        {
                            try {
                                loadUserHBoxAction();
                            } catch (IOException ex) {
                                Logger.getLogger(MainDashController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                            break;
                            
                            
                            case "notificationHBox" : 
                        {
                            try {
                                loadNotificationsHBoxAction();
                            } catch (IOException ex) {
                                Logger.getLogger(MainDashController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                            break;
                            
                            
                            case "viewMoreHBox" : 
                        {
                            try {
                                loadViewMoreHBoxAction();
                            } catch (IOException ex) {
                                Logger.getLogger(MainDashController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                            break;
                            
                            
                            case "logoutHBox" : 
                            loadLogoutHBoxAction();
                            break;
                            
                            
                            case "exitHBox" : 
                            loadExitHBoxAction();
                            break;
                            
                            
                            case "settingsHBox" : 
                        {
                            try {
                                loadSettingsHBoxAction();
                            } catch (IOException ex) {
                                Logger.getLogger(MainDashController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                            break;
                            
                            
                            case "helpHBox" : 
                        {
                            try {
                                loadHelpHBoxAction();
                            } catch (IOException ex) {
                                Logger.getLogger(MainDashController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                            break;
                            
                            
                            case "aboutHBox" : 
                        {
                            try {
                                loadAboutHBoxAction();
                            } catch (IOException ex) {
                                Logger.getLogger(MainDashController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                            break;
                        }
                    });
                }
            }
            
            HamburgerBackArrowBasicTransition hambtrans = new HamburgerBackArrowBasicTransition(mainDashHamburger);
            hambtrans.setRate(-1);
            mainDashHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (evt) -> {
                hambtrans.setRate(hambtrans.getRate()*-1);
                hambtrans.play();
                
                if (mainDashDrawer.isShown()) {
                    mainDashDrawer.close();
                } else {
                    mainDashDrawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(MainDashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void mouseExitIcon(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play(); 
            icon.setEffect(null);
            managementLbl.setText("Welcome");
            descriptionLbl.setText("Please select one of above main operations to proceed");
        }
    }

    @FXML
    private void mouseEnterIcon(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            
            switch(icon.getId()){
                case "customerIcon":
                    managementLbl.setText("Manage Customers");
                    descriptionLbl.setText("Click to add, edit, delete, search or view customers");
                    break;
                case "billIcon":
                    managementLbl.setText("Manage Billing");
                    descriptionLbl.setText("Click to issue customer bills and manage bill details");
                    break;
                case "itemIcon":
                    managementLbl.setText("Manage Items");
                    descriptionLbl.setText("Click to add, edit, delete, search or view items");
                    break;
                case "supplierIcon":
                    managementLbl.setText("Manage Suppliers");
                    descriptionLbl.setText("Click to add, edit, delete, search, view suppliers & their payment details");
                    break;
                case "employeeIcon":
                    managementLbl.setText("Manage Employees");
                    descriptionLbl.setText("Click to add, edit, delete, search or view employees");
                    break;
                case "reportIcon":
                    managementLbl.setText("Manage Reports");
                    descriptionLbl.setText("Click to generate & view reports.");
                    break;
            }
            
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play(); 
            
            DropShadow glow = new DropShadow();
            glow.setColor(Color.rgb(0,153,204));
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);            
        }
    }  

    @FXML
    private void loadManagements(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            try {
                ImageView icon = (ImageView) event.getSource();
                
                Parent root = null;
                
                switch(icon.getId()){
                    case "customerIcon":
                        root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/customerManagement.fxml"));
                        break;
                    case "billIcon":
                        root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/billingManagement.fxml"));
                        break;
                    case "itemIcon":
                        root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/itemManagement.fxml"));
                        break;
                    case "supplierIcon":
                        root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/supplierManagement.fxml"));
                        break;
                    case "employeeIcon":
                        root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/employeeManagement.fxml"));
                        break;
                    case "reportIcon":
                        root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/reportManagement.fxml"));
                        break;
                }
                
                if (root != null){
                    Scene temp = new Scene(root);
                    Stage stage = (Stage) this.mainDashAnchPane.getScene().getWindow();
                    stage.setScene(temp);
                    stage.centerOnScreen();
                    
                    TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
                    trans.setFromX(-temp.getHeight());
                    trans.setToX(0);
                    trans.play();
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(MainDashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadUserHBoxAction() throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/userProfileWindow.fxml"));
        Scene temp=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(temp);
        stage.setTitle("User Profile");
        stage.getIcons().add(new Image("lk/ijse/ardms/asset/logo_icon.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
    }
    
    private void loadNotificationsHBoxAction() throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/notificationWindow.fxml"));
        Scene temp=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(temp);
        stage.setTitle("Notifications");
        stage.getIcons().add(new Image("lk/ijse/ardms/asset/logo_icon.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
    }
    
    private void loadViewMoreHBoxAction() throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/viewMoreWindow.fxml"));
        Scene temp=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(temp);
        stage.setTitle("View More");
        stage.getIcons().add(new Image("lk/ijse/ardms/asset/logo_icon.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
    }
     
    private void loadLogoutHBoxAction(){
        
        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
        logoutAlert.setTitle("Logout?");
        logoutAlert.setHeaderText(null);
        logoutAlert.setContentText(" Are You Sure to Logout?");
        Optional <ButtonType> action=logoutAlert.showAndWait();
        
        if(action.get() == ButtonType.OK){
        try {
            firstTime=0;
            Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/logIn.fxml"));
            Scene temp=new Scene(root);
            Stage stage=(Stage) this.mainDashAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.centerOnScreen();
            
            
            TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
        } catch (IOException ex) {
            Logger.getLogger(MainDashController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    private void loadExitHBoxAction() {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit?");
        exitAlert.setHeaderText(null);
        exitAlert.setContentText(" Are You Sure to Exit?");
        Optional <ButtonType> action=exitAlert.showAndWait();
        
        if(action.get() == ButtonType.OK){
            System.exit(0);
        }
    }
    
    
    private void loadSettingsHBoxAction() throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/settingsWindow.fxml"));
        Scene temp=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(temp);
        stage.setTitle("Settings");
        stage.getIcons().add(new Image("lk/ijse/ardms/asset/logo_icon.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
    }
    
    private void loadHelpHBoxAction() throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/helpWindow.fxml"));
        Scene temp=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(temp);
        stage.setTitle("Help");
        stage.getIcons().add(new Image("lk/ijse/ardms/asset/logo_icon.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
    }
    
    private void loadAboutHBoxAction() throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/aboutWindow.fxml"));
        Scene temp=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(temp);
        stage.setTitle("About");
        stage.getIcons().add(new Image("lk/ijse/ardms/asset/logo_icon.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(350), temp.getRoot());
            trans.setFromY(-temp.getHeight());
            trans.setToY(0);
            trans.play();
    }
}
    

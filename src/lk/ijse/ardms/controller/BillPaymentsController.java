/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.ardms.business.BOFactory;
import lk.ijse.ardms.business.custom.PlaceCustomerBillBO;
import lk.ijse.ardms.dto.BillPaymentsDTO;
import lk.ijse.ardms.dto.BillingManagementDTO;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class BillPaymentsController implements Initializable {

    @FXML
    private AnchorPane billPaymentsAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox backHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private JFXRadioButton cashPaymentRadioBtn;
    @FXML
    private ToggleGroup billPaymentRBG;
    @FXML
    private JFXRadioButton cardPaymentRadioBtn;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXButton okBtn;
    @FXML
    private Label CashP_billTotalLbl;
    @FXML
    private Label CashP_paybleTotalLbl;
    @FXML
    private TextField CashP_billTotalTF;
    @FXML
    private TextField CashP_paybleTotalTF;
    @FXML
    private Label CashP_cashPaymentLbl;
    @FXML
    private TextField CashP_cashPaymentTF;
    @FXML
    private Label CashP_cashBalanceLbl;
    @FXML
    private TextField CashP_cashBalanceTF;
    @FXML
    private JFXButton CashP_printBillBtn;
    @FXML
    private JFXButton CashP_clearBtn;
    @FXML
    private Label CardP_billTotalLbl;
    @FXML
    private Label CardP_paybleTotalLbl;
    @FXML
    private TextField CardP_billTotalTF;
    @FXML
    private TextField CardP_paybleTotalTF;
    @FXML
    private JFXComboBox<String> CardP_cardTypeComboBox;
    @FXML
    private JFXTextField CardP_cardNumberTF;
    @FXML
    private JFXDatePicker CardP_datePicker;
    @FXML
    private JFXButton cardP_printBillBtn;
    @FXML
    private JFXButton cardP_clearBtn;
    @FXML
    private Label CashP_BONoLbl;
    @FXML
    private TextField CashP_BPNoTf;
    @FXML
    private Label cardP_BPNoLbl;
    @FXML
    private TextField cardP_BPNoTF;
    
    public static BillPaymentsController billPaymentsController;
    
    public static String BPNo;
    
    Integer count=null;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        billPaymentsController=this;
        
        clickCashPaymentRadioBtton();
        CashP_billTotalTF.setText(Double.toString(BillingManagementController.netAmount));
        CashP_paybleTotalTF.setText(CashP_billTotalTF.getText());
        
        CashP_BPNoTf.setText("BPNO-"+BPNo);
        cardP_BPNoTF.setText("BPNO-"+BPNo);
        
        count=0;
    }    
    
    
    public void setVslues(){}

    @FXML
    private void loadMainDash(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.billPaymentsAnchPane.getScene().getWindow();
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
        Stage stage=(Stage) this.billPaymentsAnchPane.getScene().getWindow();
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



    @FXML
    private void cancelBtn_onAction(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/billingManagement.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.billPaymentsAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(+temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void okBtn_onAction(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/billingManagement.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.billPaymentsAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(+temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void cashPaymentRadioBtn_onAction(ActionEvent event) {
        clickCashPaymentRadioBtton();
        CashP_billTotalTF.setText(Double.toString(BillingManagementController.netAmount));
        CashP_paybleTotalTF.setText(CashP_billTotalTF.getText());
        CardP_billTotalTF.setText("");
    }

    public void clickCashPaymentRadioBtton(){
        CashP_billTotalLbl.setDisable(false);
        CashP_billTotalTF.setDisable(false);
        CashP_cashPaymentLbl.setDisable(false);
        CashP_cashPaymentTF.setDisable(false);
        CashP_cashBalanceLbl.setDisable(false);
        CashP_cashBalanceTF.setDisable(false);
        CashP_paybleTotalLbl.setDisable(false);
        CashP_paybleTotalTF.setDisable(false);
        CashP_clearBtn.setDisable(false);
        CashP_printBillBtn.setDisable(true);
        
        CardP_billTotalLbl.setDisable(true);
        CardP_billTotalTF.setDisable(true);
        CardP_cardTypeComboBox.setDisable(true);
        CardP_datePicker.setDisable(true);
        CardP_cardNumberTF.setDisable(true);
        CardP_paybleTotalLbl.setDisable(true);
        CardP_paybleTotalTF.setDisable(true);
        
        CashP_clearBtn.fire();
        cardP_clearBtn.fire();
        
        cardP_clearBtn.setDisable(true);
        cardP_printBillBtn.setDisable(true);
    }
    
    @FXML
    private void cardPaymentRadioBtn_onAction(ActionEvent event) {
        clickCardPaymentRadioBtton();
        CardP_billTotalTF.setText(Double.toString(BillingManagementController.netAmount));
        CardP_paybleTotalTF.setText(CardP_billTotalTF.getText());
        CashP_billTotalTF.setText("");
    }
    
    public void clickCardPaymentRadioBtton(){
        CardP_billTotalLbl.setDisable(false);
        CardP_billTotalTF.setDisable(false);
        CardP_cardTypeComboBox.setDisable(false);
        CardP_datePicker.setDisable(false);
        CardP_cardNumberTF.setDisable(false);
        CardP_paybleTotalLbl.setDisable(false);
        CardP_paybleTotalTF.setDisable(false);
        cardP_clearBtn.setDisable(false);
        cardP_printBillBtn.setDisable(false);
        
        CashP_billTotalLbl.setDisable(true);
        CashP_billTotalTF.setDisable(true);
        CashP_cashPaymentLbl.setDisable(true);
        CashP_cashPaymentTF.setDisable(true);
        CashP_cashBalanceLbl.setDisable(true);
        CashP_cashBalanceTF.setDisable(true);
        CashP_paybleTotalLbl.setDisable(true);
        CashP_paybleTotalTF.setDisable(true);
        
        CashP_clearBtn.fire();
        cardP_clearBtn.fire();
        
        CashP_clearBtn.setDisable(true);
        CashP_printBillBtn.setDisable(true);
    }

    @FXML
    private void CP_printBillBtn_onAction(ActionEvent event) {
        printBill();
    }

    @FXML
    private void CP_clearBtn_onAction(ActionEvent event) {
        CashP_billTotalTF.setText("");
        CashP_cashPaymentTF.setText("");
        CashP_cashBalanceTF.setText("");
        CashP_paybleTotalTF.setText("");
        CashP_printBillBtn.setDisable(true);
    }

    @FXML
    private void cardP_printBillBtn_onAction(ActionEvent event) {
        printBill();
    }

    @FXML
    private void cardP_clearBtn_onAction(ActionEvent event) {
        CardP_billTotalTF.setText("");
        CardP_cardTypeComboBox.setValue("");
        //CardP_datePicker.set
        CardP_cardNumberTF.setText("");
        CardP_paybleTotalTF.setText("");
        cardP_printBillBtn.setDisable(true);
    }

    @FXML
    private void CashP_cashPaymentTF_onKeyReleased(KeyEvent event) {
        
        String cashP=CashP_cashPaymentTF.getText();
        
        boolean matches=cashP.matches("[0-9]{0,}");
        
        if(matches){
            Double billTotal=Double.parseDouble(CashP_billTotalTF.getText());
            Double cashPayment=Double.parseDouble(CashP_cashPaymentTF.getText());
       
            if(cashPayment>=billTotal){
                CashP_cashBalanceTF.setText(Double.toString(cashPayment-billTotal));
                CashP_printBillBtn.setDisable(false);
            }else{
                CashP_cashBalanceTF.setText("");
                CashP_printBillBtn.setDisable(true);
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Cash Payment.",ButtonType.OK).show();
            CashP_cashPaymentTF.requestFocus();
            CashP_cashPaymentTF.setText("");
        }
        
        
    }

    @FXML
    private void CashP_cashPaymentTF_onAction(ActionEvent event) {
        printBill();
    }

    @FXML
    private void CardP_cardNumberTF_onAction(ActionEvent event) {
        printBill();
    }
    
    public void printBill() {
        
        try {
            PlaceCustomerBillBO placeCustomerBillBO=(PlaceCustomerBillBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACECUSTOMERBILL);
            
            BillingManagementDTO billingManagementDTO=BillingManagementController.bmdto;
            
            BillPaymentsDTO billPaymentsDTO=new BillPaymentsDTO(
                    BPNo,
                    "Cash",
                    Double.parseDouble(CashP_paybleTotalTF.getText())
            );
            
            boolean result=placeCustomerBillBO.placeCustomerBill(billingManagementDTO, billPaymentsDTO);
            
            
            if(result){
                Alert backAlert = new Alert(Alert.AlertType.INFORMATION);
                backAlert.setContentText(" Bill has been successfully send for print.");
                Optional <ButtonType> action=backAlert.showAndWait();
                
                if(action.get() == ButtonType.OK){
                    okBtn.fire();
                }
            }else{
                if(count<3){
                    new Alert(Alert.AlertType.ERROR,"Ooops.. Something Went Wrong, Bill could't placed. Please try Again..",ButtonType.OK).show();
                    count++;
                }else{
                    new Alert(Alert.AlertType.ERROR," Sorry we can't place this bill.\n Please Try Again",ButtonType.OK).show();
                    okBtn.fire();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(BillPaymentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

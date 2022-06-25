/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.ardms.business.BOFactory;
import lk.ijse.ardms.business.custom.BillingManagementBO;
import lk.ijse.ardms.business.custom.CustomerManagementBO;
import lk.ijse.ardms.business.custom.ItemManagementBO;
import lk.ijse.ardms.dto.BillingManagementDTO;
import lk.ijse.ardms.dto.CustomerManagementDTO;
import lk.ijse.ardms.dto.ItemManagementDTO;
import lk.ijse.ardms.view.util.tblmodel.BillingManagementTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class BillingManagementController implements Initializable {

    @FXML
    private AnchorPane billingManagementAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private JFXButton paymentBtn;
    @FXML
    private JFXButton removeBtn;
    @FXML
    private JFXButton addBtn;
    @FXML
    private HBox customerReturnsHBox;
    @FXML
    private HBox paymentDetailsHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private TextField billNoTF;
    @FXML
    private TextField refNoTF;
    @FXML
    private TextField dateTF;
    @FXML
    private TextField cashierIdTF;
    @FXML
    private TextField customerNameTF;
    @FXML
    private JFXComboBox<String> customerIdComboBox;
    @FXML
    private TextField totalItemsTF;
    @FXML
    private TextField netAmountTF;
    @FXML
    private TextField discountTF;
    @FXML
    private TextField totalAmountTF;
    @FXML
    private JFXTextField discountPercentageTF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField unitPriceTF;
    @FXML
    private TextField QtyOnHandTF;
    @FXML
    private JFXTextField qtyTF;
    @FXML
    private JFXComboBox<String> itemCodeComboBox;
    @FXML
    private TableView<BillingManagementTM> billingManagementTbl;

    BillingManagementBO billingManagementBO=(BillingManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BILLINGMANAGEMENT);
    
    ObservableList<BillingManagementTM> billingManagementTM;
    
    static Double netAmount=0.0;
    
    static BillingManagementDTO bmdto;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        billingManagementTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        billingManagementTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        billingManagementTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        billingManagementTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        billingManagementTbl.getColumns().get(4).setStyle("-fx-alignment:center");
        billingManagementTbl.getColumns().get(5).setStyle("-fx-alignment:center");
        billingManagementTbl.getColumns().get(6).setStyle("-fx-alignment:center");
        
        billingManagementTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        billingManagementTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        billingManagementTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("size"));
        billingManagementTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("category"));
        billingManagementTbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        billingManagementTbl.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("qty"));
        billingManagementTbl.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("total"));
        
        totalItemsTF.setText("0");
        totalAmountTF.setText("0.00");
        
        Timeline date = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                dateTF.setText(LocalDate.now().toString());
        }));
            
        date.setCycleCount(Animation.INDEFINITE);
        date.play();
        
        generateRefAndBillNo();
        
        loadCustomerIdComboBox();
        loadItemCodeComboBox();
        
        setCashierId();
        
        netAmount=0.0;
        discountPercentageTF.setText("0");
        
        qtyTF.setDisable(true);
        paymentBtn.setDisable(true);
    }    

    @FXML
    private void loadMainDash(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.billingManagementAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(+temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void loadBillPayments(MouseEvent event) throws IOException {
        
        billingManagementTM=billingManagementTbl.getItems();
        
        ArrayList<BillingManagementTM> bmtms=new ArrayList<>();
        
        for(BillingManagementTM bmtm : billingManagementTM){
            bmtms.add(new BillingManagementTM(
                    bmtm.getItemcode(),
                    bmtm.getName(),
                    bmtm.getSize(),
                    bmtm.getCategory(),
                    bmtm.getUnitPrice(),
                    bmtm.getQty(),bmtm.getTotal()
            ));
            
            BillingManagementDTO billingManagementDTO=new BillingManagementDTO(
                    refNoTF.getText(),
                    billNoTF.getText(),
                    customerIdComboBox.getValue(),
                    dateTF.getText(),
                    cashierIdTF.getText(),
                    bmtms,
                    Double.parseDouble(totalAmountTF.getText()),
                    Double.parseDouble(discountTF.getText()),
                    Double.parseDouble(netAmountTF.getText())
            );
            
            bmdto=billingManagementDTO;
            
        }
        
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/billPayments.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.billingManagementAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(-temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void loadCustomerReturns(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/customerReturns.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.billingManagementAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(-temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void loadPaymentDetails(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/customerPaymentDetails.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.billingManagementAnchPane.getScene().getWindow();
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

    private void generateRefAndBillNo() {
        try {
            String refAndBillNo=billingManagementBO.generateRefAndBillNo();
            
            String [] output=refAndBillNo.split(",");
            
            billNoTF.setText(output[0]);
            refNoTF.setText(output[1]);
            
            String refno=output[1];
            
            String [] tobp=refno.split("-");
            
            BillPaymentsController.BPNo=tobp[1];
            
        } catch (Exception ex) {
            Logger.getLogger(BillingManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadCustomerIdComboBox() {
        try {
            ObservableList<String> customerIdList=FXCollections.observableArrayList();
            
            CustomerManagementBO customerManagementBO=(CustomerManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMERMANAGEMENT);
            
            ArrayList<CustomerManagementDTO> customerManagementDTOs=customerManagementBO.getAll();
            
            for(CustomerManagementDTO customerManagementDTO : customerManagementDTOs){
                customerIdList.add(customerManagementDTO.getCusid());
            }
            
            customerIdComboBox.setItems(customerIdList);
        } catch (Exception ex) {
            Logger.getLogger(BillingManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadItemCodeComboBox() {
        try {
            ObservableList<String> itemCodeList = FXCollections.observableArrayList();
            
            ItemManagementBO itemManagementBO=(ItemManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMMANAGEMENT);
            
            ArrayList<ItemManagementDTO> itemManagementDTOs=itemManagementBO.getAll();
            
            for(ItemManagementDTO itemManagementDTO : itemManagementDTOs){
                itemCodeList.add(itemManagementDTO.getItemcode());
            }
            itemCodeComboBox.setItems(itemCodeList);
        } catch (Exception ex) {
            Logger.getLogger(BillingManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void customerIdComboBox_onKeyReleased(KeyEvent event) {
        customerIdComboBox.show();
        try {
            CustomerManagementBO customerManagementBO=(CustomerManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMERMANAGEMENT);
            
            ObservableList<String> customerIdSearchList=FXCollections.observableArrayList();
            ObservableList<String> ifEmpty=FXCollections.observableArrayList();
            
            ArrayList<CustomerManagementDTO> customerManagementDTOs=customerManagementBO.search(customerIdComboBox.getValue());
            
            if(customerManagementDTOs.isEmpty()){
                customerIdComboBox.setItems(ifEmpty);
            }else{
                for(CustomerManagementDTO customerManagementDTO : customerManagementDTOs){
                    customerIdSearchList.add(customerManagementDTO.getCusid());
                }
                customerIdComboBox.setItems(customerIdSearchList);
            }
        } catch (Exception ex) {
            Logger.getLogger(BillingManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void itemCodeComboBox_onKeyReleased(KeyEvent event) {
        itemCodeComboBox.show();
        try {
            ItemManagementBO itemManagementBO=(ItemManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMMANAGEMENT);
            
            ObservableList<String> itemCodeSearchList=FXCollections.observableArrayList();
            ObservableList<String> ifEmpty=FXCollections.observableArrayList();
            
            ArrayList<ItemManagementDTO> itemManagementDTOs = itemManagementBO.searchItems(itemCodeComboBox.getValue());
            
            if(itemManagementDTOs.isEmpty()){
                itemCodeComboBox.setItems(ifEmpty);
                
            }else{
                for(ItemManagementDTO itemManagementDTO : itemManagementDTOs){
                    itemCodeSearchList.add(itemManagementDTO.getItemcode());
                }
                itemCodeComboBox.setItems(itemCodeSearchList);
            }
        } catch (Exception ex) {
            Logger.getLogger(BillingManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void customerIdComboBox_onAction(ActionEvent event) {
        try {
            CustomerManagementBO customerManagementBO=(CustomerManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMERMANAGEMENT);
            
            CustomerManagementDTO customerManagementDTO=customerManagementBO.getOthers(customerIdComboBox.getValue());
            
            customerNameTF.setText(customerManagementDTO.getName());
            
            itemCodeComboBox.requestFocus();
        } catch (Exception ex) {
            Logger.getLogger(BillingManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void itemCodeComboBox_onAction(ActionEvent event) {
        
            if(itemCodeComboBox.getValue().isEmpty()){
                //Do Nothing
            }else{
                try {
                    ItemManagementBO itemManagementBO=(ItemManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMMANAGEMENT);
                    
                    ItemManagementDTO itemManagementDTO=itemManagementBO.getOthers(itemCodeComboBox.getValue());
                    
                    nameTF.setText(itemManagementDTO.getName());
                    unitPriceTF.setText(Double.toString(itemManagementDTO.getPrice()));
                    QtyOnHandTF.setText(Integer.toString(itemManagementDTO.getQty()));
                    qtyTF.setDisable(false);
                    qtyTF.requestFocus();
                } catch (Exception ex) {
                    Logger.getLogger(BillingManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    private void setCashierId() {
        try {
            String cashierid=billingManagementBO.getCashierId();
            
            cashierIdTF.setText(cashierid);
        } catch (Exception ex) {
            Logger.getLogger(BillingManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void removeBtn_onAction(ActionEvent event) {
       billingManagementTM=billingManagementTbl.getItems();
       
       BillingManagementTM bmtm=billingManagementTbl.getSelectionModel().getSelectedItem();
       
       billingManagementTM.remove(bmtm);
       
       billingManagementTbl.refresh();
       
       itemCodeComboBox.setValue("");
       nameTF.setText("");
       unitPriceTF.setText("");
       QtyOnHandTF.setText("");
       qtyTF.setText("");
       
        Double totalAmount=0.0;
        Integer totalItems=0;
            
        for(BillingManagementTM caltot : billingManagementTM){
            totalAmount=totalAmount+caltot.getTotal();
            totalItems=totalItems+caltot.getQty();
        }
            
        totalAmountTF.setText(Double.toString(totalAmount));
        totalItemsTF.setText(Integer.toString(totalItems));
        calculate();
        
        itemCodeComboBox.requestFocus();
    }

    @FXML
    private void addBtn_onAction(ActionEvent event) {
        try {
            String itemcode=itemCodeComboBox.getValue();
            
            ItemManagementBO itemManagementBO=(ItemManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMMANAGEMENT);
            ItemManagementDTO imdto=itemManagementBO.getOthers(itemcode);
            
            billingManagementTM=billingManagementTbl.getItems();
            
            boolean result=isExists(itemcode);
            
            boolean matches=qtyTF.getText().matches("[0-9]{1,}");
            
            if(matches){
                Integer qty=Integer.parseInt(qtyTF.getText());
                Integer qtyOnHand=Integer.parseInt(QtyOnHandTF.getText());
                
                if(qty!=0 && qty<=qtyOnHand){
                    
                    if(result){
                        for(BillingManagementTM bmtm : billingManagementTM){
                            if(bmtm.getItemcode().equals(itemcode)){
                                bmtm.setQty(bmtm.getQty()+qty);
                                bmtm.setTotal(bmtm.getUnitPrice()*bmtm.getQty());
                                billingManagementTbl.refresh();
                            }
                        }
                    }else{
                        billingManagementTM.add(new BillingManagementTM(
                        itemcode,
                        imdto.getName(),
                        imdto.getSize(),
                        imdto.getCatName(),
                        imdto.getPrice(),
                        qty,
                        imdto.getPrice()*qty
                        ));
                    }
                    
                    itemCodeComboBox.setValue("");
                    nameTF.setText("");
                    unitPriceTF.setText("");
                    QtyOnHandTF.setText("");
                    qtyTF.setText("");
                    itemCodeComboBox.requestFocus();
                    qtyTF.setDisable(true);
                    
                }else{
                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Qty.",ButtonType.OK).show();
                qtyTF.requestFocus();
                qtyTF.setText("");
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Qty.",ButtonType.OK).show();
                qtyTF.requestFocus();
                qtyTF.setText("");
            }
            
            
            
            Double totalAmount=0.0;
            Integer totalItems=0;
            
            for(BillingManagementTM caltot : billingManagementTM){
                totalAmount=totalAmount+caltot.getTotal();
                totalItems=totalItems+caltot.getQty();
            }
            
            totalAmountTF.setText(Double.toString(totalAmount));
            totalItemsTF.setText(Integer.toString(totalItems));
            calculate();
            
        } catch (Exception ex) {
            Logger.getLogger(BillingManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isExists(String itemcode){
        billingManagementTM=billingManagementTbl.getItems();
        
        boolean b=false;
        for(BillingManagementTM bmtm : billingManagementTM){
            if(bmtm.getItemcode().equals(itemcode)){
                b=true;
            }
        }
        return b;
    }
    
    @FXML
    private void discountPercentageTF_onKeyReleased(KeyEvent event) {
        String discount=discountPercentageTF.getText();
        
        boolean matches=discount.matches("[0-9]{0,}");
        
        if(matches){
            if(Integer.parseInt(discount)<100 | discountPercentageTF.getText().isEmpty() ){
                calculate();
            }else{
                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Discount.",ButtonType.OK).show();
                discountPercentageTF.requestFocus();
                discountPercentageTF.setText("0");
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Discount.",ButtonType.OK).show();
            discountPercentageTF.requestFocus();
            discountPercentageTF.setText("0");
        }
    }
    
    public void calculate(){
        Double totalAmount=Double.parseDouble(totalAmountTF.getText());
        Integer discount;
        if(discountPercentageTF.getText().isEmpty()){
            discount=0;
        }else{
            discount=Integer.parseInt(discountPercentageTF.getText());
        }
        
        Double discountValue=totalAmount*discount/100;
        netAmount=totalAmount-discountValue;
        
        discountTF.setText(Double.toString(discountValue));
        netAmountTF.setText(Double.toString(netAmount));
        
        if(netAmount>0.0){
            paymentBtn.setDisable(false);
        }else{
            paymentBtn.setDisable(true);
        }
    }
    
    @FXML
    private void billingManagementTbl_onMouseClicked(MouseEvent event) {
        try {
            BillingManagementTM billingManagementTM=billingManagementTbl.getSelectionModel().getSelectedItem();
            
            ItemManagementBO itemManagementBO=(ItemManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMMANAGEMENT);
            ItemManagementDTO itemManagementDTO=itemManagementBO.getOthers(billingManagementTM.getItemcode());
            
            itemCodeComboBox.setValue(billingManagementTM.getItemcode());
            nameTF.setText(billingManagementTM.getName());
            unitPriceTF.setText(Double.toString(billingManagementTM.getUnitPrice()));
            QtyOnHandTF.setText(Integer.toString(itemManagementDTO.getQty()));
            qtyTF.setText(Integer.toString(billingManagementTM.getQty()));
            
        } catch (Exception ex) {
            Logger.getLogger(BillingManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void qtyTF_onAction(ActionEvent event) {
        addBtn.fire();
    }
    
}

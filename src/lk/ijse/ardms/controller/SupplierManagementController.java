/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
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
import lk.ijse.ardms.business.custom.SupplierManagementBO;
import lk.ijse.ardms.dto.SupplierManagementDTO;
import lk.ijse.ardms.view.util.tblmodel.SupplierManagementTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class SupplierManagementController implements Initializable {

    @FXML
    private AnchorPane supplierManagementAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private JFXButton newBtn;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private JFXTextField supIdTF;
    @FXML
    private JFXTextField companyTF;
    @FXML
    private JFXTextField tel_officeTF;
    @FXML
    private JFXTextField tel_mobileTF;
    @FXML
    private JFXTextField address_villageTF;
    @FXML
    private JFXTextField emailTF;
    @FXML
    private JFXTextField address_streetTF;
    @FXML
    private JFXTextField address_noTF;
    @FXML
    private JFXTextField address_cityTF;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextField otherDetailsTF;
    @FXML
    private JFXTextField nicTF;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<SupplierManagementTM> supplierManagementTbl;
    
    SupplierManagementBO supplierManagementBO = (SupplierManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIERMANAGEMENT);
    
    ObservableList<SupplierManagementTM> supplierManagementTM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        supplierManagementTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        supplierManagementTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("supid"));
        supplierManagementTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        supplierManagementTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nic"));
        supplierManagementTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("company"));
        
        loadAllSuppliers();
        
        generateSupplierId();
    }    

    @FXML
    private void loadMainDash(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.supplierManagementAnchPane.getScene().getWindow();
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

    
    private void loadAllSuppliers() {
        try {
            ArrayList<SupplierManagementDTO> supplierManagementDTOs = supplierManagementBO.getAll();
            
            supplierManagementTM = supplierManagementTbl.getItems();
            
            supplierManagementTM.removeAll(supplierManagementTM);
            
            for(SupplierManagementDTO supplier : supplierManagementDTOs){
                supplierManagementTM.add(new SupplierManagementTM(
                        supplier.getSupid(),
                        supplier.getName(),
                        supplier.getNic(),
                        supplier.getCompany()
                ));
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void generateSupplierId() {
        try {
            String newSupplierId=supplierManagementBO.generateSupplierId();
            
            supIdTF.setText(newSupplierId);
        } catch (Exception ex) {
            Logger.getLogger(SupplierManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void supplierManagementTbl_onMouseClicked(MouseEvent event) {
        
        try {
            SupplierManagementTM supplierManagementTM=supplierManagementTbl.getSelectionModel().getSelectedItem();
            supIdTF.setText(supplierManagementTM.getSupid());
            nameTF.setText(supplierManagementTM.getName());
            nicTF.setText(supplierManagementTM.getNic());
            companyTF.setText(supplierManagementTM.getCompany());
            
            SupplierManagementDTO supplierManagementDTO = supplierManagementBO.getOthers(supIdTF.getText());
            address_noTF.setText(supplierManagementDTO.getAddress_no());
            address_streetTF.setText(supplierManagementDTO.getAddress_street());
            address_villageTF.setText(supplierManagementDTO.getAddress_village());
            address_cityTF.setText(supplierManagementDTO.getAddress_city());
            tel_officeTF.setText(supplierManagementDTO.getTel_office());
            tel_mobileTF.setText(supplierManagementDTO.getTel_mobile());
            otherDetailsTF.setText(supplierManagementDTO.getOtherDetails());
        } catch (Exception ex) {
            Logger.getLogger(SupplierManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void newBtn_onAction(ActionEvent event) {
        supIdTF.setText("");
        nameTF.setText("");
        companyTF.setText("");
        address_noTF.setText("");
        address_streetTF.setText("");
        address_villageTF.setText("");
        address_cityTF.setText("");
        nicTF.setText("");
        tel_officeTF.setText("");
        tel_mobileTF.setText("");
        emailTF.setText("");
        otherDetailsTF.setText("");
        searchBoxTF.setText("");
        
        generateSupplierId();
    }

    
    @FXML
    private void saveBtn_onAction(ActionEvent event) {
        
        int isAlreadyHave=0;
        supplierManagementTM=supplierManagementTbl.getItems();
        for(SupplierManagementTM smtm : supplierManagementTM){
            if(supIdTF.getText().equals(smtm.getSupid()) | nicTF.getText().equals(smtm.getNic())){
                isAlreadyHave=1;
            }
        }
        
        if(isAlreadyHave==1){
            new Alert(Alert.AlertType.ERROR,"The supplier you are going to save is already in supplier table.",ButtonType.OK).show();
        }
        else{
            try {
                String supid=supIdTF.getText();
                String name=nameTF.getText();
                String company=companyTF.getText();
                String address_no=address_noTF.getText();
                String address_street=address_streetTF.getText();
                String address_village=address_villageTF.getText();
                String address_city=address_cityTF.getText();
                String nic=nicTF.getText();
                String tel_office=tel_officeTF.getText();
                String tel_mobile=tel_mobileTF.getText();
                String email=emailTF.getText();
                String otherDetails=otherDetailsTF.getText();

                SupplierManagementDTO supplierManagementDTO = new SupplierManagementDTO(supid, name, company, address_no, address_street, address_village, address_city, nic, tel_office, tel_mobile, email, otherDetails);

                boolean result=false;
                
                boolean matches1=name.matches("[a-z A-Z]{3,}");
                boolean matches2=company.matches("([a-z A-Z 0-9]{1,})?");
                boolean matches3=address_no.matches("([0-9 A-Z]{1,})?([/]?[A-Z 0-9])?([/]?[A-Z 0-9])");
                boolean matches4=address_street.matches("[a-z A-Z 0-9]{5,}");
                boolean matches5=address_village.matches("[a-z A-Z]{5,}");
                boolean matches6=address_city.matches("([a-z A-Z]{5,})?");
                boolean matches7=nic.matches("[0-9]{9,11}[V]");
                boolean matches8=tel_office.matches("[0-9]{10}");
                boolean matches9=tel_mobile.matches("[0-9]{10}");
                boolean matches10=email.matches("([a-z A-Z 0-9]+[@]{1}[a-z]{3,}[.]{1}[com | org | net | biz | lk]{2,3})?");
                boolean matches11=otherDetails.matches("(.{1,})?");
                
                if(matches1){
                    if(matches2){
                        if(matches3){
                            if(matches4){
                                if(matches5){
                                    if(matches6){
                                        if(matches7){
                                            if(matches8){
                                                if(matches9){
                                                    if(matches10){
                                                        if(matches11){
                                                            result = supplierManagementBO.saveSupplier(supplierManagementDTO);
                                                        }else{
                                                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Other Details.",ButtonType.OK).show();
                                                            otherDetailsTF.requestFocus();
                                                        }
                                                    }else{
                                                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in E-mail.",ButtonType.OK).show();
                                                        emailTF.requestFocus();
                                                    }
                                                }else{
                                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Mobile.",ButtonType.OK).show();
                                                    tel_mobileTF.requestFocus();
                                                }
                                            }else{
                                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Office.",ButtonType.OK).show();
                                                tel_officeTF.requestFocus();
                                            }
                                        }else{
                                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in NIC.",ButtonType.OK).show();
                                            nicTF.requestFocus();
                                        }
                                    }else{
                                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address City.",ButtonType.OK).show();
                                        address_cityTF.requestFocus();
                                    }
                                }else{
                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Village.",ButtonType.OK).show();
                                    address_villageTF.requestFocus();
                                }
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Street.",ButtonType.OK).show();
                                address_streetTF.requestFocus();
                            }
                        }else{
                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address No.",ButtonType.OK).show();
                            address_noTF.requestFocus();
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Company.",ButtonType.OK).show();
                        companyTF.requestFocus();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.",ButtonType.OK).show();
                    nameTF.requestFocus();
                }
                
                

                if(result){
                    new Alert(Alert.AlertType.INFORMATION,"Supplier has been saved successfully.",ButtonType.OK).show();
                    loadAllSuppliers();
                    newBtn_onAction(event);
                }else{
                    new Alert(Alert.AlertType.ERROR,"Failed to save the supplier.",ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(SupplierManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    
    @FXML
    private void updateBtn_onAction(ActionEvent event) {
        try {
            String supid=supIdTF.getText();
            String name=nameTF.getText();
            String company=companyTF.getText();
            String address_no=address_noTF.getText();
            String address_street=address_streetTF.getText();
            String address_village=address_villageTF.getText();
            String address_city=address_cityTF.getText();
            String nic=nicTF.getText();
            String tel_office=tel_officeTF.getText();
            String tel_mobile=tel_mobileTF.getText();
            String email=emailTF.getText();
            String otherDetails=otherDetailsTF.getText();
            
            SupplierManagementDTO supplierManagementDTO = new SupplierManagementDTO(supid, name, company, address_no, address_street, address_village, address_city, nic, tel_office, tel_mobile, email, otherDetails);
            
            boolean result=false;
                
            boolean matches1=name.matches("[a-z A-Z]{3,}");
            boolean matches2=company.matches("([a-z A-Z 0-9]{1,})?");
            boolean matches3=address_no.matches("([0-9 A-Z]{1,})?([/]?[A-Z 0-9])?([/]?[A-Z 0-9])");
            boolean matches4=address_street.matches("[a-z A-Z 0-9]{5,}");
            boolean matches5=address_village.matches("[a-z A-Z]{5,}");
            boolean matches6=address_city.matches("([a-z A-Z]{5,})?");
            boolean matches7=nic.matches("[0-9]{9,11}[V]");
            boolean matches8=tel_office.matches("[0-9]{10}");
            boolean matches9=tel_mobile.matches("[0-9]{10}");
            boolean matches10=email.matches("([a-z A-Z 0-9]+[@]{1}[a-z]{3,}[.]{1}[com | org | net | biz | lk]{2,3})?");
            boolean matches11=otherDetails.matches("(.{1,})?");

            if(matches1){
                if(matches2){
                    if(matches3){
                        if(matches4){
                            if(matches5){
                                if(matches6){
                                    if(matches7){
                                        if(matches8){
                                            if(matches9){
                                                if(matches10){
                                                    if(matches11){
                                                        result=supplierManagementBO.updateSupplier(supplierManagementDTO);
                                                    }else{
                                                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Other Details.",ButtonType.OK).show();
                                                        otherDetailsTF.requestFocus();
                                                    }
                                                }else{
                                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in E-mail.",ButtonType.OK).show();
                                                    emailTF.requestFocus();
                                                }
                                            }else{
                                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Mobile.",ButtonType.OK).show();
                                                tel_mobileTF.requestFocus();
                                            }
                                        }else{
                                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Office.",ButtonType.OK).show();
                                            tel_officeTF.requestFocus();
                                        }
                                    }else{
                                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in NIC.",ButtonType.OK).show();
                                        nicTF.requestFocus();
                                    }
                                }else{
                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address City.",ButtonType.OK).show();
                                    address_cityTF.requestFocus();
                                }
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Village.",ButtonType.OK).show();
                                address_villageTF.requestFocus();
                            }
                        }else{
                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Street.",ButtonType.OK).show();
                            address_streetTF.requestFocus();
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address No.",ButtonType.OK).show();
                        address_noTF.requestFocus();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Company.",ButtonType.OK).show();
                    companyTF.requestFocus();
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.",ButtonType.OK).show();
                nameTF.requestFocus();
            }
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Supplier has been updated successfully.",ButtonType.OK).show();
                loadAllSuppliers();
                newBtn_onAction(event);
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to update the supplier.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void deleteBtn_onAction(ActionEvent event) {
        
        try {
            SupplierManagementTM supplierManagementTM=supplierManagementTbl.getSelectionModel().getSelectedItem();
            
            boolean result=supplierManagementBO.deleteSuppier(supplierManagementTM.getSupid());
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Supplier has been deleted successfully.",ButtonType.OK).show();
                loadAllSuppliers();
                newBtn_onAction(event);
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to delete the supplier.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void searchBox_onMouseClicked(MouseEvent event) {
        supIdTF.setText("");
        nameTF.setText("");
        companyTF.setText("");
        address_noTF.setText("");
        address_streetTF.setText("");
        address_villageTF.setText("");
        address_cityTF.setText("");
        nicTF.setText("");
        tel_officeTF.setText("");
        tel_mobileTF.setText("");
        emailTF.setText("");
        otherDetailsTF.setText("");
        searchBoxTF.setText("");
        loadAllSuppliers();
    }

    
    @FXML
    private void searchBox_keyReleased(KeyEvent event) {
        if(searchBoxTF.getText()==null){
            loadAllSuppliers();
        }else{
            try {
                ArrayList<SupplierManagementDTO> supplierManagementDTOs = supplierManagementBO.searchSupplier(searchBoxTF.getText());                
                if(supplierManagementDTOs.isEmpty()){
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadAllSuppliers();
                }else{
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    supplierManagementTM =supplierManagementTbl.getItems();
                    supplierManagementTM.removeAll(supplierManagementTM);
                  
                    for(SupplierManagementDTO supplier : supplierManagementDTOs){
                        supplierManagementTM.add(new SupplierManagementTM(
                                supplier.getSupid(),
                                supplier.getName(),
                                supplier.getNic(),
                                (supplier.getAddress_no()+","+supplier.getAddress_street()+","+supplier.getAddress_village()+","+supplier.getAddress_city())
                        ));
                    } 
                }   
            } catch (Exception ex) {
                Logger.getLogger(SupplierManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void companyTF_onAction(ActionEvent event) {
        String company = companyTF.getText();
        boolean matches=company.matches("([a-z A-Z 0-9]{1,})?");
        if(matches){
            address_noTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Company.",ButtonType.OK).show();
            companyTF.requestFocus();
        }
    }

    @FXML
    private void tel_officeTF_onAction(ActionEvent event) {
        String tel_office = tel_officeTF.getText();
        boolean matches=tel_office.matches("[0-9]{10}");
        if(matches){
            tel_mobileTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Office.",ButtonType.OK).show();
            tel_officeTF.requestFocus();
        }
    }

    @FXML
    private void tel_mobile_onAction(ActionEvent event) {
        String tel_mobile = tel_mobileTF.getText();
        boolean matches=tel_mobile.matches("[0-9]{10}");
        if(matches){
            emailTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Mobile.",ButtonType.OK).show();
            tel_mobileTF.requestFocus();
        }
    }

    @FXML
    private void address_villageTF_onAction(ActionEvent event) {
        String address_village = address_villageTF.getText();
        boolean matches=address_village.matches("[a-z A-Z]{5,}");
        if(matches){
            address_cityTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Village.",ButtonType.OK).show();
            address_villageTF.requestFocus();
        }
    }

    @FXML
    private void emailTF_onAction(ActionEvent event) {
        String email = emailTF.getText();
        boolean matches=email.matches("([a-z A-Z 0-9]+[@]{1}[a-z]{3,}[.]{1}[com | org | net | biz | lk]{2,3})?");
        if(matches){
            otherDetailsTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in E-mail.",ButtonType.OK).show();
            emailTF.requestFocus();
        }
    }

    @FXML
    private void address_streetTF_onAction(ActionEvent event) {
        String address_street = address_streetTF.getText();
        boolean matches=address_street.matches("[a-z A-Z 0-9]{5,}");
        if(matches){
            address_villageTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Street.",ButtonType.OK).show();
            address_streetTF.requestFocus();
        }
    }

    @FXML
    private void address_noTF_onAction(ActionEvent event) {
        String address_no = address_noTF.getText();
        boolean matches=address_no.matches("([0-9 A-Z]{1,})?([/]?[A-Z 0-9])?([/]?[A-Z 0-9])");
        if(matches){
            address_streetTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address No.",ButtonType.OK).show();
            address_noTF.requestFocus();
        }
    }

    @FXML
    private void address_cityTF_onAction(ActionEvent event) {
        String address_city = address_cityTF.getText();
        boolean matches=address_city.matches("([a-z A-Z]{5,})?");
        if(matches){
            nicTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address City.",ButtonType.OK).show();
            address_cityTF.requestFocus();
        }
    }

    @FXML
    private void nameTF_onAction(ActionEvent event) {
        String supname = nameTF.getText();
        boolean matches=supname.matches("[a-z A-Z]{3,}");
        if(matches){
            companyTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.",ButtonType.OK).show();
            nameTF.requestFocus();
        }
    }

    @FXML
    private void otherDetailsTF_onAction(ActionEvent event) {
        String other = otherDetailsTF.getText();
        boolean matches=other.matches("(.{1,})?");
        if(matches){
            saveBtn.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Other Details.",ButtonType.OK).show();
            otherDetailsTF.requestFocus();
        }
    }

    @FXML
    private void nicTF_onAction(ActionEvent event) {
        String nic = nicTF.getText();
        boolean matches=nic.matches("[0-9]{9,11}[V]");
        if(matches){
            tel_officeTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in NIC.",ButtonType.OK).show();
            nicTF.requestFocus();
        }
    }
    
}

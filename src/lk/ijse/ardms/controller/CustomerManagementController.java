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
import lk.ijse.ardms.business.custom.CustomerManagementBO;
import lk.ijse.ardms.dto.CustomerManagementDTO;
import lk.ijse.ardms.view.util.tblmodel.CustomerManagementTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class CustomerManagementController implements Initializable {

    @FXML
    private AnchorPane customerManagementAnchPane;
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
    private JFXTextField customerIdTF;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextField nicTF;
    @FXML
    private JFXTextField contactNumberMobileTF;
    @FXML
    private JFXTextField addressVillageTF;
    @FXML
    private JFXTextField emailTF;
    @FXML
    private JFXTextField addressStreetTF;
    @FXML
    private JFXTextField addressNoTF;
    @FXML
    private JFXTextField addressCityTF;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private JFXTextField contactNumberHomeTF;
    @FXML
    private TableView<CustomerManagementTM> customerManagementTbl;
   
    CustomerManagementBO customerManagementBO = (CustomerManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMERMANAGEMENT);
    
    ObservableList<CustomerManagementTM> customerManagementTM;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        customerManagementTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        customerManagementTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        customerManagementTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        customerManagementTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nic"));
        customerManagementTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        
        generateCustomerId();
        loadAllCustomers();
        
        nameTF.requestFocus();
    }    

    @FXML
    private void loadMainDash(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.customerManagementAnchPane.getScene().getWindow();
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

    private void generateCustomerId() {
        try {
            customerIdTF.setText(customerManagementBO.generateCustomerId());
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadAllCustomers() {
        try {
            ArrayList<CustomerManagementDTO> customerManagementDTOs = customerManagementBO.getAll();
            
            customerManagementTM = customerManagementTbl.getItems();
            
            customerManagementTM.removeAll(customerManagementTM);
            
            for(CustomerManagementDTO customer : customerManagementDTOs){
                customerManagementTM.add(new CustomerManagementTM(
                        customer.getCusid(),
                        customer.getName(),
                        customer.getNic(),
                        (customer.getAddress_no()+","+customer.getAddress_street()+","+customer.getAddress_village()+","+customer.getAddress_city())
                ));
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @FXML
    private void CustomerManagementTblOnMouseClick(MouseEvent event) {
        try {
            CustomerManagementTM customerManagementTM=customerManagementTbl.getSelectionModel().getSelectedItem();
            customerIdTF.setText(customerManagementTM.getId());
            nameTF.setText(customerManagementTM.getName());
            nicTF.setText(customerManagementTM.getNic());
            
            CustomerManagementDTO customerManagementDTO=customerManagementBO.getOthers(customerIdTF.getText());
            addressNoTF.setText(customerManagementDTO.getAddress_no());
            addressStreetTF.setText(customerManagementDTO.getAddress_street());
            addressVillageTF.setText(customerManagementDTO.getAddress_village());
            addressCityTF.setText(customerManagementDTO.getAddress_city());
            contactNumberHomeTF.setText(customerManagementDTO.getTel_home());
            contactNumberMobileTF.setText(customerManagementDTO.getTel_mobile());
            emailTF.setText(customerManagementDTO.getEmail());
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void newBtn_onAction(ActionEvent event) {
        clearAll();
    }
    
    public void clearAll(){
        customerIdTF.setText("");
        nameTF.setText("");
        addressNoTF.setText("");
        addressStreetTF.setText("");
        addressVillageTF.setText("");
        addressCityTF.setText("");
        nicTF.setText("");
        contactNumberHomeTF.setText("");
        contactNumberMobileTF.setText("");
        emailTF.setText("");
        searchBoxTF.setText("");
        generateCustomerId();
    }

    @FXML
    private void saveBtn_onAction(ActionEvent event) {
        
        customerManagementTM = customerManagementTbl.getItems();
        int isAlreadyHave=0;
        for(CustomerManagementTM customer : customerManagementTM){
            if(customerIdTF.getText().equals(customer.getId()) | nicTF.getText().equals(customer.getNic()))  {
                isAlreadyHave=1;
            }
        }
        
        if(isAlreadyHave==1){
            new Alert(Alert.AlertType.ERROR,"The customer you are going to save is already in customer table.",ButtonType.OK).show();
        }else{
            try {
            String cusid=customerIdTF.getText();
            String name=nameTF.getText();
            String address_no=addressNoTF.getText();
            String address_street=addressStreetTF.getText();
            String address_village=addressVillageTF.getText();
            String address_city=addressCityTF.getText();
            String nic=nicTF.getText();
            String tel_home=contactNumberHomeTF.getText();
            String tel_mobile=contactNumberMobileTF.getText();
            String email=emailTF.getText();
            
            CustomerManagementDTO customerManagementDTO = new CustomerManagementDTO(cusid,name,address_no,address_street,address_village,address_city,nic,tel_home,tel_mobile,email);
            
            
            boolean matches1=cusid.matches("CUS[-]{1}[0-9]{5}");
            boolean matches2=name.matches("[a-z A-Z]{3,}");
            boolean matches3=address_no.matches("([0-9 A-Z]{1,})?([/]?[A-Z 0-9])?([/]?[A-Z 0-9])");
            boolean matches4=address_street.matches("[a-z A-Z 0-9]{5,}");
            boolean matches5=address_village.matches("[a-z A-Z]{5,}");
            boolean matches6=address_city.matches("([a-z A-Z]{5,})?");
            boolean matches7=nic.matches("[0-9]{9,11}[V]");
            boolean matches8=tel_home.matches("([0-9]{10})?");
            boolean matches9=tel_mobile.matches("([0-9]{10})?");
            boolean matches10=email.matches("([a-z A-Z 0-9]+[@]{1}[a-z]{3,}[.]{1}[com | org | net | biz | lk]{2,3})?");
            
            boolean result=false;
            
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
                                                    result=customerManagementBO.saveCustomer(customerManagementDTO);
                                                }else{
                                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in E-mail.",ButtonType.OK).show();
                                                    emailTF.requestFocus();
                                                }
                                            }else{
                                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Mobile.",ButtonType.OK).show();
                                                contactNumberMobileTF.requestFocus();
                                            }
                                        }else{
                                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Home.",ButtonType.OK).show();
                                            contactNumberHomeTF.requestFocus();
                                        }
                                    }else{
                                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in NIC.",ButtonType.OK).show();
                                        nicTF.requestFocus();
                                    }
                                }else{
                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address City.",ButtonType.OK).show();
                                    addressCityTF.requestFocus();
                                }
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Village.",ButtonType.OK).show();
                                addressVillageTF.requestFocus();
                            }
                        }else{
                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Street.",ButtonType.OK).show();
                            addressStreetTF.requestFocus();
                        }
                    }else{
                       new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address No.",ButtonType.OK).show();
                       addressNoTF.requestFocus(); 
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.",ButtonType.OK).show();
                    nameTF.requestFocus();
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in CustomerID.",ButtonType.OK).show();
                customerIdTF.requestFocus();
            }
            
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Customer has been saved successfully.",ButtonType.OK).show();
                loadAllCustomers();
                clearAll();
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to save the customer.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    }

    @FXML
    private void deleteBtn_onAction(ActionEvent event) {
        try {    
            CustomerManagementTM selectedCustomer = customerManagementTbl.getSelectionModel().getSelectedItem();
            
            if (selectedCustomer == null) {
                return;
            }

            boolean result = customerManagementBO.deleteCustomer(selectedCustomer.getId());
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Customer has been deleted successfully.",ButtonType.OK).show();
                loadAllCustomers();
                clearAll();
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to delete the customer.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateBtn_onAction(ActionEvent event) {
        try {
            String cusid=customerIdTF.getText();
            String name=nameTF.getText();
            String address_no=addressNoTF.getText();
            String address_street=addressStreetTF.getText();
            String address_village=addressVillageTF.getText();
            String address_city=addressCityTF.getText();
            String nic=nicTF.getText();
            String tel_home=contactNumberHomeTF.getText();
            String tel_mobile=contactNumberMobileTF.getText();
            String email=emailTF.getText();
            
            CustomerManagementDTO customerManagementDTO = new CustomerManagementDTO(cusid,name,address_no,address_street,address_village,address_city,nic,tel_home,tel_mobile,email);
            
            
            boolean matches1=cusid.matches("CUS[-]{1}[0-9]{5}");
            boolean matches2=name.matches("[a-z A-Z]{3,}");
            boolean matches3=address_no.matches("([0-9 A-Z]{1,})?([/]?[A-Z 0-9])?([/]?[A-Z 0-9])");
            boolean matches4=address_street.matches("[a-z A-Z 0-9]{5,}");
            boolean matches5=address_village.matches("[a-z A-Z]{5,}");
            boolean matches6=address_city.matches("([a-z A-Z]{5,})?");
            boolean matches7=nic.matches("[0-9]{9,11}[V]");
            boolean matches8=tel_home.matches("([0-9]{10})?");
            boolean matches9=tel_mobile.matches("([0-9]{10})?");
            boolean matches10=email.matches("([a-z A-Z 0-9]+[@]{1}[a-z]{3,}[.]{1}[com | org | net | biz | lk]{2,3})?");
            
            boolean result=false;
            
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
                                                    result=customerManagementBO.updateCustomer(customerManagementDTO);
                                                }else{
                                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in E-mail.",ButtonType.OK).show();
                                                    emailTF.requestFocus();
                                                }
                                            }else{
                                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Mobile.",ButtonType.OK).show();
                                                contactNumberMobileTF.requestFocus();
                                            }
                                        }else{
                                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Home.",ButtonType.OK).show();
                                            contactNumberHomeTF.requestFocus();
                                        }
                                    }else{
                                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in NIC.",ButtonType.OK).show();
                                        nicTF.requestFocus();
                                    }
                                }else{
                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address City.",ButtonType.OK).show();
                                    addressCityTF.requestFocus();
                                }
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Village.",ButtonType.OK).show();
                                addressVillageTF.requestFocus();
                            }
                        }else{
                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Street.",ButtonType.OK).show();
                            addressStreetTF.requestFocus();
                        }
                    }else{
                       new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address No.",ButtonType.OK).show();
                       addressNoTF.requestFocus(); 
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.",ButtonType.OK).show();
                    nameTF.requestFocus();
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in CustomerID.",ButtonType.OK).show();
                customerIdTF.requestFocus();
            }
            
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Customer has been updated successfully.",ButtonType.OK).show();
                loadAllCustomers();
                clearAll();
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to update the customer.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void searchBoxTF_keyReleased(KeyEvent event) {
        if(searchBoxTF.getText()==null){
            loadAllCustomers();
        }else{
            try {
                ArrayList<CustomerManagementDTO> customerManagementDTOs = customerManagementBO.search(searchBoxTF.getText());                
                if(customerManagementDTOs.isEmpty()){
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadAllCustomers();
                }else{
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    customerManagementTM = customerManagementTbl.getItems();
                    customerManagementTM.removeAll(customerManagementTM);
                  
                    for(CustomerManagementDTO customer : customerManagementDTOs){
                        customerManagementTM.add(new CustomerManagementTM(
                                customer.getCusid(),
                                customer.getName(),
                                customer.getNic(),
                                (customer.getAddress_no()+","+customer.getAddress_street()+","+customer.getAddress_village()+","+customer.getAddress_city())
                        ));
                    }
                    
                }   } catch (Exception ex) {
                Logger.getLogger(CustomerManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }

    @FXML
    private void searchBox_onMouseClick(MouseEvent event) {
        clearAll();
        loadAllCustomers();
    }

    @FXML
    private void nameTF_onAction(ActionEvent event) {
        String cusName = nameTF.getText();
        boolean matches=cusName.matches("[a-z A-Z]{3,}");
        if(matches){
            addressNoTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.",ButtonType.OK).show();
            nameTF.requestFocus();
        }
    }

    @FXML
    private void nicTF_onAction(ActionEvent event) {
        String nic = nicTF.getText();
        boolean matches=nic.matches("[0-9]{9,11}[V]");
        if(matches){
            contactNumberHomeTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in NIC.",ButtonType.OK).show();
            nicTF.requestFocus();
        }
    }

    @FXML
    private void contactNumberHomeTF_onAction(ActionEvent event) {
        String tel_home = contactNumberHomeTF.getText();
        boolean matches=tel_home.matches("([0-9]{10})?");
        if(matches){
            contactNumberMobileTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Home.",ButtonType.OK).show();
            contactNumberHomeTF.requestFocus();
        }
        
    }

    @FXML
    private void contactNumberMobileTF_onAction(ActionEvent event) {
        String tel_mobile = contactNumberMobileTF.getText();
        boolean matches=tel_mobile.matches("([0-9]{10})?");
        if(matches){
            emailTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Mobile.",ButtonType.OK).show();
            contactNumberMobileTF.requestFocus();
        }
        
    }

    @FXML
    private void addressVillageTF_onAction(ActionEvent event) {
        String address_village = addressVillageTF.getText();
        boolean matches=address_village.matches("[a-z A-Z]{5,}");
        if(matches){
            addressCityTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Village.",ButtonType.OK).show();
            addressVillageTF.requestFocus();
        }
    }

    @FXML
    private void emailTF_onAction(ActionEvent event) {
        String email = emailTF.getText();
        boolean matches=email.matches("([a-z A-Z 0-9]+[@]{1}[a-z]{3,}[.]{1}[com | org | net | biz | lk]{2,3})?");
        if(matches){
            saveBtn.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in E-mail.",ButtonType.OK).show();
            emailTF.requestFocus();
        }
    }

    @FXML
    private void addressStreetTF_onAction(ActionEvent event) {
        String address_street = addressStreetTF.getText();
        boolean matches=address_street.matches("[a-z A-Z 0-9]{5,}");
        if(matches){
            addressVillageTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address Street.",ButtonType.OK).show();
            addressStreetTF.requestFocus();
        }
    }

    @FXML
    private void addressNoTF_onAction(ActionEvent event) {
        String address_no = addressNoTF.getText();
        boolean matches=address_no.matches("([0-9 A-Z]{1,})?([/]?[A-Z 0-9])?([/]?[A-Z 0-9])");
        if(matches){
            addressStreetTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address No.",ButtonType.OK).show();
            addressNoTF.requestFocus();
        }
    }

    @FXML
    private void addressCityTF_onAction(ActionEvent event) {
        String address_city = addressCityTF.getText();
        boolean matches=address_city.matches("([a-z A-Z]{5,})?");
        if(matches){
            nicTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Address City.",ButtonType.OK).show();
            addressCityTF.requestFocus();
        }
    }
    
}

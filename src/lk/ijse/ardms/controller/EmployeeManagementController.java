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
import lk.ijse.ardms.business.custom.EmployeeManagementBO;
import lk.ijse.ardms.dto.EmployeeManagementDTO;
import lk.ijse.ardms.view.util.tblmodel.EmployeeManagementTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class EmployeeManagementController implements Initializable {

    @FXML
    private AnchorPane employeeManagementAnchPane;
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
    private JFXTextField employeeIdTF;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextField nicTF;
    @FXML
    private JFXTextField tel_homeTF;
    @FXML
    private JFXTextField tel_mobileTF;
    @FXML
    private JFXTextField address_villageTF;
    @FXML
    private JFXTextField address_streetTF;
    @FXML
    private JFXTextField address_noTF;
    @FXML
    private JFXTextField address_cityTF;
    @FXML
    private JFXTextField employeementTF;
    @FXML
    private JFXTextField otherDetailsTF;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<EmployeeManagementTM> employeeManagementTbl;
    
    EmployeeManagementBO employeeManagementBO=(EmployeeManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEEMANAGEMENT);
    
    ObservableList<EmployeeManagementTM> employeeManagementTM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        employeeManagementTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        employeeManagementTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("empid"));
        employeeManagementTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeManagementTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nic"));
        employeeManagementTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("employment"));
        
        loadAllEmployees();
        
        generateEmployeeId();
    }    

    @FXML
    private void loadMainDash(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.employeeManagementAnchPane.getScene().getWindow();
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

    
    private void loadAllEmployees() {
        try {
            ArrayList<EmployeeManagementDTO> employeeManagementDTOs=employeeManagementBO.getAll();
            
            employeeManagementTM = employeeManagementTbl.getItems();
            
            employeeManagementTM.removeAll(employeeManagementTM);
            
            for(EmployeeManagementDTO employee : employeeManagementDTOs){
                employeeManagementTM.add(new EmployeeManagementTM(
                        employee.getEmpid(),
                        employee.getName(),
                        employee.getNic(),
                        employee.getEmployment()
                ));
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void generateEmployeeId() {
        try {
            String newEmployeeId=employeeManagementBO.generateEmployeeId();
            employeeIdTF.setText(newEmployeeId);
        } catch (Exception ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void employeeManagementTbl_onMouseClick(MouseEvent event) {
        try {
            EmployeeManagementTM employeeManagementTM=employeeManagementTbl.getSelectionModel().getSelectedItem();
            employeeIdTF.setText(employeeManagementTM.getEmpid());
            nameTF.setText(employeeManagementTM.getName());
            nicTF.setText(employeeManagementTM.getNic());
            employeementTF.setText(employeeManagementTM.getEmployment());
            
            EmployeeManagementDTO employeeManagementDTO = employeeManagementBO.getOthers(employeeIdTF.getText());
            address_noTF.setText(employeeManagementDTO.getAddress_no());
            address_streetTF.setText(employeeManagementDTO.getAddress_street());
            address_villageTF.setText(employeeManagementDTO.getAddress_village());
            address_cityTF.setText(employeeManagementDTO.getAddress_city());
            tel_homeTF.setText(employeeManagementDTO.getTel_home());
            tel_mobileTF.setText(employeeManagementDTO.getTel_mobile());
            otherDetailsTF.setText(employeeManagementDTO.getOtherDetails());
        } catch (Exception ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void newBtn_onAction(ActionEvent event) {
        employeeIdTF.setText("");
        nameTF.setText("");
        employeementTF.setText("");
        address_noTF.setText("");
        address_streetTF.setText("");
        address_villageTF.setText("");
        address_cityTF.setText("");
        nicTF.setText("");
        tel_homeTF.setText("");
        tel_mobileTF.setText("");
        otherDetailsTF.setText("");
        searchBoxTF.setText("");
        
        generateEmployeeId();
    }
    

    @FXML
    private void saveBtn_onAction(ActionEvent event) {
        
        int isAlreadyHave=0;
        employeeManagementTM=employeeManagementTbl.getItems();
        for(EmployeeManagementTM emtm : employeeManagementTM){
            if(employeeIdTF.getText().equals(emtm.getEmpid()) | nicTF.getText().equals(emtm.getNic())){
                isAlreadyHave=1;
            }
        }
        
        if(isAlreadyHave==1){
            new Alert(Alert.AlertType.ERROR,"The employee you are going to save is already in employee table.",ButtonType.OK).show();
        }else{
            try {
                String empid=employeeIdTF.getText();
                String name=nameTF.getText();
                String employment=employeementTF.getText();
                String address_no=address_noTF.getText();
                String address_street=address_streetTF.getText();
                String address_village=address_villageTF.getText();
                String address_city=address_cityTF.getText();
                String nic=nicTF.getText();
                String tel_home=tel_homeTF.getText();
                String tel_mobile=tel_mobileTF.getText();
                String otherDetails=otherDetailsTF.getText();

                EmployeeManagementDTO employeeManagementDTO=new EmployeeManagementDTO(empid, name, employment, address_no, address_street, address_village, address_city, nic, tel_home, tel_mobile, otherDetails);

                boolean result=false;
                
                boolean matches1=name.matches("[a-z A-Z]{3,}");
                boolean matches2=employment.matches(".{3,}");
                boolean matches3=address_no.matches("([0-9 A-Z]{1,})?([/]?[A-Z 0-9])?([/]?[A-Z 0-9])");
                boolean matches4=address_street.matches("[a-z A-Z 0-9]{5,}");
                boolean matches5=address_village.matches("[a-z A-Z]{5,}");
                boolean matches6=address_city.matches("([a-z A-Z]{5,})?");
                boolean matches7=nic.matches("[0-9]{9,11}[V]");
                boolean matches8=tel_home.matches("([0-9]{10})?");
                boolean matches9=tel_mobile.matches("([0-9]{10})?");
                
                if(matches1){
                    if(matches2){
                        if(matches3){
                            if(matches4){
                                if(matches5){
                                    if(matches6){
                                        if(matches7){
                                            if(matches8){
                                                if(matches9){
                                                    result = employeeManagementBO.saveEmployee(employeeManagementDTO);
                                                }else{
                                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Mobile.",ButtonType.OK).show();
                                                    tel_mobileTF.requestFocus();
                                                }
                                            }else{
                                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Home.",ButtonType.OK).show();
                                                tel_homeTF.requestFocus();
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
                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Employment.",ButtonType.OK).show();
                        employeementTF.requestFocus();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.",ButtonType.OK).show();
                    nameTF.requestFocus();
                }
                
                if(result){
                    new Alert(Alert.AlertType.INFORMATION,"Employee has been saved successfully.",ButtonType.OK).show();
                    loadAllEmployees();
                    newBtn_onAction(event);
                }else{
                    new Alert(Alert.AlertType.ERROR,"Failed to save the employee.",ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    

    @FXML
    private void deleteBtn_onAction(ActionEvent event) {
        try {
            EmployeeManagementTM employeeManagementTM = employeeManagementTbl.getSelectionModel().getSelectedItem();
            
            boolean result=employeeManagementBO.deleteEmployee(employeeManagementTM.getEmpid());
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Employee has been deleted successfully.",ButtonType.OK).show();
                loadAllEmployees();
                newBtn_onAction(event);
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to delete the employee.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void updateBtn_onAction(ActionEvent event) {
        try {
            String empid=employeeIdTF.getText();
            String name=nameTF.getText();
            String employment=employeementTF.getText();
            String address_no=address_noTF.getText();
            String address_street=address_streetTF.getText();
            String address_village=address_villageTF.getText();
            String address_city=address_cityTF.getText();
            String nic=nicTF.getText();
            String tel_home=tel_homeTF.getText();
            String tel_mobile=tel_mobileTF.getText();
            String otherDetails=otherDetailsTF.getText();
            
            EmployeeManagementDTO employeeManagementDTO=new EmployeeManagementDTO(empid, name, employment, address_no, address_street, address_village, address_city, nic, tel_home, tel_mobile, otherDetails);
            
            boolean result=false;
                
                boolean matches1=name.matches("[a-z A-Z]{3,}");
                boolean matches2=employment.matches(".{3,}");
                boolean matches3=address_no.matches("([0-9 A-Z]{1,})?([/]?[A-Z 0-9])?([/]?[A-Z 0-9])");
                boolean matches4=address_street.matches("[a-z A-Z 0-9]{5,}");
                boolean matches5=address_village.matches("[a-z A-Z]{5,}");
                boolean matches6=address_city.matches("([a-z A-Z]{5,})?");
                boolean matches7=nic.matches("[0-9]{9,11}[V]");
                boolean matches8=tel_home.matches("([0-9]{10})?");
                boolean matches9=tel_mobile.matches("([0-9]{10})?");
                
                if(matches1){
                    if(matches2){
                        if(matches3){
                            if(matches4){
                                if(matches5){
                                    if(matches6){
                                        if(matches7){
                                            if(matches8){
                                                if(matches9){
                                                    result=employeeManagementBO.updateEmployee(employeeManagementDTO);
                                                }else{
                                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Mobile.",ButtonType.OK).show();
                                                    tel_mobileTF.requestFocus();
                                                }
                                            }else{
                                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Home.",ButtonType.OK).show();
                                                tel_homeTF.requestFocus();
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
                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Employment.",ButtonType.OK).show();
                        employeementTF.requestFocus();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.",ButtonType.OK).show();
                    nameTF.requestFocus();
                }
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Employee has been updated successfully.",ButtonType.OK).show();
                loadAllEmployees();
                newBtn_onAction(event);
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to update the employee.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    

    @FXML
    private void searchBox_onMouseClick(MouseEvent event) {
        employeeIdTF.setText("");
        nameTF.setText("");
        employeementTF.setText("");
        address_noTF.setText("");
        address_streetTF.setText("");
        address_villageTF.setText("");
        address_cityTF.setText("");
        nicTF.setText("");
        tel_homeTF.setText("");
        tel_mobileTF.setText("");
        otherDetailsTF.setText("");
        searchBoxTF.setText("");
        loadAllEmployees();
    }
    

    @FXML
    private void searchBox_onKeyReleased(KeyEvent event) {
        if(searchBoxTF.getText()==null){
            loadAllEmployees();
        }else{
            try {
                ArrayList<EmployeeManagementDTO> employeeManagementDTOs = employeeManagementBO.searchEmployee(searchBoxTF.getText());                
                if(employeeManagementDTOs.isEmpty()){
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadAllEmployees();
                }else{
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    employeeManagementTM = employeeManagementTbl.getItems();
                    employeeManagementTM.removeAll(employeeManagementTM);
                  
                    for(EmployeeManagementDTO employee : employeeManagementDTOs){
                        employeeManagementTM.add(new EmployeeManagementTM(
                                employee.getEmpid(),
                                employee.getName(),
                                employee.getNic(),
                                employee.getEmployment()
                        ));
                    }
                    
                }   } catch (Exception ex) {
                Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void nameTF_onAction(ActionEvent event) {
        String name = nameTF.getText();
        boolean matches=name.matches("[a-z A-Z]{3,}");
        if(matches){
            employeementTF.requestFocus();
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
            tel_homeTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in NIC.",ButtonType.OK).show();
            nicTF.requestFocus();
        }
    }

    @FXML
    private void tel_homeTF_onAction(ActionEvent event) {
        String tel_home = tel_homeTF.getText();
        boolean matches=tel_home.matches("([0-9]{10})?");
        if(matches){
            tel_mobileTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Contact Number Home.",ButtonType.OK).show();
            tel_homeTF.requestFocus();
        }
    }

    @FXML
    private void tel_mobileTF_onAction(ActionEvent event) {
        String tel_mobile = tel_mobileTF.getText();
        boolean matches=tel_mobile.matches("([0-9]{10})?");
        if(matches){
            otherDetailsTF.requestFocus();
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
    private void employeementTF_onAction(ActionEvent event) {
        String employment = employeementTF.getText();
        boolean matches=employment.matches(".{3,}");
        if(matches){
            address_noTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Employment.",ButtonType.OK).show();
            employeementTF.requestFocus();
        }
    }
    
}

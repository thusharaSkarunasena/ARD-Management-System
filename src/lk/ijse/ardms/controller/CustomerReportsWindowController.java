/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.ardms.business.BOFactory;
import lk.ijse.ardms.business.custom.CustomerManagementBO;
import lk.ijse.ardms.db.DBConnection;
import lk.ijse.ardms.dto.CustomerManagementDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class CustomerReportsWindowController implements Initializable {

    @FXML
    private AnchorPane customerReportsAnchPane;
    @FXML
    private JFXButton OKBtn;
    @FXML
    private JFXButton viewAllCustomersReportBtn;
    @FXML
    private JFXComboBox<String> selectCustomerComboBox;
    @FXML
    private JFXButton viewCustomCustomerReport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadSelectCustomerComboBox();
    }    

    @FXML
    private void disposeCustomerReportsWindow(ActionEvent event) {
        Stage stage = (Stage) OKBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void viewAllCustomersReportBtn_onAction(ActionEvent event) {

        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/allCustomers.jasper");
            Connection connection = DBConnection.getInstance().getConnection();
            HashMap map = new HashMap();
            JasperPrint report = JasperFillManager.fillReport(is, map, connection);
            JasperViewer.viewReport(report, false);
        } catch (JRException ex) {
            Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void selectCustomerComboBox_onAction(ActionEvent event) {
    }

    @FXML
    private void viewCustomCustomerReport_onAction(ActionEvent event) {
        if(selectCustomerComboBox.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Please select a customer to view report",ButtonType.OK).show();
            selectCustomerComboBox.requestFocus();
        }else{
            try {
                InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/customCustomer.jasper");
                Connection connection = DBConnection.getInstance().getConnection();
                HashMap map = new HashMap();
                map.put("cusid", selectCustomerComboBox.getValue());
                JasperPrint report = JasperFillManager.fillReport(is, map, connection);
                JasperViewer.viewReport(report, false);
            } catch (JRException ex) {
                Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadSelectCustomerComboBox() {
        try {
            ObservableList<String> customerIdList=FXCollections.observableArrayList();
            
            CustomerManagementBO customerManagementBO=(CustomerManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMERMANAGEMENT);
            
            ArrayList<CustomerManagementDTO> customerManagementDTOs=customerManagementBO.getAll();
            
            for(CustomerManagementDTO customerManagementDTO : customerManagementDTOs){
                customerIdList.add(customerManagementDTO.getCusid());
            }
            
            selectCustomerComboBox.setItems(customerIdList);
        } catch (Exception ex) {
            Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectCustomerComboBox_onKeyReleased(KeyEvent event) {
        try {
            CustomerManagementBO customerManagementBO=(CustomerManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMERMANAGEMENT);
            
            ObservableList<String> customerIdSearchList=FXCollections.observableArrayList();
            ObservableList<String> ifEmpty=FXCollections.observableArrayList();
            
            ArrayList<CustomerManagementDTO> customerManagementDTOs=customerManagementBO.search(selectCustomerComboBox.getValue());
            
            if(customerManagementDTOs.isEmpty()){
                selectCustomerComboBox.setItems(ifEmpty);
            }else{
                for(CustomerManagementDTO customerManagementDTO : customerManagementDTOs){
                    customerIdSearchList.add(customerManagementDTO.getCusid());
                }
                selectCustomerComboBox.setItems(customerIdSearchList);
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

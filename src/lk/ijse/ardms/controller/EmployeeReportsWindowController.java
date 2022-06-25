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
import lk.ijse.ardms.business.custom.EmployeeManagementBO;
import lk.ijse.ardms.db.DBConnection;
import lk.ijse.ardms.dto.EmployeeManagementDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class EmployeeReportsWindowController implements Initializable {

    @FXML
    private AnchorPane employeeReportsAnchPane;
    @FXML
    private JFXButton OKBtn;
    @FXML
    private JFXButton viewAllEmployeesReportBtn;
    @FXML
    private JFXComboBox<String> selectEmployeeComboBox;
    @FXML
    private JFXButton viewCustomEmployeeReport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadSelectEmployeeComboBox();
    }    

    @FXML
    private void okBtn_onAction(ActionEvent event) {
        Stage stage=(Stage) OKBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void viewAllEmployeesReportBtn_onAction(ActionEvent event) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/allEmployees.jasper");
            Connection connection = DBConnection.getInstance().getConnection();
            HashMap map = new HashMap();
            JasperPrint report = JasperFillManager.fillReport(is, map, connection);
            JasperViewer.viewReport(report, false);
        } catch (JRException ex) {
            Logger.getLogger(EmployeeReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EmployeeReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }

    @FXML
    private void selectEmployeeComboBox_onAction(ActionEvent event) {
    }

    @FXML
    private void selectEmployeeComboBox_onKeyReleased(KeyEvent event) {
        try {
            EmployeeManagementBO employeeManagementBO=(EmployeeManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEEMANAGEMENT);
            
            ObservableList<String> employeeIdSearchList=FXCollections.observableArrayList();
            ObservableList<String> ifEmpty=FXCollections.observableArrayList();
            
            ArrayList<EmployeeManagementDTO> employeeManagementDTOs=employeeManagementBO.searchEmployee(selectEmployeeComboBox.getValue());
            
            if(employeeManagementDTOs.isEmpty()){
                selectEmployeeComboBox.setItems(ifEmpty);
            }else{
                for(EmployeeManagementDTO employeeManagementDTO : employeeManagementDTOs){
                    employeeIdSearchList.add(employeeManagementDTO.getEmpid());
                }
                selectEmployeeComboBox.setItems(employeeIdSearchList);
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewCustomEmployeeReport_onAction(ActionEvent event) {
        if(selectEmployeeComboBox.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Please select a employee to view report",ButtonType.OK).show();
            selectEmployeeComboBox.requestFocus();
        }else{
            try {
                InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/customEmployee.jasper");
                Connection connection = DBConnection.getInstance().getConnection();
                HashMap map = new HashMap();
                map.put("empid", selectEmployeeComboBox.getValue());
                JasperPrint report = JasperFillManager.fillReport(is, map, connection);
                JasperViewer.viewReport(report, false);
            } catch (JRException ex) {
                Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadSelectEmployeeComboBox() {
        try {
            ObservableList<String> employeeIdList=FXCollections.observableArrayList();
            
            EmployeeManagementBO employeeManagementBO=(EmployeeManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEEMANAGEMENT);
            
            ArrayList<EmployeeManagementDTO> employeeManagementDTOs=employeeManagementBO.getAll();
            
            for(EmployeeManagementDTO employeeManagementDTO : employeeManagementDTOs){
                employeeIdList.add(employeeManagementDTO.getEmpid());
            }
            
            selectEmployeeComboBox.setItems(employeeIdList);
        } catch (Exception ex) {
            Logger.getLogger(EmployeeReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

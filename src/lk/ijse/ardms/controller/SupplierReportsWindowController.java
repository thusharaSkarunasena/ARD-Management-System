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
import lk.ijse.ardms.business.custom.SupplierManagementBO;
import lk.ijse.ardms.db.DBConnection;
import lk.ijse.ardms.dto.SupplierManagementDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class SupplierReportsWindowController implements Initializable {

    @FXML
    private AnchorPane supplierReportsAnchPane;
    @FXML
    private JFXButton OKBtn;
    @FXML
    private JFXButton viewAllSuppliersReportBtn;
    @FXML
    private JFXComboBox<String> selectSupplierComboBox;
    @FXML
    private JFXButton viewCustomSupplierReportBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadSelectSupplierComboBox();
    }    

    @FXML
    private void OkBtn_onAction(ActionEvent event) {
        Stage stage=(Stage) OKBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void viewAllSuppliersReportBtn_onAction(ActionEvent event) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/allSuppliers.jasper");
            Connection connection = DBConnection.getInstance().getConnection();
            HashMap map = new HashMap();
            JasperPrint report = JasperFillManager.fillReport(is, map, connection);
            JasperViewer.viewReport(report, false);
        } catch (JRException ex) {
            Logger.getLogger(SupplierReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SupplierReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectSupplierComboBox_onAction(ActionEvent event) {
    }

    @FXML
    private void selectSupplierComboBox_onKeyReleased(KeyEvent event) {
        try {
            SupplierManagementBO supplierManagementBO=(SupplierManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIERMANAGEMENT);
            
            ObservableList<String> suppplierIdSearchList=FXCollections.observableArrayList();
            ObservableList<String> ifEmpty=FXCollections.observableArrayList();
            
            ArrayList<SupplierManagementDTO> supplierManagementDTOs=supplierManagementBO.searchSupplier(selectSupplierComboBox.getValue());
            
            if(supplierManagementDTOs.isEmpty()){
                selectSupplierComboBox.setItems(ifEmpty);
            }else{
                for(SupplierManagementDTO supplierManagementDTO : supplierManagementDTOs){
                    suppplierIdSearchList.add(supplierManagementDTO.getSupid());
                }
                selectSupplierComboBox.setItems(suppplierIdSearchList);
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewCustomSupplierReportBtn_onAction(ActionEvent event) {
        if(selectSupplierComboBox.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Please select a supplier to view report",ButtonType.OK).show();
            selectSupplierComboBox.requestFocus();
        }else{
            try {
                InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/customSupplier.jasper");
                Connection connection = DBConnection.getInstance().getConnection();
                HashMap map = new HashMap();
                map.put("supid", selectSupplierComboBox.getValue());
                JasperPrint report = JasperFillManager.fillReport(is, map, connection);
                JasperViewer.viewReport(report, false);
            } catch (JRException ex) {
                Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadSelectSupplierComboBox() {
        try {
            ObservableList<String> SupplierIdList=FXCollections.observableArrayList();
            
            SupplierManagementBO supplierManagementBO=(SupplierManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIERMANAGEMENT);
            
            ArrayList<SupplierManagementDTO> supplierManagementDTOs=supplierManagementBO.getAll();
            
            for(SupplierManagementDTO supplierManagementDTO : supplierManagementDTOs){
                SupplierIdList.add(supplierManagementDTO.getSupid());
            }
            
            selectSupplierComboBox.setItems(SupplierIdList);
        } catch (Exception ex) {
            Logger.getLogger(CustomerReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

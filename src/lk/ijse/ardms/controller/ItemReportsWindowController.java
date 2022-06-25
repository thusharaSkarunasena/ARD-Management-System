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
import lk.ijse.ardms.business.custom.DamagedItemBO;
import lk.ijse.ardms.business.custom.ItemBrandBO;
import lk.ijse.ardms.business.custom.ItemManagementBO;
import lk.ijse.ardms.db.DBConnection;
import lk.ijse.ardms.dto.DamagedItemDTO;
import lk.ijse.ardms.dto.ItemBrandDTO;
import lk.ijse.ardms.dto.ItemManagementDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class ItemReportsWindowController implements Initializable {

    @FXML
    private AnchorPane itemReportsAnchPane;
    @FXML
    private JFXButton OKBtn;
    @FXML
    private JFXComboBox<String> selectItemComboBox;
    @FXML
    private JFXButton viewCustomItemReportBtn;
    @FXML
    private JFXButton viewAllItemsReportBtn;
    @FXML
    private JFXComboBox<String> selectDamagedItemComboBox;
    @FXML
    private JFXButton viewCustomDamagedItemReportBtn;
    @FXML
    private JFXButton viewAllDamagedItemsReportBtn;
    @FXML
    private JFXComboBox<String> selectitemBrandsComboBox;
    @FXML
    private JFXButton viewCustomItemBrandsReportBtn;
    @FXML
    private JFXButton viewAllItemBrandsReportBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadSelectItemComboBox();
        loadSelectDamageItemComboBox();
        loadSelectItemBrandComboBox();
    }    

    @FXML
    private void okBtn_onAction(ActionEvent event) {
        Stage stage=(Stage) OKBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void selectItemComboBox_onAction(ActionEvent event) {
    }

    @FXML
    private void selectitemComboBox_onKeyReleased(KeyEvent event) {
        try {
            ItemManagementBO itemManagementBO=(ItemManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMMANAGEMENT);
            
            ObservableList<String> itemCodeSearchList=FXCollections.observableArrayList();
            ObservableList<String> ifEmpty=FXCollections.observableArrayList();
            
            ArrayList<ItemManagementDTO> itemManagementDTOs = itemManagementBO.searchItems(selectItemComboBox.getValue());
            
            if(itemManagementDTOs.isEmpty()){
                selectItemComboBox.setItems(ifEmpty);
                
            }else{
                for(ItemManagementDTO itemManagementDTO : itemManagementDTOs){
                    itemCodeSearchList.add(itemManagementDTO.getItemcode());
                }
                selectItemComboBox.setItems(itemCodeSearchList);
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewCustomItemReportBtn_onAction(ActionEvent event) {
        if(selectItemComboBox.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Please select a Item to view report",ButtonType.OK).show();
            selectItemComboBox.requestFocus();
        }else{
            try {
                InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/customItem.jasper");
                Connection connection = DBConnection.getInstance().getConnection();
                HashMap map = new HashMap();
                map.put("itemcode", selectItemComboBox.getValue());
                JasperPrint report = JasperFillManager.fillReport(is, map, connection);
                JasperViewer.viewReport(report, false);
            } catch (JRException ex) {
                Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void viewAllItemReportBtn_onAction(ActionEvent event) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/allItems.jasper");
            Connection connection = DBConnection.getInstance().getConnection();
            HashMap map = new HashMap();
            JasperPrint report = JasperFillManager.fillReport(is, map, connection);
            JasperViewer.viewReport(report, false);
        } catch (JRException ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectDamagedItemComboBox_onAction(ActionEvent event) {
    }

    @FXML
    private void selectDamagedItemComboBox_onKeyReleased(KeyEvent event) {
        try {
            DamagedItemBO damagedItemBO=(DamagedItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DAMAGEDITEM);
            
            ObservableList<String> DamagedItemCodeSearchList=FXCollections.observableArrayList();
            ObservableList<String> ifEmpty=FXCollections.observableArrayList();
            
            ArrayList<DamagedItemDTO> damagedItemDTOs = damagedItemBO.searchDamagedItems(selectDamagedItemComboBox.getValue());
            
            if(damagedItemDTOs.isEmpty()){
                selectDamagedItemComboBox.setItems(ifEmpty);
                
            }else{
                for(DamagedItemDTO damagedItemDTO : damagedItemDTOs){
                    DamagedItemCodeSearchList.add(damagedItemDTO.getItemcode());
                }
                selectDamagedItemComboBox.setItems(DamagedItemCodeSearchList);
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewCustomDamagedItemReportBtn_onAction(ActionEvent event) {
        if(selectDamagedItemComboBox.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Please select a Damaged Item to view report",ButtonType.OK).show();
            selectDamagedItemComboBox.requestFocus();
        }else{
            try {
                InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/customDamagedItem.jasper");
                Connection connection = DBConnection.getInstance().getConnection();
                HashMap map = new HashMap();
                map.put("itemcode", selectDamagedItemComboBox.getValue());
                JasperPrint report = JasperFillManager.fillReport(is, map, connection);
                JasperViewer.viewReport(report, false);
            } catch (JRException ex) {
                Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void viewAllDamagedItemReportBtn_onAction(ActionEvent event) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/allDamagedItems.jasper");
            Connection connection = DBConnection.getInstance().getConnection();
            HashMap map = new HashMap();
            JasperPrint report = JasperFillManager.fillReport(is, map, connection);
            JasperViewer.viewReport(report, false);
        } catch (JRException ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectitemBrandsComboBox_onAction(ActionEvent event) {
    }

    @FXML
    private void selectItemBrandsComboBox_onKeyReleased(KeyEvent event) {
        try {
            ItemBrandBO itemBrandBO=(ItemBrandBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMBRAND);
            
            ObservableList<String> itemBrandSearchList=FXCollections.observableArrayList();
            ObservableList<String> ifEmpty=FXCollections.observableArrayList();
            
            ArrayList<ItemBrandDTO> itemBrandDTOs = itemBrandBO.searchBrand(selectitemBrandsComboBox.getValue());
            
            if(itemBrandDTOs.isEmpty()){
                selectitemBrandsComboBox.setItems(ifEmpty);
                
            }else{
                for(ItemBrandDTO itemBrandDTO : itemBrandDTOs){
                    itemBrandSearchList.add(itemBrandDTO.getBraId());
                }
                selectitemBrandsComboBox.setItems(itemBrandSearchList);
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewAllItemBrandsReportBtn_onAction(ActionEvent event) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/allItemBrands.jasper");
            Connection connection = DBConnection.getInstance().getConnection();
            HashMap map = new HashMap();
            JasperPrint report = JasperFillManager.fillReport(is, map, connection);
            JasperViewer.viewReport(report, false);
        } catch (JRException ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSelectItemComboBox() {
        try {
            ObservableList<String> itemCodeList = FXCollections.observableArrayList();
            
            ItemManagementBO itemManagementBO=(ItemManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMMANAGEMENT);
            
            ArrayList<ItemManagementDTO> itemManagementDTOs=itemManagementBO.getAll();
            
            for(ItemManagementDTO itemManagementDTO : itemManagementDTOs){
                itemCodeList.add(itemManagementDTO.getItemcode());
            }
            selectItemComboBox.setItems(itemCodeList);
        } catch (Exception ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSelectDamageItemComboBox() {
        try {
            ObservableList<String> itemCodeList = FXCollections.observableArrayList();
            
            DamagedItemBO damagedItemBO=(DamagedItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DAMAGEDITEM);
            
            ArrayList<DamagedItemDTO> damagedItemDTOs=damagedItemBO.getAllDamagedItem();
            
            for(DamagedItemDTO damagedItemDTO : damagedItemDTOs){
                itemCodeList.add(damagedItemDTO.getItemcode());
            }
            selectDamagedItemComboBox.setItems(itemCodeList);
        } catch (Exception ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSelectItemBrandComboBox() {
        try {
            ObservableList<String> itemBrandList = FXCollections.observableArrayList();
            
            ItemBrandBO itemBrandBO=(ItemBrandBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMBRAND);
            
            ArrayList<ItemBrandDTO> itemBrandDTOs=itemBrandBO.getAll();
            
            for(ItemBrandDTO itemBrandDTO : itemBrandDTOs){
                itemBrandList.add(itemBrandDTO.getBraId());
            }
            selectitemBrandsComboBox.setItems(itemBrandList);
        } catch (Exception ex) {
            Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewCustomItemBrandsReportBtn_onAction(ActionEvent event) {
        if(selectitemBrandsComboBox.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Please select a Brand to view report",ButtonType.OK).show();
            selectitemBrandsComboBox.requestFocus();
        }else{
            try {
                InputStream is = this.getClass().getResourceAsStream("/lk/ijse/ardms/reports/customItemBrand.jasper");
                Connection connection = DBConnection.getInstance().getConnection();
                HashMap map = new HashMap();
                map.put("braid", selectitemBrandsComboBox.getValue());
                JasperPrint report = JasperFillManager.fillReport(is, map, connection);
                JasperViewer.viewReport(report, false);
            } catch (JRException ex) {
                Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ItemReportsWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

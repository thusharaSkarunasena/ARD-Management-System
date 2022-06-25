/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import lk.ijse.ardms.business.custom.ItemManagementBO;
import lk.ijse.ardms.dto.ItemManagementDTO;
import lk.ijse.ardms.view.util.tblmodel.DamagedItemTM;
import lk.ijse.ardms.view.util.tblmodel.ItemsInDamageItemTM;
import lk.ijse.ardms.business.custom.DamagedItemBO;
import lk.ijse.ardms.dto.DamagedItemDTO;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class DamagedItemsController implements Initializable {

    @FXML
    private AnchorPane damagedItemsAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox backHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private JFXButton newBtn;
    @FXML
    private JFXButton addBtn;
    @FXML
    private JFXButton restoreBtn;
    @FXML
    private JFXButton removeBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXTextField itemcodeTF;
    @FXML
    private JFXTextField itemNameTF;
    @FXML
    private JFXTextField qtyTF;
    @FXML
    private JFXTextArea detailsTF;
    @FXML
    private TextField itemSearchBoxTF;
    @FXML
    private TableView<ItemsInDamageItemTM> itemTbl;
    @FXML
    private TextField damagedItemsSearchBoxTF;
    @FXML
    private TableView<DamagedItemTM> damagedItemsTbl;

    ObservableList<DamagedItemTM> damagedItemTM;
    
    DamagedItemBO damagedItemBO = (DamagedItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DAMAGEDITEM);
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        itemTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        itemTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        itemTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        
        damagedItemsTbl.getColumns().get(0).setStyle("-fx-alignment :center");
        damagedItemsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        damagedItemsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        damagedItemsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("size"));
        damagedItemsTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("category"));
        damagedItemsTbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        damagedItemsTbl.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("details"));
        
        loadAllItems();
        loadAllDamageItems();
    }

    @FXML
    private void loadMainDash(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.damagedItemsAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(+temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void loadManageItems(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/itemManagement.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.damagedItemsAnchPane.getScene().getWindow();
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

    private void loadAllItems() {
        try {
            ItemManagementBO itemManagementBO=(ItemManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMMANAGEMENT);
            
            ArrayList<ItemManagementDTO> itemManagementDTOs=itemManagementBO.getAll();
            
            ObservableList<ItemsInDamageItemTM> itemsInDamageItemTM;
            
            itemsInDamageItemTM = itemTbl.getItems();
            
            itemsInDamageItemTM.removeAll(itemsInDamageItemTM);
            
            for(ItemManagementDTO item : itemManagementDTOs){
                itemsInDamageItemTM.add(new ItemsInDamageItemTM(
                        item.getItemcode(),
                        item.getName()
                ));
            }
        } catch (Exception ex) {
            Logger.getLogger(DamagedItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllDamageItems() {
        try {
            ArrayList<DamagedItemDTO> damagedItemDTOs = damagedItemBO.getAllDamagedItem();
            
            damagedItemTM = damagedItemsTbl.getItems();
            
            damagedItemTM.removeAll(damagedItemTM);
            
            for(DamagedItemDTO damagedItem : damagedItemDTOs){
                damagedItemTM.add(new DamagedItemTM(
                        damagedItem.getItemcode(),
                        damagedItem.getName(),
                        damagedItem.getSize(),
                        damagedItem.getCategory(),
                        damagedItem.getQty(),
                        damagedItem.getDetails()
                ));
            }
        } catch (Exception ex) {
            Logger.getLogger(DamagedItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void newBtn_onAction(ActionEvent event) {
        clearAll();
    }

    public void clearAll(){
        itemSearchBoxTF.setText("");
        itemcodeTF.setText("");
        itemNameTF.setText("");
        qtyTF.setText("");
        detailsTF.setText("");
        damagedItemsSearchBoxTF.setText("");
    }
    
    @FXML
    private void addBtn_onAction(ActionEvent event) {
                    damagedItemTM = damagedItemsTbl.getItems();
        
            int isAlreadyHave=0;
            for(DamagedItemTM damageItem : damagedItemTM){
                if(itemcodeTF.getText() == null ? damageItem.getItemcode() == null : itemcodeTF.getText().equals(damageItem.getItemcode())){
                    isAlreadyHave=1;
                }
            }
                
            if(isAlreadyHave==1){
                new Alert(Alert.AlertType.ERROR,"This Item is Already added to Damage Item Table.",ButtonType.OK).show();;
            }else{
                try {
                String itemcode=itemcodeTF.getText();
                int qty=Integer.parseInt(qtyTF.getText());
                String details=detailsTF.getText();
            
                DamagedItemDTO damagedItemDTO =new DamagedItemDTO();
                damagedItemDTO.setItemcode(itemcode);
                damagedItemDTO.setQty(qty);
                damagedItemDTO.setDetails(details);
            
                boolean result=damagedItemBO.add(damagedItemDTO);
            
                if(result){
                    new Alert(Alert.AlertType.INFORMATION,"Damaged Item has been added successfully.",ButtonType.OK).show();
                    loadAllItems();
                    loadAllDamageItems();
                    clearAll();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Failed to add the damage item.",ButtonType.OK).show();
                }
                } catch (Exception ex) {
                    Logger.getLogger(DamagedItemsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

    }

    @FXML
    private void removeBtn_onAction(ActionEvent event) {
        try {
            DamagedItemTM ditem = damagedItemsTbl.getSelectionModel().getSelectedItem();
            
            boolean result=damagedItemBO.remove(ditem.getItemcode());
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Damaged Item has been removed successfully.",ButtonType.OK).show();
                loadAllDamageItems();
                clearAll();
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to remove the damaged item.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(DamagedItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateBtn_onAction(ActionEvent event) {
        try {
                DamagedItemDTO damagedItemDTO=new DamagedItemDTO();
                damagedItemDTO.setItemcode(itemcodeTF.getText());
                damagedItemDTO.setName(itemNameTF.getText());
                damagedItemDTO.setQty(Integer.parseInt(qtyTF.getText()));
                damagedItemDTO.setDetails(detailsTF.getText());
                
                boolean result=damagedItemBO.update(damagedItemDTO);
                
                if(result){
                    new Alert(Alert.AlertType.INFORMATION,"Damaged Item has been updated successfully.",ButtonType.OK).show();
                    loadAllDamageItems();
                    clearAll();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Failed to update the damaged item",ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(DamagedItemsController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void restoreBtn_onAction(ActionEvent event) {
        try {
            DamagedItemTM damagedItemTM=damagedItemsTbl.getSelectionModel().getSelectedItem();
            
            boolean result = damagedItemBO.restore(damagedItemTM.getItemcode());
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Damaged Items have been restored to items successfully.",ButtonType.OK).show();
                loadAllDamageItems();
                loadAllItems();
                clearAll();
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to restore the damaged items",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(DamagedItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void itemSearchBox_onKeyReleased(KeyEvent event) {
        
        ItemManagementBO itemManagementBO=(ItemManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMMANAGEMENT);
        
        if(itemSearchBoxTF.getText()==null){
            loadAllItems();
        }else{
            try {
                ArrayList<ItemManagementDTO> itemManagementDTOs = itemManagementBO.searchItems(itemSearchBoxTF.getText());
                if(itemManagementDTOs.isEmpty()){
                    itemSearchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadAllItems();
                }else{
                    itemSearchBoxTF.setStyle("-fx-text-fill: #000000");
                    ObservableList<ItemsInDamageItemTM> itemsInDamageItemTM;
                    itemsInDamageItemTM = itemTbl.getItems();
                    itemsInDamageItemTM.removeAll(itemsInDamageItemTM);
                    
                    for(ItemManagementDTO itemdto : itemManagementDTOs){
                        itemsInDamageItemTM.add(new ItemsInDamageItemTM(
                                itemdto.getItemcode(),
                                itemdto.getName()
                        ));
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(DamagedItemsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    private void itemTbl_onMouseClicked(MouseEvent event) {
        clearAll();
        ItemsInDamageItemTM itemsInDamageItemTM=itemTbl.getSelectionModel().getSelectedItem();
        itemcodeTF.setText(itemsInDamageItemTM.getItemcode());
        itemNameTF.setText(itemsInDamageItemTM.getName());
    }


    @FXML
    private void damageItemsSearchBox_onKeyReleased(KeyEvent event) {
        if(damagedItemsSearchBoxTF.getText()==null){
            loadAllDamageItems();
        }else{
            try {
                ArrayList<DamagedItemDTO> damagedItemDTOs = damagedItemBO.searchDamagedItems(damagedItemsSearchBoxTF.getText());
                if(damagedItemDTOs.isEmpty()){
                    damagedItemsSearchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadAllDamageItems();
                }else{
                    damagedItemsSearchBoxTF.setStyle("-fx-text-fill: #000000");
                    ObservableList<DamagedItemTM> damagedItemTM;
                    damagedItemTM = damagedItemsTbl.getItems();
                    damagedItemTM.removeAll(damagedItemTM);
                    
                    for(DamagedItemDTO ditemdto : damagedItemDTOs){
                        damagedItemTM.add(new DamagedItemTM(
                                ditemdto.getItemcode(),
                                ditemdto.getName(),
                                ditemdto.getSize(),
                                ditemdto.getCategory(),
                                ditemdto.getQty(),
                                ditemdto.getDetails()
                        ));
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(DamagedItemsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void damageItemsTbl_onMouseClick(MouseEvent event) {
        clearAll();
        DamagedItemTM damagedItemTM=damagedItemsTbl.getSelectionModel().getSelectedItem();
        itemcodeTF.setText(damagedItemTM.getItemcode());
        itemNameTF.setText(damagedItemTM.getName());
        qtyTF.setText(Integer.toString(damagedItemTM.getQty()));
        detailsTF.setText(damagedItemTM.getDetails());
    }

    @FXML
    private void itemSearchBox_onAMouseClicked(MouseEvent event) {
        clearAll();
        loadAllItems();
        loadAllDamageItems();
    }

    @FXML
    private void damageItemsSearchBox_onMouseClicked(MouseEvent event) {
        clearAll();
        loadAllItems();
        loadAllDamageItems();
    }

    @FXML
    private void itemcodeTF_onMouseClicked(MouseEvent event) {
        new Alert(Alert.AlertType.INFORMATION,"Sorry... You can't change item code here.",ButtonType.OK).show();
        newBtn.requestFocus();
    }

    @FXML
    private void itemNameTF_onMouseClicked(MouseEvent event) {
        new Alert(Alert.AlertType.INFORMATION,"Sorry... You can't change item name here.",ButtonType.OK).show();
        newBtn.requestFocus();
    }

    

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import lk.ijse.ardms.business.custom.ItemManagementBO;
import lk.ijse.ardms.dto.ItemBrandDTO;
import lk.ijse.ardms.dto.ItemCategoryDTO;
import lk.ijse.ardms.dto.ItemManagementDTO;
import lk.ijse.ardms.dto.SupplierManagementDTO;
import lk.ijse.ardms.view.util.tblmodel.ItemManagementTM;
import org.jfree.chart.block.Arrangement;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class ItemManagementController implements Initializable {

    @FXML
    private AnchorPane itemManagementAnchPane;
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
    private HBox damagedItemsHBox;
    @FXML
    private HBox categoryHBox;
    @FXML
    private HBox brandHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private JFXTextField itemCodeTF;
    @FXML
    private JFXTextField descriptionTF;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextField sizeTF;
    @FXML
    private JFXTextField priceTF;
    @FXML
    private JFXComboBox<String> categoryComBox;
    @FXML
    private JFXComboBox<String> brandComBox;
    @FXML
    private JFXComboBox<String> supplierComBox;
    @FXML
    private JFXTextField qtyTF;
    @FXML
    private JFXTextArea otherTA;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<ItemManagementTM> itemManagementTbl;
    ItemManagementBO itemManagementBO = (ItemManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMMANAGEMENT);
    
    ObservableList<ItemManagementTM> itemManagementTM;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        itemManagementTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        itemManagementTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        itemManagementTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        itemManagementTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("size"));
        itemManagementTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("catagory"));
        itemManagementTbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));
        itemManagementTbl.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("qty"));
        
        loadAllItems();
        
        generateItemCode();
        
        loadCategoryComboBox();
        loadBrandComboBox();
        loadSupplierComboBox();
        
    }    

    @FXML
    private void loadMainDash(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.itemManagementAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(+temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void loadDamagedItems(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/damagedItems.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.itemManagementAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(-temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void loadItemCategory(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/itemCategory.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.itemManagementAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(-temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void loadItemBrand(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/itemBrand.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.itemManagementAnchPane.getScene().getWindow();
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

    
    private void loadAllItems() {
        try {
            ArrayList<ItemManagementDTO> itemManagementDTOs = itemManagementBO.getAll();
            
            itemManagementTM = itemManagementTbl.getItems();
            
            itemManagementTM.removeAll(itemManagementTM);
            
            for(ItemManagementDTO item : itemManagementDTOs){
                itemManagementTM.add(new ItemManagementTM(
                        item.getItemcode(),
                        item.getName(),
                        item.getSize(),
                        item.getCatName(),
                        item.getPrice(),
                        item.getQty()
                ));
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void generateItemCode() {
        try {
            String newItemCode=itemManagementBO.generateItemCode();
            
            itemCodeTF.setText(newItemCode);
        } catch (Exception ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void itemManagementTbl_onMouseClicked(MouseEvent event) {
        try {
            ItemManagementTM itemManagementTM=itemManagementTbl.getSelectionModel().getSelectedItem();
            itemCodeTF.setText(itemManagementTM.getItemcode());
            
            ItemManagementDTO itemManagementDTO=itemManagementBO.getOthers(itemCodeTF.getText());
            nameTF.setText(itemManagementDTO.getName());
            descriptionTF.setText(itemManagementDTO.getDescription());
            sizeTF.setText(itemManagementDTO.getSize());
            priceTF.setText(Double.toString(itemManagementDTO.getPrice()));
            qtyTF.setText(Integer.toString(itemManagementDTO.getQty()));
            categoryComBox.setValue(itemManagementDTO.getCatName());
            brandComBox.setValue(itemManagementDTO.getBraName());
            supplierComBox.setValue(itemManagementDTO.getSupName());
            otherTA.setText(itemManagementDTO.getOther_details());
        } catch (Exception ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void newBtn_onAction(ActionEvent event) {
        itemCodeTF.setText("");
        nameTF.setText("");
        descriptionTF.setText("");
        sizeTF.setText("");
        priceTF.setText("");
        qtyTF.setText("");
        categoryComBox.setValue("");
        brandComBox.setValue("");
        supplierComBox.setValue("");
        otherTA.setText("");
        searchBoxTF.setText("");
        
        generateItemCode();
    }

    
    @FXML
    private void saveBtn_onAction(ActionEvent event) {
        
        int isAlreadyHave=0;
        itemManagementTM=itemManagementTbl.getItems();
        for(ItemManagementTM imtm : itemManagementTM){
            if(itemCodeTF.getText().equals(imtm.getItemcode()) | nameTF.getText().equals(imtm.getName())){
                isAlreadyHave=1;
            }
        }
        
        if(isAlreadyHave==1){
            new Alert(Alert.AlertType.ERROR,"The item you are going to save is already in item table.",ButtonType.OK).show();
        }
        else{
            try {
                String itemcode=itemCodeTF.getText();
                String catName=categoryComBox.getValue();
                String braName=brandComBox.getValue();
                String supName=supplierComBox.getValue();
                String name=nameTF.getText();
                String description=descriptionTF.getText();
                String size=sizeTF.getText();
                double price=Double.parseDouble(priceTF.getText());
                String priceS=priceTF.getText();
                int qty=Integer.parseInt(qtyTF.getText());
                String qtyS=qtyTF.getText();
                String other=otherTA.getText();

                ItemManagementDTO itemManagementDTO = new ItemManagementDTO(itemcode, catName, braName, supName, name, description, size, price, qty, other);

                boolean result=false;
                
                boolean matches1=name.matches("(.{1,})?");
                boolean matches2=description.matches("(.{1,})?");
                boolean matches3=size.matches(".{1,}");
                boolean matches4=priceS.matches("[0-9]{1,}");
                boolean matches5=qtyS.matches("[0-9]{1,}");
                boolean matches6=other.matches("(.{1,})?");
                
                if(matches1){
                    if(matches2){
                        if(matches3){
                            if(matches4){
                                if(matches5){
                                    if(categoryComBox.getValue()!=null){
                                        if(brandComBox.getValue()!=null){
                                            if(supplierComBox.getValue()!=null){
                                                if(matches6){
                                                    result = itemManagementBO.saveItem(itemManagementDTO);
                                                }else{
                                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Other.",ButtonType.OK).show();
                                                    otherTA.requestFocus();
                                                }
                                            }else{
                                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Supplier.",ButtonType.OK).show();
                                                supplierComBox.requestFocus();
                                            }
                                        }else{
                                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Brand.",ButtonType.OK).show();
                                            brandComBox.requestFocus();
                                        }
                                    }else{
                                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Ctegory.",ButtonType.OK).show();
                                        categoryComBox.requestFocus();
                                    }
                                }else{
                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Qty.").show();
                                    qtyTF.requestFocus();
                                }
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Price.").show();
                                priceTF.requestFocus();
                            }
                        }else{
                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Size.").show();
                            sizeTF.requestFocus();
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Description.").show();
                        descriptionTF.requestFocus();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.").show();
                    nameTF.requestFocus();
                }

                if(result){
                    new Alert(Alert.AlertType.INFORMATION,"Item has been saved successfully.",ButtonType.OK).show();
                    loadAllItems();
                    newBtn_onAction(event);
                }else{
                    new Alert(Alert.AlertType.ERROR,"Failed to save the item.",ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }

    
    @FXML
    private void deleteBtn_onAction(ActionEvent event) {
        
        try {
            ItemManagementTM itemManagementTM=itemManagementTbl.getSelectionModel().getSelectedItem();
            
            boolean result=itemManagementBO.deleteItem(itemManagementTM.getItemcode());
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Item has been deleted successfully.",ButtonType.OK).show();
                loadAllItems();
                newBtn_onAction(event);
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to delete the item.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void updateBtn_onAction(ActionEvent event) {
        try {
            String itemcode=itemCodeTF.getText();
            String catName=categoryComBox.getValue();
            String braName=brandComBox.getValue();
            String supName=supplierComBox.getValue();
            String name=nameTF.getText();
            String description=descriptionTF.getText();
            String size=sizeTF.getText();
            double price=Double.parseDouble(priceTF.getText());
            String priceS=priceTF.getText();
            int qty=Integer.parseInt(qtyTF.getText());
            String qtyS=qtyTF.getText();
            String other=otherTA.getText();
            
            ItemManagementDTO itemManagementDTO = new ItemManagementDTO(itemcode, catName, braName, supName, name, description, size, price, qty, other);
            
            boolean result=false;
            
            boolean matches1=name.matches("(.{1,})?");
            boolean matches2=description.matches("(.{1,})?");
            boolean matches3=size.matches(".{1,}");
            boolean matches4=priceS.matches("[0-9]{1,}");
            boolean matches5=qtyS.matches("[0-9]{1,}");
            boolean matches6=other.matches("(.{1,})?");
            
            if(matches1){
                    if(matches2){
                        if(matches3){
                            if(matches4){
                                if(matches5){
                                    if(categoryComBox.getValue()!=null){
                                        if(brandComBox.getValue()!=null){
                                            if(supplierComBox.getValue()!=null){
                                                if(matches6){
                                                    result = itemManagementBO.updateItem(itemManagementDTO);
                                                }else{
                                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Other.",ButtonType.OK).show();
                                                    otherTA.requestFocus();
                                                }
                                            }else{
                                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Supplier.",ButtonType.OK).show();
                                                supplierComBox.requestFocus();
                                            }
                                        }else{
                                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Brand.",ButtonType.OK).show();
                                            brandComBox.requestFocus();
                                        }
                                    }else{
                                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Ctegory.",ButtonType.OK).show();
                                        categoryComBox.requestFocus();
                                    }
                                }else{
                                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Qty.").show();
                                    qtyTF.requestFocus();
                                }
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Price.").show();
                                priceTF.requestFocus();
                            }
                        }else{
                            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Size.").show();
                            sizeTF.requestFocus();
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Description.").show();
                        descriptionTF.requestFocus();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.").show();
                    nameTF.requestFocus();
                }
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Item has been updated successfully.",ButtonType.OK).show();
                loadAllItems();
                newBtn_onAction(event);
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to update the item.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void searchBoxTF_onMouseClicked(MouseEvent event) {
        itemCodeTF.setText("");
        nameTF.setText("");
        descriptionTF.setText("");
        sizeTF.setText("");
        priceTF.setText("");
        qtyTF.setText("");
        categoryComBox.setValue("");
        brandComBox.setValue("");
        supplierComBox.setValue("");
        otherTA.setText("");
        searchBoxTF.setText("");
        loadAllItems();
    }

    
    @FXML
    private void searchBoxTF_keyReleased(KeyEvent event) {
        if(searchBoxTF.getText()==null){
            loadAllItems();
        }else{
            try {
                ArrayList<ItemManagementDTO> itemManagementDTOs = itemManagementBO.searchItems(searchBoxTF.getText());                
                if(itemManagementDTOs.isEmpty()){
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadAllItems();
                }else{
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    itemManagementTM = itemManagementTbl.getItems();
                    itemManagementTM.removeAll(itemManagementTM);
                  
                    for(ItemManagementDTO item : itemManagementDTOs){
                        itemManagementTM.add(new ItemManagementTM(
                            item.getItemcode(),
                            item.getName(),
                            item.getSize(),
                            item.getCatName(),
                            item.getPrice(),
                            item.getQty()
                        ));
                    }
                }   } catch (Exception ex) {
                Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
    
    public void loadCategoryComboBox(){
        try {
            ObservableList<String> categoryNameList=FXCollections.observableArrayList();
            
            ArrayList<ItemCategoryDTO> itemCategoryDTOs=itemManagementBO.loadCategoryComboBox();
            
            for (ItemCategoryDTO itemCategory : itemCategoryDTOs) {
                categoryNameList.add(itemCategory.getName());
            }
            categoryComBox.setItems(categoryNameList);
        } catch (Exception ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void loadBrandComboBox() {
        try {
            ObservableList<String> brandNameList=FXCollections.observableArrayList();
            
            ArrayList<ItemBrandDTO> itemBrandDTOs=itemManagementBO.loadBrandComboBox();
            
            for(ItemBrandDTO itemBrand : itemBrandDTOs){
                brandNameList.add(itemBrand.getName());
            }
            brandComBox.setItems(brandNameList);
        } catch (Exception ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSupplierComboBox() {
        try {
            ObservableList<String> supplierNameList=FXCollections.observableArrayList();
            
            ArrayList<SupplierManagementDTO> supplierManagementDTOs=itemManagementBO.loadSupplierComboBox();
            
            for(SupplierManagementDTO supplier : supplierManagementDTOs){
                supplierNameList.add(supplier.getName());
            }
            supplierComBox.setItems(supplierNameList);
        } catch (Exception ex) {
            Logger.getLogger(ItemManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void descriptionTF_onAction(ActionEvent event) {
        String description=descriptionTF.getText();
        boolean matches=description.matches("(.{1,})?");
        if(matches){
            sizeTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Description.").show();
            descriptionTF.requestFocus();
        }
    }

    @FXML
    private void nameTF_onAction(ActionEvent event) {
        String name=nameTF.getText();
        boolean matches=name.matches("(.{1,})?");
        if(matches){
            descriptionTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.").show();
            nameTF.requestFocus();
        }
    }

    @FXML
    private void priceTF_onAction(ActionEvent event) {
        String price=priceTF.getText();
        boolean matches=price.matches("[0-9]{1,}");
        if(matches){
            qtyTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Price.").show();
            priceTF.requestFocus();
        }
    }

    @FXML
    private void categoryComBox_onAction(ActionEvent event) {
        brandComBox.requestFocus();
    }

    @FXML
    private void brandComBox_onAction(ActionEvent event) {
        supplierComBox.requestFocus();
    }

    @FXML
    private void supplierComBox_onAction(ActionEvent event) {
        otherTA.requestFocus();
    }

    @FXML
    private void qtyTF_onAction(ActionEvent event) {
        String qty=qtyTF.getText();
        boolean matches=qty.matches("[0-9]{1,}");
        if(matches){
            categoryComBox.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Qty.").show();
            qtyTF.requestFocus();
        }
    }

    @FXML
    private void sizeTF_onAction(ActionEvent event) {
        String size=sizeTF.getText();
        boolean matches=size.matches(".{1,}");
        if(matches){
            priceTF.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Size.").show();
            sizeTF.requestFocus();
        }
    }
    
}

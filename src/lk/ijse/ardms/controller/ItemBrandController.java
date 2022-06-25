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
import lk.ijse.ardms.business.custom.ItemBrandBO;
import lk.ijse.ardms.dto.ItemBrandDTO;
import lk.ijse.ardms.view.util.tblmodel.ItemBrandTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class ItemBrandController implements Initializable {

    @FXML
    private AnchorPane itemBrandAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox backHBox;
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
    private JFXTextField braidTF;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextArea descriptionTA;
    @FXML
    private JFXTextArea otherDetailsTA;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<ItemBrandTM> itemBrandTbl;

    ItemBrandBO itemBrandBO=(ItemBrandBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMBRAND);
    
    ObservableList<ItemBrandTM> itemBrandTM;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        itemBrandTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        itemBrandTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("braId"));
        itemBrandTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        itemBrandTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        itemBrandTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("otherDetails"));
        
        loadAllItemBrands();
        
        generateItemBrandId();
    }    

    @FXML
    private void loadMainDash(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.itemBrandAnchPane.getScene().getWindow();
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
        Stage stage=(Stage) this.itemBrandAnchPane.getScene().getWindow();
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
    
    private void loadAllItemBrands() {
        try {
            ArrayList<ItemBrandDTO> itemBrandDTOs = itemBrandBO.getAll();
            
            itemBrandTM = itemBrandTbl.getItems();
            itemBrandTM.removeAll(itemBrandTM);
            
            for(ItemBrandDTO brand : itemBrandDTOs){
                itemBrandTM.add(new ItemBrandTM(
                        brand.getBraId(),
                        brand.getName(),
                        brand.getDescription(),
                        brand.getOtherDetails()
                ));
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemBrandController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void generateItemBrandId() {
        try {
            String newBrandId=itemBrandBO.generateItemBrandId();
            braidTF.setText(newBrandId);
        } catch (Exception ex) {
            Logger.getLogger(ItemBrandController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void itemBrandTbl_onMouseClicked(MouseEvent event) {
        
        ItemBrandTM itemBrandTM=itemBrandTbl.getSelectionModel().getSelectedItem();
        
        braidTF.setText(itemBrandTM.getBraId());
        nameTF.setText(itemBrandTM.getName());
        descriptionTA.setText(itemBrandTM.getDescription());
        otherDetailsTA.setText(itemBrandTM.getOtherDetails());
    }

    
    @FXML
    private void newBtn_onAction(ActionEvent event) {
        braidTF.setText("");
        nameTF.setText("");
        descriptionTA.setText("");
        otherDetailsTA.setText("");
        searchBoxTF.setText("");
        
        generateItemBrandId();
    }

    
    @FXML
    private void saveBtn_onAction(ActionEvent event) {
        try {
            String braid=braidTF.getText();
            String name=nameTF.getText();
            String description=descriptionTA.getText();
            String otherDetails=otherDetailsTA.getText();
            
            ItemBrandDTO itemBrandDTO=new ItemBrandDTO(braid, name, description, otherDetails);
            
            boolean result=false;
            
            boolean matches1=name.matches(".{1,}");
            boolean matches2=description.matches("(.{1,})?");
            boolean matches3=otherDetails.matches("(.{1,})?");
            
            if(matches1){
                if(matches2){
                    if(matches3){
                        result = itemBrandBO.saveBrand(itemBrandDTO);
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Other Details.").show();
                        otherDetailsTA.requestFocus();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Description.").show();
                    descriptionTA.requestFocus();
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.").show();
                nameTF.requestFocus();
            }
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Brand has been saved successfully.",ButtonType.OK).show();
                loadAllItemBrands();
                newBtn_onAction(event);
            }else{
                new Alert(Alert.AlertType.ERROR,"FAiled to save the brand.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemBrandController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void updateBtn_onAction(ActionEvent event) {
        try {
            String braid=braidTF.getText();
            String name=nameTF.getText();
            String description=descriptionTA.getText();
            String otherDetails=otherDetailsTA.getText();
            
            ItemBrandDTO itemBrandDTO=new ItemBrandDTO(braid, name, description, otherDetails);
            
            boolean result=false;
            
            boolean matches1=name.matches(".{1,}");
            boolean matches2=description.matches("(.{1,})?");
            boolean matches3=otherDetails.matches("(.{1,})?");
            
            if(matches1){
                if(matches2){
                    if(matches3){
                        result=itemBrandBO.updateBrand(itemBrandDTO);
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Other Details.").show();
                        otherDetailsTA.requestFocus();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Description.").show();
                    descriptionTA.requestFocus();
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.").show();
                nameTF.requestFocus();
            }
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Brand has been updated successfully.",ButtonType.OK).show();
                loadAllItemBrands();
                newBtn_onAction(event);
            }else{
                new Alert(Alert.AlertType.ERROR,"FAiled to update the brand.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemBrandController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @FXML
    private void deleteBtn_onAction(ActionEvent event) {
        try {
            ItemBrandTM brandTM=itemBrandTbl.getSelectionModel().getSelectedItem();
            
            boolean result=itemBrandBO.deleteBrand(brandTM.getBraId());
            
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Brand has been deleted successfully.",ButtonType.OK).show();
                loadAllItemBrands();
                newBtn_onAction(event);
            }else{
                new Alert(Alert.AlertType.ERROR,"FAiled to delete the brand.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemBrandController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void searchBox_onMouseClicked(MouseEvent event) {
        braidTF.setText("");
        nameTF.setText("");
        descriptionTA.setText("");
        otherDetailsTA.setText("");
        searchBoxTF.setText("");
        loadAllItemBrands();
    }
    
    
    @FXML
    private void searchBox_onKeyReleased(KeyEvent event) {
        if(searchBoxTF.getText()==null){
            loadAllItemBrands();
        }else{
            try {
                ArrayList<ItemBrandDTO> itemBrandDTOs = itemBrandBO.searchBrand(searchBoxTF.getText());                
                if(itemBrandDTOs.isEmpty()){
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadAllItemBrands();
                }else{
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    itemBrandTM = itemBrandTbl.getItems();
                    itemBrandTM.removeAll(itemBrandTM);
                  
                    for(ItemBrandDTO brand : itemBrandDTOs){
                        itemBrandTM.add(new ItemBrandTM(
                                brand.getBraId(),
                                brand.getName(),
                                brand.getDescription(),
                                brand.getOtherDetails()
                        ));
                    }
                }   
            } catch (Exception ex) {
                Logger.getLogger(ItemBrandController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void nameTF_onAction(ActionEvent event) {
        String name=nameTF.getText();
        boolean matches=name.matches(".{1,}");
        if(matches){
            descriptionTA.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.").show();
            nameTF.requestFocus();
        }
    }
    
}

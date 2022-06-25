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
import lk.ijse.ardms.business.custom.ItemCategoryBO;
import lk.ijse.ardms.dto.ItemCategoryDTO;
import lk.ijse.ardms.view.util.tblmodel.ItemCategoryTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class ItemCategoryController implements Initializable {

    @FXML
    private HBox homeHBox;
    @FXML
    private HBox backHBox;
    @FXML
    private AnchorPane itemCategoryAnchPane;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private JFXButton newBtn;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXTextField categoryIdTF;
    @FXML
    private JFXTextField categoryNameTF;
    @FXML
    private JFXTextArea descriptionTA;
    @FXML
    private JFXTextArea otherDetailsTA;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<ItemCategoryTM> itemCategoryTbl;
    
    ItemCategoryBO itemCategoryBO = (ItemCategoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEMCATEGORY);

    ObservableList<ItemCategoryTM> itemCategoryTM;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        itemCategoryTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        itemCategoryTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("catid"));
        itemCategoryTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        itemCategoryTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        itemCategoryTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("other_details"));
        
        loadAllItemCategories();
        
        generateItemCategoryId();
        
        categoryNameTF.requestFocus();
    }    

    @FXML
    private void loadMainDash(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/mainDash.fxml"));
        Scene temp=new Scene(root);
        Stage stage=(Stage) this.itemCategoryAnchPane.getScene().getWindow();
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
        Stage stage=(Stage) this.itemCategoryAnchPane.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(temp);
        
        TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
        trans.setFromX(+temp.getHeight());
        trans.setToX(0);
        trans.play();
    }

    @FXML
    private void showViewMoreHBox(MouseEvent event) throws IOException {
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
    private void showHelpHBox(MouseEvent event) throws IOException {
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

    
    private void loadAllItemCategories() {
        try {
            ArrayList<ItemCategoryDTO> itemManagementDTOs = itemCategoryBO.getAll();
            
            itemCategoryTM = itemCategoryTbl.getItems();
            
            itemCategoryTM.removeAll(itemCategoryTM);
            
            for(ItemCategoryDTO category : itemManagementDTOs){
                itemCategoryTM.add(new ItemCategoryTM(
                        category.getCatid(),
                        category.getName(),
                        category.getDescription(),
                        category.getOther_details()
                ));
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void generateItemCategoryId() {
        try {
            String newCategoryId=itemCategoryBO.generateItemCategoryId();
            categoryIdTF.setText(newCategoryId);
        } catch (Exception ex) {
            Logger.getLogger(ItemCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void itemCategoryTblOnMouseClick(MouseEvent event) {
        
        ItemCategoryTM itemCategoryTM=itemCategoryTbl.getSelectionModel().getSelectedItem();
        categoryIdTF.setText(itemCategoryTM.getCatid());
        categoryNameTF.setText(itemCategoryTM.getName());
        descriptionTA.setText(itemCategoryTM.getDescription());
        otherDetailsTA.setText(itemCategoryTM.getOther_details());
    }


    @FXML
    private void newBtn_onAction(ActionEvent event) {
        categoryIdTF.setText("");
        categoryNameTF.setText("");
        descriptionTA.setText("");
        otherDetailsTA.setText("");
        searchBoxTF.setText("");
        
        generateItemCategoryId();
    }

    @FXML
    private void saveBtn_onAction(ActionEvent event) {
        
        try {
            String catId=categoryIdTF.getText();
            String catName=categoryNameTF.getText();
            String description=descriptionTA.getText();
            String otherDetails=otherDetailsTA.getText();
            
            ItemCategoryDTO categoryDTO = new ItemCategoryDTO(catId,catName,description,otherDetails);
            
            boolean matches1=catName.matches(".{1,}");
            boolean matches2=description.matches("(.{1,})?");
            boolean matches3=otherDetails.matches("(.{1,})?");
            
            boolean result=false;
            
            if(matches1){
                if(matches2){
                    if(matches3){
                        result = itemCategoryBO.saveCategory(categoryDTO);
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
                categoryNameTF.requestFocus();
            }
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Category has been saved successfully.",ButtonType.OK).show();
                newBtn_onAction(event);
                loadAllItemCategories();
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to save the category.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void deleteBtn_onAction(ActionEvent event) {
        
        try {
            ItemCategoryTM categoryTM=itemCategoryTbl.getSelectionModel().getSelectedItem();
            
            boolean result=itemCategoryBO.deleteCategory(categoryTM.getCatid());
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Category has been deleted successfully.",ButtonType.OK).show();
                loadAllItemCategories();
                newBtn_onAction(event);
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to delete the category.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateBtn_onAction(ActionEvent event) {
        
        try {
            String caiId=categoryIdTF.getText();
            String catName=categoryNameTF.getText();
            String description=descriptionTA.getText();
            String otherDetails=otherDetailsTA.getText();
            
            ItemCategoryDTO categoryDTO = new ItemCategoryDTO(caiId,catName,description,otherDetails);
            
            boolean matches1=catName.matches(".{1,}");
            boolean matches2=description.matches("(.{1,})?");
            boolean matches3=otherDetails.matches("(.{1,})?");
            
            boolean result=false;
            
            if(matches1){
                if(matches2){
                    if(matches3){
                        result=itemCategoryBO.updateCategory(categoryDTO);
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
                categoryNameTF.requestFocus();
            }
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Category has been updated successfully.",ButtonType.OK).show();
                loadAllItemCategories();
                newBtn_onAction(event);
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to update the category.",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void searchBox_keyReleased(KeyEvent event) {
        
        if(searchBoxTF.getText()==null){
            loadAllItemCategories();
        }else{
            try {
                ArrayList<ItemCategoryDTO> itemCategoryDTOs = itemCategoryBO.search(searchBoxTF.getText());                
                if(itemCategoryDTOs.isEmpty()){
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadAllItemCategories();
                }else{
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    itemCategoryTM = itemCategoryTbl.getItems();
                    itemCategoryTM.removeAll(itemCategoryTM);
                  
                    for(ItemCategoryDTO itemCategory : itemCategoryDTOs){
                        itemCategoryTM.add(new ItemCategoryTM(
                                itemCategory.getCatid(),
                                itemCategory.getName(),
                                itemCategory.getDescription(),
                                itemCategory.getOther_details()
                        ));
                    }
                }   
                } catch (Exception ex) {
                    Logger.getLogger(ItemCategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    private void searchBox_onMouseClick(MouseEvent event) {
        categoryIdTF.setText("");
        categoryNameTF.setText("");
        descriptionTA.setText("");
        otherDetailsTA.setText("");
        searchBoxTF.setText("");
        loadAllItemCategories();
    }

    @FXML
    private void categoryNameTF_onAction(ActionEvent event) {
        String name=categoryNameTF.getText();
        boolean matches=name.matches(".{1,}");
        if(matches){
            descriptionTA.requestFocus();
        }else{
            new Alert(Alert.AlertType.ERROR,"Oops... Something wrong in Name.").show();
            categoryNameTF.requestFocus();
        }
    }
    
}

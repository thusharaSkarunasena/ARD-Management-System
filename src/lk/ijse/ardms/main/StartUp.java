/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.main;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Thushara Supun
 */
public class StartUp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/ardms/view/welcome.fxml"));
        Scene temp=new Scene(root);
        primaryStage.setScene(temp);
        primaryStage.getIcons().add(new Image("lk/ijse/ardms/asset/logo_icon.png"));
        primaryStage.setTitle(" ARD Management System ");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
        
        FadeTransition trans=new FadeTransition(Duration.millis(500),root);
        trans.setFromValue(0.02);
        trans.setToValue(1);
        trans.play();
    }

}
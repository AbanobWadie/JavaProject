/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Abanob wadie
 */
public class JavaProject extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(JavaProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void stop() throws Exception {
        ServerConnection con = new ServerConnection();
        con.exit();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

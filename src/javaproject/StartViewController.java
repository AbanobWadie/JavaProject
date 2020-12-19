/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class StartViewController implements Initializable {
    
     
    @FXML
    private Button btn_singlePlayer;
    
    @FXML
    private Button btn_localMultiplayer;
    
    @FXML
    private Button btn_onlineMultiplayer;
    @FXML
    private Button btn_record;

    
    @FXML 
    void singlePlayer(ActionEvent event)
    {
        
        try {
             Parent root;
             root = FXMLLoader.load(getClass().getResource("SingleView.fxml"));
             Scene scene = new Scene(root);
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StartViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
    }
    
    @FXML
    void online(ActionEvent event)
    {
        try {            
                 Parent root = FXMLLoader.load(getClass().getResource("IpNetworkView.fxml"));
                 Scene scene = new Scene(root);
                 Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
                 
            } catch (IOException ex) {
                Logger.getLogger(StartViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @FXML
     void localPlayer(ActionEvent event)
    {
          try {            
                 Parent root = FXMLLoader.load(getClass().getResource("LocalView.fxml"));
                 Scene scene = new Scene(root);
                 Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
                 
            } catch (IOException ex) {
                Logger.getLogger(StartViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
      public void exit(ActionEvent event)
    {
        System.exit(0);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RecordedGamesProcess.read();
        System.out.println(RecordedGamesProcess.records.get(0).getMoves());
    }    

    @FXML
    private void record(ActionEvent event) {
        
         try {            
                 Parent root = FXMLLoader.load(getClass().getResource("SingleRecordView.fxml"));
                 Scene scene = new Scene(root);
                 Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
                 
            } catch (IOException ex) {
                Logger.getLogger(StartViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}

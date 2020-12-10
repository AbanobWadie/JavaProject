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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class IpNetworkViewController implements Initializable {

    @FXML
    private TextField txt_ip;
    @FXML
    private Button btn_play;
    @FXML
    private Hyperlink btn_back;
    
    
    @FXML
    void back(ActionEvent event)
    {
                
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
             Scene scene = new Scene(root);
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
        } catch (IOException ex) {
            Logger.getLogger(IpNetworkViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    @FXML
    void play(ActionEvent event)
    {
         try {
                
                 Parent root = FXMLLoader.load(getClass().getResource("ListPlayerView.fxml"));
                 Scene scene = new Scene(root);
                 Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

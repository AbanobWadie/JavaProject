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
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class OnlineMultiplayerViewController implements Initializable {

    @FXML
    private Label u2;
    @FXML
    private Label u1;
    @FXML
    private TextField txt_score2;
    @FXML
    private TextField txt_score1;
    @FXML
    private RadioButton X1;
    @FXML
    private RadioButton O1;
    @FXML
    private Button btn_reset;
    @FXML
    private Button btn_record;
    @FXML
    private Button btn_back;
    @FXML
    private Canvas canvas;
    @FXML
    private RadioButton X2;
    @FXML
    private RadioButton O2;
    
    
    @FXML
    void back(ActionEvent event)
    {
        try {
                
                 Parent root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
                 Scene scene = new Scene(root);
                 Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
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

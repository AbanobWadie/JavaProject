/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class SingleViewController implements Initializable {

    @FXML
    private TextField txt_name;
    @FXML
    private Button btn_play;
    @FXML
    private RadioButton X;
    @FXML
    private RadioButton O;
    
    
    public static boolean myTurn;
    public static String gameMode;
    public static String key;
    public static String winner;
    private static String[] buttonText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    
    
}

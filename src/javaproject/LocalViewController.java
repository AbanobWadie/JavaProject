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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import players.Player;
import players.SymbolsEnum;
import static players.Turn.setTurn;
import static sun.audio.AudioPlayer.player;

/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class LocalViewController implements Initializable {

    @FXML
    private Button btn_play;
    @FXML
    private TextField txt_player1;
    @FXML
    private TextField txt_player2;
    @FXML
    RadioButton X1;
    @FXML
     RadioButton O1;
    @FXML
    RadioButton X2;
    @FXML
    RadioButton O2;
    @FXML
    private Button btn_record;
    
      Player player1=new Player();
      Player player2=new Player();
      final ToggleGroup group1 = new ToggleGroup();
      final ToggleGroup group2 = new ToggleGroup();
      boolean win;
      GraphicsContext gc;
        
        SymbolsEnum e;
SymbolsEnum a;
SymbolsEnum b;
SymbolsEnum c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         X1.setToggleGroup(group1);
            O1.setToggleGroup(group1);
            X2.setToggleGroup(group2);
            O2.setToggleGroup(group2);
    }    

    @FXML
     void play(ActionEvent event) {
         if(txt_player1.getText().equals("") ||txt_player2.getText().equals("")){
              showAlert("Please, Enter you Name!");
          
        }else
        {
              try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("LocalMultiplayerView.fxml"));
                    Parent root = loader.load();

                    //Get controller of scene2
                    LocalMultiplayerViewController o = loader.getController();
                    //Pass whatever data you want. You can have multiple method calls here
                    o.transferMessage(txt_player1.getText(),txt_player2.getText());
                    o.trnsfer(e,a);

                        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                  
            } catch (IOException ex) {
                Logger.getLogger(LocalMultiplayerViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
               
    }

    @FXML
    void player1Symbol(ActionEvent event) {
      
        if (X1.isSelected()) {
            O2.setSelected(true);
          
            // Setting Symbols for players
            player1.setSymbol(SymbolsEnum.CROSS);
            player2.setSymbol(SymbolsEnum.ROUND);
        } else if (O1.isSelected()) {
            X2.setSelected(true);
            
            // Setting Symbols for players
            player1.setSymbol(SymbolsEnum.ROUND);
            player2.setSymbol(SymbolsEnum.CROSS);
        }
        // Set initial turn for player with symbol CROSS
       if (player1.getSymbol() == SymbolsEnum.CROSS) {
            setTurn(player1);
            // Setting underlines visible or invisible acc to player turn
           // u1.setVisible(true);
           // u2.setVisible(false);
        } else {
            setTurn(player2);
            //u2.setVisible(true);
            //u1.setVisible(false);
        }
    }
    

    @FXML
     void player2Symbol(ActionEvent event) {
        
         
        if (X2.isSelected()) {
            O1.setSelected(true);
             
            // Setting Symbols for players
            player1.setSymbol(SymbolsEnum.CROSS);
          
           e =player1.getSymbol();
            player2.setSymbol(SymbolsEnum.ROUND);
            a=player2.getSymbol();
            
        } else if (O2.isSelected()) {
            X1.setSelected(true);
          
            // Seting Symbols for players
            
            player1.setSymbol(SymbolsEnum.ROUND);
            player2.setSymbol(SymbolsEnum.CROSS);
        }
        // Set initial turn for player with symbol CROSS
        if (player1.getSymbol() == SymbolsEnum.CROSS) {
            setTurn(player1);
            // Setting underlines visible or invisible acc to player turn
          //  u1.setVisible(true);
            //u2.setVisible(false);
        } else {
            setTurn(player2);
            //u2.setVisible(true);
            //u1.setVisible(false);
        }
    }
    
    
        void showAlert(String mess) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mess, ButtonType.CANCEL);
        alert.setTitle("Succedded");
        alert.setHeaderText(null);
        alert.setContentText(mess);
        alert.show();
    }
        
      
}

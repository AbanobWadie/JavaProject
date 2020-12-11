/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import players.SymbolsEnum;
import players.Turn;
import players.Result;
import players.Draw;
import players.Player;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static players.SymbolsEnum.CROSS;
import static players.SymbolsEnum.ROUND;
import static players.Turn.getTurn;
import static players.Turn.setTurn;


/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class LocalMultiplayerViewController extends LocalViewController implements Initializable {
    
  
  
    
    LocalViewController l=new LocalViewController();
    
    @FXML
    private Canvas canvas;
    @FXML // 9 boxes to draw 
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;

    // TextFields for players
 

    // Labels for underline
    @FXML
    private Label u1;

    @FXML
    private Label u2;

    // JFXRadiobuttons for symbol choice of player1
 


    @FXML // Reset or NewGame button
    private Button reset;
    
     @FXML
    private Button btn_back;
     
      String name1;
      String name2;
      
      SymbolsEnum w;
      SymbolsEnum z;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
     
         
                 l.player1.setSymbol(CROSS);
                 l.player2.setSymbol(ROUND);
           
       
             
         
              l.gc = canvas.getGraphicsContext2D();
              l.gc = Draw.draw_basic_skeleton(l.gc);
    }  
    
    @FXML
    void back(ActionEvent event)
    {
          try {
              
                 Parent root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
                 Scene scene = new Scene(root);
                 Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
              
                /* Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 Scene scene = (Scene)((Node)event.getSource()).getScene();
                 Parent root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
                 scene.setRoot(root);
                 stage.setScene(scene);
                 stage.show();*/
                
            } catch (IOException ex) {
                Logger.getLogger(LocalMultiplayerViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    


    /**
     * The radio button event to select the clicked and also to select the
     * opposite for other player (Player 1 here)
     *
     * @param event OnClick Action Event
     */
  
      @FXML
    void resetButton(ActionEvent event)
    {
          try {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("LocalMultiplayerView.fxml"));
            Parent root = loader.load();
             
            //Get controller of scene2
            LocalMultiplayerViewController p = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            p.transferMessage(name1,name2);
                 
                 Scene scene = new Scene(root);
                 ((Stage) reset.getScene().getWindow()).close();
                 Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
          } catch (IOException ex) {
            ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(LocalMultiplayerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * --------------- This is same for all 9 buttonEvents ---------------
     *
     * win = Result.add(player1, player2, 'boxnumber'); -->> Add the number of box to the moves of the respective player and check if there is a win 
     * draw(50 + 15, 50 + 15 + 15); -->> Draw and if win is false show alert -> this is included in draw function
     * ChangeTurn(); -->> This will happen only if win is false 
     * buttonNumber.setDisable(true); -->> Disable the button for further overwriting
     */
    @FXML
    void eventb1(ActionEvent event)
    {
        setAllDisable(false);
        l.win = Result.add(l.player1, l.player2, 1);
        draw(50 + 15, 50 + 15 + 15);
        ChangeTurn();
        b1.setDisable(false);
    }

    @FXML
    void eventb2(ActionEvent event)
    {
        setAllDisable(false);
        l.win = Result.add(l.player1, l.player2, 2);
        draw(50 + 15 + 70 + 30, 50 + 15 + 15);
        ChangeTurn();
        b2.setDisable(false);
    }

    @FXML
    void eventb3(ActionEvent event)
    {
        setAllDisable(false);
        l.win = Result.add(l.player1, l.player2, 3);
        draw(50 + 15 + 70 + 30 + 70 + 30, 50 + 15 + 15);
        ChangeTurn();
        b3.setDisable(false);
    }

    @FXML
    void eventb4(ActionEvent event)
    {
        setAllDisable(false);
        l.win = Result.add(l.player1, l.player2, 4);
        draw(50 + 15, 50 + 15 + 70 + 30 + 15);
        ChangeTurn();
        b4.setDisable(false);
    }

    @FXML
    void eventb5(ActionEvent event)
    {
        setAllDisable(false);
        l.win = Result.add(l.player1, l.player2, 5);
        draw(50 + 15 + 70 + 30, 50 + 15 + 70 + 30 + 15);
        ChangeTurn();
        b5.setDisable(false);
    }

    @FXML
    void eventb6(ActionEvent event)
    {
        setAllDisable(false);
        l.win = Result.add(l.player1, l.player2, 6);
        draw(50 + 15 + 70 + 30 + 70 + 30, 50 + 15 + 70 + 30 + 15);
        ChangeTurn();
        b6.setDisable(false);
    }

    @FXML
    void eventb7(ActionEvent event)
    {
        setAllDisable(false);
        l.win = Result.add(l.player1, l.player2, 7);
        draw(50 + 15, 50 + 15 + 70 + 30 + 70 + 30 + 15);
        ChangeTurn();
        b7.setDisable(false);
    }

    @FXML
    void eventb8(ActionEvent event)
    {
        setAllDisable(false);
        l.win = Result.add(l.player1, l.player2, 8);
        draw(50 + 15 + 70 + 30, 50 + 15 + 70 + 30 + 70 + 30 + 15);
        ChangeTurn();
        b8.setDisable(false);
    }

    @FXML
    void eventb9(ActionEvent event)
    {
        setAllDisable(false);
        l.win = Result.add(l.player1, l.player2, 9);
        draw(50 + 15 + 70 + 30 + 70 + 30, 50 + 15 + 70 + 30 + 70 + 30 + 15);
        ChangeTurn();
        b9.setDisable(false);
    }
    
       private void ChangeTurn()
    {
        if (getTurn() == l.player1) {
            setTurn(l.player2);
            // Setting underlines visible or invisible acc to player turn
            u1.setVisible(true);
            u2.setVisible(false);
        } else {
            setTurn(l.player1);
            u2.setVisible(true);
            u1.setVisible(false);
        }
    }

    /**
     * Calls the appropriate draw function as per the players turn and symbol.
     * shows alert if win is true and if yes disables all buttons
     *
     * @param startX X-coordinate of the point to start drawing from
     * @param startY Y-coordinate of the point to start drawing from
     */
    private void draw(int startX, int startY)
    {
        if (getTurn() == l.player1) {
            if (l.player1.getSymbol() == SymbolsEnum.CROSS) {
                Draw.draw_cross(l.gc, startX, startY);
            } else {
                Draw.draw_circle(l.gc, startX, startY);
            }
        } else if (getTurn() == l.player2) {
            if (l.player2.getSymbol() == SymbolsEnum.CROSS) {
                Draw.draw_cross(l.gc, startX, startY);
            } else {
                Draw.draw_circle(l.gc, startX, startY);
            }
        }

        // Check if there is win and if yes draw a line and show alert 
        if (l.win) {

            if (Turn.getTurn() == l.player1) {
                drawLine();
                showAlert(u1.getText());
            } else {
                drawLine();
                showAlert(u2.getText());
            }
            // Disables all buttons to stop the game
            setAllDisable(true);
        }
    }

    /**
     * Disables or enable s all buttons as per the user choice
     *
     * @param option This could be ture or false
     */
    private void setAllDisable(boolean option)
    {
        b1.setDisable(option);
        b2.setDisable(option);
        b3.setDisable(option);
        b4.setDisable(option);
        b5.setDisable(option);
        b6.setDisable(option);
        b7.setDisable(option);
        b8.setDisable(option);
        b9.setDisable(option);
    }

    /**
     * Disables or enables all radio buttons and the textfields
     *
     * @param option This could be true or false
     */
    

  

    /**
     * Sets coordinates for the draw_winning_line function and calls the
     * function with the coordinates as parameters
     */
    private void drawLine()
    {
        List<Integer> winningMoves;
        if (getTurn() == l.player1) {
            winningMoves = Result.getPlayer1moves();
        } else {
            winningMoves = Result.getPlayer2moves();
        }
        Collections.sort(winningMoves);
       
        int startX, startY, endX, endY;
        // Adding 35 to move the point to center
        startX = 35 + (50 + 15) + (winningMoves.get(0) - 1) % 3 * (70 + 30);
        startY = 35 + (50 + 15 + 15) + (winningMoves.get(0) - 1) / 3 * (70 + 30);
        endX = 35 + (50 + 15) + (winningMoves.get(2) - 1) % 3 * (70 + 30);
        endY = 35 + (50 + 15 + 15) + (winningMoves.get(2) - 1) / 3 * (70 + 30);

        Draw.draw_winning_line(l.gc, startX, startY, endX, endY);
    }
    
   
    
      /*  private void showAlert(String mess) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mess, ButtonType.CANCEL);
        alert.setTitle("Succedded");
        alert.setHeaderText(null);
        alert.setContentText(mess);
        alert.show();
    }*/

    void transferMessage(String text,String text0) {
        u1.setText(text);
        u2.setText(text0);
        name1 = text;
        name2 = text0;
    }

    void trnsfer(SymbolsEnum e, SymbolsEnum a) {
        w=e;
        a=a;
    }
    
    
    
    
}

 




    


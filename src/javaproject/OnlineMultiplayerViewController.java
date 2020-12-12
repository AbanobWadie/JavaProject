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
import static players.Turn.getTurn;
import static players.Turn.setTurn;


/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class OnlineMultiplayerViewController extends Turn implements Initializable {
    
    GraphicsContext gc;
    Player player1=new Player();
    Player player2=new Player();
    String name;
    boolean win;
    
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
    @FXML
    private TextField p1;
    @FXML
    private TextField p2;

    // Labels for underline
    @FXML
    private Label u1;

    @FXML
    private Label u2;

    // JFXRadiobuttons for symbol choice of player1
    @FXML
    private RadioButton X1;
    @FXML
    private RadioButton O1;

    // JFXRadiobuttons for symbol choice of player2
    @FXML
    private RadioButton X2;
    @FXML
    private RadioButton O2;


    @FXML // Reset or NewGame button
    private Button reset;
    
     @FXML
    private Button btn_back;
     
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
              player1 = new Player();
              player2 = new Player();
              gc = canvas.getGraphicsContext2D();
              gc = Draw.draw_basic_skeleton(gc);
    }  
    
    @FXML
    void back(ActionEvent event)
    {
          try {
              
                 Parent root = FXMLLoader.load(getClass().getResource("ListPlayerView.fxml"));
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
    
    @FXML
    void player1Symbol(ActionEvent event)
    {
        setAllDisable(false);
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
            u1.setVisible(true);
            u2.setVisible(false);
        } else {
            setTurn(player2);
            u2.setVisible(true);
            u1.setVisible(false);
        }
    }


    /**
     * The radio button event to select the clicked and also to select the
     * opposite for other player (Player 1 here)
     *
     * @param event OnClick Action Event
     */
    @FXML
    void player2Symbol(ActionEvent event)
    {
        setAllDisable(false);
        if (X2.isSelected()) {
            O1.setSelected(true);
            // Setting Symbols for players
            player1.setSymbol(SymbolsEnum.CROSS);
            player2.setSymbol(SymbolsEnum.ROUND);
        } else if (O2.isSelected()) {
            X1.setSelected(true);
            // Setting Symbols for players
            player1.setSymbol(SymbolsEnum.ROUND);
            player2.setSymbol(SymbolsEnum.CROSS);
        }
        // Set initial turn for player with symbol CROSS
        if (player1.getSymbol() == SymbolsEnum.CROSS) {
            setTurn(player1);
            // Setting underlines visible or invisible acc to player turn
            u1.setVisible(true);
            u2.setVisible(false);
        } else {
            setTurn(player2);
            u2.setVisible(true);
            u1.setVisible(false);
        }
    }
      @FXML
    void resetButton(ActionEvent event)
    {
         // Close the recent window so that there remains only one window
        // Running a new instance of the start method
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OnlineMultiplayerView.fxml"));
            Parent root = loader.load();
             
            //Get controller of scene2
            OnlineMultiplayerViewController o = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            o.transferMessage(name);
                 
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
        disableChoices(true);
        win = Result.add(player1, player2, 1);
        draw(50 + 15, 50 + 15 + 15);
        ChangeTurn();
        b1.setDisable(true);
    }

    @FXML
    void eventb2(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(player1, player2, 2);
        draw(50 + 15 + 70 + 30, 50 + 15 + 15);
        ChangeTurn();
        b2.setDisable(true);
    }

    @FXML
    void eventb3(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(player1, player2, 3);
        draw(50 + 15 + 70 + 30 + 70 + 30, 50 + 15 + 15);
        ChangeTurn();
        b3.setDisable(true);
    }

    @FXML
    void eventb4(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(player1, player2, 4);
        draw(50 + 15, 50 + 15 + 70 + 30 + 15);
        ChangeTurn();
        b4.setDisable(true);
    }

    @FXML
    void eventb5(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(player1, player2, 5);
        draw(50 + 15 + 70 + 30, 50 + 15 + 70 + 30 + 15);
        ChangeTurn();
        b5.setDisable(true);
    }

    @FXML
    void eventb6(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(player1, player2, 6);
        draw(50 + 15 + 70 + 30 + 70 + 30, 50 + 15 + 70 + 30 + 15);
        ChangeTurn();
        b6.setDisable(true);
    }

    @FXML
    void eventb7(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(player1, player2, 7);
        draw(50 + 15, 50 + 15 + 70 + 30 + 70 + 30 + 15);
        ChangeTurn();
        b7.setDisable(true);
    }

    @FXML
    void eventb8(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(player1, player2, 8);
        draw(50 + 15 + 70 + 30, 50 + 15 + 70 + 30 + 70 + 30 + 15);
        ChangeTurn();
        b8.setDisable(true);
    }

    @FXML
    void eventb9(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(player1, player2, 9);
        draw(50 + 15 + 70 + 30 + 70 + 30, 50 + 15 + 70 + 30 + 70 + 30 + 15);
        ChangeTurn();
        b9.setDisable(true);
    }
    
       private void ChangeTurn()
    {
        if (getTurn() == player1) {
            setTurn(player2);
            // Setting underlines visible or invisible acc to player turn
            u1.setVisible(true);
            u2.setVisible(false);
        } else {
            setTurn(player1);
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
        if (getTurn() == player1) {
            if (player1.getSymbol() == SymbolsEnum.CROSS) {
                Draw.draw_cross(gc, startX, startY);
            } else {
                Draw.draw_circle(gc, startX, startY);
            }
        } else if (getTurn() == player2) {
            if (player2.getSymbol() == SymbolsEnum.CROSS) {
                Draw.draw_cross(gc, startX, startY);
            } else {
                Draw.draw_circle(gc, startX, startY);
            }
        }

        // Check if there is win and if yes draw a line and show alert 
        if (win) {

            if (Turn.getTurn() == player1) {
                drawLine();
                showAlert(p1.getText());
            } else {
                drawLine();
                showAlert(p2.getText());
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
    private void disableChoices(boolean option)
    {
        X1.setDisable(option);
        O1.setDisable(option);
        X2.setDisable(option);
        O2.setDisable(option);
        p1.setDisable(option);
        p2.setDisable(option);
    }

  

    /**
     * Sets coordinates for the draw_winning_line function and calls the
     * function with the coordinates as parameters
     */
    private void drawLine()
    {
        List<Integer> winningMoves;
        if (getTurn() == player1) {
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

        Draw.draw_winning_line(gc, startX, startY, endX, endY);
    }
    
   
    
        private void showAlert(String mess) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mess, ButtonType.CANCEL);
        alert.setTitle("Succedded");
        alert.setHeaderText(null);
        alert.setContentText(mess);
        alert.show();
    }

    void transferMessage(String new_val) {
        p2.setText(new_val);
        name = new_val;
    }

   
    
    
}

 




    


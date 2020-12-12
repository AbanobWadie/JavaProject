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
import javafx.stage.Stage;
import static players.Turn.getTurn;
import static players.Turn.setTurn;


/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class LocalMultiplayerViewController extends Turn implements Initializable {
    Player p1;
    Player p2;
    RadioButton X10;
    RadioButton O10;
    RadioButton X20;
    RadioButton O20;
    boolean win;
    GraphicsContext gc;
        
    
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
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
             p1=new Player();
             p2=new Player();  
             X10=new RadioButton();
             O10=new RadioButton();
             X20=new RadioButton();
             O20=new RadioButton();
             gc = canvas.getGraphicsContext2D();
             gc = Draw.draw_basic_skeleton(gc);
    }  
    
    @FXML
    void back(ActionEvent event)
    {
          try {
                 Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 Scene scene = (Scene)((Node)event.getSource()).getScene();
                 Parent root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
                 scene.setRoot(root);
                 stage.setScene(scene);
                 stage.show();
                
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
             
            LocalMultiplayerViewController p = loader.getController();
    
            p.transferMessageText(name1, name2);
            p.transferMessagePlayers(p1, p2);
            p.transferMessageButtons(X10, O10, X20, O20);
            Result.clearMoves();
            Scene scene = new Scene(root);
            ((Stage) reset.getScene().getWindow()).close();
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            p1=new Player();
           
          } catch (IOException ex) {
            ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(LocalMultiplayerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    void player1Symbol(ActionEvent event)
    {
        setAllDisable(false);
        if (X10.isSelected()) {
            O20.setSelected(true);
            // Setting Symbols for players
            p1.setSymbol(SymbolsEnum.CROSS);
            p2.setSymbol(SymbolsEnum.ROUND);
        } else if (O10.isSelected()) {
            X20.setSelected(true);
            // Setting Symbols for players
            p1.setSymbol(SymbolsEnum.ROUND);
            p2.setSymbol(SymbolsEnum.CROSS);
        }
        // Set initial turn for player with symbol CROSS
        if (p1.getSymbol() == SymbolsEnum.CROSS) {
            setTurn(p1);
            // Setting underlines visible or invisible acc to player turn
            u1.setVisible(true);
            u2.setVisible(false);
        } else {
            setTurn(p2);
            u2.setVisible(true);
            u1.setVisible(false);
        }
    }
    
     @FXML
    void player2Symbol(ActionEvent event)
    {
        setAllDisable(false);
        if (X20.isSelected()) {
            O10.setSelected(true);
            // Setting Symbols for players
            p1.setSymbol(SymbolsEnum.CROSS);
            p2.setSymbol(SymbolsEnum.ROUND);
        } else if (O20.isSelected()) {
            X10.setSelected(true);
            // Setting Symbols for players
            p1.setSymbol(SymbolsEnum.ROUND);
            p2.setSymbol(SymbolsEnum.CROSS);
        }
        // Set initial turn for player with symbol CROSS
        if (p1.getSymbol() == SymbolsEnum.CROSS) {
            setTurn(p1);
            // Setting underlines visible or invisible acc to player turn
            u1.setVisible(true);
            u2.setVisible(false);
        } else {
            setTurn(p2);
            u2.setVisible(true);
            u1.setVisible(false);
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
        win = Result.add(p1, p2, 1);
        draw(50 + 15, 50 + 15 + 15);
        ChangeTurn();
        b1.setDisable(true);
    }

    @FXML
    void eventb2(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(p1, p2, 2);
        draw(50 + 15 + 70 + 30, 50 + 15 + 15);
        ChangeTurn();
        b2.setDisable(true);
    }

    @FXML
    void eventb3(ActionEvent event)
    {
       disableChoices(true);
        win = Result.add(p1, p2, 3);
        draw(50 + 15 + 70 + 30 + 70 + 30, 50 + 15 + 15);
        ChangeTurn();
        b3.setDisable(false);
    }

    @FXML
    void eventb4(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(p1, p2, 4);
        draw(50 + 15, 50 + 15 + 70 + 30 + 15);
        ChangeTurn();
        b4.setDisable(true);
    }

    @FXML
    void eventb5(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(p1, p2, 5);
        draw(50 + 15 + 70 + 30, 50 + 15 + 70 + 30 + 15);
        ChangeTurn();
        b5.setDisable(true);
    }

    @FXML
    void eventb6(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(p1, p2, 6);
        draw(50 + 15 + 70 + 30 + 70 + 30, 50 + 15 + 70 + 30 + 15);
        ChangeTurn();
        b6.setDisable(true);
    }

    @FXML
    void eventb7(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(p1, p2, 7);
        draw(50 + 15, 50 + 15 + 70 + 30 + 70 + 30 + 15);
        ChangeTurn();
        b7.setDisable(true);
    }

    @FXML
    void eventb8(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(p1, p2, 8);
        draw(50 + 15 + 70 + 30, 50 + 15 + 70 + 30 + 70 + 30 + 15);
        ChangeTurn();
        b8.setDisable(true);
    }

    @FXML
    void eventb9(ActionEvent event)
    {
        disableChoices(true);
        win = Result.add(p1, p2, 9);
        draw(50 + 15 + 70 + 30 + 70 + 30, 50 + 15 + 70 + 30 + 70 + 30 + 15);
        ChangeTurn();
        b9.setDisable(true);
    }
    
       private void ChangeTurn()
    {
        if (getTurn() == p1) {
            setTurn(p2);
            // Setting underlines visible or invisible acc to player turn
            u1.setVisible(true);
            u2.setVisible(false);
        } else {
            setTurn(p1);
            u2.setVisible(true);
            u1.setVisible(false);
        }
    }
       
         private void disableChoices(boolean option)
    {
        X10.setDisable(option);
        O10.setDisable(option);
        X20.setDisable(option);
        O20.setDisable(option);
       
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
        if (getTurn() == p1) {
            if (p1.getSymbol() == SymbolsEnum.CROSS) {
                Draw.draw_cross(gc, startX, startY);
            } else {
                Draw.draw_circle(gc, startX, startY);
            }
        } else if (getTurn() == p2) {
            if (p2.getSymbol() == SymbolsEnum.CROSS) {
                Draw.draw_cross(gc, startX, startY);
            } else {
                Draw.draw_circle(gc, startX, startY);
            }
        }

        // Check if there is win and if yes draw a line and show alert 
        if (win) {

            if (Turn.getTurn() ==p1) {
                drawLine();
                showAlert(u1.getText()+" is Winner.");
            } else {
                drawLine();
                showAlert(u2.getText()+" is Winner.");
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
     * Sets coordinates for the draw_winning_line function and calls the
     * function with the coordinates as parameters
     */
    private void drawLine()
    {
        List<Integer> winningMoves;
        if (getTurn() == p1) {
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
        alert.setTitle("Winner");
        alert.setHeaderText(null);
        alert.setContentText(mess);
        alert.show();
    }

        
    void transferMessageText(String text, String text0) {
        u1.setText(text);
        u2.setText(text0);
        name1 = text;
        name2 = text0;
    }

    void transferMessagePlayers(Player player1, Player player2) {
         p1=player1;
         p2=player2;
    }

    void transferMessageButtons(RadioButton X1, RadioButton O1, RadioButton X2, RadioButton O2) {
        X10=X1;
        O10=O1;
        X20=X2;
        O20=O2;
    }   
}

 




    


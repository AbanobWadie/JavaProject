package javaproject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import static javaproject.OnlineMultiplayerViewController.winner;
import static javaproject.Turn.setTurn;
import static javaproject.Turn.getTurn;

public class LocalMultiplayerViewController implements Initializable {

    public static boolean myTurn;
    public static String gameMode;
    public static String key;
    public static String winner;
    private static String[] buttonText;
    RadioButton X10 = new RadioButton();
    RadioButton O10 = new RadioButton();
    RadioButton X20 = new RadioButton();
    RadioButton O20 = new RadioButton();
    Player p10 = new Player();
    Player p20 = new Player();

    @FXML
    Label s1;
    @FXML
    Label s2;

    private String[][] ticTacToeTable;
    private ArrayList<Button> buttonsList = new ArrayList<>();
    @FXML
    private Label lbl_p1;
    @FXML
    private Label lbl_p2;
    @FXML
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
    @FXML
    private Button btn_reset;
    @FXML
    private Button btn_back;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //  p10.setSymbol("X");
        //   p20.setSymbol("O");
        loadGame();
    }

    @FXML
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            move(b1);
            ChangeTurn();
        }
        if (e.getSource() == b2) {
            move(b2);
            ChangeTurn();
        }
        if (e.getSource() == b3) {
            move(b3);
            ChangeTurn();
        }
        if (e.getSource() == b4) {
            move(b4);
            ChangeTurn();
        }
        if (e.getSource() == b5) {
            move(b5);
            ChangeTurn();
        }
        if (e.getSource() == b6) {
            move(b6);
            ChangeTurn();
        }
        if (e.getSource() == b7) {
            move(b7);
            ChangeTurn();
        }
        if (e.getSource() == b8) {
            move(b8);
            ChangeTurn();
        }
        if (e.getSource() == b9) {
            move(b9);
            ChangeTurn();
        }

    }

    private void move(Button button) {
        if (button.getText() == "") {
            if (gameMode == "twoPlayers") {
                button.setText(playController(buttonsList, ticTacToeTable));
                updateGame();

            }
        }
    }

    private void loadGame() {
        ticTacToeTable = new String[3][3];
        loadButtonsList();
        loadButtons();
        loadTicTacToeTable();
        myTurn = true;
        disableButtons(false);
    }

    private void loadButtons() {
        for (int i = 0; i < buttonsList.size(); i++) {
            buttonsList.get(i).setText("");
            buttonsList.get(i).setStyle("-fx-font: 40 arial; -fx-base: #b6e7c9;");
        }
        //restartButton.setVisible(false);
    }

    public void showVictory(String key) {
        reddeningButtons();
        //	restartButton.setVisible(true);
        switch (key) {
            case "line 0": {
                b1.setStyle("-fx-base: #00FF00;");
                b2.setStyle("-fx-base: #00FF00;");
                b3.setStyle("-fx-base: #00FF00;");
                break;
            }
            case "line 1": {
                b4.setStyle("-fx-base: #00FF00;");
                b5.setStyle("-fx-base: #00FF00;");
                b6.setStyle("-fx-base: #00FF00;");
                break;
            }
            case "line 2": {
                b7.setStyle("-fx-base: #00FF00;");
                b8.setStyle("-fx-base: #00FF00;");
                b9.setStyle("-fx-base: #00FF00;");
                break;
            }
            case "column 0": {
                b1.setStyle("-fx-base: #00FF00;");
                b4.setStyle("-fx-base: #00FF00;");
                b7.setStyle("-fx-base: #00FF00;");
                break;
            }
            case "column 1": {
                b2.setStyle("-fx-base: #00FF00;");
                b5.setStyle("-fx-base: #00FF00;");
                b8.setStyle("-fx-base: #00FF00;");
                break;
            }
            case "column 2": {
                b3.setStyle("-fx-base: #00FF00;");
                b6.setStyle("-fx-base: #00FF00;");
                b9.setStyle("-fx-base: #00FF00;");
                break;
            }
            case "main diagonal": {
                b1.setStyle("-fx-base: #00FF00;");
                b5.setStyle("-fx-base: #00FF00;");
                b9.setStyle("-fx-base: #00FF00;");
                break;
            }
            case "secundary diagonal": {
                b3.setStyle("-fx-base: #00FF00;");
                b5.setStyle("-fx-base: #00FF00;");
                b7.setStyle("-fx-base: #00FF00;");
                break;
            }
            case "draw": {
                reddeningButtons();
                break;
            }
        }
        disableButtons(true);  
         if(winner.equals("X")){
             if(s1.getText().equals("X")){
              new ShowVideo().video(lbl_p1.getText(),true);
             }
             else{
                   new ShowVideo().video(lbl_p2.getText(),true);
             }
          
        }else{
              if(s1.getText().equals("O")){
              new ShowVideo().video(lbl_p1.getText(),true);
             }
             else{
               new ShowVideo().video(lbl_p2.getText(),true);
             }
        }
       // showEndGameAlert(key);
    }

    private void showEndGameAlert(String key) {
        Alert endGame = new Alert(AlertType.INFORMATION);
        if (key != "draw") {
            endGame.setTitle("Victory");
            endGame.setContentText("Player \"" + winner + "\" won.");
        } else {
            endGame.setContentText("The game was a draw.");
            endGame.setTitle("Draw");
        }
        endGame.setHeaderText(null);
        endGame.show();

    }

    private void reddeningButtons() {
        for (int i = 0; i < buttonsList.size(); i++) {
            buttonsList.get(i).setStyle("-fx-base: #FF0000;");
        }
    }

    private void disableButtons(boolean disable) {
        for (int i = 0; i < buttonsList.size(); i++) {
            buttonsList.get(i).setDisable(disable);
        }
    }

    private void loadButtonsList() {
        buttonsList.add(b1);
        buttonsList.add(b2);
        buttonsList.add(b3);
        buttonsList.add(b4);
        buttonsList.add(b5);
        buttonsList.add(b6);
        buttonsList.add(b7);
        buttonsList.add(b8);
        buttonsList.add(b9);
    }

    private boolean updateGame() {
        loadTicTacToeTable();
        if (winningChecker(ticTacToeTable)) {
            showVictory(key);
            return false;
        } else {
            return true;
        }
    }

    private void loadTicTacToeTable() {
        int temp = 0;
        for (int i = 0; i < ticTacToeTable.length; i++) {
            for (int j = 0; j < ticTacToeTable[0].length; j++) {
                ticTacToeTable[i][j] = buttonsList.get(temp++).getText();
            }
        }

    }

    public String playController(ArrayList<Button> buttonsList, String[][] ticTacToeTable) {
        switch (gameMode) {

            case "twoPlayers": {
                if (myTurn) {

                    myTurn = !myTurn;
                    return p10.getSymbol();

                } else {
                    myTurn = !myTurn;
                    return p20.getSymbol();
                }
                /*  if(myTurn)
                        {
                            if(getTurn()==p10)
                            {
                                if(p10.getSymbol()=="X")
                                {
                                    p20.setSymbol("O");
                                    return "X";
                                }else 
                                {
                                    p10.setSymbol("O");
                                     return "O";
                                }
                            }else
                            {
                                if(p20.getSymbol()=="X")
                                {
                                    p10.setSymbol("O");
                                    return "X";
                                }else 
                                {
                                    p20.setSymbol("O");
                                     return "O";
                                }
                            }
                        }*/

            }
            default: {
                return null;
            }
        }
    }

    private void ChangeTurn() {
        if (getTurn() == p10) {
            setTurn(p20);
            // Setting underlines visible or invisible acc to player turn

        } else {
            setTurn(p10);

        }
    }

    public static boolean winningChecker(String[][] ticTacToeTable) {
        buttonText = new String[3];
        //Checking line 0
        for (int i = 0; i < buttonText.length; i++) {
            buttonText[i] = ticTacToeTable[0][i];
            if (checkVictory(buttonText)) {
                key = "line 0";
                return true;
            }
        }
        buttonText = new String[3];
        //Checking line 1
        for (int i = 0; i < buttonText.length; i++) {
            buttonText[i] = ticTacToeTable[1][i];
            if (checkVictory(buttonText)) {
                key = "line 1";
                return true;
            }
        }
        buttonText = new String[3];
        //Checking line 2
        for (int i = 0; i < buttonText.length; i++) {
            buttonText[i] = ticTacToeTable[2][i];
            if (checkVictory(buttonText)) {
                key = "line 2";
                return true;
            }
        }
        buttonText = new String[3];
        //Checking column 0
        for (int i = 0; i < buttonText.length; i++) {
            buttonText[i] = ticTacToeTable[i][0];
            if (checkVictory(buttonText)) {
                key = "column 0";
                return true;
            }
        }
        buttonText = new String[3];
        //Checking column 1
        for (int i = 0; i < buttonText.length; i++) {
            buttonText[i] = ticTacToeTable[i][1];
            if (checkVictory(buttonText)) {
                key = "column 1";
                return true;
            }
        }
        buttonText = new String[3];
        //Checking column 2
        for (int i = 0; i < buttonText.length; i++) {
            buttonText[i] = ticTacToeTable[i][2];
            if (checkVictory(buttonText)) {
                key = "column 2";
                return true;
            }
        }
        buttonText = new String[3];
        //main diagonal
        for (int i = 0; i < buttonText.length; i++) {
            buttonText[i] = ticTacToeTable[i][i];
            if (checkVictory(buttonText)) {
                key = "main diagonal";
                return true;
            }
        }
        buttonText = new String[3];
        //secondary diagonal
        for (int i = 0; i < buttonText.length; i++) {
            int j = buttonText.length - 1 - i;
            buttonText[i] = ticTacToeTable[i][j];
            if (checkVictory(buttonText)) {
                key = "secundary diagonal";
                return true;
            }
        }
        //draw
        if (checkDraw(ticTacToeTable)) {
            key = "draw";
            return true;
        }
        return false;
    }

    public static boolean checkVictory(String[] vector) {
        if (vector[0] == "X" && vector[1] == "X" && vector[2] == "X") {
            winner = "X";
            return true;

        } else if (vector[0] == "O" && vector[1] == "O" && vector[2] == "O") {
            winner = "O";
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDraw(String[][] ticTacToeTable) {
        for (int i = 0; i < ticTacToeTable.length; i++) {
            for (int j = 0; j < ticTacToeTable[0].length; j++) {
                if (ticTacToeTable[i][j] == "") {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkFreeButton(ArrayList<Button> buttonsList) {
        for (int i = 0; i < buttonsList.size(); i++) {
            if (buttonsList.get(i).getText() == "") {
                return true;
            }
        }
        return false;
    }

    void transferMessageButtons(RadioButton X1, RadioButton O1, RadioButton X2, RadioButton O2) {
        X10 = X1;
        O10 = O1;
        X20 = X2;
        O20 = O2;
    }

    void transferMesssageText(String text, String text0) {
        lbl_p1.setText(text);
        lbl_p2.setText(text0);

    }

    void transferPlayers(Player p1, Player p2) {
        p10 = p1;
        p20 = p2;
        s1.setText(p10.getSymbol());
        s2.setText(p20.getSymbol());
    }

    @FXML
    void back(ActionEvent event) {
        try {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = (Scene) ((Node) event.getSource()).getScene();
            Parent root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
            scene.setRoot(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LocalMultiplayerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    void resetButton(ActionEvent event) {
        ((Stage) btn_reset.getScene().getWindow()).close(); // Close the recent window so that there remains only one window
        // Running a new instance of the start method
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OnlineMultiplayerView.fxml"));
            Parent root = loader.load();

            /*OnlineMultiplayerViewController o = loader.getController();

            o.transferMesssageText(lbl_player.getText());
            o.transferMessageButtons(X1, O1);*/
            lbl_p1.getText();
            lbl_p2.getText();

            ((Stage) btn_reset.getScene().getWindow()).close();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = (Scene) ((Node) event.getSource()).getScene();
            stage.setScene(scene);
            stage.show();
            loadGame();

        } catch (IOException ex) {
            ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(LocalMultiplayerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

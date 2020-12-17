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
import javafx.stage.Stage;
import static javaproject.Turn.getTurn;
import static javaproject.Turn.setTurn;



public class OnlineMultiplayerViewController implements Initializable{

	
	private String[][] ticTacToeTable;
	private ArrayList<Button> buttonsList = new ArrayList<>();
        
        Player p1=new Player();
        Player p2=new Player();
        
          public static boolean myTurn;
	public static String gameMode;
	public static String key;
	public static String winner;
	private static String[] buttonText;
   
  
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
    private Label lbl_player2;
    @FXML
    private Label lbl_player1;
    @FXML
    private Label lbl_name1;
    @FXML
    private Label lbl_name2;
    @FXML
    private Button btn_back;
  
   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
            p1.setSymbol("X");
            p2.setSymbol("O");
            lbl_name1.setText(p1.getSymbol());
             lbl_name2.setText(p2.getSymbol());
		loadGame();
	}

    @FXML
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1) {
			move(b1);
                         ChangeTurn();
		}
		if(e.getSource() == b2) {
			move(b2);
                         ChangeTurn();
		}
		if(e.getSource() == b3) {
			move(b3);
                         ChangeTurn();
		}
		if(e.getSource() == b4) {
			move(b4);
                         ChangeTurn();
		}
		if(e.getSource() == b5) {
			move(b5);
                         ChangeTurn();
		}
		if(e.getSource() == b6) {
			move(b6);
                         ChangeTurn();
		}
		if(e.getSource() == b7) {
			move(b7);
                         ChangeTurn();
		}
		if(e.getSource() == b8) {
			move(b8);
                         ChangeTurn();
		}
		if(e.getSource() == b9) {
			move(b9);
                         ChangeTurn();
		}
		
	}
	
	private void move(Button button) {
		if(button.getText() == "") {
			if(gameMode == "twoPlayers") {
				button.setText(playController(buttonsList, ticTacToeTable));
				updateGame();
			
                        }}
	}
	
	private void loadGame() {
		ticTacToeTable = new String [3][3];
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
		
	}

	  public void showVictory(String key) {
        reddeningButtons();
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
        showEndGameAlert(key);
    }


	private void showEndGameAlert(String key) {
		Alert endGame = new Alert(AlertType.INFORMATION);
		if(key != "draw") {
			endGame.setTitle("Victory");
			endGame.setContentText("Player \"" +winner + "\" won.");
		}else {
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
		if (winningChecker(ticTacToeTable)){
			showVictory(key);
			return false;
		}else {
			return true;
		}
	}

	private void loadTicTacToeTable() {
		int temp = 0;
		for(int i = 0; i < ticTacToeTable.length; i++) {
			for (int j = 0; j < ticTacToeTable[0].length; j++) {
				ticTacToeTable[i][j] = buttonsList.get(temp++).getText();
			}
		}

	}
        
      


	public  String playController(ArrayList<Button> buttonsList, String[][] ticTacToeTable) {
		switch (gameMode) {
		
		case "twoPlayers": {
			/*if(myTurn) {
                            
				myTurn = !myTurn;
                               return p1.getSymbol();
				
			}else {
				myTurn = !myTurn;
				return p2.getSymbol();
			}*/
                        if(myTurn)
                        {
                            if(getTurn()==p1)
                            {
                                if(p1.getSymbol()=="X")
                                {
                                    p2.setSymbol("O");
                                    return "X";
                                }else 
                                {
                                    p1.setSymbol("O");
                                     return "O";
                                }
                            }else
                            {
                                if(p2.getSymbol()=="X")
                                {
                                    p1.setSymbol("O");
                                    return "X";
                                }else 
                                {
                                    p2.setSymbol("O");
                                     return "O";
                                }
                            }
                        }
                                
		}
		default :{
			return null;
		}
		}
	}
        
        
    private void ChangeTurn() {
        if (getTurn() == p1) {
            setTurn(p2);
            // Setting underlines visible or invisible acc to player turn
           
        } else {
            setTurn(p1);
            
        }
    }

	public static boolean winningChecker(String[][] ticTacToeTable) {
		buttonText = new String[3];
		//Checking line 0
		for(int i = 0; i < buttonText.length; i++) {
			buttonText[i] = ticTacToeTable[0][i];
			if(checkVictory(buttonText)) {
				key = "line 0";
				return true;
			}
		}
		buttonText = new String[3];
		//Checking line 1
		for(int i = 0; i < buttonText.length; i++) {
			buttonText[i] = ticTacToeTable[1][i];
			if(checkVictory(buttonText)) {
				key = "line 1";
				return true;
			}
		}
		buttonText = new String[3];
		//Checking line 2
		for(int i = 0; i < buttonText.length; i++) {
			buttonText[i] = ticTacToeTable[2][i];
			if(checkVictory(buttonText)) {
				key = "line 2";
				return true;
			}
		}
		buttonText = new String[3];
		//Checking column 0
		for(int i = 0; i < buttonText.length; i++) {
			buttonText[i] = ticTacToeTable[i][0];
			if(checkVictory(buttonText)) {
				key = "column 0";
				return true;
			}
		}
		buttonText = new String[3];
		//Checking column 1
		for(int i = 0; i < buttonText.length; i++) {
			buttonText[i] = ticTacToeTable[i][1];
			if(checkVictory(buttonText)) {
				key = "column 1";
				return true;
			}
		}
		buttonText = new String[3];
		//Checking column 2
		for(int i = 0; i < buttonText.length; i++) {
			buttonText[i] = ticTacToeTable[i][2];
			if(checkVictory(buttonText)) {
				key = "column 2";
				return true;
			}
		}
		buttonText = new String[3];
		//main diagonal
		for (int i = 0; i < buttonText.length; i++) {
			buttonText[i] = ticTacToeTable[i][i];
			if(checkVictory(buttonText)) {
				key = "main diagonal";
				return true;
			}
		}
		buttonText = new String[3];
		//secondary diagonal
		for (int i = 0; i < buttonText.length; i++) {
			int j = buttonText.length - 1 - i;
			buttonText[i] = ticTacToeTable[i][j];
			if(checkVictory(buttonText)) {
				key = "secundary diagonal";
				return true;
			}
		}
		//draw
		if(checkDraw(ticTacToeTable)) {
			key = "draw";
			return true;
		}
		return false;
	}

	public static boolean checkVictory(String[] vector) {
		if(vector[0] == "X" && vector[1] == "X" && vector[2] == "X") {
			winner = "X";
			return true;

		} else if(vector[0] == "O" && vector[1] == "O" && vector[2] == "O") {
			winner = "O";
			return true;
		}else {
			return false;
		}
	}

	public static boolean checkDraw(String[][] ticTacToeTable) {
		for (int i = 0; i < ticTacToeTable.length; i++) {
			for (int j = 0; j < ticTacToeTable[0].length; j++) {
				if(ticTacToeTable[i][j] == "") {
					return false;
				}
			}
		}
		return true;
	}

	private static void easyGameLogic(ArrayList<Button> buttonsList) {
		Random randomMove = new Random();
		int temp;
		while(checkFreeButton(buttonsList)) {
			temp = randomMove.nextInt(9);
			if(buttonsList.get(temp).getText() == "") {
				buttonsList.get(temp).setText("O");
				break;
			}
		}
	}

	private static boolean checkFreeButton(ArrayList<Button> buttonsList) {
		for (int i = 0; i < buttonsList.size(); i++) {
			if(buttonsList.get(i).getText() == "") {
				return true;
			}
		}
		return false;
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

    void transferMessageNames(String name1, String get) {
        lbl_player1.setText(name1);
        lbl_player2.setText(get);

    }
    
    void transferMessageSymbol(String s) {
        p1 = new Player();
        p2 = new Player();
        if (s.equals("X")) {
            p1.setSymbol("O");
            p2.setSymbol("X");
        } else {
            p1.setSymbol("O");
            p2.setSymbol("X");
        }
    }
}

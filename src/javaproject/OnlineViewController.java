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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import players.Player;
import players.SymbolsEnum;
import static players.Turn.setTurn;

/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class OnlineViewController implements Initializable {

    @FXML
    private Button btn_play;
    @FXML
    private RadioButton X1;
    @FXML
    private RadioButton O1;
    @FXML
    private RadioButton X2;
    @FXML
    private RadioButton O2;
    @FXML
    private Button btn_record;
    @FXML
    private Button back;

    Player player1 = new Player();
    Player player2 = new Player();
    final ToggleGroup group1 = new ToggleGroup();
    final ToggleGroup group2 = new ToggleGroup();
    @FXML
    private Label lbl_player1;
    @FXML
    private Label lbl_player2;

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

        } else {
            setTurn(player2);

        }
    }

    @FXML
    void player2Symbol(ActionEvent event) {

        if (X2.isSelected()) {
            O1.setSelected(true);

            player1.setSymbol(SymbolsEnum.CROSS);

            player2.setSymbol(SymbolsEnum.ROUND);

        } else if (O2.isSelected()) {
            X1.setSelected(true);

            player1.setSymbol(SymbolsEnum.ROUND);
            player2.setSymbol(SymbolsEnum.CROSS);
        }

        if (player1.getSymbol() == SymbolsEnum.CROSS) {
            setTurn(player1);

        } else {
            setTurn(player2);

        }
    }

    @FXML
    private void back(ActionEvent event) {

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
    private void play(ActionEvent event) {

        if (X1.isSelected() == false && O1.isSelected() == false) {
            showAlert("Please, Choose X or O.");
        } else {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("OnlineMultiplayerView.fxml"));
                Parent root = loader.load();

                //Get controller of scene2
                OnlineMultiplayerViewController o = loader.getController();
                //Pass whatever data you want. You can have multiple method calls here
               // o.transferMessageText(lbl_player1.getText(), lbl_player2.getText());
                //o.transferMessagePlayers(player1, player2);
                //o.transferMessageButtons(X1, O1, X2, O2);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(LocalMultiplayerViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    void showAlert(String mess) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mess, ButtonType.CANCEL);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(mess);
        alert.show();
    }

    void transferMessageNames(String name1, String new_val) {
        lbl_player1.setText(name1);
        lbl_player2.setText(new_val);

    }

}

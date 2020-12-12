/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
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
    void back(ActionEvent event) {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(IpNetworkViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void play(ActionEvent event) {
        StringTokenizer st;
        st = new StringTokenizer(txt_ip.getText(), ".");

        for (int i = 0; i < 4; i++) {
            if (!st.hasMoreTokens()) {
                //return false;
                showAlert("Ip" + txt_ip.getText() + "Not Valid, please enter another one.");
            }
            int num = Integer.parseInt(st.nextToken());
            if (num < 0 || num > 255) {
                //return false;
                showAlert("Ip" + txt_ip.getText() + "Not Valid, please enter another one.");
            }
        }
        if (st.hasMoreTokens() || txt_ip.getText() == "") {
            //return false;
            showAlert("Ip" + txt_ip.getText() + "Not Valid, please enter another one.");

        }
        //return true;
        try {

            ServerConnection con = new ServerConnection();
            boolean result = con.init(txt_ip.getText());

            if (result) {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(StartViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showAlert(String mess) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mess, ButtonType.CANCEL);
        alert.setTitle("Succedded");
        alert.setHeaderText(null);
        alert.setContentText(mess);
        alert.show();
    }

}

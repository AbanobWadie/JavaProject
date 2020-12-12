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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class LoginViewController implements Initializable {

    //Login Screen
    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_pass;

    @FXML
    private Button btn_login;

    @FXML
    private Hyperlink link_signUp;

    @FXML
    private Hyperlink link_pass;

    @FXML
    public void login(ActionEvent event) {
        if (!txt_name.getText().equals("") && !txt_pass.getText().equals("")) {

            try {

                ServerConnection con = new ServerConnection();
                boolean result = con.SignIn(txt_name.getText(), txt_pass.getText());

                if (result) {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("ListPlayerView.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException ex) {
                Logger.getLogger(StartViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            showAlert("Data isn't coerrect, please Enter again.");
            // JOptionPane.showConfirmDialog(null, "Data isn't coerrect, please Enter again.", "OK",JOptionPane.DEFAULT_OPTION);
        }
    }

    @FXML
    public void signUPLink(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("SignUpView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void forgetPass(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ForgetPasswordView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*    @FXML
    public void loginLink(ActionEvent event)
    {
        try {
                
                 Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
                 Scene scene = new Scene(root);
                 Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }*/
    @FXML
    private void showAlert(String mess) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mess, ButtonType.CANCEL);
        alert.setTitle("Succedded");
        alert.setHeaderText(null);
        alert.setContentText(mess);
        alert.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class ListPlayerViewController implements Initializable {

    /*ObservableList list = FXCollections.observableArrayList("soha", "shimaa", "abanob", "ahmed",
            "soha", "shimaa", "abanob", "ahmed",
            "soha", "shimaa", "abanob", "ahmed");*/

    @FXML
    private VBox vbox;
    @FXML
    private Text txt_list;
    @FXML
    private ListView<String> list_persons;
    @FXML
    private Button btn_back;

    String name1;
    @FXML
    void back(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ServerConnection con = new ServerConnection();
                        ArrayList<String> result = con.getOnlineUsers();
                        ObservableList list = FXCollections.observableArrayList(result);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                list_persons.setItems(list);
                            }
                        });

                        Thread.sleep(3000L);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ListPlayerViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    /*  public void displayed(MouseEvent event)
    {
        String person=list_persons.getSelectionModel().getSelectedItem();
        if(person==null||person.isEmpty())
        {
           showAlert("Cell is Empty.");
        }
        
    }*/
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
        loadData();

        list_persons.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov,
                    String old_val, String new_val) {
                try {

                    //Load second scene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("OnlineMultiplayerView.fxml"));
                    Parent root = loader.load();

                    //Get controller of scene2
                    OnlineMultiplayerViewController o = loader.getController();
                    //Pass whatever data you want. You can have multiple method calls here
                    //o.transferMessage(new_val);

                    Stage stage = (Stage) (list_persons).getScene().getWindow();
                    //stage.setScene(new Scene(root));

                    //Show scene 2 in new window            
                    //  Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex);
                }


                   try {
                       
                       
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OnlineView.fxml"));
            Parent root = loader.load();
             
            //Get controller of scene2
            OnlineViewController o = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            o.transferMessageNames(name1,new_val);
 
                Stage stage=(Stage)(list_persons).getScene().getWindow();
                 //stage.setScene(new Scene(root));
               
            //Show scene 2 in new window            
          //  Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex);
        }
                      
            }

        });

    }

    void transferMessageName1(String text) {
            name1=text;
        }

    
}

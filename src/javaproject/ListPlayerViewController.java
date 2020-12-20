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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author SoHa
 */
public class ListPlayerViewController implements Initializable {

    /* ObservableList list = FXCollections.observableArrayList("soha", "shimaa", "abanob", "ahmed",
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
    ObservableList list;
    String player2;
    String playerRequest;
    boolean recordFlag;
    @FXML
    private Button btn_record;

    @FXML
    void back(ActionEvent event) {
        try {
            ServerConnection.exit();

            Parent root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void playerHistory(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameHistory.fxml"));
            Parent root = loader.load();

            GameHistoryController o = loader.getController();
            // o.translate(history.games,"Online History");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> currentOnlineList = new ArrayList<>();

                ServerConnection.running = true;
                while (ServerConnection.running) {

                    ArrayList<String> result = ServerConnection.getOnlineUsers();

                    if (!result.isEmpty() && !result.get(0).contains("play request from") && !result.get(0).equals("x") && !result.get(0).equals("o")) {
                        list = FXCollections.observableArrayList(result);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                list_persons.setItems(list);
                            }
                        });
                    } else if (!result.isEmpty() && result.get(0).contains("play request from")) {
                        System.out.println("req");
                        String[] arr = result.get(0).split(" ");
                        playerRequest = arr[3];
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (recordFlag) {
                                    dialog2(result.get(0));
                                } else {
                                    dialog(result.get(0));
                                }

                            }
                        });

                    } else if (!result.isEmpty() && result.get(0).equals("x")) {

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    OnlineMultiplayerViewController.gameMode = "twoPlayers";
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("OnlineMultiplayerView.fxml"));
                                    Parent root = loader.load();

                                    OnlineMultiplayerViewController o = loader.getController();

                                    o.transferMessageRecordFlag(recordFlag);
                                    o.transferMessageNames(name1, player2);

                                    o.transferMessageSymbol("X");

                                    Stage stage = (Stage) btn_back.getScene().getWindow();
                                    stage.setScene(new Scene(root));
                                    stage.show();

                                } catch (IOException ex) {
                                    System.err.println(ex);
                                }
                            }
                        });

                        break;
                    } else if (!result.isEmpty() && result.get(0).equals("o")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("OnlineMultiplayerView.fxml"));
                                    Parent root = loader.load();

                                    OnlineMultiplayerViewController o = loader.getController();
                                    o.transferMessageRecordFlag(recordFlag);
                                    o.transferMessageNames(name1, playerRequest);

                                    o.transferMessageSymbol("O");

                                    Stage stage = (Stage) btn_back.getScene().getWindow();
                                    stage.setScene(new Scene(root));
                                    stage.show();

                                } catch (IOException ex) {
                                    System.err.println(ex);
                                }
                            }
                        });
                        break;
                    } else if (result.get(0).equals("close server")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Dialog<ButtonType> dialog = new Dialog<>();
                                dialog.setTitle("Sorry");
                                ButtonType okBtn = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                                dialog.setContentText("Sorry, the server is closed we have to logout you!");
                                dialog.getDialogPane().getButtonTypes().addAll(okBtn);

                                Optional<ButtonType> resultD = dialog.showAndWait();

                                if (resultD.get() == okBtn) {
                                    try {

                                        Parent root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
                                        Scene scene = new Scene(root);
                                        Stage stage = (Stage) btn_back.getScene().getWindow();

                                        stage.setScene(scene);
                                        stage.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            }
                        });

                    }

                    /*try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ListPlayerViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
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

        list_persons.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {

                for (int i = 0; i < list.size(); i++) {
                    if (list_persons.getSelectionModel().getSelectedIndex() == i) {
                        ServerConnection.playWith(list_persons.getItems().get(i));
                        player2 = list_persons.getItems().get(i);
                    }

                }

            }
        });

        /*   list_persons.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov,
                    String old_val, String new_val) {
                try {
                    if (new_val.contains("In-Game")) {
                        /*Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.CANCEL);
                        alert.setTitle("Sorry");
                        alert.setHeaderText(null);
                        alert.setContentText("This player is already in running game you cannot play with him before he finishes his game first");
                        alert.show();
                    } else {
                        //Load second scene
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("OnlineView.fxml"));
                        Parent root = loader.load();

                        //Get controller of scene2
                        OnlineViewController o = loader.getController();
                        //Pass whatever data you want. You can have multiple method calls here
                        o.transferMessageNames(name1, new_val);

                        Stage stage = (Stage) (list_persons).getScene().getWindow();
                        //stage.setScene(new Scene(root));

                        //Show scene 2 in new window            
                        //  Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    }


                } catch (IOException ex) {
                    System.err.println(ex);
                }

            }

        });*/
    }

    void transferMessageName1(String text) {
        name1 = text;
    }

    public void dialog(String mgs) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Request");
        ButtonType acceptBtn = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
        ButtonType refuseBtn = new ButtonType("Refuse", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.setContentText(mgs);
        dialog.getDialogPane().getButtonTypes().addAll(acceptBtn, refuseBtn);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.get() == acceptBtn) {
            ServerConnection.ok();
        } else if (result.get() == refuseBtn) {
            ServerConnection.no();
        }
    }

    public void dialog2(String mgs) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Request");
        ButtonType acceptBtn = new ButtonType("Accept without record", ButtonBar.ButtonData.OK_DONE);
        ButtonType acceptWithRecordBtn = new ButtonType("Accept with record", ButtonBar.ButtonData.OK_DONE);
        ButtonType refuseBtn = new ButtonType("Refuse", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.setContentText(mgs);
        dialog.getDialogPane().getButtonTypes().addAll(acceptBtn, refuseBtn, acceptWithRecordBtn);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.get() == acceptBtn) {
            ServerConnection.ok();
        } else if (result.get() == refuseBtn) {
            ServerConnection.no();
        } else if (result.get() == acceptWithRecordBtn) {
            ServerConnection.ok();
            recordFlag = true;
        }
    }

    @FXML
    private void record(ActionEvent event) {
        btn_record.setDisable(true);
        recordFlag = true;

    }
}

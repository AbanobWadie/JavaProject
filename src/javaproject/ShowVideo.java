/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Alshaimaa
 */
public class ShowVideo {
   
    void video(String Name, boolean flag) {
        MediaPlayer mediaPlayer;
        String vurl ;
        String title;
       if(flag){
           vurl = "file:D:/ITI/java/Project/JavaProject/JavaProject/src/video/winnervideo.mp4";
           title="congaturation";
       }else{
            vurl = "file:D:/ITI/java/Project/JavaProject/JavaProject/src/video/loservideo.mp4";
             title="ooh...Game Over";
       }
        Media media = new Media( vurl);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        Stage dialog = new Stage();
        dialog.setHeight(370);
        dialog.setWidth(370);
        Scene scene = new Scene(new Group(new MediaView(mediaPlayer)));
        dialog.setTitle(title+"  "+Name);
        dialog.setResizable(false);
        dialog.setScene(scene);
        dialog.show();
        dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                mediaPlayer.stop();
                dialog.close();
            }

        });
    
    }
        
  
}

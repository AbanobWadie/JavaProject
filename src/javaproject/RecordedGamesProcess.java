/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Abanob wadie
 */
public class RecordedGamesProcess {
    private ArrayList<Record> records = new ArrayList<>();
    
    public boolean save(Record record){
        String saveLine = record.getPlayer1() + " " + record.getPlayer2();
        for(Map.Entry<String, String> move : record.getMoves().entrySet()) {           
            saveLine += " " + move.getKey() + "|" + move.getValue();
        }
 
        File file = new File(".");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(saveLine);
            fos.close();
            dos.close();
            
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RecordedGamesProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecordedGamesProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public void read(){
        String line;
        
        File file = new File(".");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            while(dis.available() != 0){
                line = dis.readUTF();
                
                String[] arr = line.split(" ");
                Record record = new Record(arr[0], arr[1]);
                
                for(int i = 2; i < arr.length; i++){
                    String[] arr2 = arr[i].split("|");
                    record.setMove(arr2[0], arr2[1]);
                }
                
                records.add(record);
            }
            
            fis.close();
            dis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RecordedGamesProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecordedGamesProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Record {
    private String player1;
    private String player2;
    private HashMap<String, String> moves;

    public Record(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        moves = new HashMap<>();
    }
    
    public void setMove(String position, String symbol){
        moves.put(position, symbol);
    }
    
    public String getPlayer1(){
        return player1;
    }
    public String getPlayer2(){
        return player2;
    }
    public HashMap<String, String> getMoves(){
        return moves;
    }
}

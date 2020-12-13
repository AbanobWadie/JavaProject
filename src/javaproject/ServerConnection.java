/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abanob wadie
 */
public class ServerConnection {

    static private BufferedReader in;
    static private PrintWriter out;

    public boolean init(String ip) {
        try {
            Socket mySocket = new Socket(ip, 5005);
            in = new BufferedReader(new InputStreamReader(
                    mySocket.getInputStream()));
            out = new PrintWriter(
                    mySocket.getOutputStream());

            return true;
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    public boolean SignIn(String userName, String password) {
        out.println("singin " + userName + " " + password);
        out.flush();
        try {
            String requestState = in.readLine();
            if (requestState.equals("true")) {
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean SignUp(String userName, String password) {
        out.println("singup " + userName + " " + password);
        out.flush();
        try {
            String requestState = in.readLine();
            if (requestState.equals("true")) {
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void readThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    
                }
            }
        }).start();

    }

    public void exit() {
        out.println("exit");
        out.flush();

    }

    public void playWith(String name) {
        out.println("play " + name);
        out.flush();
    }

    public ArrayList<String> getOnlineUsers() {
        ArrayList<String> arr = new ArrayList<>();
        try {
            while (in.ready()) {
                String str = in.readLine();
                
                if(!str.equals("end")){
                    String[] strArr = str.split(" ");
                    arr.add(strArr[0]);
                }else{
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public String ckeckPlayRequest() {
        String playrequest = null;
        try {
            playrequest = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playrequest;
    }

    public void ok() {
        out.println("ok");
        out.flush();
    }

    public boolean requestIsAccepted() {
        try {

            String playrequest = in.readLine();
            if (playrequest.equals("ok")) {
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void sendPlayInPostion(boolean win, char c, int postion) {
        if (win) {
            out.println("win " + c + " " + postion);
        } else {
            out.println(c + " " + postion);
        }
        out.flush();
    }

    public String recivePlayInPostion() {

        try {
            return in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

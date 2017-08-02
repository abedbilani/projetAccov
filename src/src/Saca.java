/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Abed Bilani
 */
public class Saca {
//    //save plane in a list
//
//    public static ArrayList<Avion> list;

    // socket to communicate with planes
    public static void main(String[] args) throws IOException {
        System.out.println("lalalala1111111l");
        try {

            // creating sockets
            ServerSocket ss = new ServerSocket(1308);
            Socket s = ss.accept();
            System.out.println("socket created");

            // read client message 
            ObjectInputStream dataIn = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream dataOut = new ObjectOutputStream(s.getOutputStream());
            Avion avion = (Avion) dataIn.readObject();
            System.out.println("data here");

            System.out.println(avion.altittude);
            s.close(); 
       } catch (Exception e) {

        }
    }
    //socket to get commands 
    //socket to send data to the radar

}

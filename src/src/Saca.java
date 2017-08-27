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

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Avion> list = new ArrayList<Avion>();

        new Saca().runSocket(list);
    }

    public static void runSocket(ArrayList<Avion> list) throws IOException, ClassNotFoundException {
        // creating server sockets
        ServerSocket sacaSocket = new ServerSocket(1308);
        ServerSocket radarSocket = new ServerSocket(1309);

        System.out.println("Server up and ready for connections .....");
        while (true) {

            Socket cs = sacaSocket.accept();
            Socket rs = radarSocket.accept();
            System.out.println("socket created");

            // read client message 
            ObjectInputStream dataIn = new ObjectInputStream(cs.getInputStream());
//            ObjectOutputStream dataOut = new ObjectOutputStream(rs.getOutputStream());
            Avion avion = (Avion) dataIn.readObject();

//            dataOut.writeObject(avion);
//            dataOut.flush();
            //add plane to list 
            list.add(avion);
            System.out.println(list.size());
            cs.close();
//            rs.close();
        }
    }
}

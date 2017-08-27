/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import static src.Avion.inputName;

/**
 *
 * @author Abed Bilani
 */
public class Radar {

    static Socket socket;

    //socket to communicate with the system 
    Boolean systemConnect() {
        try {
            socket = new Socket("localhost", 1309);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("RADAR");
        ArrayList<Avion> list = new ArrayList<Avion>();
        try {

            Radar radar = new Radar();
            radar.systemConnect();
            ObjectOutputStream out = new ObjectOutputStream(radar.socket.getOutputStream());

            while (true) {
                ObjectInputStream dataIn = new ObjectInputStream(socket.getInputStream());

                Avion avion = (Avion) dataIn.readObject();
                list.add(avion);

                System.out.println(list.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

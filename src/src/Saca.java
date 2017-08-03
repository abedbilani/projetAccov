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
        new Saca().runSocket();
    }

    public static void runSocket() throws IOException, ClassNotFoundException {
        // creating server sockets
        ServerSocket ss = new ServerSocket(1308);
        System.out.println("Server up and ready for connections .....");
        while (true) {
            Socket s = ss.accept();
            //start thread to handle client requests
            new SerrverThread(s).start();
        }
    }
}

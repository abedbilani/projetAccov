/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Abed Bilani
 */
public class SerrverThread extends Thread {

    Socket s = null;
   
    public SerrverThread(Socket socket) {
        this.s = socket;
    }

    public void run() {
        Avion avion = null;
        try {
            // get object avion and read details 
            ObjectInputStream dataIn = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream dataOut = new ObjectOutputStream(s.getOutputStream());
            Avion planeName = (Avion) dataIn.readObject();
            
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
    }

}

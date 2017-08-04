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
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Abed Bilani
 */
public class Avion implements Serializable {

    static String inputName;
    // properties for avion
    String name;
    // plane coordonates
    int coordX;
    int coordY;
    int altittude;
    //plane speed min speed and max speed
    int vitesseX;
    int vitesseY;
    int vitesseZ;
    // plane angle
    double cap;

    //use socket to communicate
    static Socket socket;

    String[] commands = {"change plane coordinates Usage : changeCoord<x , y ,z>",
        "change plane speed     Usage : vitesse<vx , vy , vz>",
        "change plane angle       Usage : angel<angle>",
        "disconnect plane         Usage : disconnect"
    };

    //move plane in space
    //TODO
    //change speed and cap
    public Avion(String name) {

        this.coordX = 0;
        this.coordY = 0;
        this.altittude = 0;
        this.name = name;
        this.cap = 0;
        this.vitesseX = 0;
        this.vitesseY = 0;
        this.vitesseZ = 0;
    }

    void changeCoord(int x, int y, int z) {
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (z < 0) {
            z = 0;
        }
        this.coordX = x;
        this.coordY = y;
        this.altittude = z;
    }

    void changeVitesse(int vx, int vy, int vz) {
        if (vx < 0) {
            vx = 0;
        }
        if (vy < 0) {
            vy = 0;
        }
        if (vz < 0) {
            vz = 0;
        }

        this.vitesseX = vx;
        this.vitesseY = vy;
        this.vitesseZ = vz;
    }

    void changeAngel(double angle) {

        if (angle >= 0 && angle < 360) {
            this.cap = angle;
        }
    }

    Boolean avionConnect() {
        try {
            socket = new Socket("localhost", 1308);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    void avionDisconnect() {
        try {
            this.socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void listCommands() {
        for (int i = 0; i < commands.length; i++) {
            System.out.println(commands[i]);
        }
    }

    public Avion executeCommand(String com, Avion avion) {
        String[] command = com.split(" ");

        switch (command[0]) {

            // change plane coord
            case "changeCoord":
                int x = Integer.parseInt(command[1]);
                int y = Integer.parseInt(command[2]);
                int z = Integer.parseInt(command[3]);
                avion.changeCoord(x, y, z);
                return avion;

            //change speed
            case "vitesse":
                int vx = Integer.parseInt(command[1]);
                int vy = Integer.parseInt(command[2]);
                int vz = Integer.parseInt(command[3]);
                avion.changeVitesse(vx, vy, vz);
                return avion;

            //change angel
            case "angel":
                double a = Double.parseDouble(command[1]);
                avion.changeAngel(a);
                return avion;

            //disconnect 
            case "disconnect":
                avion.avionDisconnect();
                break;
            //list commands 
            case "commands":
                for (int i = 0; i < commands.length; i++) {
                    System.out.println(commands[i]);
                }
                break;

            default:
                System.out.println("Invalid command");
                break;

        }

        return avion;
    }

    public static void main(String[] args) {
        System.out.println("Type 'commands' to list available commands");
        System.out.println("hello welcome to your plane ");
        System.out.println("Kindly provide a name for this plane");

        try {
            int count = 0;
            //name your plane
            Scanner sc = new Scanner(System.in);
            inputName = sc.nextLine();
            Avion avion = new Avion(inputName);
            avion.avionConnect();
            count = 1;
            ObjectOutputStream out = new ObjectOutputStream(avion.socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(avion.socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                if (count == 1) {
                    String inputReader = bufferedReader.readLine();
                    avion.executeCommand(inputReader, avion);
                }
                //send object Avion to server
                out.writeObject(avion);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

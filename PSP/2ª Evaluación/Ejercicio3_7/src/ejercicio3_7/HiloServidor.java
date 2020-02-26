/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author a18luisdvp
 */
public class HiloServidor extends Thread{

    BufferedReader flujoEntrada;
    PrintWriter flujoSalida;
    Socket socket=null;

    public HiloServidor(Socket socket, ArrayList<Profesor> profesores) {
        this.socket = socket;
        try {
            flujoSalida = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            flujoEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        try {
            String cadena = null;
            cadena = flujoEntrada.readLine();//lee  1º cadena del cliente
            while (!cadena.equals("*")) {

                flujoSalida.println(cadena.toUpperCase());//envia al cliente
                cadena = flujoEntrada.readLine();//lee cadena del cliente
            }
            flujoSalida.close();
            flujoEntrada.close();
            socket.close();
        } catch (IOException ex) {
        }
    }
}

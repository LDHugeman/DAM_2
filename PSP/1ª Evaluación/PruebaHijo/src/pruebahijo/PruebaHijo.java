/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebahijo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 *
 * @author a16daviddss
 */
public class PruebaHijo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            PrintStream ps = new PrintStream(new File(args[0]));
            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
            double numero;
            String line = read.readLine();
            while (!line.equals("fin")) {
                numero = Math.random();

                ps.print(line+" ");
                ps.println(numero);
                line = read.readLine();
            }
            ps.close();
            System.exit(0);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

    }

}

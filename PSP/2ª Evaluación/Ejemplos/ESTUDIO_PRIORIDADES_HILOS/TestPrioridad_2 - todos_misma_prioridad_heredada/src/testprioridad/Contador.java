/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testprioridad;

/**
 *
 * @author user
 */
public class Contador extends Thread {
    //definición de la clase que da lugar a cada  objeto hilo Contador:
    public int cuenta;
    private double suma=0;
    public void run(){
        for(cuenta=0;cuenta<500000;cuenta++)
        {
            suma=suma+Math.random();
        }
     } //el método run() de la clase contiene el código o tarea del hilo
       //acumula  el valor de 500000 números aleatorios.
}

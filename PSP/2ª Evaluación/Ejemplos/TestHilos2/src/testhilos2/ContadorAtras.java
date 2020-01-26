/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testhilos2;

/**
 *
 * @author user
 */
public class ContadorAtras extends Thread {
    public ContadorAtras(String nombre){
        if(nombre!=null) setName(nombre);
        start();
    } //constructor de la clase pone nombre al hilo y lo pone en estado preparado
    public ContadorAtras(){};//constructor
    public void run()
    {
        for (int i=10;i>0;i--)
        { System.out.println(getName()+" "+i+"\t\t");
        
        }
        System.out.println();
    }//proceso que ejecuta el hilo 
}

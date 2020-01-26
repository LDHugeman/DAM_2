/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testhilos1;

/**
 *
 * @author user
 */
public class TestHilos1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ContadorAdelante cuentaAdelante1=new ContadorAdelante("contador++");
        
        System.out.println("");
        /*    
        que ponemos aquï?.
        tal y como está los dos hilos se ejecutan asincronicamnete....
        */
        System.out.println("Finalización del hilo, ahora fin hilo primario");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testhilos2;

/**
 *
 * @author user
 */
public class TestHilos2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ContadorAdelante cuenta1=new ContadorAdelante("contador++");
        ContadorAtras    cuenta2= new ContadorAtras("contador--");
        for(int i=1; i <=10; i++){
            System.out.println("hola desde el hilo principal"+ " "+i+"\r");
        }
        System.out.println("");
        
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_seccioncritica;

/**
 *
 
 */
public class Test_SeccionCritica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        C_Datos datos=new C_Datos(1000);
        // pasamos a todos los hilos la misma referencia 
        C_AdquirirDatos adq1=new C_AdquirirDatos(datos);
        C_AdquirirDatos adq2=new C_AdquirirDatos(datos);
        C_AdquirirDatos adq3=new C_AdquirirDatos(datos);
        C_AdquirirDatos adq4=new C_AdquirirDatos(datos);
        adq1.start();
        adq2.start();
        adq3.start();
        adq4.start();
        
        
        
        
        
    }
}

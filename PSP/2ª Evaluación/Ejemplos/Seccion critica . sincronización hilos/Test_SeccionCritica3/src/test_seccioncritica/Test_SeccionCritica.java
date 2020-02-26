/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_seccioncritica;

/**
 *  en este último ejemplo
 * al diseñar clase C_datos preveemos que sus objetos puedan ser compartidos por más de un hilo
 * y también
 * diseñamos la clase a la que pertenecerán los hilos como cooperantes compartiendo
 * mismo objeto m
 * 
 * ESTO NO DA PROBLEMAS PORQUE DISPONEMOS DE MONITOR REENTRANTE.........
 * 
 */
public class Test_SeccionCritica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        C_Datos datos=new C_Datos(1000);
        C_AdquirirDatos adq1=new C_AdquirirDatos(datos);
        C_AdquirirDatos adq2=new C_AdquirirDatos(datos);
        C_AdquirirDatos adq3=new C_AdquirirDatos(datos);
        adq1.start();
        adq2.start();
        adq3.start();
    }
}

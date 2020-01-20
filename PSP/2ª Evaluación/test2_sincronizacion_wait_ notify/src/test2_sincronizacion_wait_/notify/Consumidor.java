/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test2_sincronizacion_wait_.notify;

/**
 *
 * @author user
 */
public class Consumidor  extends Thread
{
   private CMensaje mensaje;
   public Consumidor(CMensaje c) //constructor recibe objeto compartido por hilos
   {
       mensaje=c;
   }
   public void run() //tarea de los hilos Consumidor
   {
       String msj;
       while(true)
       {
           msj=mensaje.obtener();
           //intenta obtener el monitor del objeto, ejecuta su sección crítica
           // definida en método obtener
           System.out.println("Consumidor  "+ getName()+" obtuvo mensaje número: "+ msj);
           
       }// fin while(true)
   }
    
    
}// fin clase Consumidor

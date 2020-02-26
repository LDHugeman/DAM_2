/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test2_sincronizacion_wait_.notify;

/**
 *
 * @author user
 */
public class Productor  extends Thread
{
    private CMensaje mensaje; //para recibir objeto 
    public Productor(CMensaje c) //constructor, recibe objeto compartido por otro hilo         
    {
      mensaje=c;  
    }
    public void run() //tarea de cualquier hilo Productor
    {
       int numeroMsj;
       while(true)
       {
           numeroMsj=(int)(Math.random()*100); //calcula número de mensaje
           mensaje.almacenar(numeroMsj);
           //intenta obtener el monitor del objeto, ejecuta sección crítica
           System.out.println("Productor  "+getName()+"  almacena el mensaje número: "+numeroMsj);
           try
           {
                int msegs=(int)(Math.random()*100);
                //ponerse a dormir el hilo msegs , hasta producir siguiente mensaje
                sleep(msegs);
           }
           catch(InterruptedException e){}
       } //fin while (true)
    }// fin  run()

}
//los hilos Productor están intentando cada msegs crear un mensaje accediendo
// al objeto de tipo CMensaje e intentando ejecutar su sección crítica almcenar
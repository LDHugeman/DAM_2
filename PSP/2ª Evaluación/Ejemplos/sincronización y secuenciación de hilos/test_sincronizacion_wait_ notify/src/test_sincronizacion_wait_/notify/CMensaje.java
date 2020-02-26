/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_sincronizacion_wait_.notify;

/**
 *
 * @author user
 */
// CMensaje clase a la que pernetecerá objeto a compartir por los hilos cooperantes

public class CMensaje 
{
   private String textoMensaje;
   private int numeroMensaje;
   
   public synchronized void almacenar(int nmsj)
   {
        //definimos aquí una sección crítica, una operación atómica...
       numeroMensaje=nmsj; // recibimos número de mensaje
       //  y ahora suponer aquí operaciones en donde
       // buscamos el mensaje dentro de una tabla de mensajes, mensaje que le corresponde al número recibido.
       textoMensaje="mensajetexto prueba ";
   } //fin almacenar, fin método en donde se define sección crítica.
   
   public synchronized String obtener()
   {
         // segunda sección crítica definida en esta clase.
       //le da un determinado formato al mensaje que tenemos en textoMensaje
       String mensaje;
       mensaje= textoMensaje +"###"+numeroMensaje;
       return mensaje;
       
   }
   
   
   }
//los métodos almacenar y obtener son operaciones atómicas y secciones críticas
//para cualquier hilo que las ejecute adquiere el monitor del objeto , no se pueden 
// interrumpir y no será ejecutado por otro hilo hasta que se libere el cerrojo
// del objeto.

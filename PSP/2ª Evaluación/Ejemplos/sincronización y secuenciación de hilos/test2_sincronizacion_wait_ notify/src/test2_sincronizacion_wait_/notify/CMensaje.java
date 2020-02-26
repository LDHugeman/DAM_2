/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test2_sincronizacion_wait_.notify;

/**
 *
 * @author user
 */
// CMensaje clase a la que pernetecerá objeto a compartir por los hilos cooperantes

public class CMensaje 
{
   private String textoMensaje;
   private int numeroMensaje;
   private boolean disponible_para_mostrar=false;      
        // valor false cuando no hay ningún mensaje que mostrar
   
   public synchronized void almacenar(int nmsj) /// El productor debe tener el monitor del objeto para acceder al metodo sincronizado
   {
        //definimos aquí una sección crítica, una operación atómica...
       // el hilo productor al ejecutar almacenar, lo primero que hace es verificar
       // valor de disponible
       while (disponible_para_mostrar==true) /// el mensaje que tiene el objeto ya ha sido leido, si es asi se impide al productor ejecutar el metodo almacenar y se bloquea el hilo
       { /// Manda al hilo al estado esperando por el monitor del objeto(lo bloquea) y libera el monitor del objeto
            //el último mensaje producido aún no ha sido mostrado, el productor tiene que esperar
           // hay un mensaje para mostrar, valor true. No se puede generar nuevo mensaje
           // sin mostrar anterior generado
           try
               {
                   wait();//el hilo se pone en estado de esperar y CEDE EL MONITOR DEL OBJETO
                          // SIN TERMINAR DE EJECUTAR LA SECCIÓN CRÍTICA
                          //una diferencia importante entre sleep() y wait() es que wait() cede el monitor
                          // del objeto y sleep no
                  //   !!!!!!!los métodos wait(), notify() y notifyAll() 
                  // !!!!!!son invocadas desde dentro de una sección crítica!!!!!
               }catch (InterruptedException e){} 
       } /* aquí retorna proceso cuando se bloqueó con wait(), por eso no vale if tiene
           que ser WHILE!!!!!!!!!!! IMPORTANTE
       */
       numeroMensaje=nmsj; // recibimos número de mensaje
       // suponer aquí operaciones en donde
       // buscamos el mensaje dentro de una tabla de mensajes.
       textoMensaje="mensaje prueba "; // genera nuevo mensaje
       disponible_para_mostrar=true; //ya tenemos nuevo mensaje producido que mostrar
       notifyAll();//el hilo despierta a todos los hilos que están esperando /// Despierta a los que entraron con wait()
                   //para adquirir elmonitor del objeto.
                   // fijarse otra vez desde dentro de sección crítca. al terminar cede monitor del objeto
   } //fin almacenar, fin método en donde se define sección crítica. ///Despiertan a los que non pudieron entrar por no adquirir el monitor del objeto
   
   public synchronized String obtener()
   {
         // segunda sección crítica definida en esta clase.
       //le da un determinado formato al mensaje que tenemos en textoMensaje,
       // el hilo consumidor lo primero
       // que hace es verificar valor de disponible, si es false no hay mensaje que mostrar
       while(disponible_para_mostrar==false) /// No hay mensajes sin leer
       {
           //valor false, no hay ningún mensaje nuevo que mostar, el hilo consumidor
           //que tiene el control del monitor , se pone en estado de espera y cede el control
           // del monitor del objeto
           try
               {
                   wait();
               }catch (InterruptedException e){} 
       }
       
       //!!! importeante while , no sirve if!!!! 
       disponible_para_mostrar=false;
       String mensaje;
       mensaje= textoMensaje +"###"+numeroMensaje;   
       notifyAll();
        
       return mensaje;
       
   }
   
   
   }
//los métodos almacenar y obtener son operaciones atómicas y secciones críticas
//para cualquier hilo que las ejecute adquiere el monitor del objeto , no se pueden 
// interrumpir y no será ejecutado por otro hilo hasta que se libere el cerrojo
// del objeto.

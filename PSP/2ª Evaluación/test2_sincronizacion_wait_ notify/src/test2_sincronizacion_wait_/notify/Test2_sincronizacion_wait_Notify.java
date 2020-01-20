/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test2_sincronizacion_wait_.notify;

/**
 *
 * @author user
 */
public class Test2_sincronizacion_wait_Notify {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    { 
        CMensaje mensaje=new CMensaje(); //objeto que compartirán  los hilos
        Productor productor1=new Productor(mensaje); 
              // primer hilo  de tipo Productor, recibe objeto mensaje
        Consumidor consumidor1= new Consumidor(mensaje);
        // segundo hilo lanzado, hilo  de tipo Consumidor, recibe mismo objeto
        //ambos hilos acceden al mismo recurso/objeto, son hilos cooperantes
        //pero realizan tareas distintas
        //NOS EQUIVOCAMOS Y NO CONTEMPLAMOS MÁS TAREAS DE SINCRONIZACIÓN...
        //lanzamos los hilos,los ponemos en estado listo.
        productor1.start();
        consumidor1.start();
        //El consumidor muestra el último mensaje producido cada vez que el planificador 
        // le asigna tiempo de ejecución.
        //no existe ninguna sincronización entre productor y consumidor.
        //realizamos una ejecución asincrónica y necesitamos establecer además 
        // una secuencia de ejecución en la tarea
        
    }
}

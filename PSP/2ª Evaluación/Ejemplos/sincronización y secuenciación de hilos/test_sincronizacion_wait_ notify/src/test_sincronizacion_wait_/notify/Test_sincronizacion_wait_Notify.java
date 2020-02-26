/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_sincronizacion_wait_.notify;

/**
 *
 * @author user
 */
public class Test_sincronizacion_wait_Notify {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        CMensaje mensaje=new CMensaje(); //objeto que compartiran  los hilos
        Productor productor1=new Productor(mensaje); 
              // primer hilo  de tipo Productor, recibe objeto mensaje
           Consumidor consumidor1= new Consumidor(mensaje);
        // segundo hilo lanzado, hilo  de tipo Consumidor, recibe mismo objeto
        //ambos hilos acceden al mismo recurso/objeto, son hilos cooperantes
        //pero realizan tareas distintas !!!!!!!!!!!!!!
        //NOS EQUIVOCAMOS Y NO CONTEMPLAMOS MÁS TAREAS, ES NECESARIO UNA TAREA  DE SECUENCIACIÓN...
        //TENEMOS QUE: ADEMÁS DE SINCRONIZAR(ESTEBLECIENDO SECCIONES CRÍTICAS) DISEÑAR CÓDIGO
        //PARA SECUENCIAR  --->obtener esta secuencia PCPCPC...
        //lanzamos los hilos,los ponemos en estado listo.
        productor1.start();
        consumidor1.start();
        //El consumidor muestra el último mensaje producido cada vez que el planificador 
        // le asigna tiempo de ejecución.
        //no existe ninguna SECUNECIACIÓN entre productor y consumidor.
        //realizamos una ejecución asincrónica y necesitaríamos establecer además 
        // una secuencia de ejecución en la tarea
        
    }
}

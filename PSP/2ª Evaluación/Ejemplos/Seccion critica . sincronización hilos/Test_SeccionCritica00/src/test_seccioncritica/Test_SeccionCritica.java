/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_seccioncritica;

/**
 *
 * @author a14miguelfc
 */
public class Test_SeccionCritica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        C_Datos datos=new C_Datos(1000);
        /**** datos  ES EL OBJETO al que van a acceder los distintos hilos que lancemos*/
        /* LOS HILOS ACCEDEN AL MISMO OBJETO, NO A DISTINTOS OBJETOS ,INSTANCIA DE LA MISMA CLASE*/ 
        /** EN ESTE EJEMPLO LOS HILOS LANZADOS COOPERAN PARA REALIZAR LA TAREA DE ALMACENAR DATOS
         * EN UN MISMO ARRAY DE  ELEMENTOS DE TIPO DOUBLE
         */
        /**FIJARSE ESTO SERÍA IMPOSIBLE EN PROCESOS (NO COMPARTEN BLOQUE DE MEMORIA***
         * EN EL CASO DE UNA APLICACIÓN MULTIPROCESO HABRÍA QUE ESTABLECER BUFFERS DE COMUNICACIÓN*/
        /* a cada hilo lanzado le pasamos el objeto que comparten en su proceso
           LOS HILOS SON TODOS OBJETOS DE LA MISMA CLASE C_AdquirirDatos, por tanto realizan la misma tarea
           pero al hacerla sobre el mismo recurso , NECESITAN SER SINCRONIZADOS
        **/ 
        C_AdquirirDatos adq1=new C_AdquirirDatos(datos);
        C_AdquirirDatos adq2=new C_AdquirirDatos(datos);
        C_AdquirirDatos adq3=new C_AdquirirDatos(datos);
        /***HASTA AQUÍ LOS  HILOS adq1, adq2 y adq3 están en estado nuevo, 
         * podríamos definir otra prioridad distinta
         * de la heredada, o ponerles nombre
         */
        adq1.start(); 
        adq2.start();
        adq3.start();
        
        /*pasa a estado preparado, se hacen visibles al planificador de hilos,
        este le asignará un núcleo
         * y lo pondrá en estado de ejecución llamando a su  método run */
    }
}

/* COMPARTEN UN RECURSO EN MEMORIA INTERNA, ACCEDEN AL MISMO OBJETO Y AL MISMO TIEMPO,
   ESTO EN APLICACIONES MULTIPROCESO NO SERÍA POSIBLE, LOS PROCESOS HIJO NO COMPARTEN
   LA MISMA IMAGEN DE MEMORIA, SÓLO PODEMOS ESTABLECER TUBERÍAS DE COMUNICACIÓN ENTRE ELLOS

/*
/****        !!!!!!!!!!!! OBSERVAR EL PROCESO ESTÁ MAL DISEÑADO!!!!!!!!
 * PORQUE NO HEMOS REALIZADO LA TAREA DE SINCRONIZAR LOS HILOS,
 * LOS RESULTADOS SON IMPREDECIBLES, SE SOBRRESCRIBEN VALORES EN EL ARRAY, SE SALTAN ELEMENTOS
 * EN ÉL, NOS SALIMOS DE LOS LÍMITES DEL ARRAY ...
 */ 
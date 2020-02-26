/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_seccioncritica;


public class Test_SeccionCritica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        C_Datos datos=new C_Datos(1000);
        /**** datos  ES EL OBJETO al que van a acceder los distintos hilos que lancemos*/
        /* LOS HILOS ACCEDEN AL MISMO OBJETO, NO A DISTINTOS OBJETOS INSTANCIA DE LA MISMA CLASE*/ 
        /** EN ESTE EJEMPLO LOS HILOS LANZADOS COOPERAN PARA REALIZAR LA TAREA DE 
         * ALMACENAR ´VALORES EN UN MISMO ARRAY DE
         * ELEMENTOS DE TIPO DOUBLE
         */
        /**FIJARSE ESTO SERÍA IMPOSIBLE EN PROCESOS (NO COMPARTEN BLOQUE DE MEMORIA***
         
        /* a cada hilo lanzado le pasamos el objeto que comparten en su proceso**/ 
        C_AdquirirDatos adq1=new C_AdquirirDatos(datos);
        C_AdquirirDatos adq2=new C_AdquirirDatos(datos);
        C_AdquirirDatos adq3=new C_AdquirirDatos(datos);
        /***HASTA AQUÍ LOS  HILOS  están en estado nuevo, podríamos definir otra prioridad distinta
         * de la heredada, o ponerles nombre
         */
        adq1.start(); 
        adq2.start();
        adq3.start();
        /*pasan a estado preparado, se hacen visibles al planificador de hilos, este le asignará un núcleo
         * y lo pondrá en estado de ejecución ejecutando su  método run 
        si en un instante dado tenemos más hilos lanzados que núcleos UC disponibles,
        entonces tenemos los hilos ejecutándose concurrentement , la ejecución concurrente nos la proporcionan las rutinas
        del planificador de hilos n hilos ejecutándose concurrentement de ellos j hilos paralelamente */
    }
}

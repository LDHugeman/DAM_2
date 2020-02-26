/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_seccioncritica;

/**
 *
 * 
 */
public class C_AdquirirDatos extends Thread{
    private C_Datos m;
    
    /* constructor
       ATENCIÓN TODOS LOS HILOS DE ESTA CLASE RECIBEN EN SU CONSTRUCTOR
     LA REFRENCIA DE UN OBJETO DE LA CLASE C_datos
      si a varios hilos le pasamos LA MISMA REFERENCIA,
    ENTONCES ESTOS HILOS COMPARTEN EL MISMO OBJETO EN MEMORIA
    ______> SON HILOS COOPERANTES 
    QUE NECESITAREMOS SINCRONIZAR,
    SU EJECUCIÓN TIENE QUE SER SINCRÖNICA
    LA TAREA DE SINCRONIZACIÓN ES RESPONSABILIDAD DEL PROGRAMADOR*/
    
    public C_AdquirirDatos(C_Datos mdatos){
        m=mdatos; /*recibimos el objeto**/
    }
    /** tarea de cada hilo de esta clase:
     * cada hilo está todo el tiempo "do..while", intentando almacenar en el objeto m
     * la colección de numeros double, hasta llenar el array, cada hilo verifica valor 
     * del atribuTo ind , todos los hilos lo manejan
     * todos los hilos en calculos acceden a una posición del array para alacenar en ella
     * un valor y avanzar, moviendo valor ind
     */
    public void run(){
        int i=0;
       
        do{
              i=m.calculos(getName());             
                
        }while(i<m.tamano);
    }
}

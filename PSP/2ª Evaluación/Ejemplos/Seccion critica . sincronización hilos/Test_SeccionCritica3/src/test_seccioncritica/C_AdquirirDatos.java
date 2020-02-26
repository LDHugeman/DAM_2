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
    
    public C_AdquirirDatos(C_Datos mdatos){
        m=mdatos;
    }
    
    @Override
    public void run(){
        int i=0;
        double x;
       do{
           synchronized(m){  
           i=m.calculos(getName()); 
           }
                
        }while(i<m.tamano);
    }
}

/** LOS HILOS DE LA CLASE C_AdquirirDatos, tienen previsto colborar en el mismo proceso
 * con más hilos de la misma clase, OSEA MISMA TAREA
 */
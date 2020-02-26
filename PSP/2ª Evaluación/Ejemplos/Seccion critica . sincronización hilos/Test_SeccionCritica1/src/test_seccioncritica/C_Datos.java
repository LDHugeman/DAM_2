/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_seccioncritica;

/**
 *@author mercedes
 * 
 */

/****primera forma de definir una sección crítica,hemos agrupado el código
 * definido como crítico en un método declarado como synchronized
 * 
 */
public class C_Datos  {
    private double[] datos;
    public int ind=0;
    public int tamano; /* fijarse ind y tamano son public*/
    
    public C_Datos(int n){
        if(n<1){
            n=10;
        }
        this.tamano=n;
        datos=new double[n];
    }
    
    public double obtener(int i){
        return this.datos[i];
    }
    /*establecemos secciones críticas  método asignar y método calculos**/
    public synchronized void asignar(double x,int i){
        datos[i]=x;
    }
    public synchronized int calculos(String hilo){
        if(ind>=tamano){
            return tamano;
        }
        double x=Math.random();
        System.out.println(hilo+" muestra "+ind);
        asignar(x,ind);
        ind++;
        return ind;
    }
}

/***** EL MÉTODO CALCULOS TAMBIÉN LO DEFINIMOS COMO SINCRONIZADO, POR QUÉ???
 *     MANEJO DEL ÍNDICE,ind++, FIJARSE ATRIBUTO ind de tipo public
 * 
 *     ADEMÁS NECESARIO MONITOR REENTRANTE !!!!  
       LANZAMOS N HILOS QUE RECIBEN MISMO OBJETO DE ESTA CLASE
       EN CADA INSTANTE SOLO UN HILO PUEDE EJECUTAR SOBRE EL OBJETO UN MÉTODO
       SINCRONIZADO, TODOS LOS DEMÁS HILOS QUE INTENTEN EJECUTAR asignar
        o claculos QUEDARÁN BLOQUEDOS ESPERANDO ADQUIR EL MONITOR DEL OBJETO 
*/
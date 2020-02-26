/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_seccioncritica;

/**
 *  SEGUNDA FORMA DE DEFINIR CÓDIGO SINCRONIZADO, ES DECIR BLOQUE DE CÓDIGO QUE ES UNA SECCIÓN CRÍTICA
 *  ESTA VEZ NO LO HEMOS ENCAPSULADO EN LOS MÉTODOS DE LA CLASE QUE DEFINE  AL TIPO DE OBJETO
 *  QUE PODRÁ SER COMPARTIDOPOR VARIOS HILOS DEL PROCESO   
 * 
 */

/** FIJARSE NO HEMOS DISEÑADO ESTA CLASE DE OBJETO PARA QUE SEA COMPARTIDA POR VARIOS HILOS**/
public class C_Datos {
    private double[] datos;
  
    public int ind=0;
    public int tamano;
    
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
    public  void asignar(double x,int i){
        
        datos[i]=x;
    }
     
    public  int calculos(String hilo){
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

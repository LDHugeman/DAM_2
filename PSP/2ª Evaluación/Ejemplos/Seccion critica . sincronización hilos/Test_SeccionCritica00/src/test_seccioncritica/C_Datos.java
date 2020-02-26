/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_seccioncritica;

/**
 *
 * @author a14miguelfc
 */

/*** clase que define el objeto que va a ser compartido por distintos hilos
 *   en tiempo de ejecución, hilos que se están ejecutandose al mismo tiempo, de forma paralela
 * , cada uno atendido por una UC núcleo**/

/*
 * @author mercedes
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

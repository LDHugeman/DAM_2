/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_seccioncritica;

/**
 *
 * DISEÑAMOS ESTA CLASE PARA OBJETOS QUE PODRÁN SER COMPARTIDOS POR VARIOS HILOS 
 */
public class C_Datos 
   {
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

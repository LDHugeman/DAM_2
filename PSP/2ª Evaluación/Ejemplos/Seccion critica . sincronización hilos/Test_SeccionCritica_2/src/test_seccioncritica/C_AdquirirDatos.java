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
        /*  EL BLOQUE SINCRONIZADO NO SE PUEDE DEFINIR DESDE
            esta posición, un solo hilo acapararía todo el método run(),
            EL ARRAY SERÍA LLENADO POR UN SOLO HILO, AUNQUE LNCE N HILOS
             El bloque sincronizado no puede contener todo
         el ciclo do.. while */ 
        do{
            x=Math.random(); /** SIMULA OPERACIONES COMPLEJAS DE CALCULO VALOR*/
            synchronized(m){
                if(m.ind>=m.tamano){
                    return;
                }
                
                
                System.out.println(getName()+" tomó la muestra "+m.ind);
                m.asignar(x, m.ind);
                m.ind++;
            }// FIN SECCIÓN CRÍTICA
                //i=m.calculos(this.getName());
        }while(m.ind<m.tamano);
    }
}


/*** HEMOS DEFINIDO DENTRO DE LA TAREA QUE REALIZA EL TIPO DE HILO, EL BLOQUE
 *   DE CODIGO SINCRONIZADO PARA OBJETO
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testprioridad;

/**
 *
 * @author user
 */
//la clase Cuentas se implementa como un hilo encargado de lanzar las  n hilos Contador
//n valor recibido
public class Cuentas extends Thread{
    private static int nCuentas; // número de hilos a lanzar
    private Contador[] cuenta; // cuenta  para referenciar un array para almacenar los n hilos Contador
    //cuenta array para almacenar los n objetos de la clase Contador
    
    public Cuentas(int n)
    {       
      nCuentas=n; // número o cantidad de hilos Contador que se van a lanzar
      setPriority((nCuentas+2)%Thread.MAX_PRIORITY);
            // establecemos la prioridad de este hilo
      cuenta=new Contador[nCuentas]; // arrray de nCuentas elementos Contador
      /*recorremos el array cuenta y  en cada elemento del mismo referenciamos 
       * un hilo Contador, lanzamos cada hilo y le asignamos prioridad. */
      for(int i=0;i<nCuentas;i++)
       {
           cuenta[i]=new Contador();  // nuevo hilo, cada cuenta[i] es un hilo, objeto Contador
                              //cada cuenta[i] hereda los métodos de la clase Thread
           cuenta[i].setPriority((i+3)%Thread.MAX_PRIORITY-1);      
       } // EN ESTE CICLO LOS HILOS REFEENCIADOS EN CADA ELEMENTO DEL ARRAY ESTÁN EN ESTADO NUEVO
    }
   // fin constructor de la clase Cuentas 
    //metodo run() contiene tarea o código a ejecutar por los objetos definidos
    // de esta clase Cuentas (hilos Cuentas)
    public void run(){
        int i;
        boolean hayaHilosVivos;
        //mostrar el nombre y la prioridad de este hilo
        System.out.println(this.getName()+", prioridad: "+this.getPriority());
        
        //lanzar los n hilos Contadores para su ejecución, ponerlos en estado preparado
        for (i=0;i<nCuentas;i++)
        {
            cuenta[i].start();
        }
        // en este cilo vamos poniendo uno a uno cada hilo en estado listo, en la cola dehilos preparados
        do
        {   //Mostrar de cada hilo su nombre, la prioridad y el estado de la cuenta
            //este hilo los arranca y cada cierto tiempo emite un informe de su proceso
          for (i=0;i<nCuentas;i++)
           {
               System.out.print (cuenta[i].getName()+", prioridad: "+cuenta[i].getPriority()+" "+
                       cuenta[i].cuenta+"  ");
           }    
           System.out.print("\r");
           //mientras haya hilos vivos (en ejecución+ preparados+ detenidos)
           hayaHilosVivos=cuenta[0].isAlive();
           //al menos n=1, nCuentas=1, al menos hay un hilo lanzado en cuenta[0]
           for(i=1;i<nCuentas;i++)        
                  hayaHilosVivos=hayaHilosVivos || cuenta[i].isAlive();
            //recorro array desde segundo, buscando un hilo vivo al menos
           //Ahora este hilo dormirá n milisegundos, mientras los n hilos contadores
           // siguen su ejecución.
           try
           {
               int nMilisegundos=(int)(10*Math.pow(2,nCuentas));
               sleep(nMilisegundos);
           }catch(InterruptedException e){};       
            
           
       }while(hayaHilosVivos);
        
        
    }//fin método run()
}// fin clase Cuentas


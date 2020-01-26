/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testdemonio;

/**
 *
 * @author user
 */
//Hilo demonio, suena "bip" cada segundo aproximadamente
public class CDemonio extends Thread {
    public CDemonio()
    {
        setDaemon(true);setName("demoniobip");
        start();
    }//constructor lleva método setDaemon
    public void run()
    {
       char sonido='\u0007';
       while (true)
       { try
          {
              System.out.print ("aaaaa");
              sleep(1000); //cantidad de milisegundos
          }
         catch (InterruptedException e) {  }
         System.out.print(sonido);
          
         
         
       }
    //fijarse método run() está definido como un proceso sin fin!!!!
    // esto es correcto porque está definido como un Demonio
    // al finalizar todos los hilos para los que presta el servicio 
       //la máquina virtual java finalizará este proceso. 
   }  //proceso que ejecuta el hilo
}

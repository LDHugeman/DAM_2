package testhilos1;

/**
 *
 * @author a13danielvc
 */
public class ContadorAdelante extends Thread{

    public ContadorAdelante(String nombre) {
        if (nombre != null) setName(nombre);
        start();
    } //constructor , el método star() pone al nuevo hilo en estado preparado
    // a partir de ahí, es el planificador de hilos el que asignará tiempo 
    // de ejecución en  la ucp, compitiendo con el resto de hilos
    
    public ContadorAdelante() {}
    
    public void run(){
        for(int i=1; i <=1000; i++){
            System.out.println(getName()+ " "+i+"\r");
        }
        System.out.println("");
    }
}

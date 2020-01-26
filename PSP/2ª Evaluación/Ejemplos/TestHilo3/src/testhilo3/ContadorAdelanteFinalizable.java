package testhilo3;

/**
 *
 * @author a13danielvc
 */
public class ContadorAdelanteFinalizable extends Thread{

    private boolean continuar=true;
    // cualquier objeto de esta clase (hilo en ejecución) está previsto
    // que será finalizado por otro hilo, usando método  terminar()
    // en general el hilo que lo arrancó pero cualquier otro hilo que lo reciba
    // como parámetro.
    
    
    public ContadorAdelanteFinalizable(String nombre) {
        if (nombre != null) setName(nombre);
        start();
    } //constructor , el método star() pone al nuevo hilo en estado preparado
    // a partir de ahí, es el planificador de hilos el que asignará tiempo 
    // de ejecución en  la ucp, compitiendo con el resto de hilos

    public ContadorAdelanteFinalizable() {
    }
    
   
    
    public void run(){
        int i=1;
        while(continuar){
            System.out.println(getName()+" "+i++ + "\r");
        }
        System.out.println();   
    }
    
    public void terminar(){
        continuar=false;
    }
}

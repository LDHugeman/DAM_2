package testhilos1;

/**
 *
 * @author a13danielvc
 */
public class ContadorAdelante extends Thread {

    public ContadorAdelante(String nombre) {
        if (nombre != null) {
            setName(nombre);
        }
        start();
    }

    public ContadorAdelante() {
    }

    public void run() {
        for (int i = 1; i <= 1000; i++) {
            System.out.println(getName() + " " + i + "\r");
        }
        System.out.println("");
    }
}

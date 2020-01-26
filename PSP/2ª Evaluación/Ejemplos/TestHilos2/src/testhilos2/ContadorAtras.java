package testhilos2;

/**
 *
 * @author user
 */
public class ContadorAtras extends Thread {

    public ContadorAtras(String nombre) {
        if (nombre != null) {
            setName(nombre);
        }
        start();
    }

    public ContadorAtras() {
    }

    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println(getName() + " " + i + "\t\t");

        }
        System.out.println();
    }
}

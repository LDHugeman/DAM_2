package testhilo3;

/**
 *
 * @author a13danielvc
 */
public class ContadorAdelanteFinalizable extends Thread {

    private boolean continuar = true;

    public ContadorAdelanteFinalizable(String nombre) {
        if (nombre != null) {
            setName(nombre);
        }
        start();
    }

    public ContadorAdelanteFinalizable() {
    }

    public void run() {
        int i = 1;
        while (continuar) {
            System.out.println(getName() + " " + i++ + "\r");
        }
        System.out.println();
    }

    public void terminar() {
        continuar = false;
    }
}

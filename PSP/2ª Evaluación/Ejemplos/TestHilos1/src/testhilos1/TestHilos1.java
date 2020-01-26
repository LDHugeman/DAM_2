package testhilos1;

/**
 *
 * @author user
 */
public class TestHilos1 {

    public static void main(String[] args) {
        ContadorAdelante cuentaAdelante1 = new ContadorAdelante("contador++");
        System.out.println("");
        System.out.println("Finalización del hilo, ahora fin hilo primario");
    }
}

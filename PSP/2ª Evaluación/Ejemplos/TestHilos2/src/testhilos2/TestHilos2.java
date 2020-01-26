package testhilos2;

/**
 *
 * @author user
 */
public class TestHilos2 {

    public static void main(String[] args) {
        ContadorAdelante cuenta1 = new ContadorAdelante("contador++");
        ContadorAtras cuenta2 = new ContadorAtras("contador--");
        for (int i = 1; i <= 10; i++) {
            System.out.println("hola desde el hilo principal" + " " + i + "\r");
        }
        System.out.println("");
    }
}

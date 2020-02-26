/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testprioridad;

/**
 *
 * @author user
 */
public class TestPrioridad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 3;
        try {
            n = Integer.parseInt(args[0]);
            if (n<=0) {
                n = 5;
            }
        } catch (Exception e) {
        }
        Cuentas hiloCuentas = new Cuentas(n);
        hiloCuentas.start();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testprioridad;

import java.sql.SQLException;

/**
 *
 * @author user
 */
public class TestPrioridad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args != null) {
            try {
                int n = Integer.parseInt(args[0]);
                if(n<=0 ){
                    n=6;
                }
                System.out.println(n);
                Cuentas hiloCuentas = new Cuentas(n);
                hiloCuentas.start();
            } catch (NumberFormatException e) {
            }
        }
    }
}

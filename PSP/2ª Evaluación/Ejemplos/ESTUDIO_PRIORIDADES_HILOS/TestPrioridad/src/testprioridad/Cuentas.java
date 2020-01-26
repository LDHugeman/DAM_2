package testprioridad;

/**
 *
 * @author user
 */
public class Cuentas extends Thread {

    private static int nCuentas;
    private Contador[] cuenta;

    public Cuentas(int n) {
        nCuentas = n;
        setPriority((nCuentas + 2) % Thread.MAX_PRIORITY);
        cuenta = new Contador[nCuentas];

        for (int i = 0; i < nCuentas; i++) {
            cuenta[i] = new Contador();
            cuenta[i].setPriority((i + 3) % Thread.MAX_PRIORITY - 1);
        }

    }

    public void run() {
        int i;
        boolean hayaHilosVivos;

        System.out.println(this.getName() + ", prioridad: " + this.getPriority());

        for (i = 0; i < nCuentas; i++) {
            cuenta[i].start();
        }

        do {
            for (i = 0; i < nCuentas; i++) {
                System.out.print(cuenta[i].getName() + ", prioridad: " + cuenta[i].getPriority() + " "
                        + cuenta[i].cuenta + "  ");
            }
            System.out.print("\r");
            hayaHilosVivos = cuenta[0].isAlive();
            for (i = 1; i < nCuentas; i++) {
                hayaHilosVivos = hayaHilosVivos || cuenta[i].isAlive();
            }
            try {
                int nMilisegundos = (int) (10 * Math.pow(2, nCuentas));
                sleep(nMilisegundos);
            } catch (InterruptedException e) {
            };

        } while (hayaHilosVivos);

    }
}

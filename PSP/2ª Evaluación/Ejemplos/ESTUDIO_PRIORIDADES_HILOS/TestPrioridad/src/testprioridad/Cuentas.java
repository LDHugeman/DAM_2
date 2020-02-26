package testprioridad;

/**
 *
 * @author user
 */
public class Cuentas extends Thread {

    private static int numeroHilosCuenta;
    private Contador[] hilosContador;

    public Cuentas(int n) {
        numeroHilosCuenta = n;
        setPriority((numeroHilosCuenta + 2) % Thread.MAX_PRIORITY);
        hilosContador = new Contador[numeroHilosCuenta];

        for (int i = 0; i < numeroHilosCuenta; i++) {
            hilosContador[i] = new Contador();
            hilosContador[i].setPriority((i + 3) % Thread.MAX_PRIORITY - 1);
        }

    }

    public void run() {
        int i;
        boolean hayaHilosContadorVivos;

        System.out.println(this.getName() + ", prioridad: " + this.getPriority());

        for (i = 0; i < numeroHilosCuenta; i++) {
            hilosContador[i].start();
        }

        do {
            for (i = 0; i < numeroHilosCuenta; i++) {
                System.out.print(hilosContador[i].getName() + ", prioridad: " + hilosContador[i].getPriority() + " "
                        + hilosContador[i].cuenta + "  ");
            }
            System.out.print("\r");
            hayaHilosContadorVivos = hilosContador[0].isAlive();
            for (i = 1; i < numeroHilosCuenta; i++) {
                hayaHilosContadorVivos = hayaHilosContadorVivos || hilosContador[i].isAlive();
            }
            try {
                int nMilisegundos = (int) (10 * Math.pow(2, numeroHilosCuenta));
                sleep(nMilisegundos);
            } catch (InterruptedException e) {
            };

        } while (hayaHilosContadorVivos);

    }
}

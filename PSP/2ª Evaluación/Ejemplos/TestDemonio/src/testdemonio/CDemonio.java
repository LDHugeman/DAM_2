package testdemonio;

/**
 *
 * @author user
 */
public class CDemonio extends Thread {

    public CDemonio() {
        setDaemon(true);
        setName("demoniobip");
        start();
    }

    public void run() {
        char sonido = '\u0007';
        while (true) {
            try {
                System.out.print("aaaaa");
                sleep(1000); //cantidad de milisegundos
            } catch (InterruptedException e) {
            }
            System.out.print(sonido);
        }
    }
}

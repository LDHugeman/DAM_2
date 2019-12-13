package excepciones;

/**
 *
 * @author a18luisdvp
 */
public class Excepcion extends Exception {

    private String error;

    public Excepcion(String message) {
        super(message);
        this.error = message;
    }

    public String getError() {
        return error;
    }
}

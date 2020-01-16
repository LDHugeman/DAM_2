package excepciones;

/**
 *
 * @authors a18luisdvp
 */
public class Validar {

    public static boolean esDniValido(String texto) {
        boolean dniEsValido = true;
        try {
            if (texto.length() != 9) {
                dniEsValido = false;
                throw new Excepcion("Longitud errónea");
            }
            if (!texto.substring(0, 8).matches("[0-9]*")) {
                dniEsValido = false;
                throw new Excepcion("Número erróneo");
            }
            if (!texto.substring(8).matches("[A-Za-z]")) {
                dniEsValido = false;
                throw new Excepcion("Letra errónea");
            }
        } catch (Excepcion excepcion) {
            System.out.println(excepcion.getError());
        }
        return dniEsValido;
    }
}

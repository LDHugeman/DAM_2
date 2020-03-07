package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Alberto y David
 */
public class Pedir {

    private static final BufferedReader LEE = new BufferedReader(new InputStreamReader(System.in));
    public static final SimpleDateFormat FORMATO_DIA_MES_ANO = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat FORMATO_HORA = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat FORMATO_ANO_MES_DIA = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat FORMATO_ANO_MES_DIA_EXIST = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat FORMATO_HORA_SEGUNDOS = new SimpleDateFormat("HH:mm:ss");

    public static String texto() {
        boolean esTextoValido;
        String texto = "";
        do {
            try {
                esTextoValido = true;
                texto = LEE.readLine();
            } catch (IOException excepcion) {
                System.err.printf("No es un texto válido %n");
                System.out.printf("Vuelva a introducirlo: ");
                esTextoValido = false;
            }
        } while (!esTextoValido);
        return texto;
    }

    public static char caracter() {
        boolean esCaracterValido;
        char caracter = ' ';
        do {
            try {
                esCaracterValido = true;
                caracter = (char) LEE.read();
            } catch (IOException excepcion) {
                System.err.printf("No es un carácter válido %n");
                System.out.printf("Vuelva a introducirlo: ");
                esCaracterValido = false;
            }
        } while (!esCaracterValido);
        return caracter;
    }

    public static Date fecha() {
        Date fecha = null;
        boolean esFechaValida;
        do {
            try {
                esFechaValida = true;
                String texto = Pedir.texto();
                fecha = FORMATO_DIA_MES_ANO.parse(texto);
            } catch (ParseException excepcion) {
                System.out.println("Fecha incorrecta");
                System.out.printf("Vuelva a introducirla: ");
                esFechaValida = false;
            }
        } while (!esFechaValida);
        return fecha;
    }

    public static Date hora() {
        Date hora = null;
        boolean esHoraValida;
        do {
            try {
                esHoraValida = true;
                String texto = Pedir.texto();
                hora = FORMATO_HORA.parse(texto);
            } catch (ParseException excepcion) {
                System.out.println("Hora incorrecta");
                System.out.printf("Vuelva a introducirla: ");
                esHoraValida = false;
            }
        } while (!esHoraValida);
        return hora;
    }

    public static int numeroEntero() {
        boolean esNumeroEnteroValido;
        int numeroEntero = 0;
        do {
            try {
                esNumeroEnteroValido = true;
                numeroEntero = Integer.parseInt(LEE.readLine());
            } catch (NumberFormatException | IOException excepcion) {
                System.err.printf("Introduzca un número válido %n");
                esNumeroEnteroValido = false;
            }
        } while (!esNumeroEnteroValido);
        return numeroEntero;
    }

    public static float numeroRealFloat() {
        boolean esNumeroRealFloatValido;
        float numeroReal = 0;
        do {
            try {
                esNumeroRealFloatValido = true;
                numeroReal = Float.parseFloat(LEE.readLine());
            } catch (NumberFormatException | IOException excepcion) {
                System.err.printf("Introduzca un número válido %n");
                esNumeroRealFloatValido = false;
            }
        } while (!esNumeroRealFloatValido);
        return numeroReal;
    }

    public static double numeroRealDouble() {
        boolean esNumeroRealDoubleValido;
        double numeroReal = 0;
        do {
            try {
                esNumeroRealDoubleValido = true;
                numeroReal = Double.parseDouble(LEE.readLine());
            } catch (NumberFormatException | IOException excepcion) {
                System.err.printf("Introduzca un número válido %n");
                esNumeroRealDoubleValido = false;
            }
        } while (!esNumeroRealDoubleValido);
        return numeroReal;
    }

    public static byte numeroByte() {
        boolean esNumeroByteValido;
        byte numeroByte = 0;
        do {
            try {
                esNumeroByteValido = true;
                numeroByte = Byte.parseByte(LEE.readLine());
            } catch (NumberFormatException | IOException excepcion) {
                System.out.printf("Introduzca un número válido %n");
                esNumeroByteValido = false;
            }
        } while (!esNumeroByteValido);
        return numeroByte;
    }

    public static long numeroLong() {
        boolean esNumeroLongValido;
        long numeroLong = 0;
        do {
            try {
                esNumeroLongValido = true;
                numeroLong = Long.parseLong(LEE.readLine());
            } catch (NumberFormatException | IOException excepcion) {
                System.out.printf("Introduzca un número válido %n");
                esNumeroLongValido = false;
            }
        } while (!esNumeroLongValido);
        return numeroLong;
    }

    public static boolean duda(String mensaje) {
        boolean esSeguroContinuar = false;
        System.out.println(mensaje);
        System.out.println("[1] Sí");
        System.out.println("[2] No");
        System.out.printf("Seleccione una opción: ");
        byte opcion = numeroByte();
        if (opcion == 1) {
            esSeguroContinuar = true;
        }
        return esSeguroContinuar;
    }
}

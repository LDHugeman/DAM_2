package clinicadentalmongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Alberto y David
 */
public class Conexion {

    private static MongoDatabase baseDatos;
    private static MongoClient cliente;

    public static MongoDatabase establecerConexion() {
        try {
            cliente = new MongoClient("localhost", 27017);
            if (!estaConexionAbierta()) {
                baseDatos = cliente.getDatabase("clinicaDental");
            }
        } catch (Exception excepcion) {
            System.out.println("Error al establecer la conexión");
            System.out.println(excepcion.getMessage());
        }
        return baseDatos;
    }

    public static void cerrarConexion() {
        try {
            if (estaConexionAbierta()) {
                cliente.close();
            }
        } catch (Exception excepcion) {
            System.out.println("Error al cerrar la conexión");
            System.out.println(excepcion.getMessage());
        }
    }

    private static boolean estaConexionAbierta(){
        return baseDatos != null && baseDatos
    }
}

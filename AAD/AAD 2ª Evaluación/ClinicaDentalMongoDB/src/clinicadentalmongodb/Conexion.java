package clinicadentalmongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alberto y David
 */
public class Conexion {

    private static MongoDatabase baseDatos;
    private static MongoClient cliente;

    public static MongoDatabase establecerConexion() {
        try {
            if (!estaConexionAbierta()) {
                Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" ); //Con estas dos sentencias hacemos que no aparezcan 
                mongoLogger.setLevel(Level.SEVERE);                           //mensajes warning en la consola                                  
                cliente = new MongoClient("localhost", 27017);
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
        try{
            cliente.getAddress();
            return true;
        } catch (Exception e){
            return false;
        }
    }
}

package clinicadentalneodatiscliente;
import org.neodatis.odb.ODB;

/**
 *
 * @author David y Alberto
 */
public class Altas {

    public static void guardar(Object objeto){
        ODB odb = Conexion.getSession();
        odb.store(objeto);
    }
}

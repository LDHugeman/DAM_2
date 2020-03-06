package clinicadentalneodatiscliente;

import org.neodatis.odb.ODB;

/**
 *
 * @author David y Alberto
 */
public class Bajas {

    public static void eliminar(Object objeto) {
        ODB odb = Conexion.getSession();
        odb.delete(objeto);
    }
}

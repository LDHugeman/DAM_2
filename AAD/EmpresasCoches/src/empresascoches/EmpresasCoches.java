package empresascoches;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import objetos.CocheAlquiler;

/**
 *
 * @author a18luisdvp
 */
public class EmpresasCoches {

    public static void main(String[] args) {
        Connection conexion = null;
        Statement sentencia = null;
        String url = "jdbc:mysql://localhost:3307/?user=root&password=usbw";
        try {
            conexion = DriverManager.getConnection(url);
        } catch (SQLException excepcion) {
            System.out.println("No hay ningún Driver registrado que reconozca la URL especificada");
            System.out.println(excepcion.getMessage());
        }

        try {
            sentencia = conexion.createStatement();
            Crear.tablas(sentencia);
        } catch (SQLException excepcion) {
            System.out.println("Error al conectarse con el driver que maneja la BD");
            System.out.println(excepcion.getMessage());
        }

        NewHibernateUtil.getSessionFactory();
        byte opcion = 0;
        do {
            opcion = Menu.seleccionarOpcionMenuPrincipal();
            switch (opcion) {
                case 1:
                    Menu.menuAltas(sentencia);
                    break;
                case 2:                   
                    Visualizar.mostrarCochesAlquiler(Consultar.extraerCochesDeAlquiler());
                    System.out.println("--- Introduzca el código del coche de alquiler que desea eliminar ---");                    
                    String codigo = Crear.pedirCodigo();
                    CocheAlquiler cocheAlquiler = Consultar.encontrarCocheAlquilerPorCodigo(codigo);
                    if(cocheAlquiler!=null){
                        Visualizar.mostrarCocheAlquiler(cocheAlquiler);
                        if(Pedir.duda("¿Es este el coche de alquiler que desea eliminar?")){
                            Bajas.cocheAlquiler(cocheAlquiler);
                        }
                    }else{
                        System.err.println("No existe un coche de alquiler con ese código");
                    }
                    break;
                case 3:
                    Menu.menuModificaciones(sentencia);
                    break;
                case 4:
                    Menu.menuVisualizar(sentencia);
                    break;
                case 0:
                    NewHibernateUtil.getSessionFactory().close();
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

}

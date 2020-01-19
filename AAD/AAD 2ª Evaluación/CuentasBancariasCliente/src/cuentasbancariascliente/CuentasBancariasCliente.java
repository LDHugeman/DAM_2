
package cuentasbancariascliente;

/**
 *
 * @author a18luisdvp
 */
public class CuentasBancariasCliente {

    public static void main(String[] args) {
        byte opcion = 0;
        
        do {
            opcion = Menu.seleccionarOpcionMenuPrincipal();
            switch (opcion) {
                case 1:
                    Menu.menuAltas();
                    break;
                case 2:
                    Menu.bajaCuentaPlazoDelCliente();
                    break;
                case 3:
                    Menu.menuModificaciones();
                    break;
                case 4:
                    Menu.menuVisualizar();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }    
}

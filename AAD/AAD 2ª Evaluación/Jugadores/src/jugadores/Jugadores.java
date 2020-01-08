package jugadores;

/**
 *
 * @author a18luisdvp
 */
public class Jugadores {

    public static void main(String[] args) {
        byte opcion = 0;
        
        do {
            opcion = Menu.seleccionarOpcionMenuPrincipal();
            switch (opcion) {
                case 1:
                    Menu.menuAltas();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

}

package metodos;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author
 */
public class Menu {
    public static int menuPrin(){
        System.out.print("\u250c");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.print("\u2510");
        System.out.println("\n\u2502                      MENU PRINCIPAL                        \u2502");
        System.out.print("\u251c");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2524");
        System.out.println("\u25021.-Insercion de nuevas filas.                               \u2502"
                + "\n\u25022.-Borrado de filas.                                        \u2502"
                + "\n\u25023.-Modificaciones.                                          \u2502"
                + "\n\u25024.-Consultas.                                               \u2502"
                + "\n\u25020.-Salir                                                    \u2502");
        System.out.print("\u2514");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2518");
        System.out.print(" > ");
        try{
            return new Scanner(System.in).nextInt();
        }catch(InputMismatchException e){
            return -1;
        }
        
    }
    public static int menuAlta(){
        System.out.print("\u250c");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.print("\u2510");
        System.out.println("\n\u2502                       MENU ALTAS                           \u2502");
        System.out.print("\u251c");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2524");
        System.out.println("\u25021.-Insercion de nuevo autor.                                \u2502"
                + "\n\u25022.-Insercion de libros.                                     \u2502"
                + "\n\u25023.-Insercion de telefonos.                                  \u2502"
                + "\n\u25020.-Salir                                                    \u2502");
        System.out.print("\u2514");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2518");
        System.out.print(" > ");
        try{
            return new Scanner(System.in).nextInt();
        }catch(InputMismatchException e){
            return -1;
        }
        
    }
    
    public static int menuBajas(){
        System.out.print("\u250c");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.print("\u2510");
        System.out.println("\n\u2502                        MENU BAJAS                          \u2502");
        System.out.print("\u251c");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2524");
        System.out.println("\u25021.-Bajas de autores                                         \u2502"
                + "\n\u25022.-Bajas de libros.                                         \u2502"
                + "\n\u25023.-Bajas de telefonos.                                      \u2502"
                + "\n\u25020.-Salir                                                    \u2502");
        System.out.print("\u2514");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2518");
        System.out.print(" > ");
        try{
            return new Scanner(System.in).nextInt();
        }catch(InputMismatchException e){
            return -1;
        }
        
    }
    
    public static int menuMod(){
        System.out.print("\u250c");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.print("\u2510");
        System.out.println("\n\u2502                     MENU MODIFICACIONES                    \u2502");
        System.out.print("\u251c");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2524");
        System.out.println("\u25021.-Modificar precio libro                                   \u2502"
                + "\n\u25022.-Modificar telefono                                       \u2502"
                + "\n\u25020.-Salir                                                    \u2502");
        System.out.print("\u2514"); 
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2518");
        System.out.print(" > ");
        try{
            return new Scanner(System.in).nextInt();
        }catch(InputMismatchException e){
            return -1;
        }
        
    }
    
    public static int menuCon(){
        System.out.print("\u250c");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.print("\u2510");
        System.out.println("\n\u2502                        MENU CONSULTAS                      \u2502");
        System.out.print("\u251c");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2524");
        System.out.println("\u25021.-Consultar libro.                                         \u2502"
                + "\n\u25022.-Consultar autor.                                         \u2502"
                + "\n\u25023.-Todos los libros.                                        \u2502"
                + "\n\u25024.-Todos los autores.                                       \u2502"
                + "\n\u25020.-Salir                                                    \u2502");
        System.out.print("\u2514");
        for (int i = 0; i < 35; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2518");
        System.out.print(" > ");
        try{
            return new Scanner(System.in).nextInt();
        }catch(InputMismatchException e){
            return -1;
        }
        
    }
}

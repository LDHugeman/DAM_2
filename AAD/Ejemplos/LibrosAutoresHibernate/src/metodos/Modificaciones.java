package metodos;

import clases.Autor;
import clases.Libro;
import clases.Telefono;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class Modificaciones {

    public static void modificarLibro() {
        int existe = Visualizar.visualizarLibros();
        int cod;
        float precio;
        char conf;
        Libro l = null;
        Scanner scan = new Scanner(System.in);
        if (existe == 1) {
            System.out.println("Introduce el codigo del libro a modificar.");
            System.out.print(" > ");
            cod = scan.nextInt();
            scan.nextLine();
            l = AdicionalesBD.comprobarLibro(cod);
            if (l != null) {
                System.out.println("Precio actual del libro: " + l.getPrecio());
                System.out.println("Introduzca el precio nuevo.");
                System.out.print(" > ");
                precio = scan.nextFloat();
                scan.nextLine();
                System.out.println("Esta seguro de querer modificar el libro?");
                System.out.print(" > ");
                conf = scan.nextLine().toLowerCase().charAt(0);
                if (conf == 's') {
                    l.setPrecio(precio);
                    Inserciones.guardarModificar(l);
                    System.out.println("Libro modificado.");
                } else {
                    System.out.println("No se ha modificado el libro");
                }
            } else {
                System.out.println("El libro no existe");
            }
        } else {
            System.out.println("No hay ningun libro.");
        }
    }

    public static void modificarTelefono() {
        int existe = Visualizar.visualizarAutores();
        String dni, telf;
        char conf;
        Autor a = null;
        Telefono t = null;
        Scanner scan = new Scanner(System.in);
        if (existe == 1) {
            System.out.println("Introduce el dni del autor al cual quieres modificar el telefono.");
            System.out.print(" > ");
            dni = scan.nextLine();
            a = AdicionalesBD.comprobarAutor(dni);
            if (a != null) {
                t = (Telefono) AdicionalesBD.busqueda1Variable1Objeto(Telefono.class.getName(), "dni", dni);
                if (t != null) {
                    System.out.println("El telefono actual es: " + t.getTelf());
                    System.out.println("Introduce el nuevo telefono.");
                    System.out.print(" > ");
                    telf = scan.nextLine();
                    System.out.println("Esta seguro de querer modificar el telefono?");
                    System.out.print(" > ");
                    conf = scan.nextLine().toLowerCase().charAt(0);
                    if (conf == 's') {
                        t.setTelf(telf);
                        Inserciones.guardarModificar(t);
                        System.out.println("Telefono modificado.");
                    } else {
                        System.out.println("No se ha modificado.");
                    }
                } else {
                    System.out.println("No hay telefonos de ese autor.");
                }
            } else {
                System.out.println("El autor introducido no existe.");
            }
        } else {
            System.out.println("No hay autores.");
        }
    }
}

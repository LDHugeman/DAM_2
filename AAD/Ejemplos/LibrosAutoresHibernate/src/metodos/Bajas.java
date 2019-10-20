package metodos;

import clases.Autor;
import clases.Libro;
import clases.Telefono;
import java.util.Scanner;
import librosautoreshibernate.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author 
 */
public class Bajas {
    
    public static void bajasAutor() {
        int existe = Visualizar.visualizarAutores();
        String dni;
        char confirmacion;
        Autor autor = null;
        Scanner scan = new Scanner(System.in);
        if (existe == 1) {
            System.out.println("Introduce el dni del autor a borrar.");
            System.out.print(" > ");
            dni = scan.nextLine();
            autor = AdicionalesBD.comprobarAutor(dni);
            if (autor != null) {
                System.out.println("Esta seguro de querer borrar el autor?");
                System.out.print(" > ");
                confirmacion = scan.nextLine().toLowerCase().charAt(0);
                if (confirmacion == 's') {
                    eliminar(autor);
                } else {
                    System.out.println("No se ha borrado al autor");
                }
            } else {
                System.out.println("El autor no existe");
            }
        } else {
            System.out.println("No hay ningun autor.");
        }
    }
    
    public static void bajasLibro() {
        int existe = Visualizar.visualizarLibros();
        int cod;
        char conf;
        Libro l = null;
        Scanner scan = new Scanner(System.in);
        if (existe == 1) {
            System.out.println("Introduce el codigo del libro a borrar.");
            System.out.print(" > ");
            cod = scan.nextInt();
            scan.nextLine();
            l = AdicionalesBD.comprobarLibro(cod);
            if (l != null) {
                System.out.println("Esta seguro de querer borrar el libro?");
                System.out.print(" > ");
                conf = scan.nextLine().toLowerCase().charAt(0);
                if (conf == 's') {
                    eliminar(l);
                    System.out.println("Libro borrado.");
                } else {
                    System.out.println("No se ha borrado el libro");
                }
            } else {
                System.out.println("El libro no existe");
            }
        } else {
            System.out.println("No hay ningun libro.");
        }
    }
    
    public static void bajasTelf() {
        int existe = Visualizar.visualizarTelf();
        String telf;
        char conf;
        Telefono t = null;
        Scanner scan = new Scanner(System.in);
        if (existe == 1) {
            System.out.println("Introduce el dni asociado al telefono a borrar.");
            System.out.print(" > ");
            telf = scan.nextLine();
            t = AdicionalesBD.comprobarTelf(telf);
            if(t!=null){
                System.out.println("Esta seguro de querer borrar el telefono?");
                System.out.print(" > ");
                conf = scan.nextLine().toLowerCase().charAt(0);
                if (conf == 's') {
                    eliminar(t);
                    System.out.println("Telefono borrado.");
                } else {
                    System.out.println("No se ha borrado el telefono.");
                }
            }else{
                System.out.println("El telefono introducido no existe.");
            }
        } else {
            System.out.println("No hay telefonos.");
        }
    }
    
    private static void eliminar(Object objeto) {
        
        Session sesion;
        try {
            sesion = NewHibernateUtil.getSession();
            sesion.beginTransaction();
            sesion.delete(objeto);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}

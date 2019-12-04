package metodos;

import clases.Autor;
import clases.Libro;
import clases.Telefono;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import librosautoreshibernate.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author 
 */
public class Consultas {

    public static void datosLibro() {
        Scanner scan = new Scanner(System.in);
        int existe = Visualizar.visualizarLibros();
        int cod;
        if (existe == 1) {
            System.out.println("Introduce el codigo del libro a visualizar");
            System.out.print(" > ");
            cod = scan.nextInt();
            scan.nextLine();
            Libro l = null;
            l = AdicionalesBD.comprobarLibro(cod);

            if (l != null) {
                Autor a = AdicionalesBD.comprobarAutor(l.getAutor().getDni());
                Telefono t = AdicionalesBD.comprobarTelf(l.getAutor().getDni());
                System.out.print("Titulo: " + l.getTitulo() + "\tPrecio: " + l.getPrecio()
                        + "\tAutor: " + l.getAutor().getDni() + "\tNacionalidad: " + a.getNacionalidad());
                if (t != null) {
                    System.out.println("\tTelefono: " + t.getTelf());
                } else {
                    System.out.println();
                }
            }
        } else {
            System.out.println("No hay libros.");
        }

    }

     /*public static void visualizarLibrosA() {
        Scanner scan = new Scanner(System.in);
        int existe = Visualizar.visualizarAutores();
        Session s;
        String dni;
        List<Object> libros;
        if (existe == 1) {
            System.out.println("Introduce el dni del autor a buscar.");
            System.out.print(" > ");
            dni = scan.nextLine();
            Autor a = null;
            a = AdicionalesBD.comprobarAutor(dni);
            s = NewHibernateUtil.getSession();
            libros = s.createQuery("FROM " + Libro.class.getName() + " WHERE autor='" + a.getDni() + "'").list();
            s.close();
            if (a != null) {
                System.out.println("Lista de libros de: " + a.getNombre());
                System.out.println(String.format("%1$-22s %2$s", "Titulo: ", "Precio: "));
                for (Object l : libros) {
                    System.out.println(String.format("%1$-22s %2$.2f€", ((Libro) l).getTitulo(), ((Libro) l).getPrecio()));
                }
            }

        } else {
            System.out.println("No hay autores.");
        }

    }*/
    public static void visualizarLibrosA() {
        Scanner scan = new Scanner(System.in);
        int existe = Visualizar.visualizarAutores();
        Session s;
        String dni;
        if (existe == 1) {
            System.out.println("Introduce el dni del autor a buscar.");
            System.out.print(" > ");
            dni = scan.nextLine();
            Autor a = null;

            s = NewHibernateUtil.getSession();
            a = (Autor) s.get(Autor.class, dni);
            //  a = AdicionalesBD.comprobarAutor(dni);
            //Set<Libro> libros = a.getLibros();
            if (a != null) {
                System.out.println("Lista de libros de: " + a.getNombre());
                System.out.println(String.format("%1$-22s %2$s", "Titulo: ", "Precio: "));
                for (Libro l : a.getLibros()) {
                    System.out.println(String.format("%1$-22s %2$.2f€", l.getTitulo(), l.getPrecio()));
                }
            }else{
                System.out.println("El autor introducido no existe.");
            }
            s.close();
        }

    }

  /*  public static void todosAutores() {
        try {
            Session s;
            s = NewHibernateUtil.getSession();
            List<Object> autores = s.createCriteria(Autor.class).list();
            s.close();
            for (Object a : autores) {
                System.out.println("\nAutor: " + ((Autor) a).getNombre());
                s = NewHibernateUtil.getSession();
                List<Object> libros
                        = s.createQuery("FROM " + Libro.class.getName() + " WHERE autor='" + ((Autor) a).getDni() + "'").list();
                System.out.println(String.format("%1$-22s %2$s", "Titulo: ", "Precio: "));
                for (Object l : libros) {
                    System.out.println(String.format("%1$-22s %2$.2f€", ((Libro) l).getTitulo(), ((Libro) l).getPrecio()));
                }
            }
        } catch (HibernateException e) {
            System.out.println(e);
        }
    }*/
    public static void todosAutores() {
        try {
            Session s;
            s = NewHibernateUtil.getSession();
            List<Object> autores = s.createCriteria(Autor.class).list();
 //           s.close();
            for (Object a : autores) {
                System.out.println("\nAutor: " + ((Autor) a).getNombre());
//                List<Object> libros
  //                      = s.createQuery("FROM " + Libro.class.getName() + " WHERE autor='" + ((Autor) a).getDni() + "'").list();
                System.out.println(String.format("%1$-22s %2$s", "Titulo: ", "Precio: "));
                for (Libro l : ((Autor)a).getLibros()) {
                    System.out.println(String.format("%1$-22s %2$.2f€", l.getTitulo(), l.getPrecio()));
                }
            }
            s.close();
        } catch (HibernateException e) {
            System.out.println(e);
        }
    }
    
}

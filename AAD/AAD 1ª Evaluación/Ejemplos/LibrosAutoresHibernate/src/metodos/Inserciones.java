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
public class Inserciones {

    public static void insertarAutor() {
        String dni, nombre, nacionalidad;
        char conf;
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el dni del autor.");
        System.out.print(" > ");
        dni = scan.nextLine();
        System.out.println("Introduce el nombre del autor.");
        System.out.print(" > ");
        nombre = scan.nextLine();
        System.out.println("Introduce la nacionalidad del autor.");
        System.out.print(" > ");
        nacionalidad = scan.nextLine();
        Autor a = new Autor(dni, nombre, nacionalidad);
        guardarModificar(a);
        System.out.println("Desea introducir un libro para ese autor?[s/n]");
        System.out.print(" > ");
        conf = scan.nextLine().toLowerCase().charAt(0);
        if (conf == 's') {
            do {
                Libro l = crearLibro(a);
                a.getLibros().add(l);
                guardarModificar(l);
                System.out.println("Desea introducir otro libro para ese autor?[s/n]");
                System.out.print(" > ");
                conf = scan.nextLine().toLowerCase().charAt(0);
            } while (conf == 's');
        }
        guardarModificar(a);
        System.out.println("Autor introducido.");
    }

    public static void insertarLibro() {
        Scanner scan = new Scanner(System.in);
        String titulo;
        float precio;
        char conf;
        String dni;
        System.out.println("Introduce el titulo del libro.");
        System.out.print(" > ");
        titulo = scan.nextLine();
        System.out.println("Introduce el precio del libro.");
        System.out.print(" > ");
        precio = scan.nextFloat();
        scan.nextLine();
        Libro libro = new Libro(titulo, precio);
        Autor autor = null;
        do {
            int opcion;
            opcion = Visualizar.visualizarAutores();
            if (opcion == 1) {
                autor = buscarAutor();
                if (autor == null) {
                    System.out.println("El autor introducido no existe, desea crearlo?[s/n].");
                    conf = scan.nextLine().toLowerCase().charAt(0);
                    if (conf == 's') {
                        autor = crearAutor(libro);
                    }
                }
            } else {
                autor = crearAutor(libro);
            }
        } while (autor == null);

        libro.setAutor(autor);
        guardarModificar(libro);
        System.out.println("Libro introducido.");

    }

    private static Autor crearAutor(Libro l) {
        String dni, nombre, nacionalidad;
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el dni del autor.");
        System.out.print(" > ");
        dni = scan.nextLine();
        System.out.println("Introduce el nombre del autor.");
        System.out.print(" > ");
        nombre = scan.nextLine();
        System.out.println("Introduce la nacionalidad del autor.");
        System.out.print(" > ");
        nacionalidad = scan.nextLine();
        Autor a = new Autor(dni, nombre, nacionalidad);
        a.getLibros().add(l);
        guardarModificar(a);
        return a;
    }

    private static Libro crearLibro(Autor a) {
        Scanner scan = new Scanner(System.in);
        String titulo;
        float precio;
        String dni;
        System.out.println("Introduce el titulo del libro.");
        System.out.print(" > ");
        titulo = scan.nextLine();
        System.out.println("Introduce el precio del libro.");
        System.out.print(" > ");
        precio = scan.nextFloat();
        scan.nextLine();

        Libro l = new Libro(titulo, precio);
        l.setAutor(a);
        return l;
    }

    public static void insertarTelf() {
        Scanner scan = new Scanner(System.in);
        String telf;
        String dni;
        System.out.println("Introduce el telefono del autor.");
        System.out.print(" > ");
        telf = scan.nextLine();
        Autor a = null;
        do {
            Visualizar.visualizarAutores();
            a = buscarAutor();
            if (a == null) {
                System.out.println("El autor introducido no existe.");
            }
        } while (a == null);
        Telefono t = new Telefono(telf);
        t.setAutor(a);
        t.setDni(a.getDni());
        guardarModificar(t);
        System.out.println("Telefono introducido.");

    }

    private static Autor buscarAutor() {
        Scanner scan = new Scanner(System.in);
        Autor a = null;
        Session s;
        String dni;

        System.out.println("Introduce el DNI del autor.");
        System.out.print(" > ");
        dni = scan.nextLine();
        try {
            s = NewHibernateUtil.getSession();
            a = (Autor) s.get(Autor.class, dni);
            s.close();

        } catch (HibernateException e) {
            System.out.println(e);
        }
        return a;
    }

    public static void guardarModificar(Object objeto) {

        Session sesion;
        try {
            sesion = NewHibernateUtil.getSession();
            sesion.beginTransaction();
            sesion.saveOrUpdate(objeto);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}

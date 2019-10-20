package metodos;

import clases.Autor;
import clases.Libro;
import clases.Telefono;
import java.util.List;
import librosautoreshibernate.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author
 */
public class Visualizar {

    public static int visualizarAutores() {
        try {
            Session s;
            s = NewHibernateUtil.getSession();
            List<Object> autores = s.createCriteria(Autor.class).list();
            s.close();
            if (!autores.isEmpty()) {
                System.out.print("\u250c");
                for (int i = 0; i < 35; i++) {
                    System.out.print("\u2500");
                }
                System.out.print("\u2510");
                System.out.println("\n\u2502                           AUTORES                          \u2502");
                System.out.print("\u251c");
                for (int i = 0; i < 35; i++) {
                    System.out.print("\u2500");
                }
                System.out.println("\u2524");
                System.out.println("\u2502 DNI                     Nombre                Nacionalidad \u2502");
                System.out.print("\u251c");
                for (int i = 0; i < 35; i++) {
                    System.out.print("\u2500");
                }
                System.out.println("\u2524");

                for (Object o : autores) {
                    System.out.println("\u2502 " + String.format("%1$-24s %2$-20s %3$-12s %4$s", ((Autor) o).getDni(), ((Autor) o).getNombre(), ((Autor) o).getNacionalidad(), "\u2502"));
                }
                System.out.print("\u2514");
                for (int i = 0; i < 35; i++) {
                    System.out.print("\u2500");
                }
                System.out.println("\u2518");
                return 1;
            } else {
                System.out.println("No hay autores.");
                return 0;
            }
        } catch (HibernateException e) {
            System.out.println(e);
        }
        return 1;
    }

    public static int visualizarLibros() {
        try {
            Session s;
            s = NewHibernateUtil.getSession();
            List<Object> libros = s.createCriteria(Libro.class).list();
            s.close();
            if (!libros.isEmpty()) {
                System.out.print("\u250c");
                for (int i = 0; i < 35; i++) {
                    System.out.print("\u2500");
                }
                System.out.print("\u2510");
                System.out.println("\n\u2502                          LIBROS                            \u2502");
                System.out.print("\u251c");
                for (int i = 0; i < 35; i++) {
                    System.out.print("\u2500");
                }
                System.out.println("\u2524");
                System.out.println("\u2502 Cod                      Titulo                     Precio \u2502");
                System.out.print("\u251c");
                for (int i = 0; i < 35; i++) {
                    System.out.print("\u2500");
                }
                System.out.println("\u2524");

                for (Object o : libros) {
                    System.out.println("\u2502 " + String.format("%1$-24s %2$-26s %3$-6.2f %4$s", ((Libro) o).getIdlibro(), ((Libro) o).getTitulo(), ((Libro) o).getPrecio(), "\u2502"));
                }
                System.out.print("\u2514");
                for (int i = 0; i < 35; i++) {
                    System.out.print("\u2500");
                }
                System.out.println("\u2518");
                return 1;
            } else {
                System.out.println("No hay libros.");
                return 0;
            }
        } catch (HibernateException e) {
            System.out.println(e);
        }
        return 1;
    }

    public static int visualizarTelf() {

        try {
            Session s;
            s = NewHibernateUtil.getSession();
            List<Object> telefonos = s.createCriteria(Telefono.class).list();
            s.close();
            if (!telefonos.isEmpty()) {
                System.out.print("\u250c");
                for (int i = 0; i < 35; i++) {
                    System.out.print("\u2500");
                }
                System.out.print("\u2510");
                System.out.println("\n\u2502                         TELEFONOS                          \u2502");
                System.out.print("\u251c");
                for (int i = 0; i < 35; i++) {
                    System.out.print("\u2500");
                }
                System.out.println("\u2524");
                System.out.println("\u2502  DNI                                             Telefono  \u2502");
                for (Object o : telefonos) {
                    System.out.println("\u2502  " + String.format("%1$-47s %2$-9s %3$s", ((Telefono) o).getDni(), ((Telefono) o).getTelf(), "\u2502"));
                }
                System.out.print("\u2514");
                for (int i = 0; i < 35; i++) {
                    System.out.print("\u2500");
                }
                System.out.println("\u2518");
                return 1;
            }else{
                System.out.println("No hay Telefonos.");
                return 0;
            }
        } catch (HibernateException e) {
            System.out.println(e);
        }
        return 1;
    }

   

}

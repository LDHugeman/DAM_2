package metodos;

import clases.Autor;
import clases.Libro;
import clases.Telefono;
import librosautoreshibernate.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author
 *
 */
public class AdicionalesBD {

    public static Autor comprobarAutor(String dni) {

        Session sesion;
        Autor a = null;
        try {
            sesion = NewHibernateUtil.getSession();
            a = (Autor) sesion.get(Autor.class, dni);
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

        return a;
    }

    public static Libro comprobarLibro(int cod) {
        Session sesion;
        Libro l = null;
        try {
            sesion = NewHibernateUtil.getSession();
            l = (Libro) sesion.get(Libro.class, cod);
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return l;
    }

    public static Telefono comprobarTelf(String dni) {
        Session s;
        Telefono t = null;
        try {
            s = NewHibernateUtil.getSession();
            t = (Telefono) s.get(Telefono.class, dni);
            s.close();
        } catch (HibernateException e) {
            System.out.println(e);
        }
        return t;
    }
    
   /* public static Telefono comprobarTelfD(String dni){
        Session s;
        Telefono t = null;
        try{
            s = NewHibernateUtil.getSession();
            s.createCriteria(Telefono.class).add(Restrictions.eq("dni", dni));
            t =(Telefono) s.get(Telefono.class,dni);
            System.out.println(t.getTelf());
            return t;
        }catch(HibernateException e){
            System.out.println(e);
        }
        return t;
    }*/
    
    public static Object busqueda1Variable1Objeto (String pojo, String columna, String variable) {
        
        Session sesion; int c = 0; Object obj = null;
        try {
            sesion = NewHibernateUtil.getSession();
            obj = sesion.createQuery("FROM " + pojo + " WHERE " + columna + " = '" + variable + "'").uniqueResult();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        
        return obj;
    }
}

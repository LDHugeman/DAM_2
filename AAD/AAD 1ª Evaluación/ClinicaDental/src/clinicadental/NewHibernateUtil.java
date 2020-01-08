package clinicadental;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @authors Alberto y David
 */
public class NewHibernateUtil {

    private static final SessionFactory sessionFactory;
    private static Session openedSession;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Abre una sesion y la guarda en caso de haber ya una abierta la retorna
     *
     * @return Session guardada
     */
    public static Session getSession() {
        if (!(estaSesionAbierta())) {
            openedSession = sessionFactory.openSession();
        }
        return openedSession;

    }

    /**
     * Cierra la sesion guardada en caso de existir y estar abierta
     */
    public static void closeSession() {
        if (estaSesionAbierta()) {
            openedSession.close();
        }
    }

    private static boolean estaSesionAbierta() {
        return openedSession != null && openedSession.isOpen();
    }
}

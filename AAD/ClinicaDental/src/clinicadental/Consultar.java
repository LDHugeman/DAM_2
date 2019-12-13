/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicadental;

import java.util.ArrayList;
import java.util.List;
import objetos.Consulta;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author luisd
 */
public class Consultar {
    public static List extraerConsultas() {
        List<Consulta> consultas = new ArrayList();
        try {
            Session session = NewHibernateUtil.getSession();
            consultas = session.createCriteria(Consulta.class).list();
            session.close();
        } catch (HibernateException excepcion) {
            System.err.println("Error al extraer las consultas");
            System.out.println(excepcion.getMessage());
        }
        return consultas;
    }
    
    public static Consulta encontrarConsultaPorNumero(int numeroConsulta) {
        Consulta consulta = null;
        try {
            Session session = NewHibernateUtil.getSession();
            consulta = (Consulta) session.get(Consulta.class, numeroConsulta);
            session.close();
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar la consulta");
            System.out.println(excepcion.getMessage());
        }
        return consulta;
    }
}

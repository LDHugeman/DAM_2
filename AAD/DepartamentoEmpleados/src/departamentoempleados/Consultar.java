
package departamentoempleados;

import java.util.ArrayList;
import java.util.List;
import objetos.Departamento;
import objetos.Empleado;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Luis David
 */
public class Consultar {
    
    public static List extraerDepartamentos(){
        List<Departamento> departamentos = new ArrayList();
        try{
            Session session = NewHibernateUtil.getSession();
            departamentos = session.createCriteria(Departamento.class).list();
            session.close();
        }catch(HibernateException excepcion){
            System.err.println("Error al extraer los departamentos");
            System.out.println(excepcion.getMessage());
        }
        return departamentos;
    }
    
    public static List extraerEmpleados(){
        List<Empleado> empleados = new ArrayList();
        try{
            Session session = NewHibernateUtil.getSession();
            empleados = session.createCriteria(Empleado.class).list();
            session.close();
        }catch(HibernateException excepcion){
            System.err.println("Error al extraer los empleados");
            System.out.println(excepcion.getMessage());
        }
        return empleados;
    }
    
    public static Departamento encontrarDepartamentoPorId(int idDepartamento){
        Departamento departamentoEncontrado = null;
        try{
            Session session = NewHibernateUtil.getSession();
            departamentoEncontrado = (Departamento)session.get(Departamento.class, idDepartamento);
            session.close();
        }catch(HibernateException excepcion){
            System.err.println("Error al buscar el departamento");
            System.out.println(excepcion.getMessage());
        }
        return departamentoEncontrado;
    }
    
    public static Departamento encontrarDepartamentoPorNombre(String nombre){
        Departamento departamentoEncontrado = null;
        try{
            Session session = NewHibernateUtil.getSession();
            departamentoEncontrado = (Departamento)session.createQuery("FROM objetos.Departamento WHERE nombre ='"+ nombre+"'").uniqueResult();
            session.close();
        }catch(HibernateException excepcion){
            System.err.println("Error al buscar el departamento");
            System.out.println(excepcion.getMessage());
        }
        return departamentoEncontrado;
    }
    
    public static Empleado encontrarEmpleadoPorNumeroSegSocial(String numeroSegSocial){
        Empleado empleadoEncontrado = null;
        try{
            Session session = NewHibernateUtil.getSession();
            empleadoEncontrado = (Empleado)session.get(Empleado.class, numeroSegSocial);
            Hibernate.initialize(empleadoEncontrado.getDepartamento());
            session.close();
        }catch(HibernateException excepcion){
            System.err.println("Error al buscar el empleado");
            System.out.println(excepcion.getMessage());
        }
        return empleadoEncontrado;
    }
    
    public static Empleado encontrarEmpleadoPorNombre(String nombre){
        Empleado empleadoEncontrado = null;
        try{
            Session session = NewHibernateUtil.getSession();
            empleadoEncontrado = (Empleado)session.createQuery("FROM objetos.Empleado WHERE nombre ='"+ nombre+"'").uniqueResult();
            Hibernate.initialize(empleadoEncontrado);
            session.close();
        }catch(HibernateException excepcion){
            System.err.println("Error al buscar el empleado");
            System.out.println(excepcion.getMessage());
        }
        return empleadoEncontrado;
    }
}

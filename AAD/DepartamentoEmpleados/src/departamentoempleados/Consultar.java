
package departamentoempleados;

import java.util.ArrayList;
import java.util.List;
import objetos.Departamento;
import objetos.Empleado;
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
    
    public static Empleado encontrarEmpleadoPorNumeroSegSocial(String numeroSegSocial){
        Empleado empleadoEncontrado = null;
        try{
            Session session = NewHibernateUtil.getSession();
            empleadoEncontrado = (Empleado)session.get(Empleado.class, numeroSegSocial);
            session.close();
        }catch(HibernateException excepcion){
            System.err.println("Error al buscar el empleado");
            System.out.println(excepcion.getMessage());
        }
        return empleadoEncontrado;
    }
}

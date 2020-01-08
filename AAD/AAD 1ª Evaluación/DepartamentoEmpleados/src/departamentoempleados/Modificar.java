
package departamentoempleados;

import objetos.Empleado;
import objetos.Departamento;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Luis David
 */
public class Modificar {
    
    public static void salarioEmpleado(Empleado empleado){
        System.out.println("--- Introduzca el nuevo salario para el empleado ---");
        float salario = Crear.pedirSalario();
        empleado.setSalario(salario);
        modificar(empleado);
    }
    
    public static void comisionEmpleado(Empleado empleado){
        System.out.println("--- Introduzca la nueva comisión para el empleado ---");
        float comision = Crear.pedirComision();
        empleado.setComision(comision);
        modificar(empleado);
    }
    
    public static void localidadDepartamento(Departamento departamento){
        System.out.println("--- Introduzca la nueva localidad para el departamento ---");
        String localidad= Crear.pedirLocalidad();
        departamento.setLocalidad(localidad);
        modificar(departamento);
    }
    
    public static void modificar(Object objeto){
        Session session;
        try{
            session = NewHibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(objeto);
            session.getTransaction().commit();
            session.close();           
        }catch(HibernateException excepcion){
            System.err.println("Error al modificar");
            System.out.println(excepcion.getMessage());
        }
    }
}

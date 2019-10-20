package departamentoempleados;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import objetos.Departamento;
import objetos.Empleado;
import org.hibernate.Session;

/**
 *
 * @author Luis David
 */
public class Visualizar {

    public static void departamento(Departamento departamento) {
        System.out.println("---------------- DEPARTAMENTO ----------------");
        System.out.println("idDepartamento: " + departamento.getIdDepartamento());
        System.out.println("Nombre: " + departamento.getNombre());
        System.out.println("Localidad: " + departamento.getLocalidad());
        System.out.println("----------------------------------------------");
    }
    
    public static void departamentos(List <Departamento> departamentos) {
        System.out.println("---------------- DEPARTAMENTOS ---------------------");
        System.out.printf("%-15s %-25s %-35s %n", "IDDEPARTAMENTO", "NOMBRE", "LOCALIDAD");
        for (Departamento departamento: departamentos){
            System.out.printf("%-15s %-25s %-35s %n", departamento.getIdDepartamento(), departamento.getNombre(), departamento.getLocalidad());
        }
        System.out.println("----------------------------------------------------");
    }
    
    public static void empleado(Empleado empleado){
        System.out.println("---------------- EMPLEADO ----------------");
        System.out.println("Número de Seguridad Social: " + empleado.getNumeroSegSocial());
        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("Oficio: " + empleado.getOficio());
        System.out.println("Dirección: " + empleado.getDireccion());
        System.out.println("Fecha de alta: " + Crear.getStringFechaVisualizar(empleado.getFechaAlta()));
        System.out.println("Salario: " + empleado.getSalario());
        System.out.println("Comisión: " + empleado.getComision());
        Session session = NewHibernateUtil.getSession();
        session.update(empleado);
        System.out.println("Departamento: " + empleado.getDepartamento().getNombre());
        session.close();
        System.out.println("------------------------------------------");
    }
    
    public static void empleados(Collection <Empleado> empleados) {
        System.out.println("---------------------------------------------------- EMPLEADOS ---------------------------------------------------------");
        System.out.printf("%-15s %-10s %-15s %-15s %-15s %-15s %-15s %-15s %n", 
                "NUMERO S.S.", "NOMBRE", "OFICIO", "DIRECCIÓN", 
                "FECHA DE ALTA", "SALARIO", "COMISIÓN", "DEPARTAMENTO");
        for (Empleado empleado: empleados){
            System.out.printf("%-15s %-10s %-15s %-15s %-15s %-15.2f %-15.2f %-15s %n", empleado.getNumeroSegSocial(), 
                    empleado.getNombre(), empleado.getOficio(), empleado.getDireccion(), 
                    Crear.getStringFechaVisualizar(empleado.getFechaAlta()), empleado.getSalario(), 
                    empleado.getComision(), empleado.getNombreDepartamento());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }
}

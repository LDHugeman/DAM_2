package departamentoempleados;

import java.util.List;
import objetos.Departamento;

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
        System.out.println("---------------- DEPARTAMENTOS ----------------");
        System.out.printf("%-15s %-25s %-35s %n", "IDDEPARTAMENTO", "NOMBRE", "LOCALIDAD");
        for (Departamento departamento: departamentos){
            System.out.printf("%-15s %-25s %-35s %n", departamento.getIdDepartamento(), departamento.getNombre(), departamento.getLocalidad());
        }
        System.out.println("----------------------------------------------");
    }
}

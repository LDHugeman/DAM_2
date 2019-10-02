package bdinstituto; 

import java.sql.Statement;
import java.util.ArrayList;
import objetos.Alumno;
import objetos.Asignatura;
import objetos.Nota;
import objetos.Profesor;

/**
 *
 * @author a18luisdvp
 */
public class Visualizar {

    public static void profesor(Profesor profesor) {
        System.out.println("---------------- PROFESOR ----------------");
        System.out.println("Dni: " + profesor.getDni());
        System.out.println("Nombre: " + profesor.getNombre());
        System.out.println("Titulación: " + profesor.getTitulacion());
        System.out.println("---------------------------------------");
    }

    public static void alumno(Alumno alumno) {
        System.out.println("---------------- ALUMNO ----------------");
        System.out.println("Código: " + alumno.getCodigo());
        System.out.println("Nombre: " + alumno.getNombre());
        System.out.println("---------------------------------------");
    }

    public static void verNota(Nota nota, String nombreAlumno, String nombreAsignatura) {
        System.out.println("---------------- NOTA ----------------");
        System.out.println("Alumno: " + nombreAlumno);
        System.out.println("Asignatura: " + nombreAsignatura);
        System.out.println("Cualificación: " + nota.getCualificacion());
        System.out.println("Fecha: " + nota.getStringFechaVisualizar());
        System.out.println("---------------------------------------");
    }
    
    public static void verNotas(ArrayList<Nota> notas, Statement sentencia){
        for(Nota nota: notas){
            Alumno alumno = Consultar.encontrarAlumnoPorID(sentencia, nota.getIdAlumno());
            Asignatura asignatura = Consultar.encontrarAsignaturaPorID(sentencia, nota.getIdAsignatura());
            verNota(nota, alumno.getNombre(), asignatura.getNombre());
        }
    }
    
    public static void verAsignatura(Asignatura asignatura){
        System.out.println("---------------- ASIGNATURA ----------------");
        System.out.println("Código: " + asignatura.getCodigo());
        System.out.println("Nombre: " + asignatura.getNombre());
        System.out.println("Ciclo: " +asignatura.getCiclo());
        System.out.println("---------------------------------------");
    }
    
    public static void asignaturas(ArrayList<Asignatura> asignaturas){
        for(Asignatura asignatura: asignaturas){
            verAsignatura(asignatura);
        }
    }
}

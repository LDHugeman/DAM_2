package bdinstituto;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import objetos.Alumno;
import objetos.Asignatura;
import objetos.Nota;
import objetos.Profesor;

/**
 *
 * @author luisd
 */
public class Menu {

    public static void menuAltas(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuAltas();
            switch (opcion) {
                case 1:
                    Profesor profesor = Crear.nuevoProfesor(sentencia);
                    Altas.nuevoProfesor(sentencia, profesor);
                    break;
                case 2:
                    Alumno alumno = Crear.nuevoAlumno();
                    Altas.nuevoAlumno(sentencia, alumno);
                    break;
                case 3:
                    Asignatura asignatura = Crear.nuevaAsignatura();
                    System.out.println("--- Introduzca el dni del profesor que imparte la asignatura ---");
                    String dniProfesor = Crear.pedirDniExistente(sentencia);
                    Altas.nuevaAsignatura(sentencia, asignatura, dniProfesor);
                    break;
                case 4:
                    System.out.println("--- Introduzca el código del alumno al que desea añadir notas ---");
                    String codigoAlumno = Crear.pedirCodigoAlumnoExistente(sentencia);
                    Alumno alumnoEncontrado = Consultar.encontrarAlumnoPorCodigo(sentencia, codigoAlumno);
                    System.out.println("--- Introduzca el código de la asignatura ---");
                    String codigoAsignatura = Crear.pedirCodigoAsignaturaExistente(sentencia);
                    Asignatura asignaturaEncontrada = Consultar.encontrarAsignaturaPorCodigo(sentencia, codigoAsignatura);
                    Nota nota = Crear.nuevaNota();
                    Altas.nuevaNota(sentencia, nota, alumnoEncontrado.getIdAlumno(), asignaturaEncontrada.getIdAsignatura());
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    public static void menuBajas(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuBajas();
            switch (opcion) {
                case 1:
                    eliminarProfesor(sentencia);
                    break;
                case 2:
                    eliminarAlumno(sentencia);
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    public static void menuVisualizar(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuVisualizar();
            switch (opcion) {
                case 1:
                    System.out.println("--- Introduzca el dni del profesor del que desea ver las asignaturas que imparte ---");
                    String dni = Crear.pedirDniExistente(sentencia);
                    Profesor profesor = Consultar.encontrarProfesorPorDni(sentencia, dni);
                    Visualizar.profesor(profesor);
                    ArrayList<Asignatura> asignaturas = Consultar.extraerAsignaturasProfesor(sentencia, dni);
                    Visualizar.asignaturas(asignaturas);
                    break;
                case 2:
                    System.out.println("--- Introduzca el codigo del alumno del que desea ver sus notas ---");
                    String codigo = Crear.pedirCodigoAlumnoExistente(sentencia);
                    Alumno alumno = Consultar.encontrarAlumnoPorCodigo(sentencia, codigo);
                    Visualizar.alumno(alumno);
                    ArrayList<Nota> notas = Consultar.extraerNotasAlumno(sentencia, alumno.getIdAlumno());
                    Visualizar.verNotas(notas, sentencia);
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    public static byte seleccionarOpcionMenuPrincipal() {
        System.out.println("------- MENU -------");
        System.out.println("[1] Altas ");
        System.out.println("[2] Bajas");
        System.out.println("[3] Modificar nota de un alumno");
        System.out.println("[4] Visualizar");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuAltas() {
        System.out.println("------- ALTAS -------");
        System.out.println("[1] Profesor");
        System.out.println("[2] Alumno");
        System.out.println("[3] Asignatura");
        System.out.println("[4] Nota");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuBajas() {
        System.out.println("------- BAJAS -------");
        System.out.println("[1] Profesor");
        System.out.println("[2] Alumno");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuVisualizar() {
        System.out.println("------- VISUALIZAR -------");
        System.out.println("[1] Profesor y asignaturas que imparte");
        System.out.println("[2] Alumno y notas en cada asignatura");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static void eliminarProfesor(Statement sentencia) {
        System.out.println("--- Introduzca el dni del profesor que desea eliminar ---");
        String dni = Crear.pedirDniExistente(sentencia);
        Profesor profesor = Consultar.encontrarProfesorPorDni(sentencia, dni);
        Visualizar.profesor(profesor);
        if (Pedir.duda("Es este el profesor que desea eliminar?")) {
            Bajas.profesor(sentencia, dni);
        }
    }

    public static void eliminarAlumno(Statement sentencia) {
        System.out.println("--- Introduzca el codigo del alumno que desea eliminar");
        String codigo = Crear.pedirCodigoAlumnoExistente(sentencia);
        Alumno alumno = Consultar.encontrarAlumnoPorCodigo(sentencia, codigo);
        Visualizar.alumno(alumno);
        if (Pedir.duda("Es este el alumno que desea eliminar?")) {
            Bajas.alumno(sentencia, codigo);
        }
    }

    public static void modificarNotaAlumno(Statement sentencia) {
        System.out.println("--- Introduzca el código del alumno al que desea modificar su nota ---");
        String codigoAlumno = Crear.pedirCodigoAlumnoExistente(sentencia);
        System.out.println("--- Introduzca el código de la asignatura ---");
        String codigoAsignatura = Crear.pedirCodigoAsignaturaExistente(sentencia);
        System.out.println("--- Introduzca la fecha de la nota ---");
        System.out.printf("Fecha(dd/MM/yyyy): ");
        Date fecha = Pedir.fecha();
        Alumno alumno = Consultar.encontrarAlumnoPorCodigo(sentencia, codigoAlumno);
        Asignatura asignatura = Consultar.encontrarAsignaturaPorCodigo(sentencia, codigoAsignatura);
        Nota nota = Consultar.encontrarNotaPorIdAlumnoIdAsignaturaYFecha(sentencia, alumno.getIdAlumno(), asignatura.getIdAsignatura(), Crear.getStringFechaSql(fecha));
        if (nota != null) {
            Visualizar.verNota(nota, alumno.getNombre(), asignatura.getNombre());
            if (Pedir.duda("¿Desea modificar esta nota?")) {
                Modificar.notaAlumno(sentencia, alumno.getIdAlumno(), asignatura.getIdAsignatura(), Crear.getStringFechaSql(fecha));
            }
        } else {
            System.err.println("No hay ninguna nota en esa fecha");
        }
    }
}

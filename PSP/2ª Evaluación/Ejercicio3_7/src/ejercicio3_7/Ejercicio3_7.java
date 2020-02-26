
package ejercicio3_7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author a18luisdvp
 */
public class Ejercicio3_7 {

    public static void main(String[] args) throws IOException {
        ArrayList<Profesor> profesores = new ArrayList<>();
        ServerSocket servidor = new ServerSocket(6000);
        System.out.println("Servidor iniciado.....");
        
        Asignatura asignatura1 = new Asignatura(1, "AAD");
        Asignatura asignatura2 = new Asignatura(2, "PSP");
        Asignatura asignatura3 = new Asignatura(3, "DI");
        Asignatura asignatura4 = new Asignatura(4, "SXE");
        Asignatura asignatura5 = new Asignatura(5, "EIE");
        Asignatura asignatura6 = new Asignatura(6, "PMDM");
        
        Asignatura [] asignaturas1 = {asignatura1,asignatura2,asignatura3};
        Asignatura [] asignaturas2 = {asignatura1,asignatura5,asignatura6};
        Asignatura [] asignaturas3 = {asignatura2,asignatura3,asignatura4};
        Asignatura [] asignaturas4 = {asignatura5,asignatura3,asignatura1};
        Asignatura [] asignaturas5 = {asignatura3,asignatura4,asignatura6};
        
        Especialidad especialidad1 = new Especialidad(1, "Redes");
        Especialidad especialidad2 = new Especialidad(2, "Jefe de departamento");
        Especialidad especialidad3 = new Especialidad(3, "Informática");
        Especialidad especialidad4 = new Especialidad(4, "Redes");
        Especialidad especialidad5 = new Especialidad(5, "Redes");
        
        Profesor profesor1 = new Profesor(1, "Pedro Ruíz", asignaturas1, especialidad1);
        Profesor profesor2 = new Profesor(2, "Rosa Alonso", asignaturas2, especialidad2);
        Profesor profesor3 = new Profesor(3, "Francisco Collado", asignaturas3, especialidad3);
        Profesor profesor4 = new Profesor(4, "Alba Conso", asignaturas4, especialidad4);
        Profesor profesor5 = new Profesor(5, "Alfonso Gómez", asignaturas5, especialidad5);
        
        profesores.add(profesor1);
        profesores.add(profesor2);
        profesores.add(profesor3);
        profesores.add(profesor4);
        profesores.add(profesor5);
        
        while(true){
           Socket cliente=null;
           cliente=servidor.accept();
           HiloServidor hiloServidor=new HiloServidor(cliente, profesores);
           hiloServidor.start();
       }
    }
    
}

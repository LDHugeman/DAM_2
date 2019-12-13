/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicadental;

import java.util.List;
import java.util.Set;
import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;

/**
 *
 * @author luisd
 */
public class Visualizar {
    
    public static void mostrarConsulta(Consulta consulta){
        String quirofano = "No";
        if (consulta.isQuirofano()){
            quirofano = "Si";
        }
        mostrarPalabrasFila(consulta.getNumero()+"", consulta.getPiso()+"", quirofano);
    }
    
    public static void mostrarDentista(Dentista dentista){
        mostrarPalabrasFila(dentista.getDni(), dentista.getNombre(), dentista.getTelefono(), dentista.getSueldo()+"");
    }
    
    
    public static void mostrarCita(Cita cita){
        System.out.println("------------------ CITA -------------------");
        System.out.println("Fecha: " + cita.getStringFecha());
        System.out.println("Hora: " + cita.getStringHora());
        System.out.println("Concepto: " + cita.getTipoTrabajo());
        System.out.println("-------------------------------------------");
    }
    
    public static void mostrarCitaSola(Cita cita){
        mostrarPalabrasFila("FECHA", "HORA", "CONCEPTO");
        mostrarCita(cita);
    }
    
    public static void consultas(List<Consulta> consultas){
        System.out.println("------------------ CONSULTAS -------------------");
        mostrarPalabrasFila("NÚMERO", "PISO", "QUIRÓFANO");
        for (Consulta consulta : consultas){
            String quirofano = "No";
            if (consulta.isQuirofano()){
                quirofano = "Si";
            }
            mostrarPalabrasFila(consulta.getNumero()+"", consulta.getPiso()+"", quirofano);
        }
        System.out.println("------------------------------------------------");
    }
    
    public static void dentistas(List<Dentista> dentistas){
        System.out.println("------------------ DENTISTAS -------------------");
        mostrarPalabrasFila("DNI", "NOMBRE", "TELÉFONO", "SUELDO");
        for (Dentista dentista : dentistas){
            mostrarPalabrasFila(dentista.getDni(), dentista.getNombre(), dentista.getTelefono(), dentista.getSueldo()+"");
        }
        System.out.println("------------------------------------------------");
    }
    
    public static void citas(List<Cita> citas){
        System.out.println("------------------ CITAS -------------------");
        mostrarPalabrasFila("FECHA", "HORA", "CONCEPTO");
        for (Cita cita : citas){
            mostrarPalabrasFila(cita.getStringFecha(), cita.getStringHora(), cita.getTipoTrabajo());
        }
        System.out.println("--------------------------------------------");
    }
    
    /**
     * Metodo que muestra una fila de Strings formateada
     * @param valores Pueden entrar Uno o mas Strings
     */
    public static void mostrarPalabrasFila(String... valores){
        for (String valor : valores){
            System.out.printf("%-10s", valor);
        }
        System.out.printf("%n");
    }
}
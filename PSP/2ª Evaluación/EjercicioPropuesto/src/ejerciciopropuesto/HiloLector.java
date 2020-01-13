package ejerciciopropuesto;

import java.io.*;
import java.util.LinkedList;

public class HiloLector extends Thread {

    //con hilos de esta clase leeremos los ficheros 
    //compartiremos una lista encadenada
    //cada hilo de esta clase HiloLector, recibe un  nombre de fichero a leer
    // y el recurso que comparten todos ellos es una lista encadenada de objetos de la clase
    //Persona, EN ELLA LOS ELEMENTOS SE INSERTAN ORDENADAMENTE
    String nfich;
    LinkedList<Persona> lista;
    //lista

    public HiloLector(String nfich, LinkedList<Persona> lista) {
        this.nfich = nfich;
        this.lista = lista;
    }

    public String getNfich() {
        return nfich;
    }

    public void setNfich(String nfich) {
        this.nfich = nfich;
    }

    public LinkedList<Persona> getLista() {
        return lista;
    }

    public void setLista(LinkedList<Persona> lista) {
        this.lista = lista;
    }

    @Override
    public void run() {
        File f = new File(nfich);
        try {
            BufferedReader read = new BufferedReader(new FileReader(f));
            String nom;
            nom = read.readLine();
            while (nom != null) {
               System.out.println(getName()+" "+nom);
                if (nom != null) {

                    int longitud = nom.length();
                    String[] sep = nom.split(" ");
                    StringBuffer ini = new StringBuffer();
                    for (String s : sep) {
                        ini.append(s.charAt(0));
                    }
                    String iniciales = ini.toString();
                    Persona p = new Persona(nom, longitud, iniciales);
                    Insercion.insercionOrd(lista, p);
                    // AQUÍ CADA HILO INTENTA ADQUIRIR EL MONITOR DEL OBJETO LISTA
                    // POR L TANTO UN HILO SE PUEDE BLOQUEAR--->PASA A ESTADO ESPERANDO POR EL MONITOR DEL OBJETO
                    //CUANDO EL ÚNICO HILO QUE ESTÁ EJECUTANDO LA SECCIÓN CRÍTICA TERMINE
                    // DESPERTARÁ A TODOS LOS HILOS ESPERANDO
                }
                nom = read.readLine();
            }
            read.close();
        } catch (IOException e) {

        }
    }
}

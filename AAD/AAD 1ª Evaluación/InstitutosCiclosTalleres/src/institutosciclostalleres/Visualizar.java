package institutosciclostalleres;

import java.util.Collection;
import objetos.Ciclo;
import objetos.Instituto;
import objetos.Uso;

/**
 *
 * @author luisd
 */
public class Visualizar {

    public static void mostrarCiclo(Ciclo ciclo) {
        System.out.println("---------------- CICLO ----------------");
        System.out.println("Código: " + ciclo.getCodigo());
        System.out.println("Nombre " + ciclo.getNombre());
        System.out.println("---------------------------------------");
    }
    
    public static void mostrarInstituto(Instituto instituto){
        System.out.println("---------------- INSTITUTO ------------");
        System.out.println("Código: " + instituto.getCodigo());
        System.out.println("Nombre: " + instituto.getNombre());
        System.out.println("Teléfono: " + instituto.getTelefono());
        System.out.println("---------------------------------------");
    }
    
    public static void ciclosUsaronTalleresEntreFechas(Collection <Uso> usos){
        for(Uso uso:usos){
            mostrarCiclo(uso.getCiclo());
        }
    } 
    
    public static void ciclos(Collection <Ciclo> ciclos){
        for(Ciclo ciclo:ciclos){
            mostrarCiclo(ciclo);
        }
    } 
    
    public static void institutos(Collection <Instituto> institutos){
        for(Instituto instituto:institutos){
            mostrarInstituto(instituto);
        }
    }
}

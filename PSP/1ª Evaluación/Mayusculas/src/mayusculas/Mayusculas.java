package mayusculas;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mayusculas {

    
    public static void main(String[] args) {
        aplicacionAntigua();
    }
    
    public static void aplicacion(){
        String linea = "";
        try {
            Process procesoHijo = new ProcessBuilder("mayusculashijo.exe").start();
            BufferedReader lecturaHijo = new BufferedReader(new InputStreamReader(procesoHijo.getInputStream()));
            PrintStream escrituraHijo = new PrintStream(procesoHijo.getOutputStream());
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            while (!linea.equals("kk")){
                linea = entrada.readLine();
                escrituraHijo.println(linea);
                escrituraHijo.flush();
                linea = lecturaHijo.readLine();
                if (!linea.equals("kk")){
                    System.out.println(linea);
                }
            }
            System.out.println("Fin proceso");
        } catch (IOException ex) {}
    }
    
    public static void aplicacionAntigua(){
        String line=null;
     
     try{
          Process hijo = new ProcessBuilder("mayusculashijo.exe").start();

          BufferedReader br = new BufferedReader(new 
                                     InputStreamReader(hijo.getInputStream()));
//br buffer de entrada desde donde leemos datos que proceso hijo nos deja
          
          PrintStream ps = new PrintStream(hijo.getOutputStream());
          // ps buffer de salida en donde escribimos datos para 
          // comunicar al proceso hijo
          
          BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


          // Para salir del bucle teclea ff
          
          do{
          line = in.readLine();          
         
          ps.println(line); //envio lo leido por teclado al hijo
          ps.flush(); // IMP: Comprueba envío de datos
          line = br.readLine();//intento leer los datos que el hijo me deja
          if (!(line.equals("kk"))) {            
                                 System.out.println(line);
                                }           
          }
          while (!(line.equals("kk")));     
          
          System.out.println("Fin proceso");
         } catch (IOException e) {
                       System.out.println("Error ocurrió durante la ejecución "
                               + "Descripción del error: " + e.getMessage());
                             } 
    }
}

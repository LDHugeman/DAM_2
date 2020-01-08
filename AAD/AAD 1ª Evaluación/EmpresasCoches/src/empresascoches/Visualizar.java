package empresascoches;

import java.util.List;
import objetos.Coche;
import objetos.CocheAlquiler;
import objetos.CocheVenta;
import objetos.Empresa;
import objetos.Uso;

/**
 *
 * @author a18luisdvp
 */
public class Visualizar {

    public static void mostrarEmpresa(Empresa empresa) {
        System.out.println("------------------ EMPRESA -------------------");
        System.out.println("Cif: " + empresa.getCif());
        System.out.println("Nombre: " + empresa.getNombre());
        System.out.println("Teléfono: " + empresa.getTelefono());
        System.out.println("----------------------------------------------");
    }
    
    public static void mostrarEmpresas(List <Empresa> empresas) {
        System.out.println("------------------ EMPRESAS -------------------");
        System.out.printf("%-15s %-15s %-15s %n", "CIF", "NOMBRE", "TELÉFONO");
        for(Empresa empresa:empresas){
            System.out.printf("%-15s %-15s %-15s %n", empresa.getCif(), empresa.getNombre(), empresa.getTelefono());
        }       
        System.out.println("-----------------------------------------------");
    }

    public static void mostrarCocheVenta(CocheVenta cocheVenta) {
        System.out.println("---------------- COCHE EN VENTA ---------------------");
        System.out.println("Código: " + cocheVenta.getCodigo());
        System.out.println("Marca: " + cocheVenta.getMarca());
        System.out.println("Modelo: " + cocheVenta.getModelo());
        System.out.println("Precio de venta: " + cocheVenta.getPrecio() + "€");
        System.out.println("-----------------------------------------------------");
    }

    public static void mostrarCocheAlquiler(CocheAlquiler cocheAlquiler) {
        System.out.println("---------------- COCHE EN ALQUILER ---------------------");
        System.out.println("Código: " + cocheAlquiler.getCodigo());
        System.out.println("Marca: " + cocheAlquiler.getMarca());
        System.out.println("Modelo: " + cocheAlquiler.getModelo());
        System.out.println("Precio por día: " + cocheAlquiler.getPrecio() + "€");
        System.out.println("--------------------------------------------------------");
    }

    public static void mostrarCochesVenta(List<CocheVenta> cochesVenta) {
        System.out.println("------------------ COCHES EN VENTA -----------------------");
        System.out.printf("%-15s %-15s %-15s %-15s %n", "CÓDIGO", "MARCA", "MODELO", "PRECIO");
        for (CocheVenta cocheVenta : cochesVenta) {
            System.out.printf("%-15s %-15s %-15s %-15s %n", cocheVenta.getCodigo(), cocheVenta.getMarca(), cocheVenta.getModelo(), cocheVenta.getPrecio());
        }
        System.out.println("----------------------------------------------------------");
    }

    public static void mostrarCochesAlquiler(List<CocheAlquiler> cochesAlquiler) {
        System.out.println("------------------ COCHES EN ALQUILER -----------------------");
        System.out.printf("%-15s %-15s %-15s %-15s %n", "CÓDIGO", "MARCA", "MODELO", "PRECIO - DÍA");
        for (CocheAlquiler cocheAlquiler : cochesAlquiler) {
            System.out.printf("%-15s %-15s %-15s %-15s %n", cocheAlquiler.getCodigo(), cocheAlquiler.getMarca(), cocheAlquiler.getModelo(), cocheAlquiler.getPrecio());
        }
        System.out.println("-------------------------------------------------------------");
    }

    public static void mostrarEmpresasYCoches(List<Empresa> empresas) {
        for (Empresa empresa : empresas) {
            mostrarEmpresa(empresa);            
            System.out.println("-------------------------- COCHES ---------------------------");
            System.out.printf("%-15s %-15s %-15s %-15s %n", "CÓDIGO", "MARCA", "MODELO", "PRECIO");
            for (Coche coche : empresa.getCoches()) {
                if(coche instanceof CocheVenta){
                    System.out.printf("%-15s %-15s %-15s %-15s %n", coche.getCodigo(), coche.getMarca(), coche.getModelo(), ((CocheVenta) coche).getPrecio());
                } else if(coche instanceof CocheAlquiler){
                    System.out.printf("%-15s %-15s %-15s %-15s %n", coche.getCodigo(), coche.getMarca(), coche.getModelo(), ((CocheAlquiler) coche).getPrecio());
                }               
            }
            System.out.println("-------------------------------------------------------------");
        }        
    }
    
    public static void facturaCocheAlquilerEntreFechas(Empresa empresa, CocheAlquiler cocheAlquiler, List<Uso> usos){
        System.out.println("------------------ FACTURA -------------------");
        System.out.println("Nombre de la empresa: "+ empresa.getNombre());
        System.out.println("Marca: " + cocheAlquiler.getMarca());
        System.out.println("Modelo: " + cocheAlquiler.getModelo());
        System.out.println("Importe por cada uso: ");
        float importeTotal = 0.0f;
        for(Uso uso:usos){
            System.out.println(uso.getImporte()+ "€ ");
            importeTotal = importeTotal+ uso.getImporte();
        }
        System.out.println("Importe total: "+ importeTotal+ "€");
        System.out.println("----------------------------------------------");
    }
}

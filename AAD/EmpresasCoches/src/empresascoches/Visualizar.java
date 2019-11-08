package empresascoches;

import java.util.List;
import objetos.CocheAlquiler;
import objetos.CocheVenta;

/**
 *
 * @author a18luisdvp
 */
public class Visualizar {

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
}

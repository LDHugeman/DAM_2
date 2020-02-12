package existdbactividad9;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author a18luisdvp
 */
public class Calcular {

    public static void visualizarCuotaFinal(Document documentoSocios, Document documentoActividades, Document documentoUsoGimnasio) {
        NodeList socios = documentoSocios.getElementsByTagName("socio");
        for (Node nodoSocio: convertToList(socios)){
            Element socio =  (Element)nodoSocio;
            String codigoSocio = socio.getAttribute("codigo");
            String cuotaFija = socio.getElementsByTagName("cuota")
                    .item(0)
                    .getTextContent();
            System.out.println("Código de socio: " + codigoSocio);
            System.out.println("Cuota fija: " + cuotaFija);
            visualizarActividades(documentoUsoGimnasio, documentoActividades, codigoSocio, cuotaFija);
        }
    }
    
    private static void visualizarActividades(Document documentoUsoGimnasio, Document documentoActividades, String codigoSocio, String cuotaFija){
        int precioTotalActividades = 0;
        for(Element uso: obtenerUsosDeSocio(documentoUsoGimnasio, codigoSocio)){
            String codigoActividad = uso.getElementsByTagName("CODACTIV").item(0).getTextContent();
            for(Element actividad: obtenerActividadesPorCodigo(documentoActividades, codigoActividad)){
                precioTotalActividades += Integer.parseInt(actividad.getElementsByTagName("cuota_adicional").item(0).getTextContent());
            }
        }
        System.out.println("Precio total de actividades: " + precioTotalActividades);
        System.out.println("Total: " + (precioTotalActividades + Integer.parseInt(cuotaFija)) + "\n");
    }
    
    private static ArrayList<Element> obtenerUsosDeSocio(Document documentoUsoGimnasio, String codigoSocio){
        ArrayList<Element> usosDeSocio = new ArrayList();
        NodeList usos = documentoUsoGimnasio.getElementsByTagName("fila_uso");
        for (Node nodoUso: convertToList(usos)){
            Element uso = (Element)nodoUso;
            String codigoSocioUso = uso.getElementsByTagName("CODSOCIO")
                    .item(0)
                    .getTextContent();
            if (codigoSocioUso.equalsIgnoreCase(codigoSocio)){
                usosDeSocio.add(uso);
            }
        }
        return usosDeSocio;
    }
    
    private static ArrayList<Element> obtenerActividadesPorCodigo(Document documentoActividades, String codigoActividadBuscado){
        ArrayList<Element> actividadesDeUso = new ArrayList();
        NodeList actividades = documentoActividades.getElementsByTagName("actividad");
        for (Node nodoActividad: convertToList(actividades)){
            Element actividad = (Element) nodoActividad;
            if (actividad.getAttribute("codigo").equalsIgnoreCase(codigoActividadBuscado)){
                actividadesDeUso.add(actividad);
            }
        }
        return actividadesDeUso;
    }
    
    private static List<Node> convertToList(NodeList listaNodos){
        return new EnvoltorioNodos(listaNodos);
    }
    
}

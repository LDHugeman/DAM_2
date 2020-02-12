package existdbactividad9;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author a18luisdvp
 */
public class ExistDBActividad9 {

    public static void main(String[] args) {
        File ficheroSocios = new File("ColeccionGimnasio/socios_gim.xml");
        File ficheroActividades = new File("ColeccionGimnasio/actividades_gim.xml");
        File ficheroUsoGimnasio = new File("ColeccionGimnasio/uso_gimnasio.xml");
        try {
            Document documentoSocios = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new InputSource(new FileInputStream(ficheroSocios)));
            Document documentoActividades = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new InputSource(new FileInputStream(ficheroActividades)));
            Document documentoUsoGimnasio = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new InputSource(new FileInputStream(ficheroUsoGimnasio)));
            Calcular.visualizarCuotaFinal(documentoSocios, documentoActividades, documentoUsoGimnasio);
        } catch (IOException | SAXException | ParserConfigurationException e) {
        }
    }
}

package bdmongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author a18luisdvp
 */
public class BDMongo {

    public static void main(String[] args) {
        MongoClient cliente = new MongoClient();
        MongoDatabase baseDatos = cliente.getDatabase("mibasedatos");
        MongoCollection<Document> coleccion = baseDatos.getCollection("amigos");
        List<Document> consultas = coleccion.find().into(new ArrayList<Document>());
        for(Document consulta:consultas){
            System.out.println(" - "+consulta.toString());
        }
        
        for(Document consulta:consultas){
            Document amigos = consulta;
            System.out.println(" - " + amigos.getString("nombre")+ 
                    " - "+ amigos.get("teléfono")+
                    " - "+amigos.getString("curso")+ 
                    " - "+ amigos.getDouble("nota"));
        }
        insertarAmigo(coleccion);
    }

    public static void insertarAmigo(MongoCollection coleccion) {
        Document amigo = new Document();
        amigo.put("nombre", "Pepito");
        amigo.put("teléfono", "925677");
        amigo.put("curso", "2DAM");
        amigo.put("fecha",new Date());
        coleccion.insertOne(amigo);
    }

}

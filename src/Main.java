import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;

public class Main {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
            MongoDatabase database = mongoClient.getDatabase("MinimarketPro");
            MongoCollection<Document> collection = database.getCollection("Usuarios");
            Document Cajero = new Document("nombre", "Javier Díaz")
                    .append("rol", "cajero")
                    .append("usuario", "cajero5")
                    .append("contrasena", "cajero567")
                    .append("fechaCreacion", "2024-07-23");
            collection.insertOne(Cajero);
            System.out.println("Dato insertado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar datos: " + e.getMessage());
        }
    }
}
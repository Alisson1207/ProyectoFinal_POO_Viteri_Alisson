import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Conexion a la nube
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
            MongoDatabase database = mongoClient.getDatabase("MinimarketPro");
            MongoCollection<Document> collection = database.getCollection("Usuarios");
            System.out.println("Conexión a MongoDB Atlas exitosa.");
            } catch (Exception e) {
                System.out.println("Error al conectar a MongoDB Atlas: " + e.getMessage());
            }

    }

}
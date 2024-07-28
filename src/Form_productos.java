import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form_productos {
    private JButton button1;
    private JPanel panel1;

    public Form_productos() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        //Insersion de datos
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
            MongoDatabase database = mongoClient.getDatabase("MinimarketPro");
            MongoCollection<Document> collection = database.getCollection("Productos");
            Document doc = new Document("Nombre Producto", "Ra")//Insersion de datos
                    .append("apellido", "Cortez")
                    .append("edad", 31);
            collection.insertOne(doc);
            System.out.println("Documento insertado con éxito");
            System.out.println("Insersión de productos exitoso.");
        } catch (Exception e) {
            System.out.println("Error al ingresar productos " + e.getMessage());
        }

    }
}
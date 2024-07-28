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
        Form_login formLogin = new Form_login();
        formLogin.setVisible(true);
    }
}
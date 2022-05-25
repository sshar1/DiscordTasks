package org.javacord.tasks.util;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public final class MongoUtil {

    private static final MongoDatabase DATABASE;

    static {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://dbReader:4Qbv7yuFm5qXuiHi@users.nzo5p.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        DATABASE = mongoClient.getDatabase("Users");
    }

    public static MongoCollection<Document> getTaskCollection() {
        return DATABASE.getCollection("Tasks");
    }

    public static void addUser(String id) {
        Document userDoc = new Document();

        userDoc.put("_id", id);
        userDoc.put("color", "WHITE");
        userDoc.put("tasks", new Document());

        getTaskCollection().insertOne(userDoc);
    }

    public static Document getDocFromId(String id) {
        FindIterable<Document> docs = getTaskCollection().find();

        for (Document doc : docs) {
            if (doc.get("_id").equals(id)) {
                return doc;
            }
        }

        return null;
    }

    public static Document getTasksFromId(String id) {

        Document user = getDocFromId(id);

        if (user != null) {
            return (Document) user.get("tasks");
        }
       
        return null;
    }

    public static boolean taskExists(String id, String title) {

        Document tasks = getTasksFromId(id);

        return tasks.containsKey(title);
    }

    public static void removeTask(String id, String title) {

        Document tasks = getTasksFromId(id);

        tasks.remove(title);
    }

    public static void addTask(String id, String title, String task) {

        Document tasks = getTasksFromId(id);

        tasks.put(title, task);   
    }

    public static void setColor(String id, String color) {

        Document user = getDocFromId(id);

        user.replace("color", color.toUpperCase());
    }

    public static String getColor(String id) {

        Document user = getDocFromId(id);

        if (user != null) {
            return (String) user.get("color");
        }

        return null;
    }
}

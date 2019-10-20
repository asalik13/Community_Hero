package com.example.communityhero;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
/*
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

*/

import org.bson.Document;
// Base Stitch Packages
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;

// Packages needed to interact with MongoDB and Stitch
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

// Necessary component for working with MongoDB Mobile
import com.mongodb.stitch.android.services.mongodb.local.LocalMongoDbService;

import org.bson.BsonString;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TasksFragment extends Fragment {

    //Fragment for tasks
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        // Create the default Stitch Client
        final StitchAppClient client =
                Stitch.initializeDefaultAppClient("community-hero-ggexr");

        // Create a Client for MongoDB Mobile (initializing MongoDB Mobile)
        final MongoClient mobileClient =
                client.getServiceClient(LocalMongoDbService.clientFactory);
        // Point to the target collection and insert a document
        MongoCollection<Document> localCollection =
                mobileClient.getDatabase("my_db").getCollection("my_collection");
        localCollection.drop();
        mobileClient.getDatabase("my_db").createCollection("my_collection");

        Document document = new Document("title", "MongoDB")
                .append("id", 1)
                .append("taskName", "Cure Cancer")
                .append("likes", 100)
                .append("url", "http://www.tutorialspoint.com/mongodb/")
                .append("by", "tutorials point");
        localCollection.insertOne(document);
        Document taskOne = new Document("taskName", "Being batman");
        Document taskTwo = new Document("taskName", "Being batman");
        Document taskThree = new Document("taskName", "Being batman");
        Document taskFour = new Document("taskName", "Being batman");
        Document taskFive = new Document("taskName", "Being batman");
        Document taskSix = new Document("taskName", "Being batman");
        List<Document> taskList = new ArrayList<>();
        taskList.add(taskOne);
        taskList.add(taskTwo);
        taskList.add(taskThree);
        taskList.add(taskFour);
        taskList.add(taskFive);
        taskList.add(taskSix);
        localCollection.insertMany(taskList);

        FindIterable<Document> cursor = localCollection.find();
        ArrayList<Document> results =
                (ArrayList<Document>) cursor.into(new ArrayList<Document>());

        ArrayList<String> arr = new ArrayList();
        String mydude;
        for (Document a: results) {
            System.out.println("DUDE 1: " + a.toString());
            mydude = a.getString("taskName");;
            System.out.println("DUDE 2: " + mydude);
            arr.add(mydude);

        }


        arr.add("Being batman");
        arr.add("Being batman");
        arr.add("Being batman");
        arr.add("Being batman");
        arr.add("Curing cancer");
        arr.add("Being batman");
        ListView listView = view.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);
        return view;

    }
    /*
    FOR REFERENCE

        // Find the first document
        //Document doc = localCollection.find().first();

        //Find all documents that match the find criteria
        //Document query = new Document();
        //query.put("name", new BsonString("veirs"));
        //-> find all documents


        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        MongoClient mongo = connect();
        System.out.println("SUCCESS2?");
        MongoDatabase database = mongo.getDatabase("myDb");
        //Creating a collection
        database.createCollection("sampleCollection");
        System.out.println("Collection created successfully");
        insertDocument(database);


    public MongoClient connect() {

        // Creating a Mongo client
        MongoClient mongo = new MongoClient("localhost", 27017);

        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("sampleUser", "myDb",
                "password".toCharArray());
        System.out.println("Connected to the database successfully");

        return mongo;


    }
    public ArrayList<String> getAllDocuments(MongoDatabase database) {
        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("collection");
        System.out.println("Collection collection selected successfully");

        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;

        // Getting the iterator
        Iterator it = iterDoc.iterator();
        ArrayList<String> res = new ArrayList<>();

        while (it.hasNext()) {
            //convertDocToString();
            //res.add()
            System.out.println(it.next());
            i++;
        }
        return null;
    }
    public void insertDocument(MongoDatabase database) {
        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("collection");
        System.out.println("Collection sampleCollection selected successfully");

        Document document = new Document("title", "MongoDB")
                .append("id", 1)
                .append("description", "database")
                .append("likes", 100)
                .append("url", "http://www.tutorialspoint.com/mongodb/")
                .append("by", "tutorials point");
        collection.insertOne(document);
        System.out.println("Document inserted successfully");

    }*/
}

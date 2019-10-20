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

public class MongoJDBCDriver {
    MongoClient mobileClient;
    MongoDatabase myDB;
    public MongoJDBCDriver() {
        // Create the default Stitch Client
        final StitchAppClient client =
                Stitch.initializeDefaultAppClient("community-hero-ggexr");

        // Create a Client for MongoDB Mobile (initializing MongoDB Mobile)
        mobileClient = client.getServiceClient(LocalMongoDbService.clientFactory);

        // Database will always be my_db
        myDB = mobileClient.getDatabase("my_db");
    }
    //public void insertCollection2(String )
    public void insertCollection(String collectionName) {
        //MongoCollection<Document> localCollection =

        myDB.getCollection(collectionName).drop();
        //If doesn't exist, simply returns false
        myDB.createCollection(collectionName);
        MongoCollection<Document> localCollection = myDB.getCollection(collectionName);

        Document document = new Document("title", "MongoDB")
                .append("id", 1)
                .append("taskName", "Cure Cancer2")
                .append("likes", 100)
                .append("url", "http://www.tutorialspoint.com/mongodb/")
                .append("by", "tutorials point");
        localCollection.insertOne(document);
        System.out.println("INSERTED");

        Document taskOne = new Document("taskName", "Being batman2");
        Document taskTwo = new Document("taskName", "Being batman2");
        Document taskThree = new Document("taskName", "Being batman2");
        Document taskFour = new Document("taskName", "Being batman2");
        Document taskFive = new Document("taskName", "Being batman223");
        Document taskSix = new Document("taskName", "Being batman52");
        List<Document> taskList = new ArrayList<>();
        taskList.add(taskOne);
        taskList.add(taskTwo);
        taskList.add(taskThree);
        taskList.add(taskFour);
        taskList.add(taskFive);
        taskList.add(taskSix);
        localCollection.insertMany(taskList);
        System.out.println("ALL INSERTED");
    }
    public ArrayList<String> findCollection(String collectionName, String key)
    {
        System.out.println("FIND COLLECTION");
        MongoCollection<Document> localCollection = myDB.getCollection(collectionName);

        ArrayList<String> res = new ArrayList<>();
        if (localCollection == null) {
            //local collection does not exist
            return res;
        }
        long a = localCollection.countDocuments();
        FindIterable<Document> cursor = localCollection.find();
        ArrayList<Document> documentList =
                (ArrayList<Document>) cursor.into(new ArrayList<Document>());
        System.out.println("FINDING: ");
        System.out.println("LONG COUNT:  " + a);
        System.out.println(documentList.get(0).getString(key));


        for (Document tempDoc: documentList) {
            res.add(tempDoc.getString(key));
            System.out.println("KEY: " + tempDoc.getString(key));
        }
        return res;
    }
}

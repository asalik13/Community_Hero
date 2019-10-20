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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MongoJDBCDriver {
    MongoClient mobileClient;
    MongoDatabase myDB;

    // Create the default Stitch Client
    static final StitchAppClient client =
            Stitch.initializeDefaultAppClient("community-hero-ggexr");
    public MongoJDBCDriver() {

        // Create a Client for MongoDB Mobile (initializing MongoDB Mobile)
        mobileClient = client.getServiceClient(LocalMongoDbService.clientFactory);

        // Database will always be my_db
        myDB = mobileClient.getDatabase("my_db");
    }
    public void insertOneTask(String collectionName, String title) {
        Document taskOne = new Document("taskName", title);
        MongoCollection<Document> localCollection = myDB.getCollection(collectionName);
        localCollection.insertOne(taskOne);
        System.out.println("SUCCESS:  " + title);
    }
    public void insertOnePostCollection(String collectionName, Post obj) {
        MongoCollection<Document> localCollection = myDB.getCollection(collectionName);
        Document a = convertPostToDocument(obj);
        localCollection.insertOne(a);
    }
    public Document convertPostToDocument(Post obj) {
        Document postsOne = new Document("id", obj.getId())
                .append("title", obj.getTitle())
                .append("description", obj.getDesc())
                .append("contributors", obj.getContributors())
                .append("date", obj.getDate())
                .append("lat", obj.getLatitude())
                .append("lng", obj.getLongitude());
        return postsOne;
    }
    public void insertPostsCollection(String collectionName) {
        myDB.getCollection(collectionName).drop();
        //If doesn't exist, simply returns false
        myDB.createCollection(collectionName);

      /*String pattern = "dd MMMM yyyy";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("us", "US"));
        String date = simpleDateFormat.format(new Date());

        MongoCollection<Document> localCollection = myDB.getCollection(collectionName);
       Document postsOne = new Document("id", 1)
                .append("title", "WAY DIFFERENT NAME")
                .append("description", "There's a lot of garbage left unattended these days. So I thought we could clean it up")
                .append("contributors", "Contributors: 4")
                .append("date", date)
                .append();
        Document postsTwo = new Document("id", 1)
                .append("title", "Clearing neighborhood garbabge")
                .append("description", "There's a lot of garbage left unattended these days. So I thought we could clean it up")
                .append("contributors", "Contributors: 4")
                .append("date", date);
        Document postsThree = new Document("id", 1)
                .append("title", "Clearing neighborhood garbabge")
                .append("description", "There's a lot of garbage left unattended these days. So I thought we could clean it up")
                .append("contributors", "Contributors: 4")
                .append("date", date);
        Document postsFour = new Document("id", 1)
                .append("title", "Clearing neighborhood garbabge")
                .append("description", "There's a lot of garbage left unattended these days. So I thought we could clean it up")
                .append("contributors", "Contributors: 4")
                .append("date", date);
        Document postsFive = new Document("id", 1)
                .append("title", "Clearing neighborhood garbabge")
                .append("description", "There's a lot of garbage left unattended these days. So I thought we could clean it up")
                .append("contributors", "Contributors: 4")
                .append("date", date);

        List<Document> postsList = new ArrayList<>();
        postsList.add(postsOne);
        postsList.add(postsTwo);
        postsList.add(postsThree);
        postsList.add(postsFour);
        postsList.add(postsFive);
        localCollection.insertMany(postsList);*/
    }
    public void insertMessage(String collectionName, String message) {
        myDB.getCollection(collectionName).drop();
        //If doesn't exist, simply returns false
        myDB.createCollection(collectionName);
        MongoCollection<Document> localCollection = myDB.getCollection(collectionName);
        Document document = new Document("sender", "MongoDB")
                .append("message", message);
        localCollection.insertOne(document);
    }
    public void insertCollectionGroups(String collectionName) {
        myDB.getCollection(collectionName).drop();
        //If doesn't exist, simply returns false
        myDB.createCollection(collectionName);
        MongoCollection<Document> localCollection = myDB.getCollection(collectionName);

        Document groupOne = new Document("groupName", "Group Harem");
        Document groupTwo = new Document("groupName", "Group Harem");
        Document groupThree = new Document("groupName", "Group Harem");
        Document groupFour = new Document("groupName", "Group Harem");
        Document groupFive = new Document("groupName", "Group Harem");
        Document groupSix = new Document("groupName", "Group Harem");
        List<Document> groupList = new ArrayList<>();
        groupList.add(groupOne);
        groupList.add(groupTwo);
        groupList.add(groupThree);
        groupList.add(groupFour);
        groupList.add(groupFive);
        groupList.add(groupSix);
        localCollection.insertMany(groupList);
    }
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
        FindIterable<Document> cursor = localCollection.find();
        ArrayList<Document> documentList =
                (ArrayList<Document>) cursor.into(new ArrayList<Document>());

        for (Document tempDoc: documentList) {
            res.add(tempDoc.getString(key));
            System.out.println("KEY: " + tempDoc.getString(key));
        }
        return res;
    }
    public List<Post> findCollectionPost(String collectionName) {
        List<Post> res = new ArrayList<>();
        System.out.println("FIND COLLECTION");
        MongoCollection<Document> localCollection = myDB.getCollection(collectionName);

        if (localCollection == null) {
            //local collection does not exist
            return res;
        }
        FindIterable<Document> cursor = localCollection.find();
        ArrayList<Document> documentList =
                (ArrayList<Document>) cursor.into(new ArrayList<Document>());

        for (Document tempDoc: documentList) {
            Post tempPost = new Post(
                    tempDoc.getInteger("id"),
                    tempDoc.getString("title"),
                    tempDoc.getString("description"),
                    tempDoc.getString("contributors"),
                    tempDoc.getString("date"),
                    tempDoc.getDouble("lat"),
                    tempDoc.getDouble("lng")
            );

            res.add(tempPost);
        }
        return res;
    }
}

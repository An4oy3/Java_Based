package com.skillbox.mongodemo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    private static String file = "data/mongo.csv";
    private static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        List<String> fileList = Files.readAllLines(Paths.get(file));
        for (String s : fileList) {
            String[] line = s.split(",", 3);
            studentList.add(new Student(line[0], Integer.parseInt(line[1]), line[2]));
        }


        MongoClient mongoClient = new MongoClient("127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> studentsCollection = database.getCollection("students");

        studentsCollection.drop();

        for (Student student : studentList) {
            studentsCollection.insertOne(new Document()
                    .append("name", student.getName())
                    .append("age", student.getAge())
                    .append("courses", student.getCourses())
            );
        }

        //studentsCollection.find().forEach((Consumer<Document>) document -> System.out.println(document) );

        //Кол-во студентов
        System.out.print("Кол-во студентов: ");
        System.out.println(studentsCollection.countDocuments());

        //Кол-во студентов старше 40 лет
        BsonDocument query = BsonDocument.parse("{age: {$gt: 40}}");
        System.out.print("Кол-во студентов старше 40 лет: ");
        System.out.println(studentsCollection.countDocuments(query));

        //Имя Самого молодого студента
        BsonDocument query1 = BsonDocument.parse("{age: 1 }");
        System.out.print("Имя самого молодого студента: ");
        studentsCollection.find().sort(query1).limit(1).forEach((Consumer<? super Document>) doc -> System.out.println(doc.get("name")));

        //Список курсов самого старого студента
        BsonDocument query2 = BsonDocument.parse("{age: -1 }");
        System.out.print("Список курсов самого старого студента: ");
        studentsCollection.find().sort(query2).limit(1).forEach((Consumer<? super Document>) doc -> System.out.println(doc.get("courses")));



    }

}

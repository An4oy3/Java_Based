package com.skillbox.mongodemo;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Filters;
import org.bson.BSON;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Consumer;

public class Main {
    private static HashSet<Store> stores = new HashSet<Store>();

    public static void main(String[] args) throws IOException {

        MongoClient mongoClient = new MongoClient("127.0.0.1" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> shopsCollection = database.getCollection("shops");
        MongoCollection<Document> productsCollection = database.getCollection("products");

        shopsCollection.drop();
        productsCollection.drop();


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String input = reader.readLine();
            if(input.equals("exit")){
                break;
            }
            String command = "";
            try {
                command = input.substring(0, input.indexOf("_"));
                input = input.substring(input.indexOf("_") + 1, input.length());
            }catch (Exception e){
                System.out.println("НЕВЕРНАЯ КОМАНДА");
                continue;
            }


            switch (command){
                case "СТАТИСТИКА" :


                    AggregateIterable<Document> aggragateAvg = shopsCollection.aggregate(Arrays.asList(
                            new Document("$lookup", new Document("from", "products")
                                                    .append("localField", "product")
                                                    .append("foreignField", "name")
                                                    .append("as", "product_list")),
                            new Document("$unwind", new Document("path", "$product_list")),
                            new Document("$group", new Document("_id", new Document("name", "$name"))
                                                                        .append("avgPrice", new Document("$avg", "$product_list.price"))
                                                                        .append("maxPrice", new Document("$max", "$product_list.price"))
                                                                        .append("minPrice", new Document("$min", "$product_list.price"))
                                                                        .append("sumPrice", new Document("$sum", 1))
                                                                        //.append("less100", new Document("$sum", new Document("$lt", Arrays.asList("$product_list.price", 55))))
                                                                        .append("less100", new Document("$sum", new Document("$cond", Arrays.asList(new Document("$ifNull", Arrays.asList(new Document("$lt",Arrays.asList("$product_list.price", 100)), false)), 1, 0))))
                                                                        //.append("less100", new Document("$sum", new Document("$match", Aggregates.match(Filters.lt("$product_list.price", 1)))))
                                                                        )));

                    System.out.print("Общее кол-во наименований товара: ");
                    aggragateAvg.forEach((Consumer<? super Document>) d -> System.out.println(d.get("sumPrice")));

                    System.out.print("Средняя цена товаров: ");
                    aggragateAvg.forEach((Consumer<? super Document>) d -> System.out.println(d.get("avgPrice")));

                    System.out.print("Самый дорогой и самый дешевый товары: ");
                    aggragateAvg.forEach((Consumer<? super Document>) d -> {
                        System.out.print(d.get("maxPrice"));
                        System.out.print(", ");
                        System.out.print(d.get("minPrice"));
                        System.out.println();
                    });

                    System.out.print("Кол-во товара дешевле 100 рублей: ");
                    aggragateAvg.forEach((Consumer<? super Document>) d -> System.out.println(d.get("less100")));


                    break;
                case "ВЫСТАВИТЬ" :
                    input = input.substring(5);
                    String[] productAndStore = input.split("(?=[А-Я])");
//                    for (Store store : stores) {
//                        if(store.getName().equals(productAndStore[1])){
//                            for (Product product : Product.getProductList()) {
//                                if(product.getName().equals(productAndStore[0])){
//                                    store.addProduct(product);
//                                    break;
//                                }
//                            }
//                            break;
//                        }
//                    } //Добавляем товар в список магазина

                    shopsCollection.insertOne(new Document()
                                .append("name", productAndStore[1])
                                .append("product", productAndStore[0]));

                    System.out.println("Товар " + productAndStore[0] + " успешно выставлен в магазине " + productAndStore[1]);
//                    shopsCollection.aggregate(Arrays.asList(
//                            new Document("$lookup", new Document().append("from", "products")
//                                    .append("localField", "product")
//                                    .append("foreignField", "name")
//                                    .append("as", "product_list")
//                    )));
                    break;
                case "ДОБАВИТЬ" :
                    if(input.startsWith("МАГАЗИН")){
                        Store store = new Store(input.substring(7));
                        stores.add(store);

                        shopsCollection.insertOne(new Document()
                        .append("name", store.getName()));
                        System.out.println("Магазин " + store.getName() + " успешно добавлен в коллекцию \"shops\"");
                    } else if(input.startsWith("ТОВАР")){
                        input = input.substring(5);
                        String dest = input.replaceAll("[^0-9]+", "");
                        input = input.replaceAll("[0-9]+", "");

                        Product product = new Product(input, Integer.parseInt(dest));
                        productsCollection.insertOne(new Document()
                        .append("name", product.getName())
                        .append("price", product.getPrice()));
                        System.out.println("Товар " + product.getName() + " с ценой " + product.getPrice() + " успешно добавлен в коллекцию \"products\"");
                    }
                    break;
                default :
                    System.out.println("НЕВЕРНАЯ КОМАНДА");
                    break;
            }

        }
    }
}

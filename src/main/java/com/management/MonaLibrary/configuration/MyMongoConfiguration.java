package com.management.MonaLibrary.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *  This is the bean creation class for Mongotemplate. that i will use later
 */
@Configuration
//@DependsOn("customMongoProperties") //it will make sure that CustomMongoProperties Bean is prepared before the MyMongoConfiguration
public class MyMongoConfiguration {

//    @Autowired
//    private CustomMongoProperties customMongoProperties;

//    @Bean(name = "mongoClient")
//    public MongoClient myMongoClient(){
//        return MongoClients.create();
//    }
//
//    @Bean(name = "mongoTemplate")
//    public MongoTemplate myMongoTemplate(){
//        return new MongoTemplate(myMongoClient(),customMongoProperties.getDatabase());
//    }
}

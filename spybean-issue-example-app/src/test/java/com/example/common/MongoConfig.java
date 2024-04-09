package com.example.common;

import com.example.repository.ExampleRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
@EnableMongoRepositories(basePackageClasses = {ExampleRepository.class})
public class MongoConfig {

   @Bean
   public MongoTemplate mongoTemplate(MongoClient mongoClient) {
      return new MongoTemplate(mongoDbFactory(mongoClient));
   }

   @Bean
   public MongoDatabaseFactory mongoDbFactory(MongoClient mongoClient) {
      return new SimpleMongoClientDatabaseFactory(mongoClient, "test");
   }

   @Bean
   public MongoDBContainer mongoServer() {
      MongoDBContainer mongoDBContainer =
         new MongoDBContainer(DockerImageName.parse("mongo").withTag("4.4.25"));
      mongoDBContainer.start();
      return mongoDBContainer;
   }

   @Bean(destroyMethod = "close")
   public MongoClient mongoClient(MongoDBContainer mongoServer) {
      return MongoClients.create(mongoServer.getConnectionString());
   }
}

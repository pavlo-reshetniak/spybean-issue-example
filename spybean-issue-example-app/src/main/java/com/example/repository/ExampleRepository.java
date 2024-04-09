package com.example.repository;

import com.example.model.ExampleData;
import com.example.model.AbstractData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExampleRepository<T extends AbstractData> extends MongoRepository<ExampleData, String> {

}

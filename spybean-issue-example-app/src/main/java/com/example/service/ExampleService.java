package com.example.service;

import com.example.model.ExampleData;
import com.example.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService {

   private final ExampleRepository<ExampleData> exampleRepository;

   public List<ExampleData> get() {
      return exampleRepository.findAll();
   }
}

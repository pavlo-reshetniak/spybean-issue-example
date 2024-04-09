package com.example.controller;

import com.example.model.ExampleData;
import com.example.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExampleController {

   private final ExampleService exampleService;

   @GetMapping(path = {"/get"}, produces = {MediaType.APPLICATION_JSON_VALUE})
   public List<ExampleData> version() {
      return exampleService.get();
   }
}

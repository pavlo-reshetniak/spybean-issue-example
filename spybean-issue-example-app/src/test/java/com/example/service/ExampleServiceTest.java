package com.example.service;

import com.example.model.ExampleData;
import com.example.repository.ExampleRepository;
import com.example.common.MongoConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(
   classes = {
      MongoConfig.class,
      ExampleService.class
   }
)
class ExampleServiceTest {

   @SpyBean
   private ExampleRepository<ExampleData> exampleRepository;

   @Autowired
   private ExampleService exampleService;

   private ExampleData inputData;

   @BeforeEach
   public void setUp() {
      inputData = ExampleData.builder()
                             .id(RandomStringUtils.randomAlphanumeric(10))
                             .someData(RandomStringUtils.randomAlphanumeric(100))
                             .build();

      exampleRepository.saveAll(List.of(inputData));
   }

   @Test
   void shouldExtractDataFromMongo() {
      // When
      var outputData = exampleService.get();

      // Then
      verify(exampleRepository, times(1)).findAll();

      Assertions.assertThat(outputData)
                .usingRecursiveComparison()
                .isEqualTo(List.of(inputData));
   }
}

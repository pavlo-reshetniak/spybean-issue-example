package com.example.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@Document("ExampleData")
public class ExampleData extends AbstractData {

   @Id
   String id;
   String someData;

   @Override
   public String getSomeData() {
      return this.someData;
   }
}

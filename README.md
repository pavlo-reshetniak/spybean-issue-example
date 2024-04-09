# Issue
Starting from Spring-Boot 3.2.x the @SpyBean is not able to initialise MongoRepository bean of the generic type.

# How to reproduce
1. Make sure the spring-boot-starter-parent has version 3.2.x (e.g. 3.2.4) in the parent POM file
2. Run unit test: com.example.service.ExampleServiceTest#shouldExtractDataFromMongo
### Result
```
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.example.repository.ExampleRepository]: Specified class is an interface
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:77)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1311)
	... 39 more
```

# Possible workaround
* Downgrading the spring-boot-starter-parent to version 3.1.10 will fix the issue
* Removing the generic type in under @SpyBean initialization will fix the issue:
```
From this:

   @SpyBean
   private ExampleRepository<ExampleData> exampleRepository;
   
to this:

   @SpyBean
   private ExampleRepository exampleRepository;
```
package org.example.address.retriever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AddressRetrieverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressRetrieverApplication.class, args);
    }
}

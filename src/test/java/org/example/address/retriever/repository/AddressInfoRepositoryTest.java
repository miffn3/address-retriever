package org.example.address.retriever.repository;

import org.example.address.retriever.entity.AddressInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@DataMongoTest
@TestPropertySource(locations="classpath:test.properties")
public class AddressInfoRepositoryTest {

    @Autowired
    private AddressInfoRepository underTest;

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        mongoDBContainer.start();

        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void saveAndFind() {
        AddressInfo addressInfo = new AddressInfo("city", "street", "house", "12345");
        AddressInfo created = underTest.save(addressInfo);

        AddressInfo actual = underTest.findById(created.getId()).get();

        assertEquals(addressInfo.getCity(), actual.getCity());
        assertEquals(addressInfo.getStreet(), actual.getStreet());
        assertEquals(addressInfo.getHouse(), actual.getHouse());
        assertEquals(addressInfo.getKladr(), actual.getKladr());
    }
}

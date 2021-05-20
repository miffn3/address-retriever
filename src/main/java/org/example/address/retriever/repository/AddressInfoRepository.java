package org.example.address.retriever.repository;

import org.example.address.retriever.entity.AddressInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressInfoRepository  extends MongoRepository<AddressInfo, String> {
}

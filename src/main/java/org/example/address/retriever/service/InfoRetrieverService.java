package org.example.address.retriever.service;

import org.example.address.retriever.bo.AddressInfoBO;
import org.example.address.retriever.entity.AddressInfo;
import org.example.address.retriever.exception.AddressInfoNotFoundException;
import org.example.address.retriever.repository.AddressInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InfoRetrieverService {

    private final AddressInfoRepository addressInfoRepository;

    @Autowired
    public InfoRetrieverService(AddressInfoRepository addressInfoRepository) {
        this.addressInfoRepository = addressInfoRepository;
    }

    public AddressInfoBO getAddressInfo(String id) throws AddressInfoNotFoundException {
        Optional<AddressInfo> addressInfoOptional = addressInfoRepository.findById(id);
        if (!addressInfoOptional.isPresent())
            throw new AddressInfoNotFoundException(String.format("Address information with ID:'%s' not found.", id));

        AddressInfo addressInfo = addressInfoOptional.get();

        return new AddressInfoBO(addressInfo.getCity(), addressInfo.getStreet(),
                addressInfo.getHouse(), addressInfo.getKladr());
    }
}

package org.example.address.retriever.service;

import org.example.address.retriever.dto.AddressInfoRequestDto;
import org.example.address.retriever.entity.AddressInfo;
import org.example.address.retriever.exception.DadataException;
import org.example.address.retriever.repository.AddressInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RequestService {

    private final AddressInfoRepository addressInfoRepository;
    private final RequestSenderService requestSenderService;

    @Autowired
    public RequestService(AddressInfoRepository addressInfoRepository, RequestSenderService requestSenderService) {
        this.addressInfoRepository = addressInfoRepository;
        this.requestSenderService = requestSenderService;
    }

    public String getRequestId(AddressInfoRequestDto addressInfoRequestDto) throws DadataException {
        String rawAddress = addressInfoRequestDto.getAddress();
        Map<String, String> mappedResponse = requestSenderService.getEntityFromDadata(rawAddress);

        AddressInfo addressInfo = new AddressInfo(mappedResponse.get("city"),
                mappedResponse.get("street"),
                mappedResponse.get("house"),
                mappedResponse.get("kladr_id"));
        return addressInfoRepository.save(addressInfo).getId();
    }

}

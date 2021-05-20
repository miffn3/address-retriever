package org.example.address.retriever.service;

import org.example.address.retriever.dto.AddressInfoRequestDto;
import org.example.address.retriever.entity.AddressInfo;
import org.example.address.retriever.repository.AddressInfoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RequestServiceTest {

    private RequestService underTest;

    @Mock
    private AddressInfoRepository repository;

    @Mock
    private RequestSenderService requestSenderService;

    @BeforeAll
    void setUpClass() {
        MockitoAnnotations.openMocks(this);
        underTest = new RequestService(repository, requestSenderService);
    }

    @Test
    void getRequestId() {
        String address = "саратов московская 1", id = "123abc", city = "Саратов", street = "Московская",
                house = "1", kladr = "123";

        AddressInfoRequestDto addressInfoRequestDto = new AddressInfoRequestDto(address);
        AddressInfo addressInfo = new AddressInfo(city, street, house, kladr);
        AddressInfo addressInfoSaved = new AddressInfo(id, city, street, house, kladr);
        Map<String, String> mappedResponse = new HashMap<>();
        mappedResponse.put("city", city);
        mappedResponse.put("street", street);
        mappedResponse.put("house", house);
        mappedResponse.put("kladr_id", kladr);


        when(repository.save(addressInfo)).thenReturn(addressInfoSaved);
        when(requestSenderService.getEntityFromDadata(address)).thenReturn(mappedResponse);

        String actualId = underTest.getRequestId(addressInfoRequestDto);

        assertEquals(id, actualId);
    }
}
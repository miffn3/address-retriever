package org.example.address.retriever.controller;

import org.example.address.retriever.bo.AddressInfoBO;
import org.example.address.retriever.dto.AddressInfoRequestDto;
import org.example.address.retriever.dto.AddressInfoResponseDto;
import org.example.address.retriever.entity.AddressInfo;
import org.example.address.retriever.exception.AddressInfoNotFoundException;
import org.example.address.retriever.service.InfoRetrieverService;
import org.example.address.retriever.service.RequestService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddressControllerTest {

    private AddressController underTest;

    @Mock
    private RequestService requestService;

    @Mock
    private InfoRetrieverService infoRetrieverService;

    @BeforeAll
    void setUpClass() {
        MockitoAnnotations.openMocks(this);
        underTest = new AddressController(this.requestService, this.infoRetrieverService);
    }

    @Test
    void getRequestId() {
        String id = "123";
        AddressInfoRequestDto address = new AddressInfoRequestDto("саратов московская 1");
        when(requestService.getRequestId(address)).thenReturn(id);

        String response = underTest.getRequestId(address).getBody();
        assertEquals(id, response);
    }

    @Test
    void getAddressInfo() throws AddressInfoNotFoundException {
        String id = "123", city = "Саратов", street = "Московская", house = "1", kladr = "123abc";
        AddressInfoBO addressInfo = new AddressInfoBO(city, street, house, kladr);
        AddressInfoResponseDto expected = new AddressInfoResponseDto(city, street, house, kladr);

        when(infoRetrieverService.getAddressInfo(id)).thenReturn(addressInfo);

        AddressInfoResponseDto response = underTest.getAddressInfo(id).getBody();
        assertEquals(expected, response);
    }
}
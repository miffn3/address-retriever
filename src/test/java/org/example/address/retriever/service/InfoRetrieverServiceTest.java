package org.example.address.retriever.service;

import org.example.address.retriever.bo.AddressInfoBO;
import org.example.address.retriever.entity.AddressInfo;
import org.example.address.retriever.exception.AddressInfoNotFoundException;
import org.example.address.retriever.repository.AddressInfoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InfoRetrieverServiceTest {

    private InfoRetrieverService underTest;

    @Mock
    private AddressInfoRepository repository;

    @BeforeAll
    void setUpClass() {
        MockitoAnnotations.openMocks(this);
        underTest = new InfoRetrieverService(repository);
    }

    @Test
    void getAddressInfo() throws AddressInfoNotFoundException {
        AddressInfo addressInfo = new AddressInfo("id", "city", "street", "house", "12345");
        Optional<AddressInfo> opt = Optional.of(addressInfo);

        when(repository.findById("id")).thenReturn(opt);

        AddressInfoBO actual = underTest.getAddressInfo(addressInfo.getId());
        assertEquals(addressInfo.getCity(), actual.getCity());
        assertEquals(addressInfo.getStreet(), actual.getStreet());
        assertEquals(addressInfo.getHouse(), actual.getHouse());
        assertEquals(addressInfo.getKladr(), actual.getKladr());
    }
}
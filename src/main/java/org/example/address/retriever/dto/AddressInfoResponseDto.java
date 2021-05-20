package org.example.address.retriever.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInfoResponseDto {
    private String city;
    private String street;
    private String house;
    private String kladr;
}

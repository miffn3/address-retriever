package org.example.address.retriever.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfoBO {
    private String city;
    private String street;
    private String house;
    private String kladr;
}

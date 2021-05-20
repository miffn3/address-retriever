package org.example.address.retriever.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfo {
    @Id
    private String id;
    private String city;
    private String street;
    private String house;
    private String kladr;

    public AddressInfo(String city, String street, String house, String kladr) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.kladr = kladr;
    }

    @Override
    public String toString() {
        return String.format(
                "AddressInfo[id=%s, city='%s', street='%s', house='%s', kladr='%s']",
                id, city, street, house, kladr);
    }
}

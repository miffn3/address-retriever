package org.example.address.retriever.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInfoRequestDto {
    @NotBlank(message = "Address is mandatory")
    private String address;
}

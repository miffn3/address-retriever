package org.example.address.retriever.controller;

import org.example.address.retriever.bo.AddressInfoBO;
import org.example.address.retriever.dto.AddressInfoResponseDto;
import org.example.address.retriever.dto.AddressInfoRequestDto;
import org.example.address.retriever.exception.AddressInfoNotFoundException;
import org.example.address.retriever.exception.DadataException;
import org.example.address.retriever.service.InfoRetrieverService;
import org.example.address.retriever.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final RequestService requestService;
    private final InfoRetrieverService infoRetrieverService;

    @Autowired
    public AddressController(RequestService requestService, InfoRetrieverService infoRetrieverService) {
        this.requestService = requestService;
        this.infoRetrieverService = infoRetrieverService;
    }

    @PostMapping("/request-id")
    @ResponseBody
    public ResponseEntity<String> getRequestId(@RequestBody @Valid AddressInfoRequestDto body) {
        try {
            return ResponseEntity
                    .ok(requestService.getRequestId(body));
        } catch (DadataException ex) {
            return ResponseEntity
                    .status(ex.getStatusCode())
                    .body(ex.getMessage());
        }

    }

    @GetMapping("/address-info/{id}")
    @ResponseBody
    public ResponseEntity<AddressInfoResponseDto> getAddressInfo(@PathVariable String id) {
        try {
            AddressInfoBO addressInfoBo = infoRetrieverService.getAddressInfo(id);
            AddressInfoResponseDto addressInfoResponseDto =
                    new AddressInfoResponseDto(addressInfoBo.getCity(), addressInfoBo.getStreet(),
                            addressInfoBo.getHouse(), addressInfoBo.getKladr());

            return ResponseEntity
                    .ok()
                    .body(addressInfoResponseDto);
        } catch (AddressInfoNotFoundException ex) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}

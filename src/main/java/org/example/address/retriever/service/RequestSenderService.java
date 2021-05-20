package org.example.address.retriever.service;

import org.example.address.retriever.exception.DadataException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class RequestSenderService {

    @Value("${dadata.url}")
    private String url;

    @Value("${dadata.token}")
    private String token;

    @Value("${dadata.secret}")
    private String secret;

    public Map<String, String> getEntityFromDadata(String rawAddress) {
        ResponseEntity<String> responseEntity = sendRequestToDaData(rawAddress);

        HttpStatus statusCode = responseEntity.getStatusCode();
        if(statusCode.isError())
            throw new DadataException(statusCode,
                    String.format("Response came with error status code: '%s'.", statusCode));
        JsonParser parser = JsonParserFactory.getJsonParser();
        List<Object> parsedResponse = parser.parseList(responseEntity.getBody());
        if(parsedResponse.size() == 0 || !(parsedResponse.get(0) instanceof Map))
            throw new DadataException(HttpStatus.BAD_REQUEST, "Bad response from Dadata's server.");

        Map<String, String> mappedResponse = (Map<String, String>)parsedResponse.get(0);
        return mappedResponse;
    }

    private ResponseEntity<String> sendRequestToDaData(String rawAddress) {
        String jsonBody = String.format("[\"%s\"]", rawAddress);
        return post(jsonBody);
    }

    private ResponseEntity<String> post(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; utf-8");
        headers.add("Authorization", "Token " + token);
        headers.add("X-Secret", secret);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(url, entity, String.class);
    }
}

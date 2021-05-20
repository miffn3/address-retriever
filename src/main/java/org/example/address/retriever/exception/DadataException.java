package org.example.address.retriever.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class DadataException extends HttpStatusCodeException {


    public DadataException(HttpStatus status, String message) {
        super(status, message);
    }
}

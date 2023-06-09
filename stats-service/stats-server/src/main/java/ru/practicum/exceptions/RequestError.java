package ru.practicum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RequestError extends ResponseStatusException {

    public RequestError(HttpStatus status, String message) {
        super(status, message);
    }
}

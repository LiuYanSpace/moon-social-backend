package com.bird.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundRequestException extends ResponseStatusException {
    public NotFoundRequestException(ErrorReasonCode reason) {
        super(HttpStatus.NOT_FOUND, reason.name());
    }
}

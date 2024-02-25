package com.bird.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConflictRequestException extends ResponseStatusException {
    public ConflictRequestException(ErrorReasonCode reason) {
        super(HttpStatus.CONFLICT, reason.name());
    }
}

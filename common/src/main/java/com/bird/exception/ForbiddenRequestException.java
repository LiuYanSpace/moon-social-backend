package com.bird.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ForbiddenRequestException extends ResponseStatusException {
    public ForbiddenRequestException(ErrorReasonCode reason) {
        super(HttpStatus.FORBIDDEN, reason.name());
    }
}

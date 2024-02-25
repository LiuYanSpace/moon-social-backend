package com.tothemoon.common.config.errorhandler;

import com.tothemoon.common.config.SecurityUtil;
import com.bird.exception.BadRequestException;
import com.bird.exception.ErrorReasonCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@Slf4j
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDeniedException(HttpServletRequest req, Exception e) {
        log.warn("Exception triggered by {} when accessing {} {} : {}",
                SecurityUtil.getCurrentUserLogin(),
                req.getMethod(),
                req.getRequestURI(),
                e);

        return new ResponseEntity<>(
                ErrorReasonCode.ACCESS_Denied,
                new HttpHeaders(),
                HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler({ResponseStatusException.class})
    protected ResponseEntity<Object> handleResponseStatus(
            ResponseStatusException e) {
        log.warn("ResponseStatusException triggered by {} : {}", SecurityUtil.getCurrentUserLogin(), e.getStatus());

        return new ResponseEntity<>(e.getReason(), new HttpHeaders(), e.getStatus());
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    protected ResponseEntity<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.warn("MaxUploadSizeExceededException triggered by {} : {}", SecurityUtil.getCurrentUserLogin(), e);

        return new ResponseEntity<>(
                ErrorReasonCode.Size_Limit_Exceeded,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleMissedException(HttpServletRequest req, Exception e) {
        log.error("Exception triggered by {} when accessing {} {} : {}",
                SecurityUtil.getCurrentUserLogin(),
                req.getMethod(),
                req.getRequestURI(),
                e);

        return new ResponseEntity<>(
                ErrorReasonCode.Server_Error,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadRequestException.class})
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(
                ex.getReason(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ClientAbortException.class})
    protected ResponseEntity<Object> handleClientAbortException(HttpServletRequest req, ClientAbortException e) {
        log.warn("ClientAbortException triggered by {} when accessing {} {} : {}",
                SecurityUtil.getCurrentUserLogin(),
                req.getMethod(),
                req.getRequestURI(),
                e);

        return new ResponseEntity<>(
                ErrorReasonCode.Server_Error,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

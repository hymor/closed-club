package com.club.closed_club.exception;

import com.club.closed_club.dto.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class InvalidQrException extends RuntimeException {
    public InvalidQrException(String message) {
        super(message);
    }

}

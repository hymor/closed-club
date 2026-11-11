package com.club.closed_club.exception;

import com.club.closed_club.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiError> handleMemberNotFound(
            MemberNotFoundException ex) {
        ApiError error = new ApiError(
                404,
                "MEMBER_NOT_FOUND",
                ex.getMessage(),
                Instant.now()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(InvalidQrException.class)
    public ResponseEntity<ApiError> handleInvalidQr(
            InvalidQrException ex) {
        ApiError error = new ApiError(
                403,
                "QR_ACCESS_DENIED",
                ex.getMessage(),
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(error);
    }
}

package com.club.closed_club.dto.error;

import java.time.Instant;

public record ApiError (
    int status,
    String code,
    String message,
    Instant timestamp
) {}

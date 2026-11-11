package com.club.closed_club.dto;

import java.time.Instant;

public record ApiError (
    int status,
    String code,
    String message,
    Instant timestamp
) {}

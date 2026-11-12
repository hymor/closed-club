package com.club.closed_club.dto;

import java.util.UUID;

public record QrScanResponse(
        String memberName,
        UUID newUuid
) {}

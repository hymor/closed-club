package com.club.closed_club.dto;

import java.util.UUID;

public record QrScanResponse(
        Integer memberId,
        String memberName,
        UUID newUuid
) {}

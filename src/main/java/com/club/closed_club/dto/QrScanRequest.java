package com.club.closed_club.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record QrScanRequest(
        @NotNull UUID uuid
) {}

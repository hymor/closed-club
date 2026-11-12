package com.club.closed_club.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MemberUpdateRequest(
        @NotBlank
        @Size(max = 100)
        String name,

        @NotNull
        Boolean status
) {}
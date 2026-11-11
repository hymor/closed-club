package com.club.closed_club.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberCreateRequest(
        @NotBlank(message = "Имя не может быть пустым")
        @Size(max = 100, message = "Имя не должно превышать 100 символов")
        String name
) {}
package com.alexeymelekhov.subscriptionservice.subscriptionservice.dto;

import java.util.Map;

public record ErrorResponseDTO(
        int status,
        String message,
        Map<String, String> errors
) {
}

package com.example.presentation.api.rest.auth.dto;

public record AuthTokenResponse(String token, String tokenType, long expiresInMs) {
}

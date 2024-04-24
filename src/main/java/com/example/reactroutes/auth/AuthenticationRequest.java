package com.example.reactroutes.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
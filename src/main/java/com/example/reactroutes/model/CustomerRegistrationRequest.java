package com.example.reactroutes.model;

public record CustomerRegistrationRequest(
        String name,
        String email,
        String password
) {
}

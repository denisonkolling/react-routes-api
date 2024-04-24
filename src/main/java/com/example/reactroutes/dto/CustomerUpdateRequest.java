package com.example.reactroutes.dto;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {
}

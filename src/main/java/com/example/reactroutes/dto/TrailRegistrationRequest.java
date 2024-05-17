package com.example.reactroutes.dto;

public record TrailRegistrationRequest(
        String title,
        String location,
        Double rate,
        Integer reviews,
        Double length,
        String time,
        String imageURL
) {
}

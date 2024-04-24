package com.example.reactroutes.auth;

import com.example.reactroutes.dto.CustomerDTO;

public record AuthenticationResponse (
        String token,
        CustomerDTO customerDTO){
}

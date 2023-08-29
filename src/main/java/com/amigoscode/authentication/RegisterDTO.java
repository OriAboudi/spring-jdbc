package com.amigoscode.authentication;

public record RegisterDTO(String firstname,
                          String lastname,
                          String email,
                          String password
                          ) {
}

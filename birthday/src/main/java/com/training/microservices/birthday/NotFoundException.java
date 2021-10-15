package com.training.microservices.birthday;

public class NotFoundException extends RuntimeException {
    NotFoundException(Long id) {
        super("Could not find birthday person " + id);
    }
}

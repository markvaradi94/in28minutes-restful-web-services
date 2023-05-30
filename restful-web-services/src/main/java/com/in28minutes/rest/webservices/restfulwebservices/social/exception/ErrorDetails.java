package com.in28minutes.rest.webservices.restfulwebservices.social.exception;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record ErrorDetails(
        String message,
        String details,
        LocalDateTime timeStamp
) {
}

package com.in28minutes.rest.webservices.restfulwebservices.hello;

import lombok.Builder;

@Builder
public record HelloWorldBean(
        String message
) {
}

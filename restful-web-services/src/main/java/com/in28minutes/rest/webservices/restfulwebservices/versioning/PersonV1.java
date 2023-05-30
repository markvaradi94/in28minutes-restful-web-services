package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import lombok.Builder;

@Builder
public record PersonV1(
        String name
) {
}

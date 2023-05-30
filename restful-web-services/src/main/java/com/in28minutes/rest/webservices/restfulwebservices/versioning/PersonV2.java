package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import lombok.Builder;

@Builder
public record PersonV2(
        Name name
) {
}

@Builder
record Name(String firstName,
            String lastName) {
}

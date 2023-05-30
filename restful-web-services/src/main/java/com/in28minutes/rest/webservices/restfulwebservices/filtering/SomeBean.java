package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Builder;
import lombok.experimental.FieldNameConstants;

@Builder
@FieldNameConstants
@JsonFilter("SomeBeanFilter")
// class level static filtering for serialization
//@JsonIgnoreProperties(field1)
public record SomeBean(
        String field1,
        // field level static filtering for serialization
//        @JsonIgnore
        String field2,
        String field3
) {
}

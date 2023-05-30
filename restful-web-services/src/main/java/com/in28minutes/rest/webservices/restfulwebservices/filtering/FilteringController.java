package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.in28minutes.rest.webservices.restfulwebservices.filtering.SomeBean.Fields.*;

@RestController
@RequestMapping(produces = "application/json")
public class FilteringController {

    @GetMapping(path = "filtering")
    public MappingJacksonValue filtering() {
        return serializeResponse(
                new SomeBean("value1", "value2", "value3"),
                field1, field3);
    }

    @GetMapping(path = "filtering-list")
    public MappingJacksonValue filteringList() {
        return serializeResponse(List.of(
                        new SomeBean("value1", "value2", "value3"),
                        new SomeBean("value1", "value2", "value3"),
                        new SomeBean("value1", "value2", "value3"),
                        new SomeBean("value1", "value2", "value3")),
                field2, field3);
    }

    private <T> MappingJacksonValue serializeResponse(T response, String... fieldNames) {
        var mappingJacksonValue = new MappingJacksonValue(response);
        var filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldNames);
        var filters = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}

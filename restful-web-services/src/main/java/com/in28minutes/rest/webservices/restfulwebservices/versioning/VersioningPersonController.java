package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping(path = "v1/person", produces = "application/json")
    public PersonV1 getFirstVersionURL() {
        return new PersonV1("Tike Myson");
    }

    @GetMapping(path = "v2/person", produces = "application/json")
    public PersonV2 getSecondVersionURL() {
        return new PersonV2(new Name("Tike", "Myson"));
    }

    @GetMapping(path = "person", params = "version=1", produces = "application/json")
    public PersonV1 getFirstVersionParam() {
        return new PersonV1("Nagy Gyuszi");
    }

    @GetMapping(path = "person", params = "version=2", produces = "application/json")
    public PersonV2 getSecondVersionParam() {
        return new PersonV2(new Name("Guszti", "Fekete"));
    }

    @GetMapping(path = "person/header", headers = "X-API-VERSION=1", produces = "application/json")
    public PersonV1 getFirstVersionHeader() {
        return new PersonV1("Lagzi Lajos");
    }

    @GetMapping(path = "person/header", headers = "X-API-VERSION=2", produces = "application/json")
    public PersonV2 getSecondVersionHeader() {
        return new PersonV2(new Name("Zambo", "Jimmy"));
    }

    @GetMapping(path = "person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionAcceptHeader() {
        return new PersonV1("Balazs Pali");
    }

    @GetMapping(path = "person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionAcceptHeader() {
        return new PersonV2(new Name("Bunyos", "Pityu"));
    }
}

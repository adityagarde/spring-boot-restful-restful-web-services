package com.github.adityagarde.rest.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping(path = "/v1/person")
    public Person getPerson() {
        return new Person("Aditya Garde");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2(new Name("Aditya", "Garde"));
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public Person getPersonWithParam() {
        return new Person("Aditya Garde");
    }

    @GetMapping(path = "/person/param", params = "version=2")
    public PersonV2 getPersonV2WithParam() {
        return new PersonV2(new Name("Aditya", "Garde"));
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public Person getPersonWithHeader() {
        return new Person("Aditya Garde");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getPersonV2WithHeader() {
        return new PersonV2(new Name("Aditya", "Garde"));
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public Person getPersonWithProduces() {
        return new Person("Aditya Garde");
    }

    @GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonV2WithProduces() {
        return new PersonV2(new Name("Aditya", "Garde"));
    }
}

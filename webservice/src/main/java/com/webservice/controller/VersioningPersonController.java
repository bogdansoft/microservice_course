package com.webservice.controller;

import com.webservice.model.versioning.Name;
import com.webservice.model.versioning.PersonV1;
import com.webservice.model.versioning.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VersioningPersonController {

    @GetMapping("/v1/persons")
    public PersonV1 getPersonV1() {
        return new PersonV1("Pes Duke");
    }

    @GetMapping("/v2/persons")
    public PersonV2 getPersonV2() {
        return new PersonV2(new Name("Pes", "Duke"));
    }

    @GetMapping(path = "/persons", params = "version=1")
    public PersonV1 getPersonV1RequestParam() {
        return new PersonV1("Pes Duke");
    }

    @GetMapping(path = "/persons", params = "version=2")
    public PersonV2 getPersonV2RequestParam() {
        return new PersonV2(new Name("Pes", "Duke"));
    }

    @GetMapping(path = "/persons/header", headers = "X-API-VERSION=1")
    public PersonV1 getPersonV1RequestHeader() {
        return new PersonV1("Pes Duke");
    }

    @GetMapping(path = "/persons/header", headers = "X-API-VERSION=2")
    public PersonV2 getPersonV2RequestHeader() {
        return new PersonV2(new Name("Pes", "Duke"));
    }

    @GetMapping(path = "/persons/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonV1AcceptHeader() {
        return new PersonV1("Pes Duke");
    }

    @GetMapping(path = "/persons/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonV2AcceptHeader() {
        return new PersonV2(new Name("Pes", "Duke"));
    }
}

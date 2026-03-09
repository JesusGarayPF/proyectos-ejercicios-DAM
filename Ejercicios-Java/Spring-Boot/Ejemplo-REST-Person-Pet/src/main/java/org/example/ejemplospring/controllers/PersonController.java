package org.example.ejemplospring.controllers;

import net.datafaker.Faker;
import org.example.ejemplospring.entities.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final Faker faker = new Faker();

    @GetMapping("")
    public String getSomething() {
        return "Hello!";
    }

    @GetMapping("/other")
    public String getOtherSomething() {
        return "Goodbye!";
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> getById(@PathVariable("personId") Integer id) {
        System.out.printf("Se ha recibido el id %d\n", id);
        if (id < 0 || id > 10) {
            return ResponseEntity.notFound().build();
        }
        ResponseEntity.ok(new Person(
                id,
                faker.name().firstName(),
                faker.name().lastName());

        return;
    }
}

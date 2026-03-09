package org.example.ejemplospring.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Person {
    @EqualsAndHashCode.Include
    private final int personId;
    private final String firstName;
    private final String lastName;
}

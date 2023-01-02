package com.webservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private Integer id;
    @Size(min = 2, message = "Name should be not less than 2 characters")
    @JsonProperty("user_name")
    private String name;
    @Past(message = "Birth date should be in the past")
    private LocalDate birthDate;
}

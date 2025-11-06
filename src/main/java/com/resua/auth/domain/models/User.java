package com.resua.auth.domain.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {

    private String name;
    private String lastName;
    private String email;
    private String profession;
    private LocalDate birthDate;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
}

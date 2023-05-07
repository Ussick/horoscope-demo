package com.lohika.goroscopedemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lohika.goroscopedemo.entities.Horoscope;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;



@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Day of birth cannot be empty")
    private String day;

    @NotBlank(message = "Month of birth cannot be empty")
    private String month;

    @NotBlank(message = "Year of birth cannot be empty")
    private String year;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    private String email;

    private String gender;

    @JsonIgnore
    private List<Horoscope> horoscopes = new ArrayList<>();
}

package com.github.alfonsoleandro.pencaucu.business.authentication.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
@Setter
public class RegisterDTO {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Pattern(regexp = "^[0-9]{8}$")
    private String ci;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
    @Size(min = 8)
    private String phone;

    @NotNull
    private int idCarrera;

    @NotNull
    private int idCampeon;

    @NotNull
    private int idSubcampeon;

}

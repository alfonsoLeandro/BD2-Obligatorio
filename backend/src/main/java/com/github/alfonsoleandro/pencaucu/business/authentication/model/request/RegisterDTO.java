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

    @NotNull(message = "NOMBRE_NULO")
    private String nombre;

    @NotNull(message = "APELLIDO_NULO")
    private String apellido;

    @NotNull(message = "CI_NULO")
    @Pattern(regexp = "^[0-9]{8}$", message = "CI_INVALIDO")
    private String ci;

    @NotNull(message = "EMAIL_NULO")
    @Email(message = "EMAIL_INVALIDO")
    private String email;

    @NotNull(message = "PASSWORD_NULO")
    @Size(min = 8, message = "PASSWORD_DEMASIADO_CORTO")
    private String password;

    @NotNull(message = "PHONE_NULO")
    @Size(min = 8, max = 11, message = "PHONE_INVALIDO")
    private String telefono;

    @NotNull(message = "ID_CARRERA_NULO")
    private int idCarrera;

    @NotNull(message = "ID_CAMPEON_NULO")
    private int idCampeon;

    @NotNull(message = "ID_SUBCAMPEON_NULO")
    private int idSubcampeon;

}

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
public class LoginDTO {

    @NotNull(message = "EMAIL_NULO")
    @Email(message = "EMAIL_INVALIDO")
    private String email;

    @NotNull(message = "PASSWORD_NULO")
    private String password;

}

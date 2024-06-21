package com.github.alfonsoleandro.pencaucu.business.authentication.model.response;

import com.github.alfonsoleandro.pencaucu.persistence.Role;
import lombok.Getter;
import lombok.Setter;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
@Setter
public class AuthenticationDTO {

    private Integer id;
    private String token;
    private Role rol;

}

package com.github.alfonsoleandro.pencaucu.business.authentication;

import com.github.alfonsoleandro.pencaucu.business.authentication.model.request.RegisterDTO;
import com.github.alfonsoleandro.pencaucu.business.authentication.model.response.AuthenticationDTO;
import com.github.alfonsoleandro.pencaucu.rest.exception.ApiException;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public interface AuthenticationService {

    AuthenticationDTO register(RegisterDTO registerBody) throws ApiException;

}

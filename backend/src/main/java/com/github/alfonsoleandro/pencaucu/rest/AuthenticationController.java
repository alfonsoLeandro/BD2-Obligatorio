package com.github.alfonsoleandro.pencaucu.rest;

import com.github.alfonsoleandro.pencaucu.business.authentication.AuthenticationService;
import com.github.alfonsoleandro.pencaucu.business.authentication.model.request.LoginDTO;
import com.github.alfonsoleandro.pencaucu.business.authentication.model.request.RegisterDTO;
import com.github.alfonsoleandro.pencaucu.business.authentication.model.response.AuthenticationDTO;
import com.github.alfonsoleandro.pencaucu.rest.exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationDTO register(@RequestBody @Valid RegisterDTO registerBody) throws ApiException {
        return this.authenticationService.register(registerBody);
    }

    @PostMapping("/login")
    public AuthenticationDTO login(@RequestBody @Valid LoginDTO loginBody) throws ApiException {
        return this.authenticationService.login(loginBody);
    }


}

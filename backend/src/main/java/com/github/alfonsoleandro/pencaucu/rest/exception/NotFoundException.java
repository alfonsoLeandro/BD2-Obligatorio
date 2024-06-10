package com.github.alfonsoleandro.pencaucu.rest.exception;

import com.github.alfonsoleandro.pencaucu.business.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public class NotFoundException extends ApiException {

    public NotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode, HttpStatus.NOT_FOUND);
    }

}

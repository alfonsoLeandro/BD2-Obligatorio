package com.github.alfonsoleandro.pencaucu.rest.exception;

import com.github.alfonsoleandro.pencaucu.business.exception.ExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
public class ApiException extends Exception {

    protected final ExceptionCode exceptionCode;
    protected final HttpStatus httpStatus;

    public ApiException(ExceptionCode exceptionCode, HttpStatus httpStatus) {
        super(exceptionCode.toString());
        this.exceptionCode = exceptionCode;
        this.httpStatus = httpStatus;
    }

}

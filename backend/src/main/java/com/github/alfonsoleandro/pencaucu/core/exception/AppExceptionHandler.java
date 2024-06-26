package com.github.alfonsoleandro.pencaucu.core.exception;

import com.github.alfonsoleandro.pencaucu.rest.exception.ApiException;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorApiResponse> apiExceptionHandler(ApiException ex, WebRequest request) {
        log.error("Api exception", ex);
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(buildErrorApiResponse(ex.getMessage(), request));
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApiResponse> validationExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Validation exception", ex);
        String firstErrorMessage = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildErrorApiResponse(firstErrorMessage, request));
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorApiResponse> badCredentialExceptionHandler(BadCredentialsException ex, WebRequest request) {
        log.error("Credentials exception", ex);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(buildErrorApiResponse("BAD_CREDENTIALS", request));
    }

    @ResponseBody
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, NoResourceFoundException.class})
    public ResponseEntity<ErrorApiResponse> notFoundExceptionHandler(ServletException ex, WebRequest request) {
        log.error("Not found exception", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildErrorApiResponse("NOT_FOUND", request));
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApiResponse> genericExceptionHandler(Exception ex, WebRequest request) {
        log.error("Internal server error", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildErrorApiResponse("ERROR_INESPERADO", request));
    }

    private ErrorApiResponse buildErrorApiResponse(String message, WebRequest request) {
        ErrorApiResponse errorApiResponse = new ErrorApiResponse();
        errorApiResponse.setMessage(message);
        errorApiResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorApiResponse.setTimestamp(OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        return errorApiResponse;
    }
}

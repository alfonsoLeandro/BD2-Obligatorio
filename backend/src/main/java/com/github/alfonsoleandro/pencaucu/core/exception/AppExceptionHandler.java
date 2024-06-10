package com.github.alfonsoleandro.pencaucu.core.exception;

import com.github.alfonsoleandro.pencaucu.rest.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(buildErrorApiResponse(ex.getMessage(), request));
    }

    private ErrorApiResponse buildErrorApiResponse(String message, WebRequest request) {
        ErrorApiResponse errorApiResponse = new ErrorApiResponse();
        errorApiResponse.setMessage(message);
        errorApiResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorApiResponse.setTimestamp(OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        return errorApiResponse;
    }
}

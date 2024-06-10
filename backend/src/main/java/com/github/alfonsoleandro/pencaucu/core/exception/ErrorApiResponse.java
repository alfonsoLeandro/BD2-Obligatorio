package com.github.alfonsoleandro.pencaucu.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
@Setter
public class ErrorApiResponse {

    private String message;
    private String path;
    private String timestamp;

}

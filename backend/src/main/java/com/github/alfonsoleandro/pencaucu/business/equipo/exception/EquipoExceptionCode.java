package com.github.alfonsoleandro.pencaucu.business.equipo.exception;

import com.github.alfonsoleandro.pencaucu.business.exception.ExceptionCode;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public enum EquipoExceptionCode implements ExceptionCode {

    CAMPEON_NO_ENCONTRADO,
    SUBCAMPEON_NO_ENCONTRADO,
    CAMPEON_IGUAL_SUBCAMPEON,
    EQUIPO_1_NO_ENCONTRADO,
    EQUIPO_2_NO_ENCONTRADO,
    EQUIPO_1_IGUAL_EQUIPO_2
}

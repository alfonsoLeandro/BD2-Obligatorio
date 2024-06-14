package com.github.alfonsoleandro.pencaucu.business.partido.exception;

import com.github.alfonsoleandro.pencaucu.business.exception.ExceptionCode;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public enum PartidoExceptionCode implements ExceptionCode {

    PARTIDO_NO_ENCONTRADO,
    EQUIPOS_NO_DEFINIDOS_PARA_PARTIDO,
    EQUIPOS_DEFINIDOS_NO_COINCIDEN,

}

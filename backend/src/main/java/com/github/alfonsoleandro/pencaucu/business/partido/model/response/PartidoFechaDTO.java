package com.github.alfonsoleandro.pencaucu.business.partido.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@AllArgsConstructor
@Getter
@Setter
public class PartidoFechaDTO {

    private Integer id;

    private Timestamp fecha;

}

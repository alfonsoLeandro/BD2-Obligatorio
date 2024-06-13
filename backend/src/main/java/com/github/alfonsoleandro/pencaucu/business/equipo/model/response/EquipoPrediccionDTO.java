package com.github.alfonsoleandro.pencaucu.business.equipo.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
@Setter
public class EquipoPrediccionDTO {

    private int id;

    private String nombre;

    private Integer goles;

    private Integer prediccion;

}

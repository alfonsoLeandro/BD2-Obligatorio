package com.github.alfonsoleandro.pencaucu.business.alumno.model.response;

import com.github.alfonsoleandro.pencaucu.business.carrera.model.response.CarreraDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
@Setter
public class AlumnoDTO {

    private Integer id;

    private String nombre;

    private CarreraDTO carrera;

    private Integer puntaje;

}
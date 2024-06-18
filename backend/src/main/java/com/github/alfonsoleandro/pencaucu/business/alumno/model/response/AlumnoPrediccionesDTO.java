package com.github.alfonsoleandro.pencaucu.business.alumno.model.response;

import com.github.alfonsoleandro.pencaucu.business.carrera.model.response.CarreraDTO;
import com.github.alfonsoleandro.pencaucu.business.equipo.model.response.EquipoPrediccionDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
@Setter
public class AlumnoPrediccionesDTO {

    private Integer idAlumno;

    private String nombreAlumno;

    private CarreraDTO carrera;

    private EquipoPrediccionDTO equipo1;

    private EquipoPrediccionDTO equipo2;

}
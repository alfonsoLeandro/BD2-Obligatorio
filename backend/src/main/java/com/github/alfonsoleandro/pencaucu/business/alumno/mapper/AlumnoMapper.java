package com.github.alfonsoleandro.pencaucu.business.alumno.mapper;

import com.github.alfonsoleandro.pencaucu.business.alumno.model.response.AlumnoPrediccionesDTO;
import com.github.alfonsoleandro.pencaucu.persistence.view.AlumnosPrediccionesView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Mapper
public interface AlumnoMapper {

    @Mapping(source = "idAlumno", target = "idAlumno")
    @Mapping(source = "nombreAlumno", target = "nombreAlumno")
    @Mapping(source = "idCarrera", target = "carrera.id")
    @Mapping(source = "nombreCarrera", target = "carrera.nombre")
    @Mapping(source = "idEquipo1", target = "equipo1.id")
    @Mapping(source = "nombreEquipo1", target = "equipo1.nombre")
    @Mapping(source = "prediccionEquipo1", target = "equipo1.prediccion")
    @Mapping(source = "idEquipo2", target = "equipo2.id")
    @Mapping(source = "nombreEquipo2", target = "equipo2.nombre")
    @Mapping(source = "prediccionEquipo2", target = "equipo2.prediccion")
    AlumnoPrediccionesDTO viewToPrediccionesDTO(AlumnosPrediccionesView view);

}

package com.github.alfonsoleandro.pencaucu.business.alumno.mapper;

import com.github.alfonsoleandro.pencaucu.business.alumno.model.response.AlumnoDTO;
import com.github.alfonsoleandro.pencaucu.business.alumno.model.response.AlumnoPrediccionesDTO;
import com.github.alfonsoleandro.pencaucu.business.usuario.model.response.AlumnoPrediccionesDetalleDTO;
import com.github.alfonsoleandro.pencaucu.persistence.view.AlumnoPuntajeDetalleView;
import com.github.alfonsoleandro.pencaucu.persistence.view.AlumnoPuntajeView;
import com.github.alfonsoleandro.pencaucu.persistence.view.AlumnosPrediccionesView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Mapper
public interface AlumnoMapper {

    @Mapping(source = "idCarrera", target = "carrera.id")
    @Mapping(source = "nombreCarrera", target = "carrera.nombre")
    @Mapping(source = "idEquipo1", target = "equipo1.id")
    @Mapping(source = "nombreEquipo1", target = "equipo1.nombre")
    @Mapping(source = "prediccionEquipo1", target = "equipo1.prediccion")
    @Mapping(source = "idEquipo2", target = "equipo2.id")
    @Mapping(source = "nombreEquipo2", target = "equipo2.nombre")
    @Mapping(source = "prediccionEquipo2", target = "equipo2.prediccion")
    AlumnoPrediccionesDTO viewToPrediccionesDTO(AlumnosPrediccionesView view);

    @Mapping(source = "idUsuario", target = "id")
    @Mapping(source = "idCarrera", target = "carrera.id")
    @Mapping(source = "nombreCarrera", target = "carrera.nombre")
    @Mapping(target = "puntaje", ignore = true)
    AlumnoDTO puntajeViewToDTO(AlumnoPuntajeView view);

    @Mapping(source = "idEtapa", target = "etapa.id")
    @Mapping(source = "nombreEtapa", target = "etapa.nombre")
    @Mapping(source = "idEquipo1", target = "equipo1.id")
    @Mapping(source = "nombreEquipo1", target = "equipo1.nombre")
    @Mapping(source = "prediccionEquipo1", target = "equipo1.prediccion")
    @Mapping(source = "golesEquipo1", target = "equipo1.goles")
    @Mapping(source = "idEquipo2", target = "equipo2.id")
    @Mapping(source = "nombreEquipo2", target = "equipo2.nombre")
    @Mapping(source = "prediccionEquipo2", target = "equipo2.prediccion")
    @Mapping(source = "golesEquipo2", target = "equipo2.goles")
    @Mapping(target = "puntajeObtenido", ignore = true)
	AlumnoPrediccionesDetalleDTO puntajeDetalleViewToDetalleDTO(AlumnoPuntajeDetalleView alumnoPuntajeView);
}

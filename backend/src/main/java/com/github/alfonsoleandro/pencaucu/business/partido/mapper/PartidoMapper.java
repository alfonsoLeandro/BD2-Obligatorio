package com.github.alfonsoleandro.pencaucu.business.partido.mapper;

import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoDTO;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoDetailsDTO;
import com.github.alfonsoleandro.pencaucu.persistence.view.PartidoSearchView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Mapper
public interface PartidoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "idEtapa", target = "etapa.id")
    @Mapping(source = "nombreEtapa", target = "etapa.nombre")
    @Mapping(source = "idEquipo1", target = "equipo1.id")
    @Mapping(source = "nombreEquipo1", target = "equipo1.nombre")
    @Mapping(source = "golesEquipo1", target = "equipo1.goles")
    @Mapping(source = "prediccionEquipo1", target = "equipo1.prediccion")
    @Mapping(source = "idEquipo2", target = "equipo2.id")
    @Mapping(source = "nombreEquipo2", target = "equipo2.nombre")
    @Mapping(source = "golesEquipo2", target = "equipo2.goles")
    @Mapping(source = "prediccionEquipo2", target = "equipo2.prediccion")
    PartidoDTO viewToDTO(PartidoSearchView view);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "idEtapa", target = "etapa.id")
    @Mapping(source = "nombreEtapa", target = "etapa.nombre")
    @Mapping(source = "idEquipo1", target = "equipo1.id")
    @Mapping(source = "nombreEquipo1", target = "equipo1.nombre")
    @Mapping(source = "golesEquipo1", target = "equipo1.goles")
    @Mapping(source = "prediccionEquipo1", target = "equipo1.prediccion")
    @Mapping(source = "idEquipo2", target = "equipo2.id")
    @Mapping(source = "nombreEquipo2", target = "equipo2.nombre")
    @Mapping(source = "golesEquipo2", target = "equipo2.goles")
    @Mapping(source = "prediccionEquipo2", target = "equipo2.prediccion")
    @Mapping(target = "predicciones", ignore = true)
    PartidoDetailsDTO viewToDetailsDTO(PartidoSearchView view);

}

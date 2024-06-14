package com.github.alfonsoleandro.pencaucu.business.partido.model.response;

import com.github.alfonsoleandro.pencaucu.business.alumno.model.response.AlumnoPrediccionesDTO;
import com.github.alfonsoleandro.pencaucu.business.equipo.model.response.EquipoGoalsPrediccionStatisticDTO;
import com.github.alfonsoleandro.pencaucu.business.etapa.model.response.EtapaDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
@Setter
public class PartidoDetailsDTO {

    private Integer id;

    private Timestamp fecha;

    private EtapaDTO etapa;

    private EquipoGoalsPrediccionStatisticDTO equipo1;

    private EquipoGoalsPrediccionStatisticDTO equipo2;

    private List<AlumnoPrediccionesDTO> predicciones;
}

package com.github.alfonsoleandro.pencaucu.business.usuario.model.response;

import com.github.alfonsoleandro.pencaucu.business.equipo.model.response.EquipoGoalsPrediccionDTO;
import com.github.alfonsoleandro.pencaucu.business.etapa.model.response.EtapaDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
@Setter
public class AlumnoPrediccionesDetalleDTO {

	private int idPartido;

	private Timestamp fecha;

	private EtapaDTO etapa;

	private Integer puntajeObtenido;

	private EquipoGoalsPrediccionDTO equipo1;

	private EquipoGoalsPrediccionDTO equipo2;

}
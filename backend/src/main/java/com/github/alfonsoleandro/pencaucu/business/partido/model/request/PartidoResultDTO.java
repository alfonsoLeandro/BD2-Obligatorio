package com.github.alfonsoleandro.pencaucu.business.partido.model.request;

import com.github.alfonsoleandro.pencaucu.business.equipo.model.request.EquipoResultDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
@Setter
public class PartidoResultDTO {

	@NotNull(message = "EQUIPO_REQUERIDO")
	@Valid
	private EquipoResultDTO equipo1;

	@NotNull(message = "EQUIPO_REQUERIDO")
	@Valid
	private EquipoResultDTO equipo2;

}

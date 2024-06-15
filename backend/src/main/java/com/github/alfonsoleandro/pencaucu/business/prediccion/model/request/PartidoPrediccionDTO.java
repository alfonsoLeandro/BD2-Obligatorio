package com.github.alfonsoleandro.pencaucu.business.prediccion.model.request;

import com.github.alfonsoleandro.pencaucu.business.equipo.model.request.EquipoPrediccionDTO;
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
public class PartidoPrediccionDTO {

    @NotNull(message = "EQUIPO_1_REQUERIDO")
    @Valid
    private EquipoPrediccionDTO equipo1;

    @NotNull(message = "EQUIPO_2_REQUERIDO")
    @Valid
    private EquipoPrediccionDTO equipo2;
}

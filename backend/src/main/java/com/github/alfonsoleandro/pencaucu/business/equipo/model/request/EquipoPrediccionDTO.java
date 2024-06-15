package com.github.alfonsoleandro.pencaucu.business.equipo.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
@Setter
public class EquipoPrediccionDTO {

    @NotNull(message = "ID_EQUIPO_REQUERIDO")
    private Integer id;

    @NotNull(message = "PREDICCION_REQUERIDO")
    @Min(value = 0, message = "PREDICCION_DEBE_SER_POSITIVO")
    private Integer prediccion;

}

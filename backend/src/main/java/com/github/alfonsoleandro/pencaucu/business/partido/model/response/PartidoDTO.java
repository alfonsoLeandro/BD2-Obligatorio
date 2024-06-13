package com.github.alfonsoleandro.pencaucu.business.partido.model.response;

import com.github.alfonsoleandro.pencaucu.business.equipo.model.response.EquipoPrediccionDTO;
import com.github.alfonsoleandro.pencaucu.business.etapa.model.response.EtapaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@AllArgsConstructor
@Getter
@Setter
public class PartidoDTO {

    private Integer id;

    private LocalDateTime fecha;

    private EtapaDTO etapa;

    private EquipoPrediccionDTO equipo1;

    private EquipoPrediccionDTO equipo2;
}
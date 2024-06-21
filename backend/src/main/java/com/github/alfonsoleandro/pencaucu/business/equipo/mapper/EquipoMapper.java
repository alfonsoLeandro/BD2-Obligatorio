package com.github.alfonsoleandro.pencaucu.business.equipo.mapper;

import com.github.alfonsoleandro.pencaucu.business.equipo.model.response.EquipoDTO;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Equipo;
import org.mapstruct.Mapper;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Mapper
public interface EquipoMapper {

	EquipoDTO toDTO(Equipo entity);

}

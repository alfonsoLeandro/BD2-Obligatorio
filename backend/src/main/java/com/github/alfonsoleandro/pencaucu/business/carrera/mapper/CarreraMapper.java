package com.github.alfonsoleandro.pencaucu.business.carrera.mapper;

import com.github.alfonsoleandro.pencaucu.business.carrera.model.response.CarreraDTO;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Carrera;
import org.mapstruct.Mapper;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Mapper
public interface CarreraMapper {

    CarreraDTO toDTO(Carrera entity);

}

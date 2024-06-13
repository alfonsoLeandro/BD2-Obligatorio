package com.github.alfonsoleandro.pencaucu.business.etapa.mapper;

import com.github.alfonsoleandro.pencaucu.business.etapa.model.response.EtapaDTO;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Etapa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EtapaMapper {

    EtapaDTO toDTO(Etapa etapa);

}

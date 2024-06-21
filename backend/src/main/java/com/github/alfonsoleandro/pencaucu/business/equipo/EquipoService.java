package com.github.alfonsoleandro.pencaucu.business.equipo;

import com.github.alfonsoleandro.pencaucu.business.equipo.model.response.EquipoDTO;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public interface EquipoService {

	List<EquipoDTO> getAvailableEquipos();

}

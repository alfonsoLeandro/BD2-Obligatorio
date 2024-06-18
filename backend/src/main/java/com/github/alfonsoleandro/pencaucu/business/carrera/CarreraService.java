package com.github.alfonsoleandro.pencaucu.business.carrera;

import com.github.alfonsoleandro.pencaucu.business.carrera.model.response.CarreraDTO;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public interface CarreraService {

	List<CarreraDTO> getAvailableCarreras();

}
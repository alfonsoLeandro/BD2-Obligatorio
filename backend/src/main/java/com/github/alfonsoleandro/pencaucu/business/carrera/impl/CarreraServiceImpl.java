package com.github.alfonsoleandro.pencaucu.business.carrera.impl;

import com.github.alfonsoleandro.pencaucu.business.carrera.CarreraService;
import com.github.alfonsoleandro.pencaucu.business.carrera.mapper.CarreraMapper;
import com.github.alfonsoleandro.pencaucu.business.carrera.model.response.CarreraDTO;
import com.github.alfonsoleandro.pencaucu.persistence.repository.CarreraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Service
public class CarreraServiceImpl implements CarreraService {

	private final CarreraMapper carreraMapper;
	private final CarreraRepository carreraRepository;

	@Override
	public List<CarreraDTO> getAvailableCarreras() {
		return this.carreraRepository.findAll().stream().map(this.carreraMapper::toDTO).toList();
	}
}

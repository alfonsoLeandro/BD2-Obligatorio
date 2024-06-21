package com.github.alfonsoleandro.pencaucu.business.equipo.impl;

import com.github.alfonsoleandro.pencaucu.business.equipo.EquipoService;
import com.github.alfonsoleandro.pencaucu.business.equipo.mapper.EquipoMapper;
import com.github.alfonsoleandro.pencaucu.business.equipo.model.response.EquipoDTO;
import com.github.alfonsoleandro.pencaucu.persistence.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Service
public class EquipoServiceImpl implements EquipoService {

	private final EquipoRepository equipoRepository;
	private final EquipoMapper equipoMapper;

	@Override
	public List<EquipoDTO> getAvailableEquipos() {
		return this.equipoRepository.findAll().stream().map(this.equipoMapper::toDTO).collect(Collectors.toList());
	}
}

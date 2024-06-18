package com.github.alfonsoleandro.pencaucu.business.usuario.impl;

import com.github.alfonsoleandro.pencaucu.business.alumno.mapper.AlumnoMapper;
import com.github.alfonsoleandro.pencaucu.business.alumno.model.response.AlumnoDTO;
import com.github.alfonsoleandro.pencaucu.business.usuario.UsuarioService;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Usuario;
import com.github.alfonsoleandro.pencaucu.persistence.repository.AlumnoRepository;
import com.github.alfonsoleandro.pencaucu.persistence.repository.JuegoRepository;
import com.github.alfonsoleandro.pencaucu.persistence.repository.UsuarioRepository;
import com.github.alfonsoleandro.pencaucu.persistence.view.AlumnoPuntajeView;
import com.github.alfonsoleandro.pencaucu.persistence.view.CampeonSubcampeonView;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final AlumnoMapper alumnoMapper;
	private final AlumnoRepository alumnoRepository;
	private final JuegoRepository juegoRepository;
	private final PasswordEncoder passwordEncoder;
	private final UsuarioRepository usuarioRepository;

	@Override
	public List<AlumnoDTO> findUsuarios(String searchText, Integer idCarrera, Boolean esAdmin) {
		List<AlumnoPuntajeView> alumnos = this.alumnoRepository.findAlumnos(searchText, idCarrera, esAdmin);
		Optional<CampeonSubcampeonView> campeonSubcampeonOptional = this.juegoRepository.findCampeonAndSubCampeonIds();
		Map<Integer, Integer> puntosByIdUsuario = new HashMap<>();
		Map<Integer, AlumnoPuntajeView> alumnoPuntajeViewByIdUsuario = new HashMap<>();

		// Calculate puntaje by alumno
		for (AlumnoPuntajeView a : alumnos) {
			Integer idUsuario = a.getIdUsuario();
			if (!alumnoPuntajeViewByIdUsuario.containsKey(idUsuario)) {
				alumnoPuntajeViewByIdUsuario.put(idUsuario, a);
			}
			Integer puntaje = puntosByIdUsuario.getOrDefault(idUsuario, 0);
			puntaje += getPuntajeFromPredicciones(a.getPrediccionEquipo1(), a.getGolesEquipo1(), a.getPrediccionEquipo2(), a.getGolesEquipo2());
			puntosByIdUsuario.put(idUsuario, puntaje);
		}

		// if campeon and subcampeon are defined, count those in
		if (campeonSubcampeonOptional.isPresent()) {
			CampeonSubcampeonView campeonSubcampeonView = campeonSubcampeonOptional.get();
			for (Map.Entry<Integer, AlumnoPuntajeView> alumnoPuntajeView : alumnoPuntajeViewByIdUsuario.entrySet()) {
				Integer idAlumno = alumnoPuntajeView.getKey();
				Integer puntaje = puntosByIdUsuario.get(idAlumno);
				if (alumnoPuntajeView.getValue().getIdCampeon().equals(campeonSubcampeonView.getIdCampeon())) {
					puntaje += 10;
				}
				if (alumnoPuntajeView.getValue().getIdSubcampeon().equals(campeonSubcampeonView.getIdSubcampeon())) {
					puntaje += 5;
				}
				puntosByIdUsuario.put(idAlumno, puntaje);
			}
		}

		// Build DTO
		List<AlumnoDTO> alumnoDTOs = new ArrayList<>();

		for (Map.Entry<Integer, AlumnoPuntajeView> alumnoPuntajeView : alumnoPuntajeViewByIdUsuario.entrySet()) {
			AlumnoDTO alumnoDTO = this.alumnoMapper.puntajeViewToDTO(alumnoPuntajeView.getValue());
			alumnoDTO.setPuntaje(puntosByIdUsuario.get(alumnoPuntajeView.getKey()));

			alumnoDTOs.add(alumnoDTO);
		}

		return alumnoDTOs;
	}

	@Transactional
	@Override
	public void modifyPassword(String newPassword) {
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modifyPassword(usuario.getId(), this.passwordEncoder.encode(newPassword));
	}

	@Transactional
	@Override
	public void modifyPassword(int id, String newPassword) {
		this.usuarioRepository.modifyPassword(id, this.passwordEncoder.encode(newPassword));
	}

	private int getPuntajeFromPredicciones(Integer prediccionEquipo1, Integer golesEquipo1, Integer prediccionEquipo2, Integer golesEquipo2) {
		if (prediccionEquipo1 == null || golesEquipo1 == null || prediccionEquipo2 == null || golesEquipo2 == null) {
			return 0;
		}
		int puntaje = 0;
		if (prediccionEquipo1.equals(golesEquipo1) && prediccionEquipo2.equals(golesEquipo2)) {
			puntaje = 4;
		} else if ((prediccionEquipo1 > prediccionEquipo2) == (golesEquipo1 > golesEquipo2)) {
			puntaje = 2;
		}
		return puntaje;
	}


}

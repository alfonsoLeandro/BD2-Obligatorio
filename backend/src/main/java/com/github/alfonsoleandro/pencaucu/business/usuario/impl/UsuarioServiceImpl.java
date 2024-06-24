package com.github.alfonsoleandro.pencaucu.business.usuario.impl;

import com.github.alfonsoleandro.pencaucu.business.alumno.mapper.AlumnoMapper;
import com.github.alfonsoleandro.pencaucu.business.alumno.model.response.AlumnoDTO;
import com.github.alfonsoleandro.pencaucu.business.usuario.UsuarioService;
import com.github.alfonsoleandro.pencaucu.business.usuario.exception.UsuarioExceptionCode;
import com.github.alfonsoleandro.pencaucu.business.usuario.mapper.UsuarioMapper;
import com.github.alfonsoleandro.pencaucu.business.usuario.model.response.AlumnoPrediccionesDetalleDTO;
import com.github.alfonsoleandro.pencaucu.business.usuario.model.response.UsuarioDetalleDTO;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Usuario;
import com.github.alfonsoleandro.pencaucu.persistence.repository.AlumnoRepository;
import com.github.alfonsoleandro.pencaucu.persistence.repository.JuegoRepository;
import com.github.alfonsoleandro.pencaucu.persistence.repository.UsuarioRepository;
import com.github.alfonsoleandro.pencaucu.persistence.view.AlumnoPuntajeDetalleView;
import com.github.alfonsoleandro.pencaucu.persistence.view.AlumnoPuntajeView;
import com.github.alfonsoleandro.pencaucu.persistence.view.CampeonSubcampeonView;
import com.github.alfonsoleandro.pencaucu.persistence.view.UsuarioDetalleView;
import com.github.alfonsoleandro.pencaucu.rest.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
	private final UsuarioMapper usuarioMapper;
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
				int puntaje = puntosByIdUsuario.get(idAlumno);
				AlumnoPuntajeView value = alumnoPuntajeView.getValue();
				puntaje += getPuntajeFromElecciones(value.getIdCampeon(),
						value.getIdSubcampeon(),
						campeonSubcampeonView.getIdCampeon(),
						campeonSubcampeonView.getIdSubcampeon());
				puntosByIdUsuario.put(idAlumno, puntaje);
			}
		}

		// Build DTO
		List<AlumnoDTO> alumnoDTOs = new ArrayList<>();

		for (Map.Entry<Integer, AlumnoPuntajeView> alumnoPuntajeView : alumnoPuntajeViewByIdUsuario
				.entrySet()
				.stream()
				.sorted((c1, c2) -> puntosByIdUsuario.get(c2.getKey()).compareTo(puntosByIdUsuario.get(c1.getKey())))
				.collect(Collectors.toCollection(LinkedHashSet::new))) {

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

	@Override
	public UsuarioDetalleDTO getUsuarioDetalle(Integer id) {
		// Obtener datos de usuario y puntaje a partir de predicciones
		// obtener predicciones de todos los usuarios para comparar con el usuario actual
		// a partir de todas las predicciones del usuario, calcular puntaje total, y listar predicciones con puntaje por prediccion
		if (!this.usuarioRepository.existsById(id)) {
			throw new NotFoundException(UsuarioExceptionCode.USUARIO_NO_EXISTE);
		}

		UsuarioDetalleView usuarioDetalleView = this.alumnoRepository.getUsuarioDetalle(id);
		UsuarioDetalleDTO usuarioDetalleDTO = this.usuarioMapper.detalleViewToDTO(usuarioDetalleView);

		List<AlumnoPuntajeDetalleView> alumnosPuntajes = this.alumnoRepository.findAlumnoDetalles();
		Optional<CampeonSubcampeonView> campeonSubcampeonOptional = this.juegoRepository.findCampeonAndSubCampeonIds();
		Map<Integer, Integer> puntosByIdUsuario = new HashMap<>();
		Map<Integer, AlumnoPuntajeDetalleView> alumnoPuntajeViewByIdUsuario = new HashMap<>();

		for (AlumnoPuntajeDetalleView a : alumnosPuntajes) {
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
			for (Map.Entry<Integer, AlumnoPuntajeDetalleView> alumnoPuntajeView : alumnoPuntajeViewByIdUsuario.entrySet()) {
				Integer idAlumno = alumnoPuntajeView.getKey();
				int puntaje = puntosByIdUsuario.get(idAlumno);
				AlumnoPuntajeDetalleView value = alumnoPuntajeView.getValue();
				puntaje += getPuntajeFromElecciones(value.getIdCampeon(),
						value.getIdSubcampeon(),
						campeonSubcampeonView.getIdCampeon(),
						campeonSubcampeonView.getIdSubcampeon());
				puntosByIdUsuario.put(idAlumno, puntaje);
			}
		}

		// Set puntaje
		usuarioDetalleDTO.setPuntaje(puntosByIdUsuario.get(id));

		// Set ranking
		List<Map.Entry<Integer, Integer>> entries = puntosByIdUsuario.entrySet()
				.stream()
				.sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
				.toList();

		for (Map.Entry<Integer, Integer> entry : entries) {
			usuarioDetalleDTO.setRanking(usuarioDetalleDTO.getRanking() + 1);
			if (entry.getKey().equals(id)) {
				break;
			}
		}

		for (AlumnoPuntajeDetalleView view : alumnoPuntajeViewByIdUsuario
				.values()
				.stream()
				.filter(alumnoPuntajeView -> alumnoPuntajeView.getIdUsuario().equals(id) && alumnoPuntajeView.getIdPartido() != null)
				.collect(Collectors.toSet())) {
			AlumnoPrediccionesDetalleDTO alumnoPrediccionDetalleDTO = this.alumnoMapper.puntajeDetalleViewToDetalleDTO(view);
			alumnoPrediccionDetalleDTO.setPuntajeObtenido(getPuntajeFromPredicciones(view.getPrediccionEquipo1(),
					view.getPrediccionEquipo2(),
					view.getGolesEquipo1(),
					view.getGolesEquipo2()));
			usuarioDetalleDTO.getPredicciones().add(alumnoPrediccionDetalleDTO);
		}


		return usuarioDetalleDTO;
	}

	private int getPuntajeFromPredicciones(Integer prediccionEquipo1, Integer golesEquipo1, Integer
			prediccionEquipo2, Integer golesEquipo2) {
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

	private int getPuntajeFromElecciones(Integer prediccionCampeon, Integer prediccionSubcampeon, Integer
			idCampeon, Integer idSubcampeon) {
		int puntaje = 0;
		if (prediccionCampeon != null && prediccionCampeon.equals(idCampeon)) {
			puntaje += 10;
		}
		if (prediccionSubcampeon != null && prediccionSubcampeon.equals(idSubcampeon)) {
			puntaje += 5;
		}
		return puntaje;
	}


}

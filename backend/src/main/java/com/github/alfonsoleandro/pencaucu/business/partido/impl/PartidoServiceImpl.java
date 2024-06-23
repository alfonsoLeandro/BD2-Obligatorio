package com.github.alfonsoleandro.pencaucu.business.partido.impl;

import com.github.alfonsoleandro.pencaucu.business.alumno.mapper.AlumnoMapper;
import com.github.alfonsoleandro.pencaucu.business.alumno.model.response.AlumnoPrediccionesDTO;
import com.github.alfonsoleandro.pencaucu.business.email.EmailService;
import com.github.alfonsoleandro.pencaucu.business.equipo.exception.EquipoExceptionCode;
import com.github.alfonsoleandro.pencaucu.business.partido.PartidoService;
import com.github.alfonsoleandro.pencaucu.business.partido.exception.PartidoExceptionCode;
import com.github.alfonsoleandro.pencaucu.business.partido.mapper.PartidoMapper;
import com.github.alfonsoleandro.pencaucu.business.partido.model.request.PartidoResultDTO;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoDTO;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoDetailsDTO;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoFechaDTO;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Usuario;
import com.github.alfonsoleandro.pencaucu.persistence.repository.*;
import com.github.alfonsoleandro.pencaucu.persistence.view.AlumnosPrediccionesView;
import com.github.alfonsoleandro.pencaucu.persistence.view.EquipoPrediccionPercentageView;
import com.github.alfonsoleandro.pencaucu.persistence.view.PartidoSearchView;
import com.github.alfonsoleandro.pencaucu.rest.exception.ConflictException;
import com.github.alfonsoleandro.pencaucu.rest.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PartidoServiceImpl implements PartidoService {

	private final AlumnoMapper alumnoMapper;
	private final AlumnoRepository alumnoRepository;
	private final EmailService emailService;
	private final EquipoRepository equipoRepository;
	private final EtapaRepository etapaRepository;
	private final JuegoRepository juegoRepository;
	private final PartidoMapper partidoMapper;
	private final PartidoRepository partidoRepository;
	private final PrediccionRepository prediccionRepository;

	@Override
	public List<PartidoDTO> searchPartidos(String searchText, Boolean jugado, Boolean conPrediccion) {
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<PartidoSearchView> partidoSearchViews = this.partidoRepository.searchPartidos(usuario.getId(),
				searchText,
				jugado,
				conPrediccion);
		return partidoSearchViews.stream().map(this.partidoMapper::viewToDTO).toList();
	}

	@Override
	public List<PartidoFechaDTO> getAvailableFechas() {
		return this.partidoRepository.getAvailableFechas().stream().map(pfv -> new PartidoFechaDTO(pfv.getId(), pfv.getFecha())).toList();
	}

	@Transactional
	@Override
	public void setPartidoEquipos(int id, int idEquipo1, int idEquipo2) {
		validatePartido(id);
		validateEquipos(idEquipo1, idEquipo2);

		// If equipos for partido are already set, delete them
		boolean exists = this.juegoRepository.existsByPartidoId(id) == 1;
		if (exists) {
			this.juegoRepository.deleteEquiposForPartido(id);
			this.prediccionRepository.deletePrediccionesForPartido(id);
		}

		// Set equipos for partido
		this.juegoRepository.insertEquiposForPartido(id, idEquipo1, idEquipo2);

		try {
			enviarEmail(id, idEquipo1, idEquipo2);
		} catch (Exception e) {
			log.error("Error al enviar mail", e);
		}
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	protected void enviarEmail(int id, int idEquipo1, int idEquipo2) {
		boolean etapaAnunciadaByPartidoId = this.etapaRepository.isAnunciadaByPartidoId(id);
		if (!etapaAnunciadaByPartidoId) {
			this.etapaRepository.setAnunciadaByPartidoId(id);
			Timestamp fecha = this.partidoRepository.getFechaById(id);
			String nombreEquipo1 = this.equipoRepository.getNombreById(idEquipo1);
			String nombreEquipo2 = this.equipoRepository.getNombreById(idEquipo2);

			List<String> alumnoEmails = this.alumnoRepository.getAlumnoEmails();
			String pattern = "EEEE dd MMMMM HH:mm";

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.of("es", "ES"));
			String date = simpleDateFormat.format(fecha);
			String emailBody = String.format("Se ha anunciado el partido el día %s vs %s  %s", nombreEquipo1, nombreEquipo2, date);
			for (String alumnoEmail : alumnoEmails) {
				this.emailService.sendEmail(alumnoEmail, emailBody);
			}
		}
	}

	@Override
	public PartidoDetailsDTO getPartidoData(int id) {
		// Validate partido exists
		validatePartido(id);

		// Get partido data
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PartidoSearchView partidoData = this.partidoRepository.getPartidoData(id, usuario.getId());

		// Map to DTO
		PartidoDetailsDTO partidoDetailsDTO = this.partidoMapper.viewToDetailsDTO(partidoData);

		// Get prediccion percentage
		Optional<EquipoPrediccionPercentageView> percentageOptional = this.prediccionRepository.getEquipoPrediccionPercentage(id,
				partidoData.getIdEquipo1(),
				partidoData.getIdEquipo2());

		if (percentageOptional.isPresent()) {
			EquipoPrediccionPercentageView percentage = percentageOptional.get();
			partidoDetailsDTO.getEquipo1().setPorcentaje(percentage.getPercentageEquipo1());
			partidoDetailsDTO.getEquipo2().setPorcentaje(percentage.getPercentageEquipo2());
		}

		// Get predicciones
		List<AlumnosPrediccionesView> prediccionesViews = this.prediccionRepository.getPredicciones(id, partidoData.getIdEquipo1(), partidoData.getIdEquipo2());
		List<AlumnoPrediccionesDTO> prediccionesDTOS = prediccionesViews
				.stream()
				.map(this.alumnoMapper::viewToPrediccionesDTO)
				.toList();
		partidoDetailsDTO.setPredicciones(prediccionesDTOS);

		return partidoDetailsDTO;
	}

	@Transactional
	@Override
	public void setPartidoResult(int id, PartidoResultDTO partidoResultDTO) {
		validatePartido(id);
		validateEquipos(partidoResultDTO.getEquipo1().getId(),
				partidoResultDTO.getEquipo2().getId());

		// Check if juego exist, if not, throw exception
		boolean existsPartidoEquipos = this.juegoRepository.existsByPartidoId(id) == 1;
		if (!existsPartidoEquipos) {
			throw new NotFoundException(PartidoExceptionCode.EQUIPOS_NO_DEFINIDOS_PARA_PARTIDO);
		}

		//check equipos playing are the same as the ones in the DTO
		List<Integer> equipoIds = this.juegoRepository.getPartidoEquipoIds(id);
		if (!equipoIds.contains(partidoResultDTO.getEquipo1().getId()) ||
				!equipoIds.contains(partidoResultDTO.getEquipo2().getId())) {
			throw new ConflictException(PartidoExceptionCode.EQUIPOS_DEFINIDOS_NO_COINCIDEN);
		}

		// update results
		this.juegoRepository.updateResult(id,
				partidoResultDTO.getEquipo1().getId(),
				partidoResultDTO.getEquipo1().getGoles());
		this.juegoRepository.updateResult(id,
				partidoResultDTO.getEquipo2().getId(),
				partidoResultDTO.getEquipo2().getGoles());
	}

	/**
	 * Validates a Partido with the given ID exists.
	 *
	 * @param id The id of the Partido.
	 */
	private void validatePartido(int id) {
		if (this.partidoRepository.findById(id).isEmpty()) {
			throw new NotFoundException(PartidoExceptionCode.PARTIDO_NO_ENCONTRADO);
		}
	}

	/**
	 * Validates the given equipos exist and are different.
	 *
	 * @param idEquipo1 The id of the first Equipo.
	 * @param idEquipo2 The id of the second Equipo.
	 */
	private void validateEquipos(Integer idEquipo1, Integer idEquipo2) {
		// Validate equipos are different
		if (idEquipo1.equals(idEquipo2)) {
			throw new ConflictException(EquipoExceptionCode.EQUIPO_1_IGUAL_EQUIPO_2);
		}
		// Validate equipo1
		if (this.equipoRepository.findById(idEquipo1).isEmpty()) {
			throw new NotFoundException(EquipoExceptionCode.EQUIPO_1_NO_ENCONTRADO);
		}
		// Validate equipo2
		if (this.equipoRepository.findById(idEquipo2).isEmpty()) {
			throw new NotFoundException(EquipoExceptionCode.EQUIPO_2_NO_ENCONTRADO);
		}
	}

}

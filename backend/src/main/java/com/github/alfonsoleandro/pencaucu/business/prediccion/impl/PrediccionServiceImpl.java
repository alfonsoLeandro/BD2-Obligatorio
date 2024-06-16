package com.github.alfonsoleandro.pencaucu.business.prediccion.impl;

import com.github.alfonsoleandro.pencaucu.business.equipo.exception.EquipoExceptionCode;
import com.github.alfonsoleandro.pencaucu.business.partido.exception.PartidoExceptionCode;
import com.github.alfonsoleandro.pencaucu.business.prediccion.PrediccionService;
import com.github.alfonsoleandro.pencaucu.business.prediccion.exception.PrediccionExceptionCode;
import com.github.alfonsoleandro.pencaucu.business.prediccion.model.request.PartidoPrediccionDTO;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Alumno;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Partido;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Usuario;
import com.github.alfonsoleandro.pencaucu.persistence.repository.*;
import com.github.alfonsoleandro.pencaucu.rest.exception.ConflictException;
import com.github.alfonsoleandro.pencaucu.rest.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Service
public class PrediccionServiceImpl implements PrediccionService {

    private final AlumnoRepository alumnoRepository;
    private final EquipoRepository equipoRepository;
    private final JuegoRepository juegoRepository;
    private final PartidoRepository partidoRepository;
    private final PrediccionRepository prediccionRepository;

    @Transactional
    @Override
    public void setPrediccion(int idPartido, PartidoPrediccionDTO partidoPrediccionDTO) {
        int idAlumno = validateAlumnoAndPartidoForPrediccion(idPartido);
        validateEquipos(partidoPrediccionDTO.getEquipo1().getId(),
                partidoPrediccionDTO.getEquipo2().getId());

        // Check if juego exist, if not, throw exception
        boolean existsPartidoEquipos = this.juegoRepository.existsByPartidoId(idPartido) == 1;
        if (!existsPartidoEquipos) {
            throw new NotFoundException(PartidoExceptionCode.EQUIPOS_NO_DEFINIDOS_PARA_PARTIDO);
        }

        // Check equipos playing are the same as the ones in the DTO
        List<Integer> equipoIds = this.juegoRepository.getPartidoEquipoIds(idPartido);
        if (!equipoIds.contains(partidoPrediccionDTO.getEquipo1().getId()) ||
                !equipoIds.contains(partidoPrediccionDTO.getEquipo2().getId())) {
            throw new ConflictException(PartidoExceptionCode.EQUIPOS_DEFINIDOS_NO_COINCIDEN);
        }

        this.prediccionRepository.deletePrediccionesForPartidoAndUsuario(idPartido, idAlumno);

        // update results
        this.prediccionRepository.setPrediccion(idPartido,
                idAlumno,
                partidoPrediccionDTO.getEquipo1().getId(),
                partidoPrediccionDTO.getEquipo1().getPrediccion());
        this.prediccionRepository.setPrediccion(idPartido,
                idAlumno,
                partidoPrediccionDTO.getEquipo2().getId(),
                partidoPrediccionDTO.getEquipo2().getPrediccion());
    }

    @Transactional
    @Override
    public void deletePredicciones(int idPartido) {
        int idAlumno = validateAlumnoAndPartidoForPrediccion(idPartido);

        this.prediccionRepository.deletePrediccionesForPartidoAndUsuario(idPartido, idAlumno);
    }

    /**
     * Validates a user is allowed to add a Prediccion, and a Partido calling {@link #validatePartido(int)}.
     * @param idPartido The id of the Partido.
     * @return The id of the Alumno.
     */
    private int validateAlumnoAndPartidoForPrediccion(int idPartido) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Alumno> alumnoOptional = this.alumnoRepository.findByUsuarioId(usuario.getId());

        if (alumnoOptional.isEmpty()) {
            throw new ConflictException(PrediccionExceptionCode.USUARIO_NO_ES_ALUMNO);
        }

        validatePartido(idPartido);
        return alumnoOptional.get().getId();
    }

    /**
     * Validates a Partido with the given ID exists.
     *
     * @param id The id of the Partido.
     */
    private void validatePartido(int id) {
        Optional<Partido> partidoOptional = this.partidoRepository.findById(id);
        if (partidoOptional.isEmpty()) {
            throw new NotFoundException(PartidoExceptionCode.PARTIDO_NO_ENCONTRADO);
        }

        // Predictions can be modified up to an hour before the game starts
        Timestamp now = new Timestamp(System.currentTimeMillis() + (1000 * 60 * 60));
        Timestamp partidoFecha = partidoOptional.get().getFecha();
        if (now.after(partidoFecha)) {
            throw new ConflictException(PrediccionExceptionCode.PREDICCIONES_NO_MODIFICABLES);
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

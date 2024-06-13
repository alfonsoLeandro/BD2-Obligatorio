package com.github.alfonsoleandro.pencaucu.business.partido.impl;

import com.github.alfonsoleandro.pencaucu.business.equipo.exception.EquipoExceptionCode;
import com.github.alfonsoleandro.pencaucu.business.partido.PartidoService;
import com.github.alfonsoleandro.pencaucu.business.partido.exception.PartidoExceptionCode;
import com.github.alfonsoleandro.pencaucu.business.partido.mapper.PartidoMapper;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoDTO;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Usuario;
import com.github.alfonsoleandro.pencaucu.persistence.repository.EquipoRepository;
import com.github.alfonsoleandro.pencaucu.persistence.repository.JuegoRepository;
import com.github.alfonsoleandro.pencaucu.persistence.repository.PartidoRepository;
import com.github.alfonsoleandro.pencaucu.persistence.view.PartidoSearchView;
import com.github.alfonsoleandro.pencaucu.rest.exception.ConflictException;
import com.github.alfonsoleandro.pencaucu.rest.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Service
public class PartidoServiceImpl implements PartidoService {

    private final EquipoRepository equipoRepository;
    private final JuegoRepository juegoRepository;
    private final PartidoMapper partidoMapper;
    private final PartidoRepository partidoRepository;

    @Override
    public List<PartidoDTO> searchPartidos(String searchText, Boolean jugado, Boolean conPrediccion) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PartidoSearchView> partidoSearchViews = this.partidoRepository.searchPartidos(usuario.getId(),
                searchText,
                jugado,
                conPrediccion);
        return partidoSearchViews.stream().map(this.partidoMapper::toDTO).toList();
    }

    @Override
    public List<Timestamp> getAvailableFechas() {
        return this.partidoRepository.getAvailableFechas();
    }

    @Transactional
    @Override
    public void setPartidoEquipos(int id, int idEquipo1, int idEquipo2) {
        // Validate equipos are different
        if (idEquipo1 == idEquipo2) {
            throw new ConflictException(EquipoExceptionCode.EQUIPO_1_IGUAL_EQUIPO_2);
        }
        // Validate partido and equipos exist
        if (this.partidoRepository.findById(id).isEmpty()) {
            throw new NotFoundException(PartidoExceptionCode.PARTIDO_NO_ENCONTRADO);
        }
        if (this.equipoRepository.findById(idEquipo1).isEmpty()) {
            throw new NotFoundException(EquipoExceptionCode.EQUIPO_1_NO_ENCONTRADO);
        }
        if (this.equipoRepository.findById(idEquipo2).isEmpty()) {
            throw new NotFoundException(EquipoExceptionCode.EQUIPO_2_NO_ENCONTRADO);
        }

        // If equipos for partido are already set, delete them
        boolean exists = this.juegoRepository.existsByPartidoId(id) == 1;
        if (exists) {
            this.juegoRepository.deleteEquiposForPartido(id);
        }

        // Set equipos for partido
        this.juegoRepository.insertEquiposForPartido(id, idEquipo1, idEquipo2);
    }

}

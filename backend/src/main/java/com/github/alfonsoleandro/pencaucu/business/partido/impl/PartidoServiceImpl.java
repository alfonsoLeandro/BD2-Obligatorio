package com.github.alfonsoleandro.pencaucu.business.partido.impl;

import com.github.alfonsoleandro.pencaucu.business.partido.PartidoService;
import com.github.alfonsoleandro.pencaucu.business.partido.mapper.PartidoMapper;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoDTO;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Usuario;
import com.github.alfonsoleandro.pencaucu.persistence.repository.PartidoRepository;
import com.github.alfonsoleandro.pencaucu.persistence.view.PartidoSearchView;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Service
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final PartidoMapper partidoMapper;

    @Override
    public List<PartidoDTO> searchPartidos(String searchText, Boolean jugado, Boolean conPrediccion) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PartidoSearchView> partidoSearchViews = this.partidoRepository.searchPartidos(usuario.getId(),
                searchText,
                jugado,
                conPrediccion);
        return partidoSearchViews.stream().map(this.partidoMapper::toDTO).toList();
    }
}

package com.github.alfonsoleandro.pencaucu.business.partido;

import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoDTO;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public interface PartidoService {

    List<PartidoDTO> searchPartidos(String searchText, Boolean jugado, Boolean conPrediccion);

}

package com.github.alfonsoleandro.pencaucu.business.partido;

import com.github.alfonsoleandro.pencaucu.business.partido.model.request.PartidoResultDTO;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoDTO;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoDetailsDTO;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoFechaDTO;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public interface PartidoService {

    List<PartidoDTO> searchPartidos(String searchText, Boolean jugado, Boolean conPrediccion);

    List<PartidoFechaDTO> getAvailableFechas();

    void setPartidoEquipos(int id, int idEquipo1, int idEquipo2);

    PartidoDetailsDTO getPartidoData(int id);

    void setPartidoResult(int id, PartidoResultDTO partidoResultDTO);

}

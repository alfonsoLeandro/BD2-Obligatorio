package com.github.alfonsoleandro.pencaucu.rest;

import com.github.alfonsoleandro.pencaucu.business.partido.PartidoService;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoDTO;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/partidos")
public class PartidoController {

    private final PartidoService partidoService;

    @GetMapping
    public List<PartidoDTO> searchPartidos(@RequestParam(name = "busqueda", required = false) String searchText,
                                           @RequestParam(name = "jugado", required = false) Boolean played,
                                           @RequestParam(name = "conPrediccion", required = false) Boolean hasPrediccion) {
        return this.partidoService.searchPartidos(searchText, played, hasPrediccion);
    }

    @GetMapping("/{id}")
    public PartidoDetailsDTO searchPartidos(@PathVariable int id) {
        return this.partidoService.getPartidoData(id);
    }

}

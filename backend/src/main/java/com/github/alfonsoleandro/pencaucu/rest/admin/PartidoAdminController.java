package com.github.alfonsoleandro.pencaucu.rest.admin;

import com.github.alfonsoleandro.pencaucu.business.partido.PartidoService;
import com.github.alfonsoleandro.pencaucu.business.partido.model.request.PartidoResultDTO;
import com.github.alfonsoleandro.pencaucu.business.partido.model.response.PartidoFechaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/admin/partidos")
public class PartidoAdminController {

    private final PartidoService partidoService;

    @GetMapping("/fechas")
    public List<PartidoFechaDTO> getAvailableFechas() {
        return this.partidoService.getAvailableFechas();
    }

    @PostMapping("/{id}")
    public void setPartidoEquipos(@PathVariable int id,
                                  @RequestParam int idEquipo1,
                                  @RequestParam int idEquipo2) {
        this.partidoService.setPartidoEquipos(id, idEquipo1, idEquipo2);
    }

    @PostMapping("/{id}/resultado")
    public void setPartidoEquipos(@PathVariable int id,
                                  @RequestBody @Valid PartidoResultDTO partidoResultDTO) {
        this.partidoService.setPartidoResult(id, partidoResultDTO);
    }

}

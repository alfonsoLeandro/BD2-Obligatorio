package com.github.alfonsoleandro.pencaucu.rest;

import com.github.alfonsoleandro.pencaucu.business.partido.PartidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/partidos")
public class PartidoAdminController {

    private final PartidoService partidoService;

    @GetMapping("/fechas")
    public List<Timestamp> getAvailableFechas() {
        return this.partidoService.getAvailableFechas();
    }

    @PostMapping("/{id}")
    public void setPartidoEquipos(@PathVariable int id,
                                  @RequestParam int idEquipo1,
                                  @RequestParam int idEquipo2) {
        this.partidoService.setPartidoEquipos(id, idEquipo1, idEquipo2);
    }

}

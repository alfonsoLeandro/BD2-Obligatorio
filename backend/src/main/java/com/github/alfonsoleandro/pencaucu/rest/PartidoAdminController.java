package com.github.alfonsoleandro.pencaucu.rest;

import com.github.alfonsoleandro.pencaucu.business.partido.PartidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

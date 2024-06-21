package com.github.alfonsoleandro.pencaucu.rest;

import com.github.alfonsoleandro.pencaucu.business.equipo.EquipoService;
import com.github.alfonsoleandro.pencaucu.business.equipo.model.response.EquipoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/public/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping
    public List<EquipoDTO> getAvailableEquipos() {
        return this.equipoService.getAvailableEquipos();
}

}
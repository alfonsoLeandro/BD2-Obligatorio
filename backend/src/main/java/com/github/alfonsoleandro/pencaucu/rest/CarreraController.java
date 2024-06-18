package com.github.alfonsoleandro.pencaucu.rest;

import com.github.alfonsoleandro.pencaucu.business.carrera.CarreraService;
import com.github.alfonsoleandro.pencaucu.business.carrera.model.response.CarreraDTO;
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
@RequestMapping("/api/v1/carreras")
public class CarreraController {

    private final CarreraService carreraService;

    @GetMapping
    public List<CarreraDTO> getAvailableCarreras() {
        return this.carreraService.getAvailableCarreras();
}

}
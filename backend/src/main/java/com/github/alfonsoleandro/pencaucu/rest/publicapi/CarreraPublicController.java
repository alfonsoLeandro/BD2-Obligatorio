package com.github.alfonsoleandro.pencaucu.rest.publicapi;

import com.github.alfonsoleandro.pencaucu.business.carrera.CarreraService;
import com.github.alfonsoleandro.pencaucu.business.carrera.model.response.CarreraDTO;
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
@RequestMapping("/api/v1/public/carreras")
public class CarreraPublicController {

    private final CarreraService carreraService;

    @GetMapping
    public List<CarreraDTO> getAvailableCarreras() {
        return this.carreraService.getAvailableCarreras();
}

}
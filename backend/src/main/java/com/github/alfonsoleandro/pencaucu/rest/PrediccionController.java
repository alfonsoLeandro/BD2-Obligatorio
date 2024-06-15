package com.github.alfonsoleandro.pencaucu.rest;

import com.github.alfonsoleandro.pencaucu.business.prediccion.PrediccionService;
import com.github.alfonsoleandro.pencaucu.business.prediccion.model.request.PartidoPrediccionDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/predicciones")
public class PrediccionController {

    private final PrediccionService prediccionService;

    @PutMapping("/partido/{idPartido}")
    public void setPartidoEquipos(@PathVariable int idPartido,
                                  @RequestBody @Valid PartidoPrediccionDTO partidoPrediccionDTO) {
        this.prediccionService.setPrediccion(idPartido, partidoPrediccionDTO);
    }

}

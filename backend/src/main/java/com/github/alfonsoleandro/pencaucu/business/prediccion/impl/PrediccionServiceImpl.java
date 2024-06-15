package com.github.alfonsoleandro.pencaucu.business.prediccion.impl;

import com.github.alfonsoleandro.pencaucu.business.prediccion.PrediccionService;
import com.github.alfonsoleandro.pencaucu.business.prediccion.model.request.PartidoPrediccionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Service
public class PrediccionServiceImpl implements PrediccionService {
    @Override
    public void setPrediccion(int idPartido, PartidoPrediccionDTO partidoPrediccionDTO) {
        //TODO
    }
}

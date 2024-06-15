package com.github.alfonsoleandro.pencaucu.business.prediccion;

import com.github.alfonsoleandro.pencaucu.business.prediccion.model.request.PartidoPrediccionDTO;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public interface PrediccionService {

    void setPrediccion(int idPartido, PartidoPrediccionDTO partidoPrediccionDTO);

}

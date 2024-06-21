package com.github.alfonsoleandro.pencaucu.persistence.view;

import java.sql.Timestamp;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public interface AlumnoPuntajeDetalleView {

    Integer getIdUsuario();

    Integer getIdCampeon();

    Integer getIdSubcampeon();

    Integer getIdPartido();

    Timestamp getFecha();

    Integer getIdEtapa();

    String getNombreEtapa();

    Integer getIdEquipo1();

    String getNombreEquipo1();

    Integer getPrediccionEquipo1();

    Integer getGolesEquipo1();

    Integer getIdEquipo2();

    String getNombreEquipo2();

    Integer getPrediccionEquipo2();

    Integer getGolesEquipo2();

}

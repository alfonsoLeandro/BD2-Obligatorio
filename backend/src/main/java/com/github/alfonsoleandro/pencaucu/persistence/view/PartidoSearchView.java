package com.github.alfonsoleandro.pencaucu.persistence.view;

import java.time.LocalDateTime;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public interface PartidoSearchView {

    Integer getId();

    LocalDateTime getFecha();

    Integer getIdEtapa();

    String getNombreEtapa();

    Integer getIdEquipo1();

    String getNombreEquipo1();

    Integer getGolesEquipo1();

    Integer getPrediccionEquipo1();

    Integer getIdEquipo2();

    String getNombreEquipo2();

    Integer getGolesEquipo2();

    Integer getPrediccionEquipo2();

}

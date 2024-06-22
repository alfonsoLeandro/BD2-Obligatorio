package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Partido;
import com.github.alfonsoleandro.pencaucu.persistence.view.PartidoFechaView;
import com.github.alfonsoleandro.pencaucu.persistence.view.PartidoSearchView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

    @Override
    @NonNull
    @Query(value = """
            SELECT p.* FROM partidos p
            WHERE p.id = :id
            """, nativeQuery = true)
    Optional<Partido> findById(@NonNull Integer id);

    @Query(value = """
            SELECT p.id         AS id,
                   p.fecha      AS fecha,
                   e.id         AS idEtapa,
                   e.nombre     AS nombreEtapa,
                   eq1.id       AS idEquipo1,
                   eq1.nombre   AS nombreEquipo1,
                   j1.goles     AS golesEquipo1,
                   p1.goles     AS prediccionEquipo1,
                   eq2.id       AS idEquipo2,
                   eq2.nombre   AS nombreEquipo2,
                   j2.goles     AS golesEquipo2,
                   p2.goles     AS prediccionEquipo2
            FROM partidos p
                     JOIN etapas e ON e.id = p.id_etapa
                     JOIN juegos j1 ON j1.id_partido = p.id
                     JOIN juegos j2 ON j2.id_partido = p.id AND NOT j2.id = j1.id
                     JOIN equipos eq1 ON eq1.id = j1.id_equipo
                     JOIN equipos eq2 ON eq2.id = j2.id_equipo
                     LEFT JOIN usuarios u ON u.id = :idUsuario AND u.role = 0
                     LEFT JOIN alumnos a ON a.id_usuario = u.id
                     LEFT JOIN predicciones p1 ON p1.id_equipo = j1.id_equipo
                AND p1.id_partido = p.id
                AND p1.id_alumno = a.id
                     LEFT JOIN predicciones p2 ON p2.id_equipo = j2.id_equipo
                AND p2.id_partido = p.id
                AND p2.id_alumno = a.id
            WHERE j1.id_equipo < j2.id_equipo
                AND (:busqueda IS NULL OR (eq1.nombre RLIKE :busqueda OR eq2.nombre RLIKE :busqueda))
                AND (:jugado IS NULL OR (j1.goles IS NOT NULL = :jugado AND j2.goles IS NOT NULL = :jugado))
                AND (:conPrediccion IS NULL OR (p1.goles IS NOT NULL = :conPrediccion AND p2.goles IS NOT NULL = :conPrediccion))
            """, nativeQuery = true)
    List<PartidoSearchView> searchPartidos(Integer idUsuario, String busqueda, Boolean jugado, Boolean conPrediccion);

    @Query(value = """
            SELECT p.id, p.fecha
            FROM partidos p
            ORDER BY p.fecha
            """, nativeQuery = true)
    List<PartidoFechaView> getAvailableFechas();

    @Query(value = """
            SELECT p.id       AS id,
                   p.fecha    AS fecha,
                   e.id       AS idEtapa,
                   e.nombre   AS nombreEtapa,
                   eq1.id     AS idEquipo1,
                   eq1.nombre AS nombreEquipo1,
                   j1.goles   AS golesEquipo1,
                   p1.goles   AS prediccionEquipo1,
                   eq2.id     AS idEquipo2,
                   eq2.nombre AS nombreEquipo2,
                   j2.goles   AS golesEquipo2,
                   p2.goles   AS prediccionEquipo2
            FROM partidos p
                     JOIN etapas e ON e.id = p.id_etapa
                     JOIN juegos j1 ON j1.id_partido = p.id
                     JOIN juegos j2 ON j2.id_partido = p.id AND NOT j2.id = j1.id
                     JOIN equipos eq1 ON eq1.id = j1.id_equipo
                     JOIN equipos eq2 ON eq2.id = j2.id_equipo
                     LEFT JOIN usuarios u ON u.id = :idUsuario AND u.role = 0
                     LEFT JOIN alumnos a ON a.id_usuario = u.id
                     LEFT JOIN predicciones p1 ON p1.id_equipo = j1.id_equipo
                AND p1.id_partido = p.id
                AND p1.id_alumno = a.id
                     LEFT JOIN predicciones p2 ON p2.id_equipo = j2.id_equipo
                AND p2.id_partido = p.id
                AND p2.id_alumno = a.id
            WHERE
                p.id = :id
                AND j1.id_equipo < j2.id_equipo
            """, nativeQuery = true)
    PartidoSearchView getPartidoData(int id, int idUsuario);

}

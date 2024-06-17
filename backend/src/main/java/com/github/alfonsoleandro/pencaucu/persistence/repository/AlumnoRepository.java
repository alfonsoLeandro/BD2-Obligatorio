package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Alumno;
import com.github.alfonsoleandro.pencaucu.persistence.view.AlumnoPuntajeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    @Query(value = """
            SELECT a.*
            FROM alumnos a
                     JOIN usuarios u ON a.id_usuario = u.id
            WHERE a.id_usuario = :idUsuario
              AND u.role = 0
            """, nativeQuery = true)
    Optional<Alumno> findByUsuarioId(int idUsuario);

    //TODO: test
    @Query(value = """
            SELECT a.id         AS id,
                   CONCAT(u.nombre, ' ', u.apellido) AS nombre,
            	   c.id         AS idCarrera,
            	   c.nombre     AS nombreCarrera,
            	   p1.goles     AS prediccionEquipo1,
            	   j1.goles     AS golesEquipo1,
            	   p2.goles     AS prediccionEquipo2,
            	   j2.goles     AS golesEquipo2
            FROM predicciones p1
            	JOIN alumnos a ON a.id = p1.id_alumno
            	JOIN usuarios u ON u.id = a.id_usuario AND u.role = 0
            	JOIN carreras c ON c.id = a.id_carrera
            	JOIN predicciones p2 ON p2.id_alumno = a.id
            		AND p2.id_partido = p1.id_partido
            		AND NOT p2.id_equipo = p1.id_equipo
                JOIN juegos j1 ON j1.id_partido = p1.id_partido AND j1.id_equipo = p1.id_equipo
                JOIN juegos j2 ON j2.id_partido = j1.id_partido AND j2.id_equipo = p2.id_equipo
            WHERE
            	p1.id_equipo = :idEquipo1
                AND (:searchText IS NULL OR u.nombre RLIKE :searchText OR u.apellido RLIKE :searchText)
                AND (:idCarrera IS NULL OR a.id_carrera = :idCarrera)
            """, nativeQuery = true)
    List<AlumnoPuntajeView> findAlumnos(String searchText, Integer idCarrera);
}
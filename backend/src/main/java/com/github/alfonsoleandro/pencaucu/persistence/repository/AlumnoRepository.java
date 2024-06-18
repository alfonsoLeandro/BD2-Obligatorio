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

	@Query(value = """
			SELECT u.id                              AS idUsuario,
			       CONCAT(u.nombre, ' ', u.apellido) AS nombre,
			       c.id                              AS idCarrera,
			       c.nombre                          AS nombreCarrera,
			       e.id_campeon                      AS idCampeon,
			       e.id_subcampeon                   AS idSubcampeon,
			       p1.goles                          AS prediccionEquipo1,
			       j1.goles                          AS golesEquipo1,
			       p2.goles                          AS prediccionEquipo2,
			       j2.goles                          AS golesEquipo2
			FROM usuarios u
			         LEFT JOIN alumnos a ON a.id_usuario = u.id
			    AND u.role = 0
			         LEFT JOIN elecciones e ON e.id_alumno = a.id
			         LEFT JOIN predicciones p1 ON p1.id_alumno = a.id
			         LEFT JOIN carreras c ON c.id = a.id_carrera
			         LEFT JOIN predicciones p2 ON p2.id_alumno = a.id
			    AND p2.id_partido = p1.id_partido
			    AND NOT p2.id_equipo = p1.id_equipo
			         LEFT JOIN juegos j1 ON j1.id_partido = p1.id_partido AND j1.id_equipo = p1.id_equipo
			         LEFT JOIN juegos j2 ON j2.id_partido = j1.id_partido AND j2.id_equipo = p2.id_equipo
			WHERE (p1.id IS NULL OR p2.id IS NULL OR p1.id_equipo > p2.id_equipo)
			  AND (:searchText IS NULL OR u.nombre RLIKE :searchText OR u.apellido RLIKE :searchText)
			  AND (:idCarrera IS NULL OR a.id_carrera = :idCarrera)
			  AND (:esAdmin IS NULL OR :esAdmin = (a.id IS NULL))
			""", nativeQuery = true)
	List<AlumnoPuntajeView> findAlumnos(String searchText, Integer idCarrera, Boolean esAdmin);
}
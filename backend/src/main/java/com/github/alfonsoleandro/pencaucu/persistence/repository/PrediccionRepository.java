package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Prediccion;
import com.github.alfonsoleandro.pencaucu.persistence.view.AlumnosPrediccionesView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrediccionRepository extends JpaRepository<Prediccion, Integer> {

	@Query(value = """
			WITH totales AS (SELECT COUNT(*) as favorable_predicciones
			                 FROM partidos p
			                          LEFT JOIN predicciones p1 ON p1.id_equipo = :idEquipo1
			                     AND p1.id_partido = p.id
			                          LEFT JOIN predicciones p2 ON p2.id_equipo = :idEquipo2
			                     AND p2.id_partido = p.id
			                     AND p2.id_alumno = p1.id_alumno
			                 WHERE p.id = :idPartido),
			     equipo1_wins AS (SELECT COUNT(*) as favorable_predicciones
			             FROM partidos p
			                      LEFT JOIN predicciones p1 ON p1.id_equipo = :idEquipo1
			                 AND p1.id_partido = p.id
			                      LEFT JOIN predicciones p2 ON p2.id_equipo = :idEquipo2
			                 AND p2.id_partido = p.id
			                 AND p2.id_alumno = p1.id_alumno
			             WHERE p.id = :idPartido
			               AND p1.goles > p2.goles)
			SELECT equipo1_wins.favorable_predicciones / totales.favorable_predicciones
			FROM equipo1_wins,
			     totales;
			""", nativeQuery = true)
	Optional<Double> getEquipoPrediccionPercentage(int idPartido, Integer idEquipo1, Integer idEquipo2);

	@Query(value = """
			SELECT a.id         AS idAlumno,
			       u.nombre     AS nombreAlumno,
				   c.id         AS idCarrera,
				   c.nombre     AS nombreCarrera,
				   eq1.id 		AS idEquipo1,
				   eq1.nombre 	AS nombreEquipo1,
				   p1.goles     AS prediccionEquipo1,
				   eq2.id		AS idEquipo2,
				   eq2.nombre	AS nombreEquipo2,
				   p2.goles     AS prediccionEquipo2
			FROM predicciones p1
				JOIN alumnos a ON a.id = p1.id_alumno
				JOIN usuarios u ON u.id = a.id_usuario AND u.role = 0
				JOIN carreras c ON c.id = a.id_carrera
			    JOIN equipos eq1 ON eq1.id = p1.id_equipo
				JOIN predicciones p2 ON p2.id_alumno = a.id
					AND p2.id_partido = :idPartido
					AND p2.id_equipo = :idEquipo2
				JOIN equipos eq2 ON eq2.id = p2.id_equipo
			WHERE p1.id_partido = :idPartido
				AND p1.id_equipo = :idEquipo1
			""", nativeQuery = true)
	List<AlumnosPrediccionesView> getPredicciones(int idPartido, Integer idEquipo1, Integer idEquipo2);
}
package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Juego;
import com.github.alfonsoleandro.pencaucu.persistence.view.CampeonSubcampeonView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Integer> {

	// Warning saying that the return type should be boolean, it should not.
	@SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
	@Query(value = """
			SELECT EXISTS (SELECT j.id
			               FROM juegos j
			               WHERE j.id_partido = :idPartido)
			""", nativeQuery = true)
	long existsByPartidoId(int idPartido);

	@Modifying
	@Query(value = """
			DELETE FROM juegos
			WHERE id_partido = :idPartido
			""", nativeQuery = true)
	void deleteEquiposForPartido(int idPartido);

	@Modifying
	@Query(value = """
			INSERT INTO juegos (id_partido, id_equipo, goles)
			VALUES (:idPartido, :idEquipo1, NULL),
			       (:idPartido, :idEquipo2, NULL)
			""", nativeQuery = true)
	void insertEquiposForPartido(int idPartido, int idEquipo1, int idEquipo2);

	@Query(value = """
			SELECT j.id_equipo
			FROM juegos j
			WHERE j.id_partido = :idPartido
			""", nativeQuery = true)
	List<Integer> getPartidoEquipoIds(int idPartido);

    @Modifying
    @Query(value = """
			UPDATE juegos j SET j.goles = :newResult
			WHERE j.id_partido = :idPartido
			  AND j.id_equipo = :idEquipo
			""", nativeQuery = true)
    void updateResult(int idPartido, int idEquipo, int newResult);

	@Query(value = """
		SELECT j1.id_equipo AS idCampeon,
		       j2.id_equipo AS idSubCampeon
		FROM juegos j1
		         JOIN partidos p ON p.id = j1.id_partido AND p.id_etapa = 5
		         JOIN juegos j2 ON j2.id_partido = p.id AND NOT j2.id_equipo = j1.id_equipo
		WHERE j1.goles > j2.goles
		""", nativeQuery = true)
	Optional<CampeonSubcampeonView> findCampeonAndSubCampeonIds();



}
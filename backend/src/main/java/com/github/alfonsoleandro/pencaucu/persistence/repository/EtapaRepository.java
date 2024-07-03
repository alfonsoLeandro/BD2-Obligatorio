package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Eleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Repository
public interface EtapaRepository extends JpaRepository<Eleccion, Integer> {

	@Query(value = """
		SELECT e.anunciado
		FROM etapas e
		         JOIN partidos p ON e.id = p.id_etapa
		WHERE p.id = :idPartido
		""", nativeQuery = true)
	boolean isAnunciadaByPartidoId(int idPartido);

	@Modifying
	@Query(value = """
		UPDATE etapas
		SET anunciado = true
		WHERE id = (SELECT id_etapa FROM partidos WHERE id = :idPartido)
		""", nativeQuery = true)
	void setAnunciadaByPartidoId(int idPartido);
}
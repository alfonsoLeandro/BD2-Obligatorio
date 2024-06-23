package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    @Override
    @NonNull
    @Query(value = "SELECT e.* FROM equipos e WHERE e.id = :id", nativeQuery = true)
    Optional<Equipo> findById(@NonNull Integer id);

	@Query(value = "SELECT e.nombre FROM equipos e WHERE e.id = :id", nativeQuery = true)
	String getNombreById(int id);

}
package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    @Override
    @NonNull
    @Query(value = "SELECT e.* FROM equipos e WHERE e.id = :id", nativeQuery = true)
    Optional<Equipo> findById(@NonNull Integer id);

}
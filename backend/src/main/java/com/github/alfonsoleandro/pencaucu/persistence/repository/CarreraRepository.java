package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

  @Query(value = "SELECT c.* FROM carreras c WHERE c.id = :id", nativeQuery = true)
  Optional<Carrera> findById(int id);
}
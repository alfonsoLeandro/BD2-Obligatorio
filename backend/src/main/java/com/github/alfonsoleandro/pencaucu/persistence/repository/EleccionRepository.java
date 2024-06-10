package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Eleccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EleccionRepository extends JpaRepository<Eleccion, Integer> {

}
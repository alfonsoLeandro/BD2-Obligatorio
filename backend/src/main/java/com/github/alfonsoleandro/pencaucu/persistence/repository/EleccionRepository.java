package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Eleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Repository
public interface EleccionRepository extends JpaRepository<Eleccion, Integer> {

}
package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}
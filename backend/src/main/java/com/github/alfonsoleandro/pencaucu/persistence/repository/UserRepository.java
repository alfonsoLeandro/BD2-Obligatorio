package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author alfonsoLeandro
 * @since 0.0.1
 */
public interface UserRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = """
            SELECT u FROM user u
            WHERE u.email = :email
            """, nativeQuery = true)
    Optional<Usuario> findByEmail(String email);
}

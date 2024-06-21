package com.github.alfonsoleandro.pencaucu.persistence.repository;

import com.github.alfonsoleandro.pencaucu.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author alfonsoLeandro
 * @since 0.0.1
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = """
            SELECT u.* FROM usuarios u
            WHERE u.email = :email
            """, nativeQuery = true)
    Optional<Usuario> findByEmail(String email);

    @Modifying(flushAutomatically = true)
    @Query(value = """
            UPDATE usuarios u
            SET u.contrasena = :newPassword
            WHERE id = :id
            """, nativeQuery = true)
    void modifyPassword(Integer id, String newPassword);

}

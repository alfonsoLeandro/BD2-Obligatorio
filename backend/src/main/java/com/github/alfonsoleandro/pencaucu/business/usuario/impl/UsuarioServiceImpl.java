package com.github.alfonsoleandro.pencaucu.business.usuario.impl;

import com.github.alfonsoleandro.pencaucu.business.alumno.model.response.AlumnoDTO;
import com.github.alfonsoleandro.pencaucu.business.usuario.UsuarioService;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Usuario;
import com.github.alfonsoleandro.pencaucu.persistence.repository.AlumnoRepository;
import com.github.alfonsoleandro.pencaucu.persistence.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final AlumnoRepository alumnoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<AlumnoDTO> findUsuarios(String searchText, Integer idCarrera) {
        this.alumnoRepository.findAlumnos(searchText, idCarrera);

        return List.of();
    }

    @Transactional
    @Override
    public void modifyPassword(String newPassword) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modifyPassword(usuario.getId(), this.passwordEncoder.encode(newPassword));
    }

    @Transactional
    @Override
    public void modifyPassword(int id, String newPassword) {
        this.usuarioRepository.modifyPassword(id, this.passwordEncoder.encode(newPassword));
    }

}

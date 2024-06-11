package com.github.alfonsoleandro.pencaucu.business.usuario.impl;

import com.github.alfonsoleandro.pencaucu.business.usuario.UsuarioService;
import com.github.alfonsoleandro.pencaucu.persistence.entity.Usuario;
import com.github.alfonsoleandro.pencaucu.persistence.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void modifyPassword(String newPassword) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modifyPassword(usuario.getId(), newPassword);
    }

    @Transactional
    @Override
    public void modifyPassword(int id, String newPassword) {
        this.usuarioRepository.modifyPassword(id, this.passwordEncoder.encode(newPassword));
    }
}

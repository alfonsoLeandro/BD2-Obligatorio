package com.github.alfonsoleandro.pencaucu.rest;

import com.github.alfonsoleandro.pencaucu.business.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/usuarios")
public class UsuarioAdminController {

    private final UsuarioService usuarioService;

    @PutMapping("/{id}")
    public void modifyPassword(@PathVariable Integer id,
                               @RequestParam String newPassword) {
        this.usuarioService.modifyPassword(id, newPassword);
    }

}
package com.github.alfonsoleandro.pencaucu.rest;

import com.github.alfonsoleandro.pencaucu.business.alumno.model.response.AlumnoDTO;
import com.github.alfonsoleandro.pencaucu.business.usuario.UsuarioService;
import com.github.alfonsoleandro.pencaucu.business.usuario.model.response.UsuarioDetalleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<AlumnoDTO> findUsuarios(@RequestParam(name = "busqueda", required = false) String searchText,
                                        @RequestParam(required = false) Integer idCarrera,
                                        @RequestParam(required = false) Boolean esAdmin) {
        return this.usuarioService.findUsuarios(searchText, idCarrera, esAdmin);
    }

    @GetMapping("/{id}")
    public UsuarioDetalleDTO getUsuario(@PathVariable Integer id) {
        return this.usuarioService.getUsuarioDetalle(id);
    }

    @PutMapping("/modifyPassword")
    public void modifyPassword(@RequestParam String newPassword) {
        this.usuarioService.modifyPassword(newPassword);
    }
}
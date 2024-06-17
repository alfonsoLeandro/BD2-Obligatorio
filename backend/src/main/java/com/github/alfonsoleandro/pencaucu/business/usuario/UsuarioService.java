package com.github.alfonsoleandro.pencaucu.business.usuario;

import com.github.alfonsoleandro.pencaucu.business.alumno.model.response.AlumnoDTO;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public interface UsuarioService {

    List<AlumnoDTO> findUsuarios(String searchText, Integer idCarrera);

    void modifyPassword(String newPassword);

    void modifyPassword(int id, String newPassword);

}

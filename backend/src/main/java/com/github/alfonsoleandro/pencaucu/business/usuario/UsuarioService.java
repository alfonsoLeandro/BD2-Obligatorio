package com.github.alfonsoleandro.pencaucu.business.usuario;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public interface UsuarioService {

    void modifyPassword(String newPassword);

    void modifyPassword(int id, String newPassword);

}

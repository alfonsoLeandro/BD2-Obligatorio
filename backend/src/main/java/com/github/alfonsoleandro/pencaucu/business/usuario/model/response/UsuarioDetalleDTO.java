package com.github.alfonsoleandro.pencaucu.business.usuario.model.response;

import com.github.alfonsoleandro.pencaucu.business.carrera.model.response.CarreraDTO;
import com.github.alfonsoleandro.pencaucu.business.equipo.model.response.EquipoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Getter
@Setter
public class UsuarioDetalleDTO {

	private Integer id;

	private String nombre;

	private CarreraDTO carrera;

	private EquipoDTO campeon;

	private EquipoDTO subcampeon;

	private Integer puntaje;

	private Integer ranking;

	private List<AlumnoPrediccionesDetalleDTO> predicciones;
}

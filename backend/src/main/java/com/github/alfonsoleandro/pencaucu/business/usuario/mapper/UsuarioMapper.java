package com.github.alfonsoleandro.pencaucu.business.usuario.mapper;

import com.github.alfonsoleandro.pencaucu.business.usuario.model.response.UsuarioDetalleDTO;
import com.github.alfonsoleandro.pencaucu.persistence.view.UsuarioDetalleView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Mapper
public interface UsuarioMapper {

	@Mapping(source = "idCarrera", target = "carrera.id")
	@Mapping(source = "nombreCarrera", target = "carrera.nombre")
	@Mapping(source = "idCampeon", target = "campeon.id")
	@Mapping(source = "nombreCampeon", target = "campeon.nombre")
	@Mapping(source = "idSubcampeon", target = "subcampeon.id")
	@Mapping(source = "nombreSubcampeon", target = "subcampeon.nombre")
	@Mapping(target = "puntaje", ignore = true)
	@Mapping(target = "ranking", constant = "0")
	@Mapping(target = "predicciones", expression = "java(new java.util.ArrayList<>())")
	UsuarioDetalleDTO detalleViewToDTO(UsuarioDetalleView usuarioDetalleView);

}

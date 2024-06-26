import { CarreraApiDto } from './carrera-api-dto';
import { EquipoApiDto } from './equipo-api-dto';
import { AlumnoPrediccionDetalleApiDto } from './alumno-prediccion-detalle-api-dto';

export interface UsuarioDetalleApiDto {
    id: number;
    nombre: string;
    carrera: CarreraApiDto;
    campeon: EquipoApiDto;
    subcampeon: EquipoApiDto;
    puntaje: number;
    ranking: number;
    predicciones: AlumnoPrediccionDetalleApiDto[];
}

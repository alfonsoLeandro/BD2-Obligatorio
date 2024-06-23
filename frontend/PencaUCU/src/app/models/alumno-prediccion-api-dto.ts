import { CarreraApiDto } from './carrera-api-dto';
import { EquipoPrediccionApiDto } from './equipo-prediccion-api-dto';

export interface AlumnoPrediccionApiDto {
  idAlumno: number;
  nombreAlumno: string;
  carrera: CarreraApiDto;
  equipo1: EquipoPrediccionApiDto;
  equipo2: EquipoPrediccionApiDto;
  predicciones: AlumnoPrediccionApiDto[];
}

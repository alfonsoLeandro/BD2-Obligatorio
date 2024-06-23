import { EtapaApiDto } from './etapa-api-dto';
import { EquipoGoalsPrediccionStatisticsApiDto } from './equipo-goals-prediccion-statistics-api-dto';
import { AlumnoPrediccionApiDto } from './alumno-prediccion-api-dto';

export interface PartidoDetalleApiDto {
  id: number;
  fecha: Date;
  etapa: EtapaApiDto;
  equipo1: EquipoGoalsPrediccionStatisticsApiDto;
  equipo2: EquipoGoalsPrediccionStatisticsApiDto;
  predicciones: AlumnoPrediccionApiDto[];
}

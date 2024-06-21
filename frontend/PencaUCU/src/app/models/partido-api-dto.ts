import { EtapaApiDto } from './etapa-api-dto';
import { EquipoGoalsPrediccionApiDTO } from './equipo-goals-prediccion-api-dto';

export interface PartidoApiDto {
  id: number;
  fecha: Date;
  etapa: EtapaApiDto;
  equipo1: EquipoGoalsPrediccionApiDTO;
  equipo2: EquipoGoalsPrediccionApiDTO;
}

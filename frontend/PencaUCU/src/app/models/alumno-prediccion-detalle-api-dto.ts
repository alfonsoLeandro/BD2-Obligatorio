import { EtapaApiDto } from './etapa-api-dto';
import { EquipoGoalsPrediccionApiDTO } from './equipo-goals-prediccion-api-dto';

export interface AlumnoPrediccionDetalleApiDto {
    idPartido: number;
    fecha: Date;
    etapa: EtapaApiDto;
    puntajeObtenido: number;
    equipo1: EquipoGoalsPrediccionApiDTO;
    equipo2: EquipoGoalsPrediccionApiDTO;
}

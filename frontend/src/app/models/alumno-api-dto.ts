import { CarreraApiDto } from './carrera-api-dto';

export interface AlumnoApiDto {
    id: number;
    nombre: string;
    carrera: CarreraApiDto;
    puntaje: number;
}

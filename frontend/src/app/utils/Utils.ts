import { PartidoDetalleApiDto } from '../models/partido-detalle-api-dto';
import { AlumnoPrediccionDetalleApiDto } from '../models/alumno-prediccion-detalle-api-dto';
import { PartidoApiDto } from '../models/partido-api-dto';

export class Utils {

    static resultadoErroneo(data: PartidoApiDto | PartidoDetalleApiDto | AlumnoPrediccionDetalleApiDto) {
        const pred1 = data.equipo1.prediccion;
        const pred2 = data.equipo2.prediccion;
        const goles1 = data.equipo1.goles;
        const goles2 = data.equipo2.goles;

        if (pred1 == null || pred2 == null || goles1 == null || goles2 == null) {
            return false;
        }

        return pred1 != goles1 || pred2 != goles2;

    }

    static resultadoCercano(data: PartidoApiDto | PartidoDetalleApiDto | AlumnoPrediccionDetalleApiDto) {
        const pred1 = data.equipo1.prediccion;
        const pred2 = data.equipo2.prediccion;
        const goles1 = data.equipo1.goles;
        const goles2 = data.equipo2.goles;

        if (pred1 == null || pred2 == null || goles1 == null || goles2 == null) {
            return false;
        }

        return (
                (pred1 > pred2 && goles1 > goles2)
                || (pred1 < pred2 && goles1 < goles2)
                || (pred1 == pred2 && goles1 == goles2)
            ) &&
            (pred1 != goles1 || pred2 != goles2);

    }

    static resultadoExacto(data: PartidoApiDto | PartidoDetalleApiDto | AlumnoPrediccionDetalleApiDto) {
        const pred1 = data.equipo1.prediccion;
        const pred2 = data.equipo2.prediccion;
        const goles1 = data.equipo1.goles;
        const goles2 = data.equipo2.goles;
        if (pred1 == null || pred2 == null || goles1 == null || goles2 == null) {
            return false;
        }

        return pred1 == goles1 && pred2 == goles2;
    }

}

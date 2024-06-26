import { Component, Input } from '@angular/core';
import { AlumnoPrediccionDetalleApiDto } from '../../models/alumno-prediccion-detalle-api-dto';
import { DatePipe, NgClass } from '@angular/common';
import { FlagService } from '../../services/flag.service';

@Component({
    selector: 'app-alumno-prediccion-detalle-card',
    standalone: true,
    imports: [
        DatePipe,
        NgClass
    ],
    templateUrl: './alumno-prediccion-detalle-card.component.html',
    styleUrl: './alumno-prediccion-detalle-card.component.scss'
})
export class AlumnoPrediccionDetalleCardComponent {

    @Input({ required: true }) alumnoPrediccion!: AlumnoPrediccionDetalleApiDto;

    constructor(private flagService: FlagService) {
    }

    getFlagPath(idEquipo: number) {
        return this.flagService.getFlagPath(idEquipo);
    }

    resultadoErroneo() {
        const pred1 = this.alumnoPrediccion.equipo1.prediccion;
        const pred2 = this.alumnoPrediccion.equipo2.prediccion;
        const goles1 = this.alumnoPrediccion.equipo1.goles;
        const goles2 = this.alumnoPrediccion.equipo2.goles;

        if (pred1 == null || pred2 == null || goles1 == null || goles2 == null) {
            return false;
        }

        return (pred1 > pred2 !== goles1 > goles2) || (pred1 == pred2 && goles1 != goles2) || (pred1 != pred2 && goles1 == goles2);

    }

    resultadoCercano() {
        const pred1 = this.alumnoPrediccion.equipo1.prediccion;
        const pred2 = this.alumnoPrediccion.equipo2.prediccion;
        const goles1 = this.alumnoPrediccion.equipo1.goles;
        const goles2 = this.alumnoPrediccion.equipo2.goles;

        if (pred1 == null || pred2 == null || goles1 == null || goles2 == null) {
            return false;
        }

        return (pred1 > goles1) == (pred2 > goles2);

    }

    resultadoExacto() {
        const pred1 = this.alumnoPrediccion.equipo1.prediccion;
        const pred2 = this.alumnoPrediccion.equipo2.prediccion;
        const goles1 = this.alumnoPrediccion.equipo1.goles;
        const goles2 = this.alumnoPrediccion.equipo2.goles;
        if (pred1 == null || pred2 == null || goles1 == null || goles2 == null) {
            return false;
        }

        return pred1 == goles1 && pred2 == goles2;
    }
}

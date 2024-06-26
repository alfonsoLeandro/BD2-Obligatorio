import { Component, Input } from '@angular/core';
import { AlumnoPrediccionDetalleApiDto } from '../../models/alumno-prediccion-detalle-api-dto';
import { DatePipe, NgClass } from '@angular/common';
import { FlagService } from '../../services/flag.service';
import { Utils } from '../../utils/Utils';

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

    protected readonly Utils = Utils;
}

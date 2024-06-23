import { Component, Input } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { AlumnoPrediccionApiDto } from '../../models/alumno-prediccion-api-dto';

@Component({
    selector: 'app-alumno-prediccion-card',
    standalone: true,
    imports: [
        MatIcon
    ],
    templateUrl: './alumno-prediccion-card.component.html',
    styleUrl: './alumno-prediccion-card.component.scss'
})
export class AlumnoPrediccionCardComponent {

    @Input({required: true}) alumnoPrediccion!: AlumnoPrediccionApiDto;
}

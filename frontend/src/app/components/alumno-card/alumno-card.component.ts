import { Component, Input } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { AlumnoApiDto } from '../../models/alumno-api-dto';

@Component({
  selector: 'app-alumno-card',
  standalone: true,
    imports: [
        MatIcon
    ],
  templateUrl: './alumno-card.component.html',
  styleUrl: './alumno-card.component.scss'
})
export class AlumnoCardComponent {

    @Input({required: true}) alumno!: AlumnoApiDto;

}

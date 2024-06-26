import { Component, Input, OnInit } from '@angular/core';
import { PartidoApiDto } from '../../models/partido-api-dto';
import { FlagService } from '../../services/flag.service';
import { DatePipe, NgClass } from '@angular/common';
import { MatButton } from '@angular/material/button';

@Component({
    selector: 'app-partido-card',
    standalone: true,
    imports: [
        DatePipe,
        MatButton,
        NgClass
    ],
    templateUrl: './partido-card.component.html',
    styleUrl: './partido-card.component.scss'
})
export class PartidoCardComponent implements OnInit {

    @Input({ required: true }) partido!: PartidoApiDto;
    equipo1FlagPath: string = '';
    equipo2FlagPath: string = '';

    constructor(private flagService: FlagService) {
    }

    ngOnInit(): void {
        this.equipo1FlagPath = this.flagService.getFlagPath(this.partido.equipo1.id);
        this.equipo2FlagPath = this.flagService.getFlagPath(this.partido.equipo2.id);

    }

    resultadoErroneo() {
        const pred1 = this.partido.equipo1.prediccion;
        const pred2 = this.partido.equipo2.prediccion;
        const goles1 = this.partido.equipo1.goles;
        const goles2 = this.partido.equipo2.goles;

        if (pred1 == null || pred2 == null || goles1 == null || goles2 == null) {
            return false;
        }

        return (pred1 > pred2 !== goles1 > goles2) || (pred1 == pred2 && goles1 != goles2) || (pred1 != pred2 && goles1 == goles2);

    }

    resultadoCercano() {
        const pred1 = this.partido.equipo1.prediccion;
        const pred2 = this.partido.equipo2.prediccion;
        const goles1 = this.partido.equipo1.goles;
        const goles2 = this.partido.equipo2.goles;

        if (pred1 == null || pred2 == null || goles1 == null || goles2 == null) {
            return false;
        }

        return (pred1 > goles1) == (pred2 > goles2);

    }

    resultadoExacto() {
        const pred1 = this.partido.equipo1.prediccion;
        const pred2 = this.partido.equipo2.prediccion;
        const goles1 = this.partido.equipo1.goles;
        const goles2 = this.partido.equipo2.goles;

        if (pred1 == null || pred2 == null || goles1 == null || goles2 == null) {
            return false;
        }

        return pred1 == goles1 && pred2 == goles2;
    }

}

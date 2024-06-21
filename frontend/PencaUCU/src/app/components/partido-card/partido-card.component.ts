import { Component, Input, OnInit } from '@angular/core';
import { PartidoApiDto } from '../../models/partido-api-dto';
import { FlagService } from '../../services/flag.service';
import { DatePipe } from '@angular/common';
import { MatButton } from '@angular/material/button';

@Component({
    selector: 'app-partido-card',
    standalone: true,
    imports: [
        DatePipe,
        MatButton
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

}

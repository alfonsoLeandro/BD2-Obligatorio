import { Component, inject, OnInit } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatOption } from '@angular/material/autocomplete';
import { MatSelect } from '@angular/material/select';
import { MatDialogRef } from '@angular/material/dialog';
import { PartidoService } from '../../services/partido.service';
import { AlertService } from '../../services/alert.service';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { EquipoApiDto } from '../../models/equipo-api-dto';
import { EquipoService } from '../../services/equipo.service';
import { PartidoFechaApiDto } from '../../models/partido-fecha-api-dto';

@Component({
    selector: 'app-partido-equipos-dialog',
    standalone: true,
    imports: [
        MatButton,
        MatFormField,
        MatIcon,
        MatLabel,
        MatOption,
        MatSelect,
        FormsModule,
        DatePipe
    ],
    templateUrl: './partido-equipos-dialog.component.html',
    styleUrl: './partido-equipos-dialog.component.scss'
})
export class PartidoEquiposDialogComponent implements OnInit {

    availableFechas: PartidoFechaApiDto[] = [];
    availableEquipos: EquipoApiDto[] = [];
    partidoSeleccionado?: number;
    idEquipo1?: number;
    idEquipo2?: number;

    readonly dialogRef = inject(MatDialogRef<PartidoEquiposDialogComponent>);
    readonly partidoService: PartidoService = inject(PartidoService);
    readonly equipoService: EquipoService = inject(EquipoService);
    readonly alertService: AlertService = inject(AlertService);

    ngOnInit(): void {
        this.getAvailableFechas();
        this.getAvailableEquipos();
    }

    accept() {
        if (!this.partidoSeleccionado || !this.idEquipo1 || !this.idEquipo2) {
            this.alertService.showError('Todos los campos son requeridos');
            return;
        }
        this.dialogRef.close({
            idPartido: this.partidoSeleccionado,
            idEquipo1: this.idEquipo1,
            idEquipo2: this.idEquipo2
        });
    }

    cancel(): void {
        this.dialogRef.close();
    }

    private getAvailableFechas() {
        this.partidoService.getAvailableFechas().subscribe({
            next: (availableFechas) => {
                this.availableFechas = availableFechas;
            },
            error: async (error) => {
                if (error.status == 403) {
                    await this.alertService.showError('No tiene autorización');
                } else {
                    await this.alertService.showError(error.error?.message ?? 'Error inesperado');
                }
                this.cancel();
            }
        })
    }

    private getAvailableEquipos() {
        this.equipoService.getEquipos().subscribe({
            next: (availableEquipos) => {
                this.availableEquipos = availableEquipos;
            },
            error: (error) => {
                this.alertService.showError(error.error?.message ?? 'Error inesperado');
                this.cancel();
            }
        });
    }
}

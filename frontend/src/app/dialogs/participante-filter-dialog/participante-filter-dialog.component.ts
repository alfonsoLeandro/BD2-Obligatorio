import { Component, inject, OnInit } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatOption } from '@angular/material/autocomplete';
import { MatSelect } from '@angular/material/select';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CarreraApiDto } from '../../models/carrera-api-dto';
import { CarreraService } from '../../services/carrera.service';
import { AlertService } from '../../services/alert.service';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-participante-filter-dialog',
    standalone: true,
    imports: [
        MatButton,
        MatFormField,
        MatIcon,
        MatLabel,
        MatOption,
        MatSelect,
        FormsModule
    ],
    templateUrl: './participante-filter-dialog.component.html',
    styleUrl: './participante-filter-dialog.component.scss'
})
export class ParticipanteFilterDialogComponent implements OnInit {

    idCarrera?: number;
    isAdmin?: boolean;
    availableCarreras: CarreraApiDto[] = [];

    readonly dialogRef = inject(MatDialogRef<ParticipanteFilterDialogComponent>);
    readonly data = inject<{ idCarrera: number, isAdmin: boolean }>(MAT_DIALOG_DATA);

    constructor(private carreraService: CarreraService,
                private alertService: AlertService) {
        this.getAvailableCarreras();
    }

    private getAvailableCarreras() {
        this.carreraService.getCarreras().subscribe({
            next: (carreras) => {
                this.availableCarreras = carreras;
            },
            error: (error) => {
                if (error.error && error.error.message) {
                    this.alertService.showError('No tiene autorización');
                } else {
                    this.alertService.showError(error.error?.message ?? 'Error inesperado');
                }
                this.cancel();
            }
        });
    }

    ngOnInit(): void {
        this.idCarrera = this.data.idCarrera;
        this.isAdmin = this.data.isAdmin;
    }

    accept() {
        this.dialogRef.close({ idCarrera: this.idCarrera, isAdmin: this.isAdmin });
    }

    cancel(): void {
        this.dialogRef.close();
    }

}

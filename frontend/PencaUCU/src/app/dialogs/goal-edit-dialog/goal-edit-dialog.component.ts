import { Component, inject, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { MatButton, MatIconButton, MatMiniFabButton } from '@angular/material/button';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatOption } from '@angular/material/autocomplete';
import { MatSelect } from '@angular/material/select';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AlertService } from '../../services/alert.service';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInput } from '@angular/material/input';

@Component({
    selector: 'app-goal-edit-dialog',
    standalone: true,
    imports: [
        DatePipe,
        MatButton,
        MatFormField,
        MatIcon,
        MatLabel,
        MatOption,
        MatSelect,
        FormsModule,
        MatInput,
        ReactiveFormsModule,
        MatIconButton,
        MatMiniFabButton
    ],
    templateUrl: './goal-edit-dialog.component.html',
    styleUrl: './goal-edit-dialog.component.scss'
})
export class GoalEditDialogComponent implements OnInit {

    titulo: string = '';
    golesEquipo1 = new FormControl(0, [Validators.max(20), Validators.min(0), Validators.required])
    golesEquipo2 = new FormControl(0, [Validators.max(20), Validators.min(0), Validators.required])

    readonly dialogRef = inject(MatDialogRef<GoalEditDialogComponent>);
    readonly alertService: AlertService = inject(AlertService);
    readonly data = inject<{ titulo: string, golesEquipo1?: number, golesEquipo2?: number }>(MAT_DIALOG_DATA);

    ngOnInit(): void {
        this.titulo = this.data.titulo;
        this.golesEquipo1.setValue(this.data.golesEquipo1 || 0);
        this.golesEquipo2.setValue(this.data.golesEquipo2 || 0);
    }

    accept() {
        if (!this.golesEquipo1.valid || !this.golesEquipo2.valid) {
            this.alertService.showError('Goles inválidos');
            return;
        }
        this.dialogRef.close({
            golesEquipo1: this.golesEquipo1.value,
            golesEquipo2: this.golesEquipo2.value,
        });
    }

    cancel(): void {
        this.dialogRef.close();
    }

    increase(formControl: FormControl<number | null>) {
        formControl.setValue(formControl.value! + 1);
        formControl.markAsTouched();
    }

    decrease(formControl: FormControl<number | null>) {
        formControl.setValue(formControl.value! - 1);
        formControl.markAsTouched();
    }

}

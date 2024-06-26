import { Component, inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatOption } from '@angular/material/autocomplete';
import { MatSelect } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButton, MatIconButton } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-partido-filter-dialog',
  standalone: true,
    imports: [
        MatFormField,
        MatLabel,
        MatOption,
        MatSelect,
        ReactiveFormsModule,
        FormsModule,
        MatButton,
        MatIconButton,
        MatIcon
    ],
  templateUrl: './partido-filter-dialog.component.html',
  styleUrl: './partido-filter-dialog.component.scss'
})
export class PartidoFilterDialogComponent implements OnInit {

    jugado?: boolean;
    conPrediccion?: boolean;

    readonly dialogRef = inject(MatDialogRef<PartidoFilterDialogComponent>);
    readonly data = inject<{jugado?: boolean, conPrediccion?: boolean}>(MAT_DIALOG_DATA);

    ngOnInit(): void {
        this.jugado = this.data.jugado;
        this.conPrediccion = this.data.conPrediccion;
    }

    accept() {
        this.dialogRef.close({jugado: this.jugado, conPrediccion: this.conPrediccion});
    }

    cancel(): void {
        this.dialogRef.close();
    }

}

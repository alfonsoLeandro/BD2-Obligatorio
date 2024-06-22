import { Component } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { MatToolbar } from '@angular/material/toolbar';
import { MatIconButton } from '@angular/material/button';
import { MatFormField, MatPrefix, MatSuffix } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { PartidoCardComponent } from '../../components/partido-card/partido-card.component';
import { PartidoService } from '../../services/partido.service';
import { PartidoApiDto } from '../../models/partido-api-dto';
import Swal from 'sweetalert2';
import { AlertService } from '../../services/alert.service';
import { FormsModule } from '@angular/forms';
import { debounceTime, Subject } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { PartidoFilterDialogComponent } from '../../dialogs/partido-filter-dialog/partido-filter-dialog.component';
import { Router } from '@angular/router';
import { Role } from '../../models/role';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [
        MatIcon,
        MatToolbar,
        MatIconButton,
        MatFormField,
        MatInput,
        PartidoCardComponent,
        MatSuffix,
        MatPrefix,
        FormsModule
    ],
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss'
})
export class HomeComponent {

    role: Role = Role.ALUMNO;
    $searchSub = new Subject<void>();
    partidos: PartidoApiDto[] = [];
    searchText: string = '';
    jugado?: boolean;
    conPrediccion?: boolean;

    constructor(private partidoService: PartidoService,
                private router: Router,
                private alertService: AlertService,
                private dialog: MatDialog) {
        this.getPartidos();
        this.$searchSub
            .pipe(debounceTime(500))
            .subscribe(() => {
                this.getPartidos();
            });
        this.role = localStorage.getItem('role') == "ADMIN" ? Role.ADMIN : Role.ALUMNO;
    }

    getPartidos() {
        this.alertService.showLoading();
        this.partidoService.getPartidos(this.searchText, this.jugado, this.conPrediccion).subscribe({
            next: (partidos) => {
                this.partidos = partidos;
                Swal.close();
            },
            error: (error) => {
                console.log(error);
                Swal.close();
            }
        });
    }

    openFilters() {
        this.dialog.open(PartidoFilterDialogComponent, {
            width: '250px',
            data: {
                jugado: this.jugado,
                conPrediccion: this.conPrediccion
            }
        }).afterClosed().subscribe((result) => {
            if (result) {
                this.jugado = result.jugado;
                this.conPrediccion = result.conPrediccion;
                this.getPartidos();
            }
        });
    }

    logout() {
        localStorage.clear();
        this.router.navigate(['/login']);
    }

    addPartido() {
        //TODO
    }

    protected readonly Role = Role;
}

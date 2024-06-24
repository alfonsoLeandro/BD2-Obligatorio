import { Component, OnDestroy } from '@angular/core';
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
import { Router, RouterLink } from '@angular/router';
import { Role } from '../../models/role';
import { PartidoEquiposDialogComponent } from '../../dialogs/partido-equipos-dialog/partido-equipos-dialog.component';

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
        FormsModule,
        RouterLink
    ],
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss'
})
export class HomeComponent implements OnDestroy {

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
        this.role = localStorage.getItem('role') == 'ADMIN' ? Role.ADMIN : Role.ALUMNO;
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
        this.dialog.open(PartidoEquiposDialogComponent, {
            width: '250px'
        }).afterClosed().subscribe((result?: { idPartido: number, idEquipo1: number, idEquipo2: number }) => {
            if (result) {
                this.partidoService.setPartidoEquipos(result.idPartido, result.idEquipo1, result.idEquipo2)
                    .subscribe({
                        next: () => {
                            this.alertService.showSuccess();
                            this.getPartidos();
                        },
                        error: (error) => {
                            this.alertService.showError(error.error?.message ?? 'Error inesperado');
                        }
                    })
            }
        });
    }

    navigateToPartido(id: number) {
        this.router.navigate(['/partido', id]);
    }

    ngOnDestroy(): void {
        this.$searchSub.subscribe();
    }

    protected readonly Role = Role;
}

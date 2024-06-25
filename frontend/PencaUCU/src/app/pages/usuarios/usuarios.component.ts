import { Component, OnDestroy } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormField, MatPrefix, MatSuffix } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatIconButton } from '@angular/material/button';
import { MatInput } from '@angular/material/input';
import { MatToolbar } from '@angular/material/toolbar';
import { PartidoCardComponent } from '../../components/partido-card/partido-card.component';
import { debounceTime, Subject } from 'rxjs';
import { AlumnoApiDto } from '../../models/alumno-api-dto';
import { Router, RouterLink } from '@angular/router';
import { AlertService } from '../../services/alert.service';
import { MatDialog } from '@angular/material/dialog';
import { UsuarioService } from '../../services/usuario.service';
import Swal from 'sweetalert2';
import { AlumnoCardComponent } from '../../components/alumno-card/alumno-card.component';
import { MatDivider } from '@angular/material/divider';
import {
    ParticipanteFilterDialogComponent
} from '../../dialogs/participante-filter-dialog/participante-filter-dialog.component';

@Component({
    selector: 'app-usuarios',
    standalone: true,
    imports: [
        FormsModule,
        MatFormField,
        MatIcon,
        MatIconButton,
        MatInput,
        MatPrefix,
        MatSuffix,
        MatToolbar,
        PartidoCardComponent,
        AlumnoCardComponent,
        MatDivider,
        RouterLink
    ],
    templateUrl: './usuarios.component.html',
    styleUrl: './usuarios.component.scss'
})
export class UsuariosComponent implements OnDestroy {

    $searchSub = new Subject<void>();
    alumnos: AlumnoApiDto[] = [];
    searchText: string = '';
    idCarrera?: number;
    isAdmin?: boolean;

    constructor(private alumnoService: UsuarioService,
                private router: Router,
                private alertService: AlertService,
                private dialog: MatDialog) {
        this.getAlumnos();
        this.$searchSub
            .pipe(debounceTime(500))
            .subscribe(() => {
                this.getAlumnos();
            });
    }

    getAlumnos() {
        this.alertService.showLoading();
        this.alumnoService.getUsuarios(this.searchText, this.idCarrera, this.isAdmin).subscribe({
            next: (alumnos) => {
                this.alumnos = alumnos;
                Swal.close();
            },
            error: (error) => {
                console.log(error);
                if (error.error && error.error.message) {
                    this.alertService.showError(error.error.message);
                } else {
                    this.alertService.showError('Ocurrió un error al obtener los usuarios');
                }

            }
        });
    }

    openFilters() {
        this.dialog.open(ParticipanteFilterDialogComponent, {
            width: '250px',
            data: {
                idCarrera: this.idCarrera,
                isAdmin: this.isAdmin
            }
        }).afterClosed().subscribe((result: { idCarrera: number, isAdmin: boolean }) => {
            if (result) {
                this.idCarrera = result.idCarrera;
                this.isAdmin = result.isAdmin;
                this.getAlumnos();
            }
        });
    }

    logout() {
        localStorage.clear();
        this.router.navigate(['/login']);
    }

    ngOnDestroy(): void {
        this.$searchSub.subscribe();
    }

    navigateToUsuario(id: number) {
        this.router.navigate(['/usuario', id]);
    }

}

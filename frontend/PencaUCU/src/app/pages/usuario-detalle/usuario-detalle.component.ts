import { Component } from '@angular/core';
import {
    AlumnoPrediccionCardComponent
} from '../../components/alumno-prediccion-card/alumno-prediccion-card.component';
import { DatePipe, Location, PercentPipe } from '@angular/common';
import { MatDivider } from '@angular/material/divider';
import { MatIcon } from '@angular/material/icon';
import { MatIconButton } from '@angular/material/button';
import { MatToolbar } from '@angular/material/toolbar';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from '../../services/alert.service';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { Role } from '../../models/role';
import { UsuarioService } from '../../services/usuario.service';
import { UsuarioDetalleApiDto } from '../../models/usuario-detalle-api-dto';
import { FlagService } from '../../services/flag.service';
import {
    AlumnoPrediccionDetalleCardComponent
} from '../../components/alumno-prediccion-detalle-card/alumno-prediccion-detalle-card.component';

@Component({
    selector: 'app-usuario-detalle',
    standalone: true,
    imports: [
        AlumnoPrediccionCardComponent,
        DatePipe,
        MatDivider,
        MatIcon,
        MatIconButton,
        MatToolbar,
        PercentPipe,
        AlumnoPrediccionDetalleCardComponent
    ],
    templateUrl: './usuario-detalle.component.html',
    styleUrl: './usuario-detalle.component.scss'
})
export class UsuarioDetalleComponent {

    private idUsuario: number = 0;
    role: Role = Role.ALUMNO;
    usuario!: UsuarioDetalleApiDto;

    constructor(private route: ActivatedRoute,
                private usuarioService: UsuarioService,
                private location: Location,
                private alertService: AlertService,
                private flagService: FlagService,
                private dialog: MatDialog) {
        this.alertService.showLoading();
        this.role = localStorage.getItem('rol') == 'ADMIN' ? Role.ADMIN : Role.ALUMNO;
        this.route.params.subscribe(params => {
            this.idUsuario = params['id'];
            this.getAlumnoDetalle();
        });
    }

    getAlumnoDetalle() {
        this.usuarioService.getUsuarioDetalle(this.idUsuario).subscribe({
            next: (alumno) => {
                this.usuario = alumno;
                Swal.close();
            },
            error: (error) => {
                console.log(error);
                this.alertService.showError('Error al cargar el participante');
                this.location.back();
            }
        });
    }

    back() {
        this.location.back();
    }

    editUser() {
        //TODO
    }

    getFlagPath(idEquipo: number) {
        return this.flagService.getFlagPath(idEquipo);
    }

    protected readonly Role = Role;
}

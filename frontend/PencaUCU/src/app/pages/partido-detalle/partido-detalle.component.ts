import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from '../../services/alert.service';
import { PartidoService } from '../../services/partido.service';
import Swal from 'sweetalert2';
import { Role } from '../../models/role';
import { MatIcon } from '@angular/material/icon';
import { MatIconButton } from '@angular/material/button';
import { MatToolbar } from '@angular/material/toolbar';
import { DatePipe, Location, NgClass, PercentPipe } from '@angular/common';
import { PartidoDetalleApiDto } from '../../models/partido-detalle-api-dto';
import { FlagService } from '../../services/flag.service';
import { MatProgressBar } from '@angular/material/progress-bar';
import {
    AlumnoPrediccionCardComponent
} from '../../components/alumno-prediccion-card/alumno-prediccion-card.component';
import { MatDivider } from '@angular/material/divider';
import { PrediccionService } from '../../services/prediccion.service';
import { MatDialog } from '@angular/material/dialog';
import { GoalEditDialogComponent } from '../../dialogs/goal-edit-dialog/goal-edit-dialog.component';
import { EquipoGoalsApiDto } from '../../models/equipo-goals-api-dto';

@Component({
    selector: 'app-partido-detalle',
    standalone: true,
    imports: [
        MatIcon,
        MatIconButton,
        MatToolbar,
        DatePipe,
        MatProgressBar,
        PercentPipe,
        AlumnoPrediccionCardComponent,
        MatDivider,
        NgClass
    ],
    templateUrl: './partido-detalle.component.html',
    styleUrl: './partido-detalle.component.scss'
})
export class PartidoDetalleComponent {

    private idPartido: number = 0;
    role: Role = Role.ALUMNO;
    partido!: PartidoDetalleApiDto;
    equipo1FlagPath: string = '';
    equipo2FlagPath: string = '';

    constructor(private route: ActivatedRoute,
                private location: Location,
                private alertService: AlertService,
                private partidoService: PartidoService,
                private flagService: FlagService,
                private prediccionService: PrediccionService,
                private dialog: MatDialog) {
        this.alertService.showLoading();
        this.role = localStorage.getItem('role') == 'ADMIN' ? Role.ADMIN : Role.ALUMNO;
        this.route.params.subscribe(params => {
            this.idPartido = params['id'];
            this.getPartido();
        });
    }

    getPartido() {
        this.partidoService.getPartido(this.idPartido).subscribe({
            next: (partido) => {
                this.partido = partido;
                this.equipo1FlagPath = this.flagService.getFlagPath(partido.equipo1.id);
                this.equipo2FlagPath = this.flagService.getFlagPath(partido.equipo2.id);
                Swal.close();
            },
            error: (error) => {
                console.log(error);
                this.alertService.showError('Error al cargar el partido');
                this.location.back();
            }
        });
    }

    back() {
        this.location.back();
    }

    editResult() {
        this.dialog.open(GoalEditDialogComponent, {
            width: '250px',
            data: {
                titulo: 'Editar resultado',
                golesEquipo1: this.partido.equipo1.goles,
                golesEquipo2: this.partido.equipo2.goles
            }
        }).afterClosed()
            .subscribe((result: { golesEquipo1: number, golesEquipo2: number }) => {
                if (result) {
                    this.alertService.showLoading();
                    const equipo1: EquipoGoalsApiDto = { id: this.partido.equipo1.id, goles: result.golesEquipo1 };
                    const equipo2: EquipoGoalsApiDto = { id: this.partido.equipo2.id, goles: result.golesEquipo2 };
                    this.partidoService.editResultado(this.idPartido, equipo1, equipo2).subscribe({
                        next: () => {
                            this.getPartido();
                        },
                        error: (error) => {
                            Swal.close();
                            if (error.error && error.error.message) {
                                this.alertService.showError(error.error.message);
                            } else {
                                this.alertService.showError('Error al guardar la predicción');
                            }
                        }
                    });
                }
            });
    }

    getPorcentajeEmpate() {
        return 1 - ((this.partido?.equipo1.porcentaje || 0) + (this.partido?.equipo2.porcentaje || 0));
    }

    canEditPrediccion() {
        let nowPlusOne = new Date();
        nowPlusOne.setHours(nowPlusOne.getHours() + 1);
        let fechaPartido = new Date(this.partido.fecha);
        return nowPlusOne < fechaPartido;
    }

    deletePrediccion() {
        Swal.fire({
            title: '¿Eliminar predicción?',
            showCancelButton: true,
            confirmButtonText: 'Eliminar',
            cancelButtonText: 'Cancelar',
            confirmButtonColor: '#d33',
        }).then((result) => {
            if (result.isConfirmed) {
                this.alertService.showLoading();
                this.prediccionService.deletePrediccion(this.idPartido).subscribe({
                    next: () => {
                        this.alertService.showSuccess('Predicción eliminada');
                        this.getPartido();
                    },
                    error: (error) => {
                        console.log(error);
                        if (error.error.message) {
                            this.alertService.showError(error.error.message);
                        } else {
                            this.alertService.showError('Error al eliminar la predicción');
                        }
                    }
                });
            }
        });
    }

    editPrediccion() {
        this.dialog.open(GoalEditDialogComponent, {
            width: '250px',
            data: {
                titulo: 'Editar predicción',
                golesEquipo1: this.partido.equipo1.prediccion,
                golesEquipo2: this.partido.equipo2.prediccion
            }
        }).afterClosed()
            .subscribe((result: { golesEquipo1: number, golesEquipo2: number }) => {
                if (result) {
                    this.alertService.showLoading();
                    this.prediccionService.editPrediccion(this.idPartido,
                        this.partido.equipo1.id,
                        result.golesEquipo1,
                        this.partido.equipo2.id,
                        result.golesEquipo2).subscribe({
                        next: () => {
                            this.getPartido();
                        },
                        error: (error) => {
                            Swal.close();
                            if (error.error && error.error.message) {
                                this.alertService.showError(error.error.message);
                            } else {
                                this.alertService.showError('Error al guardar la predicción');
                            }
                        }
                    });
                }
            });
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

    protected readonly Role = Role;

}

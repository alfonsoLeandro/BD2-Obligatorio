import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '../../services/alert.service';
import { PartidoService } from '../../services/partido.service';
import Swal from 'sweetalert2';
import { Role } from '../../models/role';
import { MatIcon } from '@angular/material/icon';
import { MatIconButton } from '@angular/material/button';
import { MatToolbar } from '@angular/material/toolbar';
import { DatePipe, Location, PercentPipe } from '@angular/common';
import { PartidoDetalleApiDto } from '../../models/partido-detalle-api-dto';
import { FlagService } from '../../services/flag.service';
import { MatProgressBar } from '@angular/material/progress-bar';

@Component({
  selector: 'app-partido-detalle',
  standalone: true,
    imports: [
        MatIcon,
        MatIconButton,
        MatToolbar,
        DatePipe,
        MatProgressBar,
        PercentPipe
    ],
  templateUrl: './partido-detalle.component.html',
  styleUrl: './partido-detalle.component.scss'
})
export class PartidoDetalleComponent {

    private idPartido: number = 0;
    role: Role = Role.ALUMNO;
    partido?: PartidoDetalleApiDto;
    equipo1FlagPath: string = '';
    equipo2FlagPath: string = '';

    constructor(private route: ActivatedRoute,
                private router: Router,
                private location: Location,
                private alertService: AlertService,
                private partidoService: PartidoService,
                private flagService: FlagService) {
        this.alertService.showLoading();
        this.role = localStorage.getItem('role') == 'ADMIN' ? Role.ADMIN : Role.ALUMNO;
        this.route.params.subscribe(params => {
            this.idPartido = params['id'];
            this.partidoService.getPartido(this.idPartido).subscribe({
                next: (partido) => {
                    console.log(partido);
                    this.partido = partido;
                    this.equipo1FlagPath = this.flagService.getFlagPath(partido.equipo1.id);
                    this.equipo2FlagPath = this.flagService.getFlagPath(partido.equipo2.id);
                    Swal.close();
                },
                error: (error) => {
                    console.log(error);
                    this.alertService.showError('Error al cargar el partido');
                    this.router.navigate(['/home']);
                }
            });
        });
    }

    protected readonly Role = Role;

    back() {
        this.location.back();
    }

    editResult() {

    }

    getPorcentajeEmpate() {
        return 1 - ((this.partido?.equipo1.porcentaje || 0) + (this.partido?.equipo2.porcentaje || 0));
    }
}

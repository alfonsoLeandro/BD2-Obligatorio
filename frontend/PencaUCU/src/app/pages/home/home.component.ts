import { Component } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { MatToolbar } from '@angular/material/toolbar';
import { MatIconButton } from '@angular/material/button';
import { MatFormField } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { PartidoCardComponent } from '../../components/partido-card/partido-card.component';
import { PartidoService } from '../../services/partido.service';
import { PartidoApiDto } from '../../models/partido-api-dto';
import Swal from 'sweetalert2';
import { AlertService } from '../../services/alert.service';

@Component({
  selector: 'app-home',
  standalone: true,
    imports: [
        MatIcon,
        MatToolbar,
        MatIconButton,
        MatFormField,
        MatInput,
        PartidoCardComponent
    ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

    partidos: PartidoApiDto[] =[];

    constructor(private partidoService: PartidoService,
                private alertService: AlertService) {
        this.getPartidos();
    }

    getPartidos() {
        this.alertService.showLoading();
        this.partidoService.getPartidos().subscribe({
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

}

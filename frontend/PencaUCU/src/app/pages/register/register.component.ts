import { Component, inject, OnInit } from '@angular/core';
import {
    AbstractControl,
    FormControl,
    FormGroup,
    FormsModule,
    ReactiveFormsModule,
    ValidationErrors,
    ValidatorFn,
    Validators
} from '@angular/forms';
import { MatError, MatFormField, MatLabel } from '@angular/material/form-field';
import { MatInput, MatInputModule } from '@angular/material/input';
import { NgIf } from '@angular/common';
import { MatButtonModule, MatIconButton } from '@angular/material/button';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatOption, MatSelect } from '@angular/material/select';
import { CarreraService } from '../../services/carrera.service';
import { EquipoService } from '../../services/equipo.service';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthApiDto } from '../../models/auth-api-dto';
import { AuthService } from '../../services/auth.service';
import { AlertService } from '../../services/alert.service';
import { CarreraApiDto } from '../../models/carrera-api-dto';
import { EquipoApiDto } from '../../models/equipo-api-dto';

@Component({
    selector: 'app-register',
    standalone: true,
    imports: [
        HttpClientModule,
        FormsModule,
        MatError,
        MatFormField,
        MatInput,
        MatLabel,
        NgIf,
        ReactiveFormsModule,
        MatIconButton,
        MatIcon,
        MatIconModule,
        MatButtonModule,
        MatInputModule,
        MatSelect,
        MatOption
    ],
    templateUrl: './register.component.html',
    styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit {

    router = inject(Router);
    hidePassword = true;
    carreras: CarreraApiDto[] = [];
    equipos: EquipoApiDto[] = [];

    passwordMatchValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
        const password = control.get('password');
        const confirmPassword = control.get('confirmPassword');

        return password && confirmPassword && password.value !== confirmPassword.value ? { 'passwordMatch': true } : null;
    };

    registerFormControl: FormGroup = new FormGroup({
        name: new FormControl<string>('', [Validators.required]),
        surname: new FormControl<string>('', [Validators.required]),
        ci: new FormControl<string>('', [Validators.required]),
        email: new FormControl<string>('', [Validators.required, Validators.email]),
        password: new FormControl<string>('', [Validators.required, Validators.minLength(8)]),
        confirmPassword: new FormControl<string>('', [Validators.required]),
        telefono: new FormControl<string>('', [Validators.required]),
        carrera: new FormControl<number | null>(null, [Validators.required]),
        campeon: new FormControl<number | null>(null, [Validators.required]),
        subcampeon: new FormControl<number | null>(null, [Validators.required]),
    }, { validators: this.passwordMatchValidator });


    constructor(
        private carreraService: CarreraService,
        private equipoService: EquipoService,
        private authService: AuthService,
        private alertService: AlertService
    ) {
    }

    ngOnInit(): void {
        this.carreraService.getCarreras().subscribe((carreras) => {
            this.carreras = carreras;
        });

        this.equipoService.getEquipos().subscribe((equipos) => {
            this.equipos = equipos;
        });

    }

    register() {
        this.authService.register(
            this.registerFormControl.get('name')!.value,
            this.registerFormControl.get('surname')!.value,
            this.registerFormControl.get('ci')!.value,
            this.registerFormControl.get('email')!.value,
            this.registerFormControl.get('password')!.value,
            this.registerFormControl.get('telefono')!.value,
            this.registerFormControl.get('carrera')!.value,
            this.registerFormControl.get('campeon')!.value,
            this.registerFormControl.get('subcampeon')!.value
        ).subscribe({
            next: (authData: AuthApiDto) => {
                console.log(authData);
                localStorage.setItem('token', authData.token);
                localStorage.setItem('rol', authData.rol.toString());
                localStorage.setItem('id', authData.id.toString());
                this.router.navigate(['/home']);
            },
            error: (error) => {
                if(error.error.message) {
                    this.alertService.showError(error.error.message);
                }
                console.log(error);
            }
        });
    }

    back() {
        this.router.navigate(['/login']);
    }

}

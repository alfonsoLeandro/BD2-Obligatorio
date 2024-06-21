import { Component, inject, ViewEncapsulation } from '@angular/core';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButton, MatIconButton } from '@angular/material/button';
import { MatInput } from '@angular/material/input';
import { MatIcon } from '@angular/material/icon';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { AuthApiDto } from '../../models/auth-api-dto';
import { NgIf, NgOptimizedImage } from '@angular/common';
import { MatCheckbox } from '@angular/material/checkbox';
import { Router } from '@angular/router';


@Component({
    selector: 'app-login',
    standalone: true,
    imports: [
        MatFormField,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatButton,
        MatInput,
        MatIcon,
        HttpClientModule,
        NgIf,
        NgOptimizedImage,
        MatCheckbox,
        MatIconButton
    ],
    templateUrl: './login.component.html',
    styleUrl: './login.component.scss',
    encapsulation: ViewEncapsulation.None,
})
export class LoginComponent {

    rememberPassword: boolean = false;
    router = inject(Router);
    hidePassword = true;

    loginFormControl: FormGroup = new FormGroup({
        email: new FormControl<string>('', [Validators.required, Validators.email]),
        password: new FormControl<string>('', [Validators.required, Validators.minLength(8)]),
        remember: new FormControl<boolean>(false)
    });

    constructor(private authService: AuthService) {
    }

    login() {
        this.authService.login(this.loginFormControl.get<string>('email')!.value,
            this.loginFormControl.get<string>('password')!.value)
            .subscribe({
                next: (authData: AuthApiDto) => {
                    console.log(authData);
                    localStorage.setItem('token', authData.token);
                    localStorage.setItem('rol', authData.rol.toString());
                    localStorage.setItem('id', authData.id.toString());
                    this.router.navigate(['/home']);
                },
                error: (error) => {
                    console.log(error);
                }
            });
    }

    register() {
        this.router.navigate(['/register']);
    }

}

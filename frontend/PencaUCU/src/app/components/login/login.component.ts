import {Component, inject, OnInit, ViewEncapsulation} from '@angular/core';
import {MatFormField, MatFormFieldModule} from "@angular/material/form-field";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {MatIcon} from "@angular/material/icon";
import { HttpClientModule } from '@angular/common/http';
import {AuthService} from "../../services/auth.service";
import {AuthApiDto} from "../../models/auth-api-dto";
import {Observable} from "rxjs";
import {NgIf, NgOptimizedImage} from "@angular/common";
import {MatCheckbox} from "@angular/material/checkbox";
import {Router} from "@angular/router";


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
export class LoginComponent implements OnInit {

  rememberPassword: boolean = false;
  router = inject(Router);
  hidePassword = true;
  hideConfirmPassword = true;

  loginFormControl: FormGroup = new FormGroup({
    email: new FormControl<string>('', [Validators.required, Validators.email]),
    password: new FormControl<string>('', [Validators.required, Validators.minLength(8)]),
    remember: new FormControl<boolean>(false)
  });

  constructor(
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadSavedPassword();
  }

  login() {
    alert(this.loginFormControl.get('email')?.value);

    this.savePasswordLocally();

    (this.authService.login(this.loginFormControl.get<string>('email')!.value,
      this.loginFormControl.get<string>('password')!.value) as Observable<AuthApiDto>)
    .subscribe({
      next: (authData: AuthApiDto) => {
        console.log(authData);
        localStorage.setItem('token', authData.token);
        // save token
        // save rol
        // mostrar mensaje de exito
        // redirigir a '/'
      },
      error: (error) => {
        alert(error.message);
      }
    })
  }

  register(){
    this.router.navigate(['/register']);
  }

  savePasswordLocally() {
    if (this.rememberPassword) {
      localStorage.setItem('savedPassword', this.loginFormControl.get('password')!.value);
    }
  }

  loadSavedPassword() {
    const savedPassword = localStorage.getItem('savedPassword');
    if (savedPassword) {
      this.loginFormControl.patchValue({ password: savedPassword });
    }
  }

}

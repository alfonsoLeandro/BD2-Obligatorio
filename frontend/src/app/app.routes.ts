import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { authGuard } from './guards/auth.guard';
import { PartidoDetalleComponent } from './pages/partido-detalle/partido-detalle.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';
import { UsuarioDetalleComponent } from './pages/usuario-detalle/usuario-detalle.component';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'register',
        component: RegisterComponent
    },
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [authGuard]
    },
    {
        path: 'partido/:id',
        component: PartidoDetalleComponent,
        canActivate: [authGuard]
    },
    {
        path: 'usuarios',
        component: UsuariosComponent,
        canActivate: [authGuard]
    },
    {
        path: 'usuario/:id',
        component: UsuarioDetalleComponent,
        canActivate: [authGuard]
    },
    {
        path: '**',
        redirectTo: 'home'
    }
];

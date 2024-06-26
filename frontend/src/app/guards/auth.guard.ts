import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { PartidoService } from '../services/partido.service';

export const authGuard: CanActivateFn = async (route, state) => {
    const token = localStorage.getItem('token');
    const router = inject(Router);
    const partidoService = inject(PartidoService);

    if (token) {
        try {
            // Check if a secure endpoint is accessible using the current token.
            await firstValueFrom(partidoService.getPartidos());
            return true;
        } catch (error) {
            await router.navigate(['/login']);
            return false;
        }
    } else {
        await router.navigate(['/login']);
        return false;
    }
};

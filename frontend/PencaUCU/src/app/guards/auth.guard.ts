import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import { AuthService } from '../services/auth.service';
import { firstValueFrom } from 'rxjs';

export const authGuard: CanActivateFn = async (route, state) => {
  const token = localStorage.getItem('token');
  const router = inject(Router);
  const authService = inject(AuthService);

  if(token) {
    try {
      const isValid = await firstValueFrom(authService.validateToken(token));
      if (isValid) {
        return true;
      } else {
        await router.navigate(['/login']);
        return false;
      }
    } catch (error) {
      await router.navigate(['/login']);
      return false;
    }
  } else {
    await router.navigate(['/login']);
    return false;
  }
};

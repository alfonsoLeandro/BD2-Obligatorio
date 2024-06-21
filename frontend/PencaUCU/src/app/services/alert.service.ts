import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
    providedIn: 'root'
})
export class AlertService {

    showLoading() {
        return Swal.fire({
            title: 'Cargando...',
            didOpen() {
                Swal.showLoading();
            },
            allowOutsideClick: false
        });
    }

    showError(message: string) {
        return Swal.fire({
            icon: 'error',
            title: 'Error',
            text: message
        });
    }



}

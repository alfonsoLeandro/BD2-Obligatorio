import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
    providedIn: 'root'
})
export class FlagService {

    getFlagPath(id: number) {
        switch (id) {
            case 1:
                return 'assets/images/flags/argentina.png';
            case 2:
                return 'assets/images/flags/chile.png';
            case 3:
                return 'assets/images/flags/peru.png';
            case 4:
                return 'assets/images/flags/canada.png';
            case 5:
                return 'assets/images/flags/mexico.png';
            case 6:
                return 'assets/images/flags/ecuador.png';
            case 7:
                return 'assets/images/flags/venezuela.png';
            case 8:
                return 'assets/images/flags/jamaica.png';
            case 9:
                return 'assets/images/flags/estados-unidos.png';
            case 10:
                return 'assets/images/flags/uruguay.png';
            case 11:
                return 'assets/images/flags/bolivia.png';
            case 12:
                return 'assets/images/flags/panama.png';
            case 13:
                return 'assets/images/flags/brasil.png';
            case 14:
                return 'assets/images/flags/colombia.png';
            case 15:
                return 'assets/images/flags/paraguay.png';
            case 16:
                return 'assets/images/flags/costa-rica.png';
            default:
                return 'assets/images/flags/uruguay.png';
        }
    }

}

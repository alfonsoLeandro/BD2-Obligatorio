import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class EquipoService {

    private readonly httpHeaders: HttpHeaders = new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': '*'
    });
    private readonly urlBase = 'http://localhost:8080/api/v1';

    constructor(private httpClient: HttpClient) {
    }

    getEquipos(): Observable<any[]> {
        return this.httpClient.get<any[]>(`${this.urlBase}/public/equipos`, { headers: this.httpHeaders });
    }

}

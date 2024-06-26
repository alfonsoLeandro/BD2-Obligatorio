import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CarreraApiDto } from '../models/carrera-api-dto';

@Injectable({
    providedIn: 'root'
})
export class CarreraService {

    private readonly httpHeaders: HttpHeaders = new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': '*'
    });
    private readonly urlBase = 'http://localhost:8080/api/v1';

    constructor(private httpClient: HttpClient) {
    }

    getCarreras(): Observable<CarreraApiDto[]> {
        return this.httpClient.get<any[]>(`${this.urlBase}/public/carreras`, { headers: this.httpHeaders });
    }

}

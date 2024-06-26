import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class PrediccionService {

    private readonly httpHeaders: HttpHeaders = new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': '*'
    });
    private readonly urlBase = 'http://localhost:8080/api/v1';

    constructor(private httpClient: HttpClient) {
    }

    deletePrediccion(id: number) {
        return this.httpClient.delete<void>(`${this.urlBase}/predicciones/partido/${id}`, {
            headers: this.httpHeaders.append('Authorization', 'Bearer ' + localStorage.getItem('token'))
        });
    }

    editPrediccion(idPartido: number, idEquipo1: number, golesEquipo1: number, idEquipo2: number, golesEquipo2: number) {
        return this.httpClient.put<void>(`${this.urlBase}/predicciones/partido/${idPartido}`, {
            equipo1: {
                id: idEquipo1,
                prediccion: golesEquipo1
            },
            equipo2: {
                id: idEquipo2,
                prediccion: golesEquipo2
            }
        }, {
            headers: this.httpHeaders.append('Authorization', 'Bearer ' + localStorage.getItem('token'))
        });
    }
}

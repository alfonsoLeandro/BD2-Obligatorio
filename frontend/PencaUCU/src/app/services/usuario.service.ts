import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AlumnoApiDto } from '../models/alumno-api-dto';

@Injectable({
    providedIn: 'root'
})
export class UsuarioService {

    private readonly httpHeaders: HttpHeaders = new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': '*'
    });
    private readonly urlBase = 'http://localhost:8080/api/v1';

    constructor(private httpClient: HttpClient) {
    }

    getUsuarios(searchText?: string, idCarrera?: number, isAdmin?: boolean) {
        let params: HttpParams = new HttpParams();
        if (searchText) {
            params = params.set('busqueda', searchText);
        }
        if (idCarrera) {
            params = params.set('idCarrera', idCarrera.toString());
        }
        if (isAdmin) {
            params = params.set('esAdmin', isAdmin.toString());
        }
        return this.httpClient.get<AlumnoApiDto[]>(`${this.urlBase}/usuarios`,
            {
                headers: this.httpHeaders.append('Authorization',
                    'Bearer ' + localStorage.getItem('token')),
                params: params
            });
    }

}

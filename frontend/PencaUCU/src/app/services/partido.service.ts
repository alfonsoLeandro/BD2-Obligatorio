import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { PartidoApiDto } from '../models/partido-api-dto';

@Injectable({
    providedIn: 'root'
})
export class PartidoService {

    private readonly httpHeaders: HttpHeaders = new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': '*'
    });
    private readonly urlBase = 'http://localhost:8080/api/v1/partidos';

    constructor(private httpClient: HttpClient) {
    }

    getPartidos(searchText?: string, jugado?: boolean, conPrediccion?: boolean) {
        let params: HttpParams = new HttpParams();
        if (searchText) {
            params = params.set('busqueda', searchText);
        }
        if (jugado) {
            params = params.set('jugado', jugado.toString());
        }
        if (conPrediccion) {
            params = params.set('conPrediccion', conPrediccion.toString());
        }
        return this.httpClient.get<PartidoApiDto[]>(this.urlBase,
            {
                headers: this.httpHeaders.append('Authorization',
                    'Bearer ' + localStorage.getItem('token')),
                params: params
            });
    }

}

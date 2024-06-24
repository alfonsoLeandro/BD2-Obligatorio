import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { PartidoApiDto } from '../models/partido-api-dto';
import { PartidoFechaApiDto } from '../models/partido-fecha-api-dto';
import { PartidoDetalleApiDto } from '../models/partido-detalle-api-dto';
import { EquipoGoalsApiDto } from '../models/equipo-goals-api-dto';

@Injectable({
    providedIn: 'root'
})
export class PartidoService {

    private readonly httpHeaders: HttpHeaders = new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': '*'
    });
    private readonly urlBase = 'http://localhost:8080/api/v1';

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
        return this.httpClient.get<PartidoApiDto[]>(`${this.urlBase}/partidos`,
            {
                headers: this.httpHeaders.append('Authorization',
                    'Bearer ' + localStorage.getItem('token')),
                params: params
            });
    }

    getAvailableFechas() {
        return this.httpClient.get<PartidoFechaApiDto[]>(`${this.urlBase}/admin/partidos/fechas`,
            {
                headers: this.httpHeaders.append('Authorization',
                    'Bearer ' + localStorage.getItem('token'))
            });
    }

    setPartidoEquipos(idPartido: number, idEquipo1: number, idEquipo2: number) {
        let params: HttpParams = new HttpParams();
        params = params.set('idEquipo1', idEquipo1);
        params = params.set('idEquipo2', idEquipo2);
        return this.httpClient.post<void>(`${this.urlBase}/admin/partidos/${idPartido}`, null,
            {
                headers: this.httpHeaders.append('Authorization',
                    'Bearer ' + localStorage.getItem('token')),
                params: params
            });
    }

    getPartido(id: number) {
        return this.httpClient.get<PartidoDetalleApiDto>(`${this.urlBase}/partidos/${id}`, {
            headers: this.httpHeaders.append('Authorization', 'Bearer ' + localStorage.getItem('token'))
        });
    }

    editResultado(idPartido: number, equipo1: EquipoGoalsApiDto, equipo2: EquipoGoalsApiDto) {
        return this.httpClient.put<void>(`${this.urlBase}/admin/partidos/${idPartido}/resultado`, {
            equipo1,
            equipo2
        }, {
            headers: this.httpHeaders.append('Authorization', 'Bearer ' + localStorage.getItem('token'))
        });
    }
}

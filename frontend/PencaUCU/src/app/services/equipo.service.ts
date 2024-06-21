import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
//import {EquipoApiDto} from "../models/equipo-api-dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EquipoService {

  private readonly urlBase = 'http://localhost:8080/api/v1';

  constructor(private httpClient: HttpClient) {
  }

  getEquipos(): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.urlBase}/equipos`);
  }

}

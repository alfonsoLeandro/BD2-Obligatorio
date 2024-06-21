import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CarreraService {

  private readonly urlBase = 'http://localhost:8080/api/v1';

  constructor(private httpClient: HttpClient) {
  }

  getCarreras(): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.urlBase}/carreras`);
  }

}

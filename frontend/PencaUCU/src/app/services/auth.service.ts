import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthApiDto} from "../models/auth-api-dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly urlBase = 'http://localhost:8080/api/v1';

  constructor(private httpClient: HttpClient) {
  }

  login(email: string, password: string) {
    return this.httpClient.post<AuthApiDto>(this.urlBase + '/auth/login', {
      email: email,
      password: password,
    });
  }

  register(nombre: string, apellido: string, ci: string, email: string, password: string, telefono: string, idCarrera: number, idCampeon: number, idSubcampeon: number): Observable<AuthApiDto> {
    return this.httpClient.post<AuthApiDto>(this.urlBase + '/auth/register', {
      nombre: nombre,
      apellido: apellido,
      ci: ci,
      email: email,
      password: password,
      telefono: telefono,
      idCarrera: idCarrera,
      idCampeon: idCampeon,
      idSubcampeon: idSubcampeon
    });
  }

  validateToken(token: string): Observable<boolean> {
    return this.httpClient.post<boolean>(`${this.urlBase}/auth/validate-token`, {
      token: token,
    });
  }

}

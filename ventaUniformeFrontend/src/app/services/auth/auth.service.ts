import { HttpClient } from '@angular/common/http';
import { ErrorHandler, Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, tap } from 'rxjs';
import { environment } from 'src/app/environments/environment';
import { jwtDecode } from 'jwt-decode';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private httpClient:HttpClient,
  ) {}

  iniciarSession(persona:any){
    return this.httpClient.post(`${environment.url}auth/login`, persona);
  }
  registrar(persona:any){
    return this.httpClient.post(`${environment.url}auth/register`, persona);
  }

  getDecodedToken(token: string): any {
    try {
      return jwtDecode(token);
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }

  // Método para obtener el correo electrónico del token
  setEmailFromToken() {
    let token=sessionStorage.getItem('token');
    let decodedToken = this.getDecodedToken(String(token));
    console.log(decodedToken);
    if (decodedToken) {
      let correo=decodedToken.sub;
      sessionStorage.setItem('correoPersona',correo);
      console.log(correo);
    }
    return null;
  }

}

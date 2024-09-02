import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  token:string=String(sessionStorage.getItem('token'));

  constructor(
    private httpClient:HttpClient
  ) { 

  }

  reservar(reserva:any){
    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.httpClient.post(`${environment.urlApi}reserva/insert`, reserva,{headers});
  }
  findAll(){
    return this.httpClient.get<any[]>(`${environment.urlApi}reserva/findAll`)
  }

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VestimentaService {

  constructor(
    private httpClient:HttpClient
  ) { }

  findTheMostRecents(){
    return this.httpClient.get(`${environment.urlApi}vestimenta/findTheMostRecents`);
  }
  findById(id:string){
    return this.httpClient.get(`${environment.urlApi}vestimenta/findById?id=${id}`);
  }findBySearch(busqueda:string){
    return this.httpClient.get(`${environment.urlApi}vestimenta/findBySearch?search=${busqueda}`)
  }findByCategoria(categoria:string){
    return this.httpClient.get(`${environment.urlApi}vestimenta/findByCategoria?categoria=${categoria}`)
  }
}

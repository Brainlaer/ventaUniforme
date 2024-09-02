import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {


  constructor(
    private httpCliente:HttpClient,
  ) {}

  findByCorreo(correo:String){
    return this.httpCliente.get(`${environment.urlApi}persona/findByCorreo?correo=${correo}`)
  }





}

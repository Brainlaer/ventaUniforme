import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonaService } from 'src/app/services/persona.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit{

  persona:any;

  constructor(
    private personaService:PersonaService,
    private router:Router
  ){}

  ngOnInit(): void {
    this.findByCorreo();
    
  }

  irAdmin(){
    this.router.navigateByUrl('/reservas')
  }

  findByCorreo(){
    this.personaService.findByCorreo(String(sessionStorage.getItem('correoPersona'))).subscribe(
      (persona:any)=>{
        this.persona=persona;
        console.log(persona);
      },(error)=>{
        console.log(error);
      }
    )
  }

}

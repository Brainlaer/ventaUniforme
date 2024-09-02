import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { PersonaService } from 'src/app/services/persona.service';

@Component({
  selector: 'app-inicio-session',
  templateUrl: './inicio-session.component.html',
  styleUrls: ['./inicio-session.component.css']
})
export class InicioSessionComponent{

  respuesta:any;

  constructor(public router:Router,
    private authService:AuthService,
  ){}

  personaForm=new FormGroup({
    correo:new FormControl('',[Validators.required, Validators.email]),
    password:new FormControl('',[Validators.required, Validators.minLength(8)])
  });

  iniciarSession(){
    this.authService.iniciarSession(this.personaForm.value).subscribe(
      async (token:any)=>{
        sessionStorage.setItem('isLoggin','true');
        sessionStorage.setItem('token', token.token);
        if(sessionStorage.getItem('isLoggin')){
          await this.authService.setEmailFromToken();
          window.location.replace('/inicio');
        }
      },(error)=>{
        this.respuesta="Error no se pudo iniciar sessión";
      }
    )
  }
  
  irRegistro(){
    this.router.navigateByUrl('/registro');
  }
  

}

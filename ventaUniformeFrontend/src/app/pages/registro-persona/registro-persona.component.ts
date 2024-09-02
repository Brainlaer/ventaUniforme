import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IRespuesta } from 'src/app/interfaces/i-respuesta';
import { AuthService } from 'src/app/services/auth/auth.service';
import { PersonaService } from 'src/app/services/persona.service';

@Component({
  selector: 'app-registro-persona',
  templateUrl: './registro-persona.component.html',
  styleUrls: ['./registro-persona.component.css']
})
export class RegistroPersonaComponent implements OnInit{

  respuesta:IRespuesta={
    message:'',
    state:''
  };

  constructor(
    public authService:AuthService,
    private router:Router
  ){}

  personaForm=new FormGroup({
    id:new FormControl('',Validators.required),
    nombre:new FormControl('',Validators.required),
    apellido:new FormControl('',Validators.required),
    celular:new FormControl(''),
    correo:new FormControl('',[Validators.required, Validators.email]),
    password:new FormControl('',[Validators.required, Validators.minLength(8)])
  });

  ngOnInit(): void {
  }

  irRegistro(){
    this.router.navigateByUrl('/inicio-session');
  }

  registrar(){
      this.authService.registrar(this.personaForm.value).subscribe(
        async (token:any)=>{

          this.respuesta.message="Cuenta creada correctamente";
          this.respuesta.state="success";
          this.router.navigateByUrl('/inicio-session')
        },(error)=>{
          this.respuesta.message="Error no se pudo crear la cuenta";
          this.respuesta.state="danger";
        }
      )
  }

}

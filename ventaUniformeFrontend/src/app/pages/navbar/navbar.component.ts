import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { IMenuItem } from 'src/app/interfaces/i-menu-item.interface';
import { PersonaService } from 'src/app/services/persona.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent{
  
  @Input() isLoggin:any;

  constructor(
    private router:Router,
    private personaService:PersonaService
  ){}

  busquedaForm=new FormGroup({
    busqueda: new FormControl<string>('', Validators.required)
  })

  irInicio(){
    this.router.navigateByUrl(`/inicio`);
  }
  irSobreNosotros(){
    this.router.navigateByUrl('/sobre-nosotros');
  }
  irPerfil(){
    this.router.navigateByUrl('/perfil');
  }
  irInicioSession(){
    this.router.navigateByUrl('/inicio-session');
  }
  irRegistro(){
    this.router.navigateByUrl('/registro');
  }

  cerrarSession(){
    this.isLoggin=sessionStorage.removeItem('isLoggin');
    if(!this.isLoggin){
      this.isLoggin=false;
    }

    this.router.navigateByUrl('/inicio');
    
  }
  buscar(){
    if(this.busquedaForm.value.busqueda){
      this.router.navigate(['/resultado-busqueda'],{queryParams:{busqueda:this.busquedaForm.value.busqueda}})
    }
  }



}

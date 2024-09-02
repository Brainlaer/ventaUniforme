import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonaService } from './services/persona.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Club Deportivo Harold Lozano';
  isLoggin:any;

  
  constructor(
    public router:Router,
    private personaService:PersonaService
  ){}

  ngOnInit(): void {
    this.isLoggin=sessionStorage.getItem('isLoggin')||false;
  }
  goInicio(){
    this.router.navigateByUrl(`/inicio`);
  }

}

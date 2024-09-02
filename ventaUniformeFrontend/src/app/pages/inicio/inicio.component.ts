import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IVestimentaResultado } from 'src/app/models/vestimenta-preview';
import { AuthService } from 'src/app/services/auth/auth.service';
import { VestimentaService } from 'src/app/services/vestimenta/vestimenta.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit{

  vestimentaList!:IVestimentaResultado[];

  constructor(
    private router:Router,
    private vestimentaService:VestimentaService,
    private authService:AuthService
  ){}

  ngOnInit(): void {
    this.findTheMostRecents();
    console.log(sessionStorage.getItem('correoPersona'));
      }

  findTheMostRecents(){
    this.vestimentaService.findTheMostRecents().subscribe(
      (vestimentas:any)=>{
        this.vestimentaList=vestimentas;
      },(error)=>{
        console.log(error);
      }
    )
  }
  searchByCamisa(){
    this.router.navigate(['/resultado-busqueda'],{queryParams:{vestimenta:'camisas'}})
  }
  searchByPantaloneta(){
    this.router.navigate(['/resultado-busqueda'],{queryParams:{vestimenta:'pantalonetas'}})
  }
  searchByzapatilla(){
    this.router.navigate(['/resultado-busqueda'],{queryParams:{vestimenta:'zapatillas'}})
  }
  verVestimenta(id:string){
    this.router.navigate(['/vista-vestimenta'],{queryParams:{id:id}})
  }

  




}
